<template>
  <ScaleBox
      :width="1550"
      :height="830"
      bgc="transparent"
      :delay="0"
      :isFlat="false"
  >
    <div class="ec-demo" id="ec-demo">
      <img src="@/assets/bg.jpg" alt="登录背景图">
      <el-card
          style="width: 50%;height: 60%;z-index: 999;position: fixed;top: 18%;left: 25%;background-color:rgba(255,255,255,.9);">
        <div style="color: #0753a2;">
          <h2 style="text-align: center;">用户登录</h2>
          <h4 style="text-align: center;margin-top: 20px;margin-bottom: 20px;">大学选课系统登录</h4>
        </div>
        <el-form
            style="padding-right: 60px;"
            ref="ruleFormRef"
            :model="ruleForm"
            status-icon
            :rules="rules"
            label-width="60px"
        >
          <el-form-item label="账号" prop="acc">
            <el-input size="large" placeholder="请输入账号" v-model="ruleForm.acc" type="text" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="密码" prop="pwd">
            <el-input size="large" placeholder="请输入密码"
                      v-model="ruleForm.pwd"
                      type="password"
                      autocomplete="off"
            />
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-row style="width: 100%;height: 40px;">
              <el-col :span="19">
                <el-input size="large" v-model.number="ruleForm.code" placeholder="请输入验证码"/>
              </el-col>
              <el-col :span="5">
                <div class="code">
                  <img :src="codeSrc" alt="code">
                  <el-link
                      style="transform: translateY(-20px);z-index: 999;font-size: 12px;margin-left: 40px;color: #0753a2"
                      type="info" :underline="false" @click="getCode">
                    看不清 换一张
                  </el-link>
                </div>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top: 40px">
            <el-button size="large" color="#0753a2" type="primary" @click="submitForm(ruleFormRef)">
              登录
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-button size="large" @click="resetForm(ruleFormRef)">取消</el-button>
          </el-form-item>
          <el-row justify="end" align="middle">
            <el-link style="color: #0753a2;" type="primary" @click="$router.push('/')">忘记密码，前往找回密码页面</el-link>
          </el-row>
        </el-form>
      </el-card>
    </div>
  </ScaleBox>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import type {FormInstance, FormRules} from 'element-plus'
import api from "@/api";
import {ElMessage} from "element-plus";
import router from "@/router";
import ScaleBox from "vue3-scale-box";

onMounted((() => {
  localStorage.setItem("keypath", '0');
  document.addEventListener('contextmenu', function (e) {
    e.preventDefault();
  });
  document.addEventListener('selectstart', function (e) {
    e.preventDefault();
  });
  getCode();
}))
const codeSrc = ref('');

function getCode() {
  api.login.generateImageCode().then((res: any) => {
    const bufferUrl = btoa(
        new Uint8Array(res).reduce(
            (data, byte) => data + String.fromCharCode(byte),
            ""
        )
    );
    codeSrc.value = "data:image/png;base64," + bufferUrl;
  });
}

const ruleFormRef = ref<FormInstance>()

const validateAcc = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入账号'))
  }
  callback()
}
const validatePwd = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入密码'))
  }
  callback()
}
const checkCode = (rule: any, value: any, callback: any) => {
  if (!value) {
    return callback(new Error('请输入验证码'))
  }
  callback()
}

const ruleForm = reactive({
  acc: '',
  pwd: '',
  code: '',
})

const rules = reactive<FormRules>({
  acc: [{validator: validateAcc, trigger: 'blur'}],
  pwd: [{validator: validatePwd, trigger: 'blur'}],
  code: [{validator: checkCode, trigger: 'blur'}],
})

import useTokenStore from "@/stores/token";

const {token, getToken, setToken} = useTokenStore();
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  //@ts-ignore
  formEl.validate((valid) => {
    if (valid) {
      api.login.login(ruleForm.acc, ruleForm.pwd, ruleForm.code).then((res: any) => {
        ElMessage({message: res.message, type: res.success ? 'success' : 'error', grouping: true, showClose: true});
        localStorage.setItem("user", JSON.stringify(res.data.user));
        localStorage.setItem("role", JSON.stringify(res.data.role));
        localStorage.setItem("menu", JSON.stringify(res.data.menu));
        localStorage.setItem("token", JSON.stringify(res.data.token));
        setToken(res.data.token);
        router.push("/home");
      })
    } else {
      ElMessage.warning({message: "请填写表单", grouping: true, showClose: true});
      return false
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>

<style scoped>
.el-button {
  width: 100%;
}

.code {
  width: 100%;
  height: 40px;
}
</style>