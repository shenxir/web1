<template>
  <div class="auth-layout">
    <div class="auth-wrapper">
      <div class="auth-title">{{ title }}</div>
      <slot />
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const title = computed(() => route.meta.title || '')
</script>

<style scoped>
.auth-layout {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #409EFF, #66b1ff);
  padding: 20px;
}

.auth-wrapper {
  width: 100%;
  max-width: 900px;
}

.auth-title {
  text-align: center;
  color: #ffffff;
  font-size: 34px;
  font-weight: bold;
  margin-bottom: 25px;
  letter-spacing: 2px;
}
</style>