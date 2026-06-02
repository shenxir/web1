<template>
  <component :is="layout">
    <router-view />
  </component>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import AuthLayout from '@/layouts/AuthLayout.vue'
import AdminLayout from '@/layouts/AdminLayout.vue'

const route = useRoute()

const layouts = { auth: AuthLayout, admin: AdminLayout }
const layout = computed(() => layouts[route.meta.layout] || AuthLayout)
</script>