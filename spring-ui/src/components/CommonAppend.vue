<template>
  <el-dialog
      v-model="AppendVisible"
      title="添加"
      width="700"
  >
    <el-form
        ref="formRef"
        :model="AppendForm"
        label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12" v-for="item in AppendType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop"
                        v-if="item.showType!=='select'&&item.showType!=='text'&&item.showType!=='icon'">
            <el-input v-model="AppendForm[item.prop]"/>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='select'">
            <el-select v-model="AppendForm[item.prop]" placeholder="请选择...">
              <el-option v-for="item in [{label:'正常',value:'1'},{label:'禁用',value:'0'}]"
                         :key="item.value" :label="item.label" :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='icon'">
            <el-select v-model="AppendForm[item.prop]" placeholder="请选择...">
              <el-option v-for="i in iconList" :key="i.value" :label="i.label" :value="i.value">
                <component :is="i.value"></component>
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" v-for="item in AppendType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='text'">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="AppendForm[item.prop]"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="closeVisible(formRef)">取消</el-button>
        <el-button type="primary" @click="onAppend(formRef)">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {defineProps} from 'vue'
import {defineModel} from 'vue'
import {iconList} from "@/utils";

import {reactive, ref} from 'vue'
import type {FormInstance} from 'element-plus'

const formRef = ref<FormInstance>();
const AppendVisible = defineModel('visible');
const AppendForm = defineModel('form');
const AppendType = defineModel('type');

interface Props {
  closeVisible?: Function;
  onAppend?: Function;
}

defineProps<Props>();
</script>

<style scoped>
svg {
  width: 18px;
  height: 18px;
}
</style>