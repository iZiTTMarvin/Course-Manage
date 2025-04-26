<template>
  <div>
    <el-descriptions
        title="我的信息"
        :column="3"
        :size="size"
        border
    >
      <template #extra>
        <el-button type="primary">修改我的信息</el-button>
      </template>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <user/>
            </el-icon>
            姓名
          </div>
        </template>
        {{ user.name }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <iphone/>
            </el-icon>
            学号
          </div>
        </template>
        {{ user.acc }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <location/>
            </el-icon>
            学院
          </div>
        </template>
        {{ user.academic }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <tickets/>
            </el-icon>
            专业
          </div>
        </template>
<!--        <el-tag size="small">{{ user.profess }}</el-tag>-->
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <office-building/>
            </el-icon>
            班级
          </div>
        </template>
<!--        {{ user.team }}-->
      </el-descriptions-item>
    </el-descriptions>
  </div>
</template>

<script setup lang="ts">
import {computed, ref} from 'vue'
import {
  Iphone,
  Location,
  OfficeBuilding,
  Tickets,
  User,
} from '@element-plus/icons-vue'
import {onMounted, reactive} from "vue";

const user = reactive(JSON.parse(localStorage.getItem("user") || '{}'));
const role = reactive(JSON.parse(localStorage.getItem("role") || '{}'));
const menu = ref(JSON.parse(localStorage.getItem("menu") || '[]'));
const token = JSON.parse(localStorage.getItem("token") || '""');

onMounted(() => {
  console.log('当前用户信息:', user);
})

const size = ref('')
const iconStyle = computed(() => {
  const marginMap = {
    large: '8px',
    default: '6px',
    small: '4px',
  }
  return {
    marginRight: marginMap[size.value] || marginMap.default,
  }
})
</script>

<style scoped>

</style>
