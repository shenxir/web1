<template>
  <div class="bottom-container">
    <button class="bottom-btn" @click="$emit('left-click')">
      {{ leftLabel }}
    </button>
    <button class="bottom-btn" @click="$emit('right-click')">
      {{ rightLabel }}
    </button>
  </div>
</template>

<script setup>
defineProps({
  leftLabel: {
    type: String,
    default: '注册用户'
  },
  rightLabel: {
    type: String,
    default: '修改密码'
  }
})

defineEmits(['left-click', 'right-click'])
</script>