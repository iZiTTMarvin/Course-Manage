<template>
  <RouterView/>
</template>

<script setup lang="ts">
import {RouterView} from 'vue-router'
</script>

<style>
body, html {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

img {
  width: 100%;
  height: 100%;
}

/* 针对Webkit核心的浏览器（如Chrome和Safari） */
::-webkit-scrollbar {
  width: 0;
  height: 0;
}

::-webkit-scrollbar-thumb {
  background: #c0c0c0;
  /* 滚动条滑块的背景颜色 */
  border-radius: 6px;
  /* 滑块的圆角 */
}

::-webkit-scrollbar-track {
  background: #f1f1f1;
  /* 滚动条轨道的背景颜色 */
}

scrollbar {
  -moz-appearance: none;
  width: 12px;
  height: 12px;
}

scrollbar-thumb {
  background: #c0c0c0;
  border-radius: 6px;
}

scrollbar-track {
  background: #f1f1f1;
}
</style>
