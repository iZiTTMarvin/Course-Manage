<template>
  <el-space size="default">
    <el-button plain type="primary" :icon="Opportunity" @click="showSearch">检索</el-button>
    <el-button plain type="primary" :icon="CirclePlusFilled" @click="showAppend">添加</el-button>
    <el-button plain type="danger" :icon="DeleteFilled" @click="onMultiDelete">批量删除</el-button>
    <el-button plain type="success" :icon="Promotion" @click="showRecover">数据恢复</el-button>
    <el-button plain type="info" :icon="UploadFilled" @click="showImport">导入</el-button>
    <el-button plain type="warning" :icon="Share" @click="onMultiExport">导出</el-button>
  </el-space>
</template>

<script setup lang="ts">
import {defineProps} from 'vue'
import {
  Plus,
  Delete,
  Opportunity,
  DeleteFilled,
  Promotion,
  Upload,
  UploadFilled,
  Share,
  Search,
  Pointer,
  CirclePlusFilled
} from '@element-plus/icons-vue'
import {defineModel} from 'vue'

interface Props {
  showSearch?: Function;
  showAppend?: Function;
  showImport?: Function;
  showRecover?: Function;
  onMultiDelete?: Function;
  onMultiExport?: Function;
}

defineProps<Props>();
</script>