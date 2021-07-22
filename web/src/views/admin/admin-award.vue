<template>
  <a-layout>
    <a-layout-content
      :style="{
        background: '#fff',
        padding: '24px',
        margin: 0,
        minHeight: '280px',
      }"
    >
      <!-- 查询栏 & 新增栏 -->
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input placeholder="奖品名"> </a-input>
          </a-form-item>
          <a-form-item>
            <a-button
              type="primary"
              @click="handleQuery({ page: 1, size: pagination.pageSize })"
            >
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="onCreate()"> 新增 </a-button>
          </a-form-item>
        </a-form>
      </p>
      <!-- 数据展示区 -->
      <a-table bordered :data-source="dataSource" :columns="columns">
        <!-- 主要数据 -->
        <template #awardId="{ text, record }">
          <div class="editable-cell">
            <!-- 如果正处于可编辑单元格 -->
            <div
              v-if="editableData[record.awardId]"
              class="editable-cell-input-wrapper"
            >
              <!-- 输入框（按下Enter时触发事件） -->
              <a-input
                v-model:value="editableData[record.awardId].awardId"
                @pressEnter="save(record.awardId)"
              />
              <!-- 对勾 -->
              <check-outlined
                class="editable-cell-icon-check"
                @click="save(record.awardId)"
              />
            </div>
            <!-- 如果并非正处于可编辑单元格 -->
            <div v-else class="editable-cell-text-wrapper">
              {{ text || " " }}
              <edit-outlined
                class="editable-cell-icon"
                @click="edit(record.awardId)"
              />
            </div>
          </div>
        </template>
        <!-- 操作 -->
        <template #operation="{ record }">
          <a-popconfirm
            v-if="dataSource.length"
            title="是否确定重置?"
            @confirm="onReset(record.awardId)"
          >
            <a-button type="danger">
              重置
            </a-button>
          </a-popconfirm>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
</template>
<script>
import { defineComponent } from "vue";
import { CheckOutlined, EditOutlined } from "@ant-design/icons-vue";
import { cloneDeep } from "lodash-es";
import * as AwardApi from "@/api/award";
import moment from "moment";
moment.locale("zh-cn");
export default defineComponent({
  name: "AdminAward",
  components: {
    CheckOutlined,
    EditOutlined,
  },
  data() {
    return {
      groupId: "G_0001",
      // 查询全部奖品Param
      param: {},
      createParam: {
        groupId: this.groupId,
        awardIds: ["A_0001", "A_0002", "A_0003", "A_0004", "A_0005"],
      },
      // 数据区域
      columns: [
        {
          title: "奖品id",
          dataIndex: "awardId",
          slots: {
            customRender: "awardId",
          },
        },
        {
          title: "奖品名称",
          dataIndex: "awardName",
          slots: {
            customRender: "awardName",
          },
        },
        {
          title: "单次抽取数",
          dataIndex: "onceQuantity",
        },
        {
          title: "奖品总数",
          dataIndex: "totalQuantity",
        },
        {
          title: "奖品剩余数",
          dataIndex: "remainQuantity",
        },
        {
          title: "优先级",
          dataIndex: "priority",
        },
        {
          title: "图片路径",
          dataIndex: "img",
        },
        {
          title: "创建时间",
          dataIndex: "createdAt",
          customRender: (text) => {
            return moment(text).format("YYYY-MM-DD HH:mm:ss");
          },
        },
        {
          title: "更新时间",
          dataIndex: "updatedAt",
          customRender: (text) => {
            return moment(text).format("YYYY-MM-DD HH:mm:ss");
          },
        },
        {
          title: "操作",
          dataIndex: "operation",
          slots: {
            customRender: "operation",
          },
        },
      ],
      // 源数据
      dataSource: [],
      // 可编辑的数据
      editableData: [],
    };
  },
  mounted() {
    this.refresh();
  },
  methods: {
    refresh() {
      AwardApi.list(this.param).then((response) => {
        this.dataSource = response.data.content;
      });
    },
    // 编辑
    edit(awardId) {
      this.editableData[awardId] = cloneDeep(
        this.dataSource.filter((item) => awardId === item.awardId)[0]
      );
    },
    save(awardId) {
      Object.assign(
        this.dataSource.filter((item) => awardId === item.awardId)[0],
        this.editableData[awardId]
      );
      delete this.editableData[awardId];
    },
    onReset(awardId) {
      AwardApi.reset(awardId).then((response) => {
        this.refresh();
      });
    },
    onCreate() {
      AwardApi.create(this.createParam).then((response) => {
        this.refresh();
      });
    },
  },
  setup() {
    return {};
  },
});
</script>
<style lang="less">
.editable-cell {
  position: relative;
  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    margin-top: 4px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
}
.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>
