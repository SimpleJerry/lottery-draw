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
        <a-form layout="inline" :model="listParam">
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
          <a-space size="small">
            <a-button type="primary" @click="doJob(record.jobId)">
              抽奖
            </a-button>
            <a-popconfirm
              v-if="dataSource.length"
              title="是否确认删除?"
              @confirm="onDelete(record.jobId)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
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
      listParam: {
        groupId: "G_0001",
      },
      createParam: {
        groupId: "G_0001",
        awardIds: ["A_0001", "A_0002", "A_0003", "A_0004", "A_0005"],
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
      JobApi.list(this.listParam).then((response) => {
        this.dataSource = response.data.content;
      });
    },
    // 执行抽奖事务
    doJob(jobId) {
      console.log(jobId);
      this.$router.push({ path: "/job/do", query: { jobId: jobId } });
    },
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
      JobApi.deleteJob(jobId).then((response) => {
        this.refresh();
      });
    },
    onCreate() {
      JobApi.create(this.createParam).then((response) => {
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
