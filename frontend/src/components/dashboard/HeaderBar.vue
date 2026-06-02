<template>
  <div class="header">
    <div>欢迎进入后台管理系统</div>
    <div>
      <el-button type="danger" size="small" @click="logout">退出登录</el-button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'

const router = useRouter()

const logout = () => {
  ElMessage.success('退出成功，即将返回登录页')
  setTimeout(() => {
    router.push('/login')
  }, 1000)
}
</script>

<style scoped>
.header {
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  border-radius: 12px;
  color: #fff;
  margin-bottom: 20px;
  background: var(--theme-header);
  box-shadow: 0 2px 12px rgba(0,0,0,0.08);
}

@media screen and (max-width: 768px) {
  .header {
    flex-direction: column;
    height: auto;
    padding: 15px;
    gap: 10px;
  }
}
</style>