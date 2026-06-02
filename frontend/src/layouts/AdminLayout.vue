<template>
  <div class="main-layout" :class="theme === 'yellow' ? 'theme-yellow' : ''">
    <!-- 左侧菜单 -->
    <SidebarMenu />

    <!-- 主内容区域 -->
    <div class="content">
      <HeaderBar />
      <slot />
    </div>
  </div>
</template>

<script setup>
import SidebarMenu from '@/components/dashboard/SidebarMenu.vue'
import HeaderBar from '@/components/dashboard/HeaderBar.vue'
import { useTheme } from '@/composables/useTheme'

const { theme } = useTheme()
</script>

<style scoped>
.main-layout {
  display: flex;
  min-height: 100vh;
  background: var(--theme-bg);
}

.content {
  flex: 1;
  padding: 20px;
  overflow: auto;
}

@media screen and (max-width: 1000px) {
  .content {
    width: 100%;
  }
}
</style>