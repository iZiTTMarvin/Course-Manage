<template>
  <el-dialog
      v-model="RecoverVisible"
      title="数据恢复"
      width="1000"
  >
    <el-table :data="RecoverData" style="width: 100%;height: 400px;overflow: scroll;">
      <el-table-column v-for="item in RecoverType" :label="item.label" :width="item.width">
        <template #default="scope"
                  v-if="item.showType!=='sex'&&item.showType!=='img'&&item.showType!=='text'&&item.showType!=='select'&&item.showType!=='icon'">
          <span>{{ scope.row[item.prop] }}</span>
        </template>
        <!--   性别   -->
        <template #default="scope" v-if="item.showType==='sex'">
          <el-tag :type="scope.row[item.prop] === '男'? 'primary' : 'danger'">
            {{ scope.row[item.prop] }}
          </el-tag>
        </template>
        <!--   头像   -->
        <template #default="scope" v-if="item.showType==='img'">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>{{ scope.row[item.prop] }}</div>
            </template>
            <template #reference>
              <img :class="item.showType" :src="scope.row[item.prop]" :alt="scope.row[item.prop]">
            </template>
          </el-popover>
        </template>
        <!--   备注   -->
        <template #default="scope" v-if="item.showType==='text'">
          <el-popover effect="light" trigger="hover" placement="top" width="auto">
            <template #default>
              <div>{{ scope.row[item.prop] }}</div>
            </template>
            <template #reference>
              <span class="text-overflow"> {{ scope.row[item.prop] }} </span>
            </template>
          </el-popover>
        </template>
        <!--   状态   -->
        <template #default="scope" v-if="item.showType==='select'">
          <el-tag :type="scope.row[item.prop] === 1?'primary':'danger'">
            {{ scope.row[item.prop] === 1 ? '正常' : '禁用' }}
          </el-tag>
        </template>
        <!--   图标   -->
        <template #default="scope" v-if="item.showType==='icon'">
          <component :is="scope.row[item.prop] "></component>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button link size="small" type="success" @click="onRecover(scope.$index, scope.row)">
            恢复
          </el-button>
          <el-button link size="small" type="danger" @click="onDeleteForever(scope.$index, scope.row)">
            永久删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="closeVisible(formRef)">取消</el-button>
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
const RecoverVisible = defineModel('visible');
const RecoverType = defineModel('type');
const RecoverData = defineModel('data');

interface Props {
  closeVisible?: Function;
  onRecover?: Function;
  onDeleteForever?: Function;
}

defineProps<Props>();
</script>

<style scoped>
svg {
  width: 18px;
  height: 18px;
}

</style>