# Vue 3 + SpringBoot 后台管理系统

基于 Vue 3 + Element Plus + Vite 构建前端，Spring Boot + MyBatis + MySQL 构建后端的后台管理系统。前后端通过 REST API 进行数据传输。

## 技术栈

### 前端
- **Vue 3** — 组合式 API（`<script setup>`）
- **Element Plus** — UI 组件库
- **Vue Router 4** — 路由管理
- **Vite** — 构建工具
- **Axios** — HTTP 请求

### 后端
- **Spring Boot 3.2** — Web 框架
- **MyBatis** — ORM 持久层框架
- **MySQL 8** — 关系型数据库
- **Java 21** — 运行环境
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
│       │   │   ├── CorsConfig.java         # 跨域配置
│       │   │   └── WebMvcConfig.java       # 静态资源映射
│       │   ├── controller/                 # 控制器层
│       │   │   ├── AuthController.java
│       │   │   ├── UserController.java
│       │   │   ├── ArticleController.java
│       │   │   └── FileController.java     # 文件上传
│       │   ├── mapper/                     # MyBatis Mapper
│       │   │   ├── UserMapper.java
│       │   │   └── UserTMapper.java
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
│       │   │   ├── UserT.java
│       │   │   └── Article.java
│       │   └── data/
│       │       └── MockDataStore.java      # 文章静态数据
│       └── resources/
│           ├── application.properties
│           └── mapper/                     # MyBatis XML
│               ├── UserMapper.xml
│               └── UserTMapper.xml
├── pic/                        # 用户头像存储目录
└── README.md
```

## 数据库设计

### userT 表（登录/注册）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(11) | 主键，自增 |
| name | varchar(255) | 用户名 |
| password | varchar(255) | 密码 |
| email | varchar(255) | 邮箱 |
| birthday | datetime | 生日 |
| money | float | 余额 |
| avatar | varchar(45) | 头像路径 |

### User 表（用户列表展示）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | int(11) | 主键，自增 |
| date | date | 日期 |
| name | varchar(45) | 姓名（与 userT 关联） |
| province | varchar(45) | 省份 |
| city | varchar(45) | 城市 |
| address | varchar(45) | 地址 |
| zip | varchar(45) | 邮编 |

### 初始化数据

```sql
-- userT 表
INSERT INTO `userT` (`name`, `password`, `email`, `birthday`, `money`, `avatar`) VALUES
('admin', '123456', 'admin@example.com', '2000-01-01 00:00:00', 9999.00, NULL),
('张三', '123456', 'zhangsan@qq.com', '1998-05-15 00:00:00', 1500.00, NULL),
('李四', '123456', 'lisi@qq.com', '1999-08-20 00:00:00', 2300.00, NULL),
('王五', '123456', 'wangwu@qq.com', '2001-03-10 00:00:00', 800.00, NULL);

-- User 表
INSERT INTO `User` (`date`, `name`, `province`, `city`, `address`, `zip`) VALUES
('2025-01-01', 'admin', '北京市', '北京市', '朝阳区建国路', '100000'),
('2025-01-02', '张三', '浙江省', '杭州市', '西湖区文三路', '310000'),
('2025-01-03', '李四', '江苏省', '南京市', '鼓楼区中山路', '210000'),
('2025-01-04', '王五', '广东省', '广州市', '天河区体育西路', '510000');
```

## 功能模块

### 认证模块
- 用户登录（POST `/api/auth/login`）
- 用户注册（POST `/api/auth/register`）— 同时写入 userT 和 User 两张表
- 修改密码（POST `/api/auth/changePassword`）
- 上传头像（POST `/api/auth/avatar`）

### 仪表盘首页
- 欢迎信息（显示当前登录用户名）
- 用户头像展示（从数据库读取）
- 完善个人信息（省份、城市、地址、邮编）
- 日历组件

### 用户管理
- 分页查询用户列表（支持按姓名搜索）
- 每页默认 10 条

### 文章管理
- 分页查询文章列表（支持按标题搜索）
- 文章数据为静态数据（MockDataStore）
- 每页默认 10 条

## REST API

### 认证接口

| 方法 | 路径 | 请求体 | 说明 |
|------|------|--------|------|
| POST | `/api/auth/login` | `{ username, password }` | 用户登录 |
| POST | `/api/auth/register` | `{ username, password, email, birthday }` | 用户注册 |
| POST | `/api/auth/changePassword` | `{ username, oldPassword, newPassword }` | 修改密码 |
| POST | `/api/auth/avatar` | `{ name, avatar }` | 保存头像路径 |

### 用户接口

| 方法 | 路径 | 参数 | 说明 |
|------|------|------|------|
| GET | `/api/users` | `page`, `pageSize`, `name`(可选) | 分页获取用户列表 |
| GET | `/api/users/{name}` | - | 获取单个用户信息（含头像） |
| PUT | `/api/users/profile` | `{ name, province, city, address, zip }` | 更新用户资料 |

### 文章接口

| 方法 | 路径 | 参数 | 说明 |
|------|------|------|------|
| GET | `/api/articles` | `page`, `pageSize`, `title`(可选) | 分页获取文章列表 |

### 文件接口

| 方法 | 路径 | 参数 | 说明 |
|------|------|------|------|
| POST | `/api/file/upload` | `file` (multipart) | 上传文件，返回 `/pic/文件名` |

统一响应格式：
```json
{
  "success": true,
  "message": "操作成功",
  "data": null
}
```

分页响应格式：
```json
{
  "success": true,
  "message": "获取成功",
  "data": {
    "list": [...],
    "total": 100
  }
}
```

## 快速开始

### 环境要求

- JDK >= 21
- Maven >= 3.6
- Node.js >= 18
- MySQL 8

### 数据库准备

1. 创建数据库 `exp1`
2. 建表并插入初始化数据（见上方 SQL）

### 配置数据库连接

编辑 `backend/src/main/resources/application.properties`：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/exp1?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=你的密码
```

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

前端运行在 http://localhost:5173，`/api` 和 `/pic` 请求自动代理到后端 8080 端口。

### 默认账号

- 用户名：`admin`
- 密码：`123456`

## 架构说明

```
浏览器 ←→ Vite Dev Server (5173) ←→ SpringBoot (8080) ←→ MySQL
              前端页面                 REST API            数据库
              /api/* 代理转发           MyBatis Mapper
              /pic/* 代理转发           FileController
```

- 前端通过 Axios 调用 `/api/*` 接口
- Vite 开发服务器将 `/api/*` 和 `/pic/*` 请求代理到 `localhost:8080`
- 后端 Controller 接收请求，调用 Service 处理业务逻辑
- Service 通过 MyBatis Mapper 与 MySQL 数据库交互
- 文章数据存储在 MockDataStore 内存中（静态数据）
- 用户头像存储在项目根目录 `pic/` 文件夹，通过 WebMvcConfig 映射为静态资源
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
