<template>
  <el-dialog
      v-model="DetailVisible"
      title="详情"
      width="700"
  >
    <el-descriptions border :column="2" title="">
      <template #default>
        <el-descriptions-item v-for="item in DetailType" :label="item.label">
          <template #default v-if="item.showType!=='select'&&item.showType!=='icon'">
            {{ DetailForm[item.prop] }}
          </template>
          <template #default v-if="item.showType==='select'">
            <el-tag :type="DetailForm[item.prop]===1?'primary':'danger'">
              {{ DetailForm[item.prop] === 1 ? '正常' : '禁用' }}
            </el-tag>
          </template>
          <template #default v-if="item.showType==='icon'">
            <component :is="DetailForm[item.prop]"></component>
          </template>
        </el-descriptions-item>
      </template>
    </el-descriptions>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="closeVisible">取消</el-button>
        <el-button type="primary" @click="onDetail">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {defineProps} from 'vue'
import {defineModel} from 'vue'

const DetailVisible = defineModel('visible');
const DetailForm = defineModel('form');
const DetailType = defineModel('type');

interface Props {
  closeVisible?: Function;
  onDetail?: Function;
}

defineProps<Props>();
</script>

<style scoped>
svg {
  width: 18px;
  height: 18px;
}
</style>