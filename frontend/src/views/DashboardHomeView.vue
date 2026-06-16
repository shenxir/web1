<template>
  <div class="home-row">
    <el-card class="left-card" shadow="hover">
      <div class="welcome-title">
        欢迎回来，<span class="user-name">{{ username }}</span>
      </div>
      <div class="avatar-wrapper">
        <img v-if="avatarUrl" :src="avatarUrl" class="avatar-img" />
        <el-icon v-else class="avatar-icon"><UserFilled /></el-icon>
      </div>
      <el-button type="primary" class="profile-btn" @click="openDialog">
        完善信息
      </el-button>
    </el-card>
    <el-card class="right-card" shadow="hover">
      <div class="calendar-box">
        <el-calendar v-model="currentDate" />
      </div>
    </el-card>
  </div>

  <!-- 完善信息弹窗 -->
  <el-dialog v-model="dialogVisible" title="完善个人信息" width="500px" destroy-on-close>
    <el-form :model="profileForm" label-width="80px">
      <el-form-item label="省份">
        <el-input v-model="profileForm.province" placeholder="请输入省份" />
      </el-form-item>
      <el-form-item label="城市">
        <el-input v-model="profileForm.city" placeholder="请输入城市" />
      </el-form-item>
      <el-form-item label="详细地址">
        <el-input v-model="profileForm.address" placeholder="请输入详细地址" />
      </el-form-item>
      <el-form-item label="邮编">
        <el-input v-model="profileForm.zip" placeholder="请输入邮编" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="submitProfile">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getUserByName, updateProfile } from '@/api/index.js'

const username = ref(localStorage.getItem('username') || 'admin')
const avatarUrl = ref('')
const currentDate = ref(new Date())

const dialogVisible = ref(false)
const profileForm = reactive({
  province: '',
  city: '',
  address: '',
  zip: ''
})

// 加载用户信息（头像 + 资料）
const loadUserInfo = async () => {
  try {
    const res = await getUserByName(username.value)
    if (res.data.success && res.data.data) {
      const data = res.data.data
      avatarUrl.value = data.avatar || ''
      profileForm.province = data.province || ''
      profileForm.city = data.city || ''
      profileForm.address = data.address || ''
      profileForm.zip = data.zip || ''
    }
  } catch (e) {
    // 忽略
  }
}

const openDialog = async () => {
  dialogVisible.value = true
}

const submitProfile = async () => {
  try {
    const res = await updateProfile({
      name: username.value,
      province: profileForm.province,
      city: profileForm.city,
      address: profileForm.address,
      zip: profileForm.zip
    })
    if (res.data.success) {
      ElMessage.success('信息更新成功')
      dialogVisible.value = false
    } else {
      ElMessage.error(res.data.message || '更新失败')
    }
  } catch (e) {
    ElMessage.error('网络错误，请稍后重试')
  }
}

onMounted(() => {
  loadUserInfo()
})
</script>

<style scoped>
.home-row {
  display: flex;
  align-items: stretch;
  gap: 20px;
  margin-top: 5px;
}

.left-card {
  flex: 0 0 38%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 40px 30px;
}

.right-card {
  flex: 1;
  aspect-ratio: 1/1;
  display: flex;
  flex-direction: column;
}

.welcome-title {
  font-size: 28px;
  color: #333;
  margin-bottom: 35px;
  text-align: center;
  font-weight: 500;
}

.user-name {
  color: var(--theme-primary);
  font-weight: bold;
  font-size: 30px;
}

.avatar-wrapper {
  width: 260px;
  aspect-ratio: 1/1;
  border: 3px solid var(--theme-border);
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(245,247,250,0.4);
  overflow: hidden;
}

.avatar-icon {
  font-size: 120px;
  color: var(--theme-primary);
}

.avatar-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-btn {
  margin-top: 20px;
  font-size: 15px;
  width: 260px;
}

.calendar-box {
  flex: 1;
}

/* 日历主题适配 */
:deep(.el-calendar__button-group .el-button-line.is-active) {
  background: var(--theme-primary) !important;
  border-color: var(--theme-primary) !important;
}

:deep(.el-calendar-day:hover) {
  background-color: var(--theme-primary) !important;
  opacity: 0.2;
}

@media screen and (max-width: 768px) {
  .home-row {
    flex-direction: column;
  }

  .left-card {
    flex: none;
    width: 100%;
  }

  .avatar-wrapper {
    width: 200px;
  }

  .avatar-icon {
    font-size: 90px;
  }
}
</style>
