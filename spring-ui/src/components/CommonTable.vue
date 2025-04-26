<template>
  <el-table :row-key="tableType[0].prop" default-expand-all :data="tableData"
            @selection-change="onSelectionChange">
    <el-table-column type="selection" width="55"/>
    <el-table-column v-for="item in tableType" :label="item.label" :width="item.width">
      <template #default="scope"
                v-if="item.showType!=='sex'&&item.showType!=='img'&&item.showType!=='text'&&item.showType!=='select'&&item.showType!=='icon'&&item.showType!=='tree'">
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
            <!--<div>{{ scope.row[item.prop] }}</div>-->
          </template>
          <template #reference>
            <img v-if="scope.row[tableType[2].prop]==='男'" :class="item.showType" src="@/assets/avatar-male.png"
                 :alt="scope.row[item.prop]">
            <img v-if="scope.row[tableType[2].prop]==='女'" :class="item.showType" src="@/assets/avatar-female.png"
                 :alt="scope.row[item.prop]">
            <!--<img :class="item.showType" :src="scope.row[item.prop]" :alt="scope.row[item.prop]">-->
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
        <component :is="scope.row[item.prop]"></component>
      </template>
      <!--   数组   -->
      <template #default="scope" v-if="item.showType==='tree'">
        <el-popover
            trigger="click"
            placement="right" :width="200"
        >
          <template #reference>
            <el-button>分配权限</el-button>
          </template>
          <el-tree
              ref="treeRef"
              :data="scope.row[item.prop]"
              show-checkbox
              node-key="id"
              :default-checked-keys="scope.row.rmc"
              :props="defaultProps"
              @check-change="getCheckedNodes"
          />
        </el-popover>
      </template>
    </el-table-column>

    <el-table-column label="操作" width="120">
      <template #default="scope">
        <el-button link size="small" type="primary" @click="showDetail(scope.$index, scope.row)">
          详情
        </el-button>
        <el-button link size="small" type="warning" @click="showEditor(scope.$index, scope.row)">
          编辑
        </el-button>
        <el-button link size="small" type="danger" @click="onDelete(scope.$index, scope.row)">
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import {defineProps} from "vue";
import api from "@/api";
import {ElMessage} from "element-plus";

const defaultProps = {
  children: 'children',
  label: 'name',
}

const getCheckedNodes = (data) => {
  if (data.undock == 1) {
    api.others.recoverRm(data.role, data.menu).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
    })
  } else if (data.undock == 0) {
    api.others.updateRm(data.role, data.menu).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
    })
  }
}

interface Props {
  tableType?: Array<any>;
  tableData?: Array<any>;
  onSelectionChange?: Function;
  showDetail?: Function;
  showEditor?: Function;
  onDelete?: Function;
}

defineProps<Props>();
</script>

<style scoped>
::-webkit-scrollbar {
  width: 0; /* 横向滚动条的宽度 */
  height: 12px; /* 纵向滚动条的高度 */
}

:deep(.el-table__cell) {
  padding: 0 !important;
}

:deep(.cell) {
  width: 100%;
  height: 44px !important;
  display: flex;
  align-items: center;
  justify-content: center;
}

.img {
  width: 30px;
  height: 30px;
}

.text-overflow {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  overflow: hidden;
}

svg {
  width: 18px;
  height: 18px;
  margin-right: 5px;
}
</style>