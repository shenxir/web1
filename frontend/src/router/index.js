import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/LoginView.vue'),
    meta: { layout: 'auth', title: '用户登录' }
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('@/views/RegisterView.vue'),
    meta: { layout: 'auth', title: '用户注册' }
  },
  {
    path: '/forgot',
    name: 'ForgotPassword',
    component: () => import('@/views/ForgotPasswordView.vue'),
    meta: { layout: 'auth', title: '修改密码' }
  },
  {
    path: '/dashboard',
    component: () => import('@/views/DashboardLayoutView.vue'),
    meta: { layout: 'admin' },
    children: [
      {
        path: '',
        name: 'DashboardHome',
        component: () => import('@/views/DashboardHomeView.vue'),
        meta: { title: '后台管理系统' }
      },
      {
        path: 'users',
        name: 'UserManagement',
        component: () => import('@/views/UserManagementView.vue'),
        meta: { title: '用户管理' }
      },
      {
        path: 'articles',
        name: 'ArticleManagement',
        component: () => import('@/views/ArticleManagementView.vue'),
        meta: { title: '文章管理' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Navigation guard to set document title
router.afterEach((to) => {
  document.title = to.meta.title || '后台管理系统'
})

export default router