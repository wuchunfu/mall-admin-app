<template>
  <el-row>
    <el-col :span="6" :offset="9">
      <el-card class="box-card"
               shadow="never"
               style="border: none;">
        <img src="../assets/logo.png" class="logo-img" alt="logo"/><br>
        <p class="logo-family">Welcome To Mall</p><br>
        <el-form :model="loginForm"
                 status-icon
                 :rules="rules"
                 ref="loginForm"
                 class="demo-ruleForm">
          <el-form-item prop="username">
            <el-input type="text" v-model="loginForm.username" autocomplete="off" prefix-icon="el-icon-user">
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" autocomplete="off" prefix-icon="el-icon-lock">
            </el-input>
          </el-form-item>
          <el-form-item prop="code"  style="width: 380px;">
            <el-input v-model="loginForm.code" prefix-icon="el-icon-circle-check" style="width: 150px; float: left" maxlength="5"></el-input>
            <el-image :src="captchaImg" class="captchaImg" @click="getCaptcha" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary"
                       :style="{ width: '100%'}"
                       @click="submitForm('loginForm')">登 录
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import qs from "qs";

export default {
  name: "Login",
  data() {
    const validateUsercode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入账号'));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      show: true,
      loginForm: {
        username: 'admin',
        password: '123',
        key: '',
        code: '',
      },
      captchaImg: null,
      rules: {
        username: [
          {validator: validateUsercode, trigger: 'blur'}
        ],
        password: [
          {validator: validatePassword, trigger: 'blur'}
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          { min: 5, max: 5, message: '长度为 5 个字符', trigger: 'blur' }
        ],
      }
    };
  },
  mounted() {
    this.getCaptcha();
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/login?' + qs.stringify(this.loginForm)).then((response) => {
            const token = response.headers['authorization'];
            console.log(token)
            this.$store.commit('SET_TOKEN', token)
            localStorage.setItem("USER_ID",response.data.data)
            this.$router.push('/home');
            this.$message({
              message: response.data.message,
              type: 'success'
            });
          }).catch((error) => {
            console.log(error);
            this.getCaptcha();
          })
        } else {
          console.log('提交错误!');
          return false;
        }
      });
    },
    getCaptcha() {
      this.$axios.get('/captcha').then(response => {
        console.log(response.data.data)
        this.loginForm.key = response.data.data.key
        this.captchaImg = response.data.data.captchaImg
        console.log(this.captchaImg)
        console.log(this.loginForm.key)
        this.loginForm.code = ''
      })
    }
  }
}
</script>

<style scoped>
.logo-img{
  width: 80px;
  height: 80px;
}
.box-card {
  margin-top: 150px;
  text-align: center;
}
.logo-family {
  font-size: 16px;
  color: #409eff;
  font-weight: bold;
}
.captchaImg {
  float: left;
  margin-left: 8px;
  border-radius: 4px;
}
</style>