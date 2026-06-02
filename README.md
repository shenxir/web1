# Vue 3 后台管理系统

基于 Vue 3 + Element Plus + Vite 构建的后台管理系统前端项目。

## 技术栈

- **Vue 3** — 组合式 API（`<script setup>`）
- **Element Plus** — UI 组件库
- **Vue Router 4** — 路由管理
- **Vite** — 构建工具

## 项目结构

```
frontend/
├── src/
│   ├── components/        # 公共组件
│   │   ├── auth/          # 认证相关组件
│   │   │   ├── AuthBottomBar.vue
│   │   │   └── SocialLoginButtons.vue
│   │   └── dashboard/     # 仪表盘组件
│   │       ├── HeaderBar.vue
│   │       └── SidebarMenu.vue
│   ├── composables/       # 组合式函数
│   │   └── useTheme.js
│   ├── layouts/           # 布局组件
│   │   ├── AdminLayout.vue
│   │   └── AuthLayout.vue
│   ├── router/            # 路由配置
│   │   └── index.js
│   ├── styles/            # 全局样式
│   │   ├── global.css
│   │   └── variables.css
│   ├── views/             # 页面视图
│   │   ├── LoginView.vue
│   │   ├── RegisterView.vue
│   │   ├── ForgotPasswordView.vue
│   │   ├── DashboardLayoutView.vue
│   │   ├── DashboardHomeView.vue
│   │   ├── UserManagementView.vue
│   │   └── ArticleManagementView.vue
│   ├── App.vue
│   └── main.js
├── package.json
└── vite.config.js
```

## 功能模块

### 认证模块
- 用户登录
- 用户注册
- 修改密码

### 后台管理
- 仪表盘首页（欢迎信息 + 日历）
- 用户管理
- 文章管理

## 快速开始

### 环境要求

- Node.js >= 18

### 安装依赖

```bash
cd frontend
npm install
```

### 启动开发服务器

```bash
npm run dev
```

访问 http://localhost:5173

### 构建生产版本

```bash
npm run build
```

### 预览生产版本

```bash
npm run preview
```

## 路由说明

| 路径 | 页面 | 布局 |
|------|------|------|
| `/login` | 用户登录 | 认证布局 |
| `/register` | 用户注册 | 认证布局 |
| `/forgot` | 修改密码 | 认证布局 |
| `/dashboard` | 仪表盘首页 | 管理后台布局 |
| `/dashboard/users` | 用户管理 | 管理后台布局 |
| `/dashboard/articles` | 文章管理 | 管理后台布局 |

> 访问 `/` 会自动重定向到 `/login`
