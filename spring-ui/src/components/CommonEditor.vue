<template>
  <el-dialog
      v-model="EditorVisible"
      title="编辑"
      width="700"
  >
    <el-form
        ref="formRef"
        :model="EditorForm"
        label-width="auto"
    >
      <el-row :gutter="20">
        <el-col :span="12" v-for="item in EditorType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop"
                        v-if="item.showType!=='select'&&item.showType!=='text'&&item.showType!=='icon'">
            <el-input v-model="EditorForm[item.prop]"/>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='select'">
            <el-select v-model="EditorForm[item.prop]" placeholder="请选择...">
              <el-option v-for="i in [{label:'正常',value:1},{label:'禁用',value:0}]"
                         :key="i.value" :label="i.label" :value="i.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='icon'">
            <el-select v-model="EditorForm[item.prop]" placeholder="请选择...">
              <el-option v-for="i in iconList" :key="i.value" :label="i.label" :value="i.value">
                <component :is="i.value"></component>
              </el-option>
              <template #tag>
                <component :is="EditorForm[item.prop]"></component>
              </template>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" v-for="item in EditorType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='text'">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="EditorForm[item.prop]"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="closeVisible(formRef)">取消</el-button>
        <el-button type="primary" @click="onEditor(formRef)">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {defineProps, ref} from 'vue'
import {defineModel} from 'vue'
import {FormInstance} from "element-plus";
import {iconList} from "@/utils";

const formRef = ref<FormInstance>();
const EditorVisible = defineModel('visible');
const EditorForm = defineModel('form');
const EditorType = defineModel('type');

interface Props {
  closeVisible?: Function;
  onEditor?: Function;
}

defineProps<Props>();
</script>

<style scoped>
svg {
  width: 18px;
  height: 18px;
}
</style>