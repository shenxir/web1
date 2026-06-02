<template>
  <div class="sidebar">
    <div class="logo"></div>
    <el-menu :default-active="route.path" router background-color="transparent">
      <!-- 首页 -->
      <el-menu-item index="/dashboard">
        <el-icon><HomeFilled /></el-icon>
        <span>首页</span>
      </el-menu-item>

      <!-- 功能菜单 -->
      <el-sub-menu index="function-menu">
        <template #title>
          <el-icon><Menu /></el-icon>
          <span>功能菜单</span>
        </template>
        <el-menu-item index="/dashboard/users">
          <el-icon><User /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="/dashboard/articles">
          <el-icon><Document /></el-icon>
          <span>文章管理</span>
        </el-menu-item>
      </el-sub-menu>

      <!-- 切换主题 -->
      <el-sub-menu index="theme-menu">
        <template #title>
          <el-icon><Setting /></el-icon>
          <span>切换主题</span>
        </template>
        <el-menu-item @click="toggleTheme('blue')">蓝色风格</el-menu-item>
        <el-menu-item @click="toggleTheme('yellow')">黄色风格</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { useTheme } from '@/composables/useTheme'

const route = useRoute()
const { toggleTheme } = useTheme()
</script>

<style scoped>
.sidebar {
  width: 220px;
  background: #ffffff;
  border-right: 1px solid var(--theme-border);
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  color: var(--theme-primary);
  font-size: 22px;
  font-weight: bold;
  border-bottom: 1px solid var(--theme-border);
  background: #ffffff;
}

/* 侧边栏菜单样式 */
:deep(.el-menu) {
  border-right: none !important;
  background: transparent !important;
}

:deep(.el-menu-item),
:deep(.el-sub-menu__title) {
  color: var(--theme-text) !important;
}

:deep(.el-menu-item.is-active) {
  background-color: var(--theme-primary) !important;
  color: #fff !important;
}

:deep(.el-sub-menu__title:hover),
:deep(.el-menu-item:hover) {
  background-color: var(--theme-primary) !important;
  color: #fff !important;
  opacity: 0.9;
}

@media screen and (max-width: 1000px) {
  .sidebar {
    display: none;
  }
}
</style>