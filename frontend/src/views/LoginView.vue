<template>
  <div class="login-container">
    <!-- 左侧 -->
    <div class="left-panel">
      <SocialLoginButtons />
    </div>

    <!-- 右侧 -->
    <div class="right-panel">
      <!-- 用户名 -->
      <el-input placeholder="请输入用户名" v-model="loginForm.username">
        <template #prefix>
          <el-icon><User /></el-icon>
        </template>
      </el-input>

      <!-- 密码 -->
      <el-input placeholder="请输入密码" v-model="loginForm.password" show-password>
        <template #prefix>
          <el-icon><Lock /></el-icon>
        </template>
      </el-input>

      <!-- 登录 -->
      <button class="login-btn" @click="login">登录</button>
    </div>
  </div>

  <AuthBottomBar
    left-label="注册用户"
    right-label="修改密码"
    @left-click="router.push('/register')"
    @right-click="router.push('/forgot')"
  />
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { login as loginApi } from '@/api/index.js'
import SocialLoginButtons from '@/components/auth/SocialLoginButtons.vue'
import AuthBottomBar from '@/components/auth/AuthBottomBar.vue'

const router = useRouter()

const loginForm = reactive({
  username: '',
  password: ''
})

const login = async () => {
  if (loginForm.username === '' || loginForm.password === '') {
    ElMessage.error('请输入用户名和密码')
    return
  }
  try {
    const res = await loginApi(loginForm)
    if (res.data.success) {
      ElMessage.success('登录成功')
      setTimeout(() => { router.push('/dashboard') }, 1000)
    } else {
      ElMessage.error(res.data.message || '登录失败')
    }
  } catch (e) {
    ElMessage.error('网络错误，请稍后重试')
  }
}
</script>

<style scoped>
.login-container {
  background: #ffffff;
  border-radius: 16px 16px 0 0;
  overflow: hidden;
  box-shadow: 0 10px 30px rgba(0,0,0,0.2);
  display: flex;
}

.left-panel {
  width: 40%;
  background: #f5f7fa;
  padding: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 25px;
}

.right-panel {
  width: 60%;
  padding: 40px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.el-input {
  margin-bottom: 25px;
}

.login-btn {
  width: 100%;
  height: 52px;
  border: none;
  border-radius: 10px;
  background: #67C23A;
  color: #ffffff;
  font-size: 17px;
  cursor: pointer;
  transition: 0.3s;
}

.login-btn:hover {
  opacity: 0.9;
}

@media screen and (max-width: 800px) {
  .login-container {
    flex-direction: column;
  }

  .left-panel {
    width: 100%;
    padding: 30px;
  }

  .right-panel {
    width: 100%;
    padding: 30px;
  }
}
</style>