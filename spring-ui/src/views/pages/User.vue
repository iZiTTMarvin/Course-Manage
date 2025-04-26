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
import {FindUserParams} from "@/api/user";

const {appContext} = getCurrentInstance()!


const tableType  = [
  {label: '头像', width: '', rules: [{required: false, message: '请输入头像', trigger: 'blur'}], showType: 'img', prop: 'pic'}
  , {label: '名称', width: '', rules: [{required: 'true', message: '请输入名称', trigger: 'blur'}], showType: '', prop: 'name'}
  , {label: '性别', width: '', rules: [{required: false, message: '请输入性别', trigger: 'blur'}], showType: 'sex', prop: 'gender'}
  , {label: '生日', width: '', rules: [{required: false, message: '请输入生日', trigger: 'blur'}], showType: '', prop: 'birth'}
  , {label: '账号', width: '', rules: [{required: false, message: '请输入账号', trigger: 'blur'}], showType: '', prop: 'acc'}
  // , {label: '密码', width: '', rules: [{required: false, message: '请输入密码', trigger: 'blur'}], showType: '', prop: 'pwd'}
  , {label: '角色@', width: '', rules: [{required: false, message: '请输入角色@', trigger: 'blur'}], showType: '', prop: 'role'}
  // , {label: '入学年份', width: '', rules: [{required: false, message: '请输入入学年份', trigger: 'blur'}], showType: '', prop: 'start'}
  , {label: '学院', width: '', rules: [{required: false, message: '请输入学院', trigger: 'blur'}], showType: '', prop: 'academic'}
  , {label: '专业', width: '', rules: [{required: false, message: '请输入专业', trigger: 'blur'}], showType: '', prop: 'profess'}
  , {label: '班级', width: '', rules: [{required: false, message: '请输入班级', trigger: 'blur'}], showType: '', prop: 'team'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const SearchType = [
  // {label: '头像', width: '', rules: [{required: false, message: '请输入头像', trigger: 'blur'}], showType: 'img', prop: 'pic'}
  {label: '名称', width: '', rules: [{required: 'true', message: '请输入名称', trigger: 'blur'}], showType: '', prop: 'name'}
  , {label: '性别', width: '', rules: [{required: false, message: '请输入性别', trigger: 'blur'}], showType: 'sex', prop: 'gender'}
  , {label: '生日', width: '', rules: [{required: false, message: '请输入生日', trigger: 'blur'}], showType: '', prop: 'birth'}
  , {label: '账号', width: '', rules: [{required: false, message: '请输入账号', trigger: 'blur'}], showType: '', prop: 'acc'}
  // , {label: '密码', width: '', rules: [{required: false, message: '请输入密码', trigger: 'blur'}], showType: '', prop: 'pwd'}
  , {label: '角色@', width: '', rules: [{required: false, message: '请输入角色@', trigger: 'blur'}], showType: '', prop: 'role'}
  , {label: '入学年份', width: '', rules: [{required: false, message: '请输入入学年份', trigger: 'blur'}], showType: '', prop: 'start'}
  , {label: '学院', width: '', rules: [{required: false, message: '请输入学院', trigger: 'blur'}], showType: '', prop: 'academic'}
  , {label: '专业', width: '', rules: [{required: false, message: '请输入专业', trigger: 'blur'}], showType: '', prop: 'profess'}
  , {label: '班级', width: '', rules: [{required: false, message: '请输入班级', trigger: 'blur'}], showType: '', prop: 'team'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const AppendType = [
  {label: '头像', width: '', rules: [{required: false, message: '请输入头像', trigger: 'blur'}], showType: 'img', prop: 'pic'}
  , {label: '名称', width: '', rules: [{required: 'true', message: '请输入名称', trigger: 'blur'}], showType: '', prop: 'name'}
  , {label: '性别', width: '', rules: [{required: false, message: '请输入性别', trigger: 'blur'}], showType: 'sex', prop: 'gender'}
  , {label: '生日', width: '', rules: [{required: false, message: '请输入生日', trigger: 'blur'}], showType: '', prop: 'birth'}
  , {label: '账号', width: '', rules: [{required: false, message: '请输入账号', trigger: 'blur'}], showType: '', prop: 'acc'}
  , {label: '密码', width: '', rules: [{required: false, message: '请输入密码', trigger: 'blur'}], showType: '', prop: 'pwd'}
  , {label: '角色@', width: '', rules: [{required: false, message: '请输入角色@', trigger: 'blur'}], showType: '', prop: 'role'}
  , {label: '入学年份', width: '', rules: [{required: false, message: '请输入入学年份', trigger: 'blur'}], showType: '', prop: 'start'}
  , {label: '学院', width: '', rules: [{required: false, message: '请输入学院', trigger: 'blur'}], showType: '', prop: 'academic'}
  , {label: '专业', width: '', rules: [{required: false, message: '请输入专业', trigger: 'blur'}], showType: '', prop: 'profess'}
  , {label: '班级', width: '', rules: [{required: false, message: '请输入班级', trigger: 'blur'}], showType: '', prop: 'team'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const DetailType = [
  {label: '编号', width: '', rules: [{required: false, message: '请输入编号', trigger: 'blur'}], showType: '', prop: 'id'}
  , {label: '头像', width: '', rules: [{required: false, message: '请输入头像', trigger: 'blur'}], showType: 'img', prop: 'pic'}
  , {label: '名称', width: '', rules: [{required: 'true', message: '请输入名称', trigger: 'blur'}], showType: '', prop: 'name'}
  , {label: '性别', width: '', rules: [{required: false, message: '请输入性别', trigger: 'blur'}], showType: 'sex', prop: 'gender'}
  , {label: '生日', width: '', rules: [{required: false, message: '请输入生日', trigger: 'blur'}], showType: '', prop: 'birth'}
  , {label: '账号', width: '', rules: [{required: false, message: '请输入账号', trigger: 'blur'}], showType: '', prop: 'acc'}
  , {label: '密码', width: '', rules: [{required: false, message: '请输入密码', trigger: 'blur'}], showType: '', prop: 'pwd'}
  , {label: '角色@', width: '', rules: [{required: false, message: '请输入角色@', trigger: 'blur'}], showType: '', prop: 'role'}
  , {label: '入学年份', width: '', rules: [{required: false, message: '请输入入学年份', trigger: 'blur'}], showType: '', prop: 'start'}
  , {label: '学院', width: '', rules: [{required: false, message: '请输入学院', trigger: 'blur'}], showType: '', prop: 'academic'}
  , {label: '专业', width: '', rules: [{required: false, message: '请输入专业', trigger: 'blur'}], showType: '', prop: 'profess'}
  , {label: '班级', width: '', rules: [{required: false, message: '请输入班级', trigger: 'blur'}], showType: '', prop: 'team'}
  , {label: '创建者', width: '', rules: [{required: false, message: '请输入创建者', trigger: 'blur'}], showType: '', prop: 'createBy'}
  , {label: '创建时间', width: '', rules: [{required: false, message: '请输入创建时间', trigger: 'blur'}], showType: '', prop: 'createTime'}
  , {label: '更新者', width: '', rules: [{required: false, message: '请输入更新者', trigger: 'blur'}], showType: '', prop: 'updateBy'}
  , {label: '更新时间', width: '', rules: [{required: false, message: '请输入更新时间', trigger: 'blur'}], showType: '', prop: 'updateTime'}
  , {label: '伪删除', width: '', rules: [{required: false, message: '请输入伪删除', trigger: 'blur'}], showType: '', prop: 'undock'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
  , {label: '备注', width: '', rules: [{required: false, message: '请输入备注', trigger: 'blur'}], showType: 'text', prop: 'remark'}
];

const EditorType = [
  {label: '头像', width: '', rules: [{required: false, message: '请输入头像', trigger: 'blur'}], showType: 'img', prop: 'pic'}
  , {label: '名称', width: '', rules: [{required: 'true', message: '请输入名称', trigger: 'blur'}], showType: '', prop: 'name'}
  , {label: '性别', width: '', rules: [{required: false, message: '请输入性别', trigger: 'blur'}], showType: 'sex', prop: 'gender'}
  , {label: '生日', width: '', rules: [{required: false, message: '请输入生日', trigger: 'blur'}], showType: '', prop: 'birth'}
  , {label: '账号', width: '', rules: [{required: false, message: '请输入账号', trigger: 'blur'}], showType: '', prop: 'acc'}
  , {label: '密码', width: '', rules: [{required: false, message: '请输入密码', trigger: 'blur'}], showType: '', prop: 'pwd'}
  , {label: '角色@', width: '', rules: [{required: false, message: '请输入角色@', trigger: 'blur'}], showType: '', prop: 'role'}
  , {label: '入学年份', width: '', rules: [{required: false, message: '请输入入学年份', trigger: 'blur'}], showType: '', prop: 'start'}
  , {label: '学院', width: '', rules: [{required: false, message: '请输入学院', trigger: 'blur'}], showType: '', prop: 'academic'}
  , {label: '专业', width: '', rules: [{required: false, message: '请输入专业', trigger: 'blur'}], showType: '', prop: 'profess'}
  , {label: '班级', width: '', rules: [{required: false, message: '请输入班级', trigger: 'blur'}], showType: '', prop: 'team'}
  , {label: '状态', width: '', rules: [{required: 'true', message: '请输入状态', trigger: 'blur'}], showType: 'select', prop: 'state'}
];

const SearchVisible = ref(false);
const SearchForm = ref(<FindUserParams>{
  "id": 0,
  "pic": "",
  "name": "",
  "gender": "",
  "birth": "",
  "acc": "",
  "pwd": "",
  "role": 0,
  "startYear": "",
  "academic": "",
  "profess": "",
  "team": "",
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
  api.user.listPageUser(val, 12).then((res: any) => {
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
  api.user.recoverListUser().then((res: any) => {
    RecoverData.value = res.data;
  })
}

function onRecover(index, row) {
  ElMessageBox.confirm('确认恢复此数据吗?', '提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }, appContext).then(() => {
    api.user.recoverUser(row.id).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
      api.user.recoverListUser().then((res: any) => {
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
    api.user.deleteUser(row.id).then((res: any) => {
      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
      api.user.recoverListUser().then((res: any) => {
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
  api.user.findUser(SearchForm.value).then((res: any) => {
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
      api.user.insertUser(AppendForm.value).then((res: any) => {
        ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
        if (res.success) {
          AppendVisible.value = false;
        }
        getTableData(1);
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
          api.user.deleteTempUser(element.id).then((res) => {
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
  console.log(file);
  if (file.raw.type !== 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
    ElMessage.error('仅支持 .xlsx 格式的文件');//限制文件类型
    return false;
  }
  ImportForm.value.append('file', file.raw);
}

function onImport() {
  api.user.importExcelUser(ImportForm.value).then((res: any) => {
    ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
  })
}

function onMultiExport() {
  api.user.exportExcelUser().then((res: any) => {
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
      api.user.updateUser(EditorForm.value).then((res: any) => {
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
        api.user.deleteTempUser(row.id).then((res: any) => {
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

