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
            <a-button type="primary" @click="add()"> 新增 </a-button>
          </a-form-item>
        </a-form>
      </p>
      <!-- 数据展示区 -->
      <a-table bordered :data-source="dataSource" :columns="columns">
        <!-- 主要数据 -->
        <template #jobId="{ text, record }">
          <div class="editable-cell">
            <!-- 如果正处于可编辑单元格 -->
            <div
              v-if="editableData[record.jobId]"
              class="editable-cell-input-wrapper"
            >
              <!-- 输入框（按下Enter时触发事件） -->
              <a-input
                v-model:value="editableData[record.jobId].jobId"
                @pressEnter="save(record.jobId)"
              />
              <!-- 对勾 -->
              <check-outlined
                class="editable-cell-icon-check"
                @click="save(record.jobId)"
              />
            </div>
            <!-- 如果并非正处于可编辑单元格 -->
            <div v-else class="editable-cell-text-wrapper">
              {{ text || " " }}
              <edit-outlined
                class="editable-cell-icon"
                @click="edit(record.jobId)"
              />
            </div>
          </div>
        </template>
        <!-- 操作 -->
        <template #operation="{ record }">
          <a-popconfirm
            v-if="dataSource.length"
            title="Sure to delete?"
            @confirm="onDelete(record.jobId)"
          >
            <a>Delete</a>
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
import * as JobApi from "@/api/job";
import moment from "moment";
moment.locale("zh-cn");
export default defineComponent({
  name: "AdminJob",
  components: {
    CheckOutlined,
    EditOutlined,
  },
  data() {
    return {
      groupId: "G_0001",
      param: {
        groupId: "G_0001",
      },
      // 数据区域
      columns: [
        {
          title: "抽奖Id",
          dataIndex: "jobId",
          slots: {
            customRender: "jobId",
          },
        },
        {
          title: "创建时间",
          dataIndex: "time",
          customRender: (text, record, index) => {
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
    JobApi.list(this.param).then((response) => {
      this.dataSource = response.data.content;
    });
  },
  methods: {
    // 编辑
    edit(jobId) {
      this.editableData[jobId] = cloneDeep(
        this.dataSource.filter((item) => jobId === item.jobId)[0]
      );
    },
    save(jobId) {
      Object.assign(
        this.dataSource.filter((item) => jobId === item.jobId)[0],
        this.editableData[jobId]
      );
      delete this.editableData[jobId];
    },
    onDelete(jobId) {
      this.dataSource.value = this.dataSource.filter(
        (item) => item.jobId !== jobId
      );
    },
    add() {
      const newData = {};
      this.dataSource.value.push(newData);
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
