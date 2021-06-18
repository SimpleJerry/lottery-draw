import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/home.vue";
import About from "../views/about.vue";
import Test from "../views/test.vue";
import Award from "../views/admin/admin-award.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/test",
    name: "Test",
    component: Test,
  },
  {
    path: "/award",
    name: "AdminAward",
    component: Award,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
