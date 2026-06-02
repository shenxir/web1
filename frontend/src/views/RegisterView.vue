<template>
  <div class="register-container">
    <div class="register-left">
      <el-form ref="formRef" :model="registerForm" :rules="rules" label-width="0">
        <!-- 用户名 -->
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="registerForm.username">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" v-model="registerForm.password" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>

        <!-- 邮箱 -->
        <el-form-item prop="email">
          <el-input placeholder="请输入邮箱" v-model="registerForm.email">
            <template #prefix>
              <el-icon><Message /></el-icon>
            </template>
          </el-input>
        </el-form-item>

        <!-- 出生年月日 -->
        <el-form-item prop="birthday">
          <el-date-picker
            v-model="registerForm.birthday"
            type="date"
            placeholder="请选择出生年月日"
            value-format="YYYY-MM-DD"
            style="width: 100%"
          />
        </el-form-item>
      </el-form>
    </div>

    <div class="register-right">
      <div class="upload-tip">请上传头像</div>
      <div class="avatar-box">
        <i class="fa-solid fa-plus"></i>
        <input type="file" id="avatarInput" accept="image/*">
      </div>
    </div>
  </div>

  <AuthBottomBar
    left-label="注册用户"
    right-label="返回登录"
    @left-click="handleRegister"
    @right-click="router.push('/login')"
  />
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import AuthBottomBar from '@/components/auth/AuthBottomBar.vue'

const router = useRouter()
const formRef = ref(null)

const registerForm = reactive({
  username: '',
  email: '',
  password: '',
  birthday: ''
})

const checkBirthday = (rule, value, callback) => {
  if (!value) {
    return callback(new Error('请选择出生日期'))
  }
  const now = new Date()
  const selectDate = new Date(value)
  if (selectDate > now) {
    callback(new Error('出生日期不能晚于当前时间'))
  } else {
    callback()
  }
}

const rules = {
  username: [
    { required: true, message: '用户名不能为空', trigger: 'blur' }
  ],
  email: [
    { required: true, message: '邮箱不能为空', trigger: 'blur' },
    { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
  ],
  birthday: [
    { validator: checkBirthday, trigger: 'change' }
  ],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' }
  ]
}

const handleRegister = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      ElMessage.success('注册成功')
      setTimeout(() => {
        router.push('/login')
      }, 1000)
    }
  })
}
</script>

<style scoped>
.register-container {
  background: #ffffff;
  border-radius: 16px 16px 0 0;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.2);
  display: flex;
}

.register-left {
  width: 50%;
  padding: 40px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.register-right {
  width: 50%;
  padding: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.upload-tip {
  font-size: 18px;
  color: #666666;
  margin-bottom: 20px;
}

.avatar-box {
  width: 280px;
  height: 280px;
  border: 2px dashed #cccccc;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
}

.avatar-box:hover {
  border-color: #409EFF;
  background: #f5f7fa;
}

.avatar-box i {
  font-size: 50px;
  color: #909399;
}

#avatarInput {
  position: absolute;
  width: 100%;
  height: 100%;
  opacity: 0;
  cursor: pointer;
}

.el-input__inner, .el-date-editor {
  height: 50px;
  border-radius: 10px;
  font-size: 16px;
}

@media screen and (max-width: 800px) {
  .register-container {
    flex-direction: column;
  }

  .register-left, .register-right {
    width: 100%;
    padding: 30px;
  }

  .avatar-box {
    width: 240px;
    height: 240px;
  }
}
</style>