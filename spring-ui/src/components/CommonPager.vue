<template>
  <el-row justify="end" style="position: fixed;bottom: 30px;right: 20px;">
    <el-pagination background layout="total, prev, pager, next" :total="currentTotal"
                   v-model:current-page="currentPage"
                   @current-change="handleCurrentChange"/>
  </el-row>
</template>

<script setup lang="ts">
import {defineProps} from "vue";
import {defineModel} from 'vue'

const currentPage = defineModel('page');
const currentTotal = defineModel('total');

interface Props {
  // currentPage?: Number;
  handleCurrentChange?: Function;
}

defineProps<Props>()
</script>