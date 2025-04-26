<template>
  <el-dialog
      v-model="ImportVisible"
      title="导入"
      width="500"
  >
    <el-upload drag :show-file-list="false" :limit="1" action="#" :auto-upload="false" accept=".xlsx"
               :on-change="handleImportChange">
      <el-icon class="el-icon--upload">
        <upload-filled/>
      </el-icon>
      <div class="el-upload__text">
        <em> 点击 </em> 或<em> 拖动文件 </em>上传
      </div>
      <template #tip>
        <div class="el-upload__tip">
          仅支持 .xsl / .xlsl 文件
        </div>
      </template>
    </el-upload>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="closeVisible">取消</el-button>
        <el-button type="primary" @click="onImport">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {defineProps} from 'vue'
import {defineModel} from 'vue'
import {UploadFilled} from '@element-plus/icons-vue'

const ImportVisible = defineModel('visible');
const ImportForm = defineModel('form');

interface Props {
  closeVisible?: Function;
  onImport?: Function;
  handleImportChange?: Function;
}

defineProps<Props>();
</script>