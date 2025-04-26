<template>
  <el-dialog
      v-model="SearchVisible"
      title="搜索"
      width="1000"
  >
    <el-form
        ref="formRef"
        :model="SearchForm"
        label-width="auto"
    >
      <el-row>
        <div v-for="item in SearchType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop"
                        v-if="item.showType!=='select'&&item.showType!=='text'&&item.showType!=='icon'">
            <el-input v-model="SearchForm[item.prop]"/>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='select'">
            <el-select v-model="SearchForm[item.prop]" placeholder="请选择...">
              <el-option v-for="item in [{label:'正常',value:1},{label:'禁用',value:0}]"
                         :key="item.value" :label="item.label" :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='icon'">
            <el-select v-model="SearchForm[item.prop]" placeholder="请选择...">
              <el-option v-for="i in iconList" :key="i.value" :label="i.label" :value="i.value">
                <component :is="i.value"></component>
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <el-button type="primary" :icon="Opportunity" @click="onSearch(formRef)">检索</el-button>
      </el-row>

      <el-table :data="SearchData" style="width: 100%;height: 400px;overflow: scroll;">
        <el-table-column v-for="item in SearchType" :label="item.label" :width="item.width">
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
      </el-table>

      <el-row>
        <el-col :span="24" v-for="item in SearchType">
          <el-form-item :label="item.label" :rules="item.rules" :prop="item.prop" v-if="item.showType==='text'">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" v-model="SearchForm[item.prop]"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <!--        <el-button @click="closeVisible(formRef)">取消</el-button>-->
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {defineProps} from 'vue'
import {defineModel} from 'vue'
import {iconList} from "@/utils";
import {Opportunity,} from '@element-plus/icons-vue'
import {reactive, ref} from 'vue'
import type {FormInstance} from 'element-plus'

const formRef = ref<FormInstance>();
const SearchVisible = defineModel('visible');
const SearchForm = defineModel('form');
const SearchType = defineModel('type');
const SearchData = defineModel('data');

interface Props {
  closeVisible?: Function;
  onSearch?: Function;
}

defineProps<Props>();
</script>

<style scoped>
svg {
  width: 18px;
  height: 18px;
}

:deep(.el-input__wrapper), :deep(.el-select__wrapper), :deep(.el-button) {
  width: 120px !important;
}

</style>