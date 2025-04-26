<template>
  <common-action :show-recover="showRecover" :show-search="showSearch"
                 :show-append="showAppend" :show-import="showImport"
                 :on-multi-delete="onMultiDelete" :on-multi-export="onMultiExport"/>
  <common-table :table-type="tableType" :table-data="tableData" :on-selection-change="onSelectionChange"
                :show-detail="showDetail" :show-editor="showEditor" :on-delete="onDelete"/>
  <common-pager v-model:total="currentTotal" v-model:page="currentPage" :handle-current-change="handleCurrentChange"/>
  <common-import v-model:visible="ImportVisible" v-model:form="ImportForm" :close-visible="closeVisible"
                 :on-import="onImport" :handle-import-change="handleImportChange"/>
  <common-detail v-model:visible="DetailVisible" v-model:form="DetailForm" :close-visible="closeVisible"
                 :on-detail="onDetail" v-model:type="DetailType"/>
  <common-append v-model:visible="AppendVisible" v-model:form="AppendForm" :close-visible="closeVisible"
                 :on-append="onAppend" v-model:type="AppendType"/>
  <common-editor v-model:visible="EditorVisible" v-model:form="EditorForm" :close-visible="closeVisible"
                 :on-editor="onEditor" v-model:type="EditorType"/>
  <common-search v-model:visible="SearchVisible" v-model:form="SearchForm" :close-visible="closeVisible"
                 :on-search="onSearch" v-model:type="SearchType" v-model:data="SearchData"/>
  <common-recover v-model:visible="RecoverVisible" :close-visible="closeVisible"
                  :on-recover="onRecover" :on-delete-forever="onDeleteForever" v-model:type="SearchType"
                  v-model:data="RecoverData"/>
</template>

<script setup lang="ts">
import CommonAction from "@/components/CommonAction.vue";
import CommonSearch from "@/components/CommonSearch.vue";
import CommonTable from "@/components/CommonTable.vue";
import CommonPager from "@/components/CommonPager.vue";
import CommonAppend from "@/components/CommonAppend.vue";
import CommonEditor from "@/components/CommonEditor.vue";
import CommonDetail from "@/components/CommonDetail.vue";
import CommonImport from "@/components/CommonImport.vue";
import CommonRecover from "@/components/CommonRecover.vue";
import {onMounted, ref} from "vue";
import {ElMessage, ElMessageBox, FormInstance} from "element-plus";
import {getCurrentInstance} from 'vue'
import api from "@/api";
import {FindTableParams} from "@/api/table";
const {appContext} = getCurrentInstance()!

const tableType  = [
  // {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'time'}
  // , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  // , {label: '课程@', width: '', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'list'}
  // , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'classroom'}
  // , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'teacher'}
  {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'tname'}
  , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  , {label: '课程@', width: '300', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'lname'}
  , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'cname'}
  , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'uname'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const SearchType = [
  {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'time'}
  , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  , {label: '课程@', width: '', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'list'}
  , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'classroom'}
  , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'teacher'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const AppendType = [
  {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'time'}
  , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  , {label: '课程@', width: '', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'list'}
  , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'classroom'}
  , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'teacher'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const DetailType = [
  {label: '编号', width: '', rules: [{required: false, message: '请输入编号', trigger: 'blur'}], showType: '', prop: 'id'}
  , {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'time'}
  , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  , {label: '课程@', width: '', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'list'}
  , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'classroom'}
  , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'teacher'}
  , {label: '创建者', width: '', rules: [{required: false, message: '请输入创建者', trigger: 'blur'}], showType: '', prop: 'createBy'}
  , {label: '创建时间', width: '', rules: [{required: false, message: '请输入创建时间', trigger: 'blur'}], showType: '', prop: 'createTime'}
  , {label: '更新者', width: '', rules: [{required: false, message: '请输入更新者', trigger: 'blur'}], showType: '', prop: 'updateBy'}
  , {label: '更新时间', width: '', rules: [{required: false, message: '请输入更新时间', trigger: 'blur'}], showType: '', prop: 'updateTime'}
  , {label: '伪删除', width: '', rules: [{required: false, message: '请输入伪删除', trigger: 'blur'}], showType: '', prop: 'undock'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
  , {label: '备注', width: '', rules: [{required: false, message: '请输入备注', trigger: 'blur'}], showType: 'text', prop: 'remark'}
];

