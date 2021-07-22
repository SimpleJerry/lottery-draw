<template>
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
      <a-form layout="inline">
        <a-form-item>
          <a-button type="primary" @click="doJob()"> 抽奖 </a-button>
        </a-form-item>
      </a-form>
    </p>
    <a-table
      :columns="columns"
      :data-source="dataSource"
      :pagination="pagination"
    >
      <template #name="{ text }">
        <a>{{ text }}</a>
      </template>
    </a-table>
  </a-layout-content>
</template>
<script>
import { defineComponent } from "vue";
import * as JobApi from "@/api/job";
import moment from "moment";
moment.locale("zh-cn");
export default defineComponent({
  name: "AdminJobDo",
  data() {
    return {
      jobId: this.$route.query.jobId,
      // 分页器配置
      pagination: {
        total: 0,
        pageSize: 10, //每页中显示10条数据
        showSizeChanger: true,
        pageSizeOptions: ["10", "20", "50", "100"], //每页中显示的数据
        showTotal: (total) => `共有 ${total} 条数据`, //分页中显示总的数据
      },
      // 数据区域
      columns: [
        {
          title: "组织Id",
          dataIndex: "groupId",
        },
        {
          title: "组织名称",
          dataIndex: "groupName",
        },
        {
          title: "员工Id",
          dataIndex: "employeeId",
        },
        {
          title: "员工姓名",
          dataIndex: "employeeName",
        },
        {
          title: "奖品Id",
          dataIndex: "awardId",
        },
        {
          title: "奖品名称",
          dataIndex: "awardName",
        },
        {
          title: "抽奖时间",
          dataIndex: "time",
          customRender: (text, record, index) => {
            return moment(text).format("YYYY-MM-DD HH:mm:ss");
          },
        },
      ],
      // 源数据
      dataSource: [],
    };
  },
  mounted() {
    this.refresh();
  },
  methods: {
    refresh() {
      JobApi.queryJobResult(this.jobId).then((response) => {
        this.dataSource = response.data.content.infoList;
      });
    },
    doJob() {
      // 抽奖
      JobApi.doJob(this.jobId).then((response) => {
        console.log("返回结果为：", response.data);
        // 刷新
        this.refresh();
      });
    },
  },
  setup() {
    return {};
  },
});
</script>
