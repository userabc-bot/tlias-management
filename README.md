# 智能教学管理系统（Tlias）

前后端分离的教学管理系统，黑马程序员 Tlias 实战项目。提供部门、员工、学员、班级、课程、数据统计等管理能力，包含文件上传与登录鉴权。

## 技术栈

**后端**

- Java 17 / Spring Boot 3.5.4
- MyBatis、MySQL、PageHelper、JWT、Spring AOP
- 阿里云 OSS（文件上传）

**前端**

- Vue 3 + Vue Router + Element Plus
- Vite 构建、Axios 请求封装

## 目录结构

```
tlias-management/
├── backend/
│   ├── tlias-parent/          # Maven 聚合与依赖管理
│   ├── tlias-pojo/            # Entity / DTO / VO
│   ├── tlias-utils/           # 工具类（JWT 等）
│   └── tlias-web-management/  # 接口层与业务实现
└── frontend/                  # Vue 3 管理端
```

## 本地运行

后端：

1. 创建数据库 `tlias` 并导入表结构。
2. 按需修改 `backend/tlias-web-management/src/main/resources/application.yml` 中的数据库连接。
3. 启动：

```bash
cd backend/tlias-parent
mvn -pl ../tlias-web-management spring-boot:run
```

前端：

```bash
cd frontend
npm install
npm run dev
```
