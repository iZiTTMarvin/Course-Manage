package com.example.springboot.common.generator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorVue {
    public static void main(String[] args) {
        // 示例注释字符串，请根据您的实际情况替换
        String little = "table";
        String bigger = "Table";
        String comment = """
                /**
                 * 修改编号
                 * @param {object} params $!{table.comment}
                 * @param {number} params.id 编号
                 * @param {number} params.time 时间@
                 * @param {number} params.week 星期（1~7)
                 * @param {string} params.list 课程@
                 * @param {string} params.classroom 教室@
                 * @param {number} params.teacher 老师@
                 * @param {string} params.createBy 创建者
                 * @param {object} params.createTime 创建时间
                 * @param {string} params.updateBy 更新者
                 * @param {object} params.updateTime 更新时间
                 * @param {number} params.undock 伪删除
                 * @param {number} params.state 状态
                 * @param {string} params.remark 备注
                 * @returns
                 */
                 
                                                """;

        // 正则表达式匹配字段名称和中文名
        Pattern pattern = Pattern.compile("\\@param\\s+\\{\\w+\\}\\s+params\\.(\\w+)\\s+([^\n]+)");
        Matcher matcher = pattern.matcher(comment);

        // 需要从特定数组中排除的标签集合
        ArrayList<String> excludeLabels = new ArrayList<>();
        excludeLabels.add("编号");
        excludeLabels.add("创建者");
        excludeLabels.add("创建时间");
        excludeLabels.add("更新者");
        excludeLabels.add("更新时间");

        // 初始化字段数组
        ArrayList<String> fields = new ArrayList<>();

        // 遍历匹配结果，构建字段对象字符串
        while (matcher.find()) {
            String field_name = matcher.group(1);
            String label_zh = matcher.group(2).trim();

            // 根据label的值设置required属性为true或false
            boolean required = "状态".equals(label_zh) || "名称".equals(label_zh);

            // 根据label的值设置showType属性
            String show_type = determineShowType(label_zh);

            // 构建字段对象字符串
            String field_js = String.format("""
                    {label: '%s', width: '', rules: [{required: '%s', message: '请输入%s', trigger: 'blur'}], showType: '%s', prop: '%s'}
                    """, label_zh, required, label_zh, show_type, field_name);

            // 添加到字段数组
            fields.add(field_js);
        }

        // 根据不同的数组类型，决定是否排除字段
        ArrayList<String> tableFields = new ArrayList<>(fields);
        ArrayList<String> appendFields = new ArrayList<>(fields);
        ArrayList<String> editorFields = new ArrayList<>(fields);
        excludeFields(tableFields, "tableType");
        excludeFields(appendFields, "AppendType");
        excludeFields(editorFields, "EditorType");

        // DetailType不需要排除字段，直接输出
        ArrayList<String> detailFields = fields;


        // 定义一个方法来输出JavaScript数组
        System.out.println("""
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
                import {FindRoleParams} from "@/api/role";

                const {appContext} = getCurrentInstance()!
                                                
                                """);
        System.out.println("const tableType " + " = [\n" + String.join(", ", tableFields) + " ];\n");
        System.out.println("const SearchType" + " = [\n" + String.join(", ", tableFields) + " ];\n");
        System.out.println("const AppendType" + " = [\n" + String.join(", ", appendFields) + "];\n");
        System.out.println("const DetailType" + " = [\n" + String.join(", ", detailFields) + "];\n");
        System.out.println("const EditorType" + " = [\n" + String.join(", ", editorFields) + "];\n");

        String originalString = """

                const SearchVisible = ref(false);
                const SearchForm = ref(<FindRoleParams>{
                  "id": 0,
                  "name": "",
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
                  api.role.listPageRole(val, 12).then((res: any) => {
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
                  api.role.recoverListRole().then((res: any) => {
                    RecoverData.value = res.data;
                  })
                }

                function onRecover(index, row) {
                  ElMessageBox.confirm('确认恢复此数据吗?', '提示', {
                    confirmButtonText: '确认',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }, appContext).then(() => {
                    api.role.recoverRole(row.id).then((res: any) => {
                      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
                      api.role.recoverListRole().then((res: any) => {
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
                    api.role.deleteRole(row.id).then((res: any) => {
                      ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
                      api.role.recoverListRole().then((res: any) => {
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
                  api.role.findRole(SearchForm.value).then((res: any) => {
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
                      api.role.insertRole(AppendForm.value).then((res: any) => {
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
                          api.role.deleteTempRole(element.id).then((res) => {
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
                  api.role.importExcelRole(ImportForm.value).then((res: any) => {
                    ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
                  })
                }

                function onMultiExport() {
                  api.role.exportExcelRole().then((res: any) => {
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
                      api.role.updateRole(EditorForm.value).then((res: any) => {
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
                        api.role.deleteTempRole(row.id).then((res: any) => {
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

                                """;
        // 替换 "role" 为 "_mine"
        String replacedRole = originalString.replace("role", little);

        // 替换 "UserRole" 为 "_Mine"
        String finalString = replacedRole.replace("Role", bigger);

        // 输出最终的字符串
        System.out.println(finalString);

    }

    // 辅助方法，根据label_zh的值设置showType属性
    private static String determineShowType(String label_zh) {
        // 这里实现showType的逻辑，返回对应的字符串
        // 例如：
        switch (label_zh) {
            case "性别":
                return "sex";
            case "状态":
                return "select";
            case "备注":
                return "text";
            case "图标":
                return "icon";
            case "头像":
            case "图片":
                return "img";
            default:
                return "";
        }
    }

    // 辅助方法，根据数组类型排除字段
    private static void excludeFields(ArrayList<String> fields, String arrayType) {
        ArrayList<String> excludeLabels = new ArrayList<>();
        excludeLabels.add("编号");
        excludeLabels.add("创建者");
        excludeLabels.add("创建时间");
        excludeLabels.add("更新者");
        excludeLabels.add("更新时间");
        excludeLabels.add("伪删除");
        excludeLabels.add("备注");

        fields.removeIf(field -> excludeLabels.stream()
                .anyMatch(label -> field.contains(label)));
    }
}