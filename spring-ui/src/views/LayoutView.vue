<template>
  <el-container>
    <el-menu
        :collapse="isCollapse"
        :default-active="current_menu_item"
        @select="handleSelect"
        :collapse-transition="false"
    >
      <h4 style="line-height: 60px;text-align: center;color: #606266;">
        {{ isCollapse ? '选课' : '大学选课系统' }}
      </h4>
      <el-menu-item index="0">
        <el-icon>
          <House/>
        </el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item v-for="item in noChildren" :index="item.keypath">
        <component :is="item.icon"></component>
        <span>{{ item.name }}</span>
      </el-menu-item>
      <el-sub-menu v-for="item in hasChildren" :index="item.keypath">
        <template #title>
          <component :is="item.icon"></component>
          <span>{{ item.name }}</span>
        </template>
        <el-menu-item-group :title="item.name">
          <el-menu-item v-for="i in item.children" :index="i.keypath">
            <component :is="i.icon"></component>
            {{ i.name }}
          </el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>
    </el-menu>
    <el-container>
      <el-header>
        <el-row justify="space-between" align="middle" style="height: 100%">
          <el-button text bg size="large" @click="handleCollapse">
            <el-icon v-if="isCollapse" style="transform: scale(3);margin-left: 15px">
              <Expand/>
            </el-icon>
            <el-icon v-else style="transform: scale(3);margin-left: 15px">
              <Fold/>
            </el-icon>
          </el-button>
          <el-dropdown style="cursor: pointer;">
            <span class="block">
              <el-row>
                <img style="width: 40px;height: 40px;margin-right: 5px;" v-if="user.gender==='女'"
                     src="@/assets/avatar-female.png">
                <img style="width: 40px;height: 40px;margin-right: 5px;" v-if="user.gender==='男'"
                     src="@/assets/avatar-male.png">
                <!--                <el-avatar shape="square" :size="40" style="transform: translateX(-10px)"-->
                <!--                           src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"/>-->
                <div style="display: flex;flex-direction: column;justify-content: space-around">
                  <div>{{ user.name }}</div>
                  <div style="color: #0753a2">{{ role.name }}</div>
                </div>
              </el-row>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="goToMyself">我的信息</el-dropdown-item>
                <el-dropdown-item @click="goToMyself">修改信息</el-dropdown-item>
                <el-dropdown-item divided @click="loginOut">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-row>
      </el-header>
      <el-main>
        <component :is="current_comp"></component>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import {HomeFilled, House, Expand, Fold} from '@element-plus/icons-vue'
import {computed, onMounted, reactive, ref, shallowRef} from "vue";
import Home from "@/views/pages/Home.vue";
import Choose from "@/views/pages/Choose.vue";
import Course from "@/views/pages/Course.vue";
import Menu from "@/views/pages/Menu.vue";
import MyCourse from "@/views/pages/MyCourse.vue";
import Myself from "@/views/pages/Myself.vue";
import Role from "@/views/pages/Role.vue";
import Table from "@/views/pages/Table.vue";
import Time from "@/views/pages/Time.vue";
import Type from "@/views/pages/Type.vue";
import User from "@/views/pages/User.vue";
import Academic from "@/views/pages/Academic.vue";
import Profess from "@/views/pages/Profess.vue";
import Team from "@/views/pages/Team.vue";
import Grade from "@/views/pages/Grade.vue";
import Schedule from "@/views/pages/Schedule.vue";
import Classroom from "@/views/pages/Classroom.vue";

import router from "@/router";

const subComponents = reactive({
  '0': shallowRef(Home),
  '8': shallowRef(Choose),
  '5': shallowRef(Course),
  '3': shallowRef(Menu),
  '9': shallowRef(MyCourse),
  '10': shallowRef(Myself),
  '2': shallowRef(Role),
  '6': shallowRef(Table),
  '7': shallowRef(Time),
  '4': shallowRef(Type),
  '1': shallowRef(User),
  '11': shallowRef(Classroom),
  '12': shallowRef(Team),
  '13': shallowRef(Profess),
  '14': shallowRef(Academic),
  '15': shallowRef(Grade),
  '16': shallowRef(Schedule),
});
const current_menu_item = ref('0');
const current_comp = shallowRef(null);

const user = reactive(JSON.parse(localStorage.getItem("user") || '{}'));
const role = reactive(JSON.parse(localStorage.getItem("role") || '{}'));
const menu = ref(JSON.parse(localStorage.getItem("menu") || '[]'));
const token = JSON.parse(localStorage.getItem("token") || '""');

onMounted(() => {
  // 获取上次保存的页面路径，如果没有则默认为首页
  const savedKeypath = localStorage.getItem("keypath") || '0';
  current_menu_item.value = savedKeypath;
  current_comp.value = subComponents[savedKeypath];
  console.log('当前菜单:', menu.value);
  console.log('当前组件:', current_comp.value);
})

computed(() => {

})
const isCollapse = ref(false)
const handleSelect = (key, keypath) => {
  current_menu_item.value = key;
  current_comp.value = subComponents[key];
  localStorage.setItem("keypath", key);
}

function handleCollapse() {
  isCollapse.value = !isCollapse.value;
}

const noChildren = computed(() =>
    menu.value.filter((item: any) => !item.children)
);
const hasChildren = computed(() =>
    menu.value.filter((item: any) => item.children)
);

function loginOut() {
  localStorage.clear();
  router.push('/');
}

function goToMyself() {
  // 跳转到个人信息页面
  current_menu_item.value = '10';
  current_comp.value = subComponents['10'];
  localStorage.setItem("keypath", '10');
}
</script>

<style scoped>
.el-menu {
  height: 100vh !important;
  overflow: scroll;
  border-right: 1px solid #ccc;
}

.el-header {
  border-bottom: 1px solid #ccc;
}

:deep(.el-tooltip__trigger:focus-visible) {
  outline: unset;
}

svg {
  width: 18px;
  height: 18px;
  margin-right: 5px;
}
</style>