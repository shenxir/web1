import { ref, watchEffect } from 'vue'

const theme = ref('blue')

export function useTheme() {
  const toggleTheme = (type) => {
    theme.value = type
  }

  // Apply theme class to document root
  watchEffect(() => {
    const root = document.documentElement
    root.className = theme.value === 'yellow' ? 'theme-yellow' : 'theme-blue'
  })

  return { theme, toggleTheme }
}