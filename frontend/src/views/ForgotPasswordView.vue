<template>
  <div class="forgot-container">
    <el-form ref="formRef" :model="form" :rules="rules" label-width="0" style="display: flex; width: 100%">
      <!-- 左侧：用户名+旧密码 -->
      <div class="left-panel">
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="form.username" :prefix-icon="User" />
        </el-form-item>
        <el-form-item prop="oldPassword">
          <el-input placeholder="请输入旧密码" v-model="form.oldPassword" show-password :prefix-icon="Lock" />
        </el-form-item>
      </div>

      <!-- 右侧：新密码+确认密码 -->
      <div class="right-panel">
        <el-form-item prop="newPassword">
          <el-input placeholder="请输入新密码" v-model="form.newPassword" show-password :prefix-icon="Lock" />
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="请确认新密码" v-model="form.confirmPassword" show-password :prefix-icon="Lock" />
        </el-form-item>
      </div>
    </el-form>
  </div>

  <AuthBottomBar
    left-label="修改密码"
    right-label="返回登录"
    @left-click="submit"
    @right-click="router.push('/login')"
  />
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { User, Lock } from '@element-plus/icons-vue'
import { changePassword as changePasswordApi } from '@/api/index.js'
import AuthBottomBar from '@/components/auth/AuthBottomBar.vue'

const router = useRouter()
const formRef = ref(null)

const form = reactive({
  username: '',
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
})

const checkPassword = (rule, value, callback) => {
  if (value !== form.newPassword) {
    callback(new Error('两次输入的密码不一致'))
  } else {
    callback()
  }
}

const rules = reactive({
  username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  oldPassword: [{ required: true, message: '旧密码不能为空', trigger: 'blur' }],
  newPassword: [{ required: true, message: '新密码不能为空', trigger: 'blur' }],
  confirmPassword: [
    { required: true, message: '确认密码不能为空', trigger: 'blur' },
    { validator: checkPassword, trigger: 'blur' }
  ]
})

const submit = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await changePasswordApi({
          username: form.username,
          oldPassword: form.oldPassword,
          newPassword: form.newPassword
        })
        if (res.data.success) {
          ElMessage.success('密码修改成功！即将返回登录页')
          setTimeout(() => { router.push('/login') }, 1000)
        } else {
          ElMessage.error(res.data.message || '修改失败')
        }
      } catch (e) {
        ElMessage.error('网络错误，请稍后重试')
      }
    } else {
      ElMessage.error('请完善表单信息后再提交！')
    }
  })
}
</script>

<style scoped>
.forgot-container {
  background: #ffffff;
  border-radius: 16px 16px 0 0;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.2);
  display: flex;
}

.left-panel {
  width: 50%;
  padding: 40px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.right-panel {
  width: 50%;
  padding: 40px;
  display: flex;
  flex-direction: column;
  gap: 25px;
}

.reset-btn {
  background: #409EFF;
}

@media screen and (max-width: 800px) {
  .forgot-container {
    flex-direction: column;
  }

  .left-panel, .right-panel {
    width: 100%;
    padding: 30px;
  }
}
</style>