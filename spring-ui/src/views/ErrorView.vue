<template>
  <div @click="refreshPage" style="cursor: pointer">
    <el-row justify="center" align="middle" style="height: 90vh;">
      <img style="width: 200px;height: 300px;" class="gif" src="@/assets/401.gif" alt="404">
      <div>
        <div style="line-height: 60px;font-size: 20px;">系统找不到页面了...</div>
        <div style="line-height: 60px;font-size: 14px;"><u>点击任意一处返回首页</u></div>
      </div>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";

function refreshPage() {
  // location.reload();
  router.push("/home");
}
</script>

<style scoped>

</style>