const EditorType = [
  {label: '时间@', width: '', rules: [{required: false, message: '请输入时间@', trigger: 'blur'}], showType: '', prop: 'time'}
  , {label: '星期（1~7)', width: '', rules: [{required: false, message: '请输入星期（1~7)', trigger: 'blur'}], showType: '', prop: 'week'}
  , {label: '课程@', width: '', rules: [{required: false, message: '请输入课程@', trigger: 'blur'}], showType: '', prop: 'list'}
  , {label: '教室@', width: '', rules: [{required: false, message: '请输入教室@', trigger: 'blur'}], showType: '', prop: 'classroom'}
  , {label: '老师@', width: '', rules: [{required: false, message: '请输入老师@', trigger: 'blur'}], showType: '', prop: 'teacher'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const SearchVisible = ref(false);
const SearchForm = ref(<FindTableParams>{
  "id": 0,
  "time": 0,
  "week": 0,
  "list": "",
  "classroom": "",
  "teacher": 0,
  "createBy": "",
  "createTime": "",
  "updateBy": "",
  "updateTime": "",
  "undock": 0,
  "state": 1,
  "remark": ""
});
const SearchData = ref([])
const RecoverVisible = ref(false);
const RecoverData = ref([])
const AppendVisible = ref(false);
const AppendForm = ref({id: 0});
const DetailVisible = ref(false);
const DetailForm = ref({});
const EditorVisible = ref(false);
const EditorForm = ref({});
function getTableData(val: number) {
  api.table.listPageTable(val, 12).then((res: any) => {
    if (res.success) {
      tableData.value = res.data.records;
      currentTotal.value = res.data.total;
    }
    ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
  })
}

onMounted(() => {
  getTableData(1);
})

function showRecover() {
  RecoverVisible.value = true;
  api.table.recoverListTable().then((res: any) => {
    RecoverData.value = res.data;
  })
}

function onRecover(index, row) {
  ElMessageBox.confirm('确认恢复此数据吗?', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }, appContext).then(() => {
    api.table.recoverTable(row.id).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
      api.table.recoverListTable().then((res: any) => {
        RecoverData.value = res.data;
      })
      getTableData(1);
    })
  }).catch(() => {
    ElMessage.info({message: "已取消", grouping: true, showClose: true});
  })
}

function onDeleteForever(index, row) {
  ElMessageBox.confirm('确认删除此数据吗?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'error'
  }, appContext).then(() => {
    api.table.deleteTable(row.id).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
      api.table.recoverListTable().then((res: any) => {
        RecoverData.value = res.data;
      })
      getTableData(1);
    })
  }).catch(() => {
    ElMessage.info({message: "已取消", grouping: true, showClose: true});
  })
}

const tableData = ref([]);
const tableSelection = ref<any[]>([]);
const onSelectionChange = (val: any[]) => {
  tableSelection.value = val;
}

function showSearch() {
  SearchVisible.value = true;
}

function onSearch() {
  api.table.findTable(SearchForm.value).then((res: any) => {
    if (res.success) {
      SearchData.value = res.data;
      console.log(res);
    }
    ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
  })
}

function showAppend() {
  AppendVisible.value = true;
}

function onAppend(formEl: FormInstance | undefined) {
  if (!formEl) return
  //@ts-ignore
  formEl.validate((valid) => {
    if (valid) {
      api.table.insertTable(AppendForm.value).then((res: any) => {
        ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
        if (res.success) {
          AppendVisible.value = false;
        }
      })
    } else {
      ElMessage.error({message: "请完整填写表单", grouping: true, showClose: true});
      return false
    }
  })
}

function onMultiDelete() {
  ElMessageBox.confirm('确认批量删除勾选项吗?', '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }, appContext)
      .then(() => {
        for (let i = 0; i < tableSelection.value.length; i++) {
          const element = tableSelection.value[i];
          api.table.deleteTempTable(element.id).then((res) => {
            ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
          })
        }
        getTableData(1);
      })
      .catch(() => {
        ElMessage.info({message: '已取消删除', grouping: true, showClose: true})
      })
}

const ImportVisible = ref(false);
const ImportForm = ref(new FormData());

function showImport() {
  ImportVisible.value = true;
}

function handleImportChange(file) {
  if (file.raw.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
    ElMessage.error('仅支持 .xlsx 格式的文件');//限制文件类型
    return false;
  }
  ImportForm.value.append('file', file.raw);
}

function onImport() {
  api.table.importExcelTable(ImportForm.value).then((res: any) => {
    ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
  })
}

function onMultiExport() {
  api.table.exportExcelTable().then((res: any) => {
    const link = document.createElement('a')
    const fileName = "角色表.xlsx";
    let blob = new Blob([res], {type: 'application/vnd.ms-excel'})
    link.style.display = 'none'
    link.href = URL.createObjectURL(blob)
    link.download = fileName
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
  })
}

function showDetail(index: number, row: any) {
  DetailVisible.value = true;
  DetailForm.value = row;
}

function onDetail() {
  DetailVisible.value = false;
}

function onEditor(formEl: FormInstance | undefined) {
  if (!formEl) return
  //@ts-ignore
  formEl.validate((valid) => {
    if (valid) {
      api.table.updateTable(EditorForm.value).then((res: any) => {
        ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
        if (res.success) {
          EditorVisible.value = false;
        }
      })
    } else {
      ElMessage.error({message: "请完整填写表单", grouping: true, showClose: true});
      return false
    }
  })
}

function showEditor(index: number, row: any) {
  EditorVisible.value = true;
  EditorForm.value = row;
}

function onDelete(index: string, row: any) {
  ElMessageBox.confirm('确认删除吗?', '警告', {confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning'}, appContext)
      .then(() => {
        api.table.deleteTempTable(row.id).then((res: any) => {
          ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
          getTableData(1);
        })
      })
      .catch(() => {
        ElMessage.info({message: '已取消删除'})
      })
}

const currentPage = ref(1);
const currentTotal = ref(0);
const handleCurrentChange = (val: number) => {
  getTableData(val);
}

function closeVisible(formEl: FormInstance | undefined) {
  if (AppendVisible.value === true || EditorVisible.value === true) {
    if (!formEl) return
    formEl.resetFields();
  }
  RecoverVisible.value = false;
  SearchVisible.value = false;
  AppendVisible.value = false;
  DetailVisible.value = false;
  EditorVisible.value = false;
  ImportVisible.value = false;
}
</script>



