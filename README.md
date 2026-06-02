# Vue 3 + SpringBoot 后台管理系统

基于 Vue 3 + Element Plus + Vite 构建前端，SpringBoot 构建后端的后台管理系统。前后端通过 REST API 进行数据传输，后端使用内存静态数据，无需数据库。

## 技术栈

### 前端
- **Vue 3** — 组合式 API（`<script setup>`）
- **Element Plus** — UI 组件库
- **Vue Router 4** — 路由管理
- **Vite** — 构建工具
- **Axios** — HTTP 请求

### 后端
- **Spring Boot 3.2** — Web 框架
- **Java 22** — 运行环境
- **Maven** — 项目构建

## 项目结构

```
exp1/
├── frontend/                   # 前端项目
│   ├── src/
│   │   ├── api/                # API 请求封装
│   │   │   └── index.js
│   │   ├── components/         # 公共组件
│   │   │   ├── auth/           # 认证相关组件
│   │   │   └── dashboard/      # 仪表盘组件
│   │   ├── composables/        # 组合式函数
│   │   │   └── useTheme.js
│   │   ├── layouts/            # 布局组件
│   │   ├── router/             # 路由配置
│   │   ├── styles/             # 全局样式
│   │   ├── views/              # 页面视图
│   │   ├── App.vue
│   │   └── main.js
│   ├── package.json
│   └── vite.config.js
├── backend/                    # 后端项目
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/exp1/admin/
│       │   ├── AdminApplication.java       # 启动类
│       │   ├── config/
│       │   │   └── CorsConfig.java         # 跨域配置
│       │   ├── controller/                 # 控制器层
│       │   │   ├── AuthController.java
│       │   │   ├── UserController.java
│       │   │   └── ArticleController.java
│       │   ├── service/                    # 服务层
│       │   │   ├── AuthService.java
│       │   │   ├── UserService.java
│       │   │   └── ArticleService.java
│       │   ├── model/                      # 数据模型
│       │   │   ├── ApiResponse.java
│       │   │   ├── LoginRequest.java
│       │   │   ├── RegisterRequest.java
│       │   │   ├── ChangePasswordRequest.java
│       │   │   ├── User.java
│       │   │   └── Article.java
│       │   └── data/
│       │       └── MockDataStore.java      # 内存静态数据
│       └── resources/
│           └── application.properties
└── README.md
```

## 功能模块

### 认证模块
- 用户登录（POST `/api/auth/login`）
- 用户注册（POST `/api/auth/register`）
- 修改密码（POST `/api/auth/changePassword`）

### 后台管理
- 仪表盘首页（欢迎信息 + 日历）
- 用户管理（GET `/api/users`）
- 文章管理（GET `/api/articles`）

## REST API

| 方法 | 路径 | 请求体 | 说明 |
|------|------|--------|------|
| POST | `/api/auth/login` | `{ username, password }` | 用户登录 |
| POST | `/api/auth/register` | `{ username, password, email, birthday }` | 用户注册 |
| POST | `/api/auth/changePassword` | `{ username, oldPassword, newPassword }` | 修改密码 |
| GET | `/api/users` | - | 获取用户列表 |
| GET | `/api/articles` | - | 获取文章列表 |

统一响应格式：
```json
{
  "success": true,
  "message": "操作成功",
  "data": null
}
```

## 快速开始

### 环境要求

- JDK >= 22
- Maven >= 3.6
- Node.js >= 18

### 启动后端

```bash
cd backend
mvn spring-boot:run
```

后端运行在 http://localhost:8080

### 启动前端

```bash
cd frontend
npm install
npm run dev
```

前端运行在 http://localhost:5173，`/api` 请求自动代理到后端 8080 端口。

### 默认账号

- 用户名：`admin`
- 密码：`123456`

## 架构说明

```
浏览器 ←→ Vite Dev Server (5173) ←→ SpringBoot (8080)
              前端页面                 REST API
              /api/* 代理转发           MockDataStore
```

- 前端通过 Axios 调用 `/api/*` 接口
- Vite 开发服务器将 `/api/*` 请求代理到 `localhost:8080`
- 后端 Controller 接收请求，调用 Service 处理业务逻辑
- Service 从 MockDataStore 获取内存中的静态数据返回给前端
- 后端同时配置了 CORS，支持跨域直接访问（如 Postman 测试）

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
