<template>
  <div class="home-row">
    <el-card class="left-card" shadow="hover">
      <div class="welcome-title">
        欢迎回来，<span class="user-name">admin</span>
      </div>
      <div class="avatar-wrapper">
        <el-icon class="avatar-icon"><UserFilled /></el-icon>
      </div>
    </el-card>
    <el-card class="right-card" shadow="hover">
      <div class="calendar-box">
        <el-calendar v-model="currentDate" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const currentDate = ref(new Date())
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
}

.avatar-icon {
  font-size: 120px;
  color: var(--theme-primary);
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