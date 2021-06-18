<template>
  <a-layout-content style="padding: 0 50px">
    <!-- 主页面 -->
    <a-layout style="padding: 24px 0; background: #fff">
      <!-- 侧边栏 -->
      <a-layout-sider width="200" style="background: #fff">
        <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
        >
          <a-menu-item key="welcome">
            <MailOutlined />
            <span>欢迎</span>
          </a-menu-item>
          <a-sub-menu key="sub1">
            <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
            </template>
            <a-menu-item key="1">option1</a-menu-item>
            <a-menu-item key="2">option2</a-menu-item>
            <a-menu-item key="3">option3</a-menu-item>
            <a-menu-item key="4">option4</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub2">
            <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
            </template>
            <a-menu-item key="5">option5</a-menu-item>
            <a-menu-item key="6">option6</a-menu-item>
            <a-menu-item key="7">option7</a-menu-item>
            <a-menu-item key="8">option8</a-menu-item>
          </a-sub-menu>
          <a-sub-menu key="sub3">
            <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
            </template>
            <a-menu-item key="9">option9</a-menu-item>
            <a-menu-item key="10">option10</a-menu-item>
            <a-menu-item key="11">option11</a-menu-item>
            <a-menu-item key="12">option12</a-menu-item>
          </a-sub-menu>
        </a-menu>
      </a-layout-sider>
      <!-- 内容栏 -->
      <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
        Content
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout-content>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "home",
  components: {},
  methods: {
    //路由内容切换
    handleClick(route) {
      console.log(route);
      //获取路由对象并切换
      this.$router.push(route);
    },
  },
});
</script>
