<script setup>
import {
  Document,
  Help,
  Histogram,
  House,
  InfoFilled,
  Menu,
  Promotion,
  Setting,
  Share,
  User,
} from '@element-plus/icons-vue'
</script>

<template>
  <div class="common-layout">
    <el-container>
      <!-- Header 区域 -->
      <el-header class="header">
        <span class="title">Tlias智能学习辅助系统</span>
        <span class="right_tool">
          <a href="">
            <el-icon><EditPen /></el-icon> 修改密码 &nbsp;&nbsp;&nbsp; |  &nbsp;&nbsp;&nbsp;
          </a>
          <a href="">
            <el-icon><SwitchButton /></el-icon> 退出登录
          </a>
        </span>
      </el-header>
      
      <el-container>
        <!-- 左侧菜单 -->
        <el-aside width="220px" class="aside">
          <el-menu
            class="el-menu-vertical-demo"
            :default-openeds="['class-student', 'system', 'statistics']"
            router
          >
            <el-menu-item index="/index">
              <el-icon><Promotion /></el-icon>
              <span>首页</span>
            </el-menu-item>

            <el-sub-menu index="class-student">
              <template #title>
                <el-icon><Menu /></el-icon>
                <span>班级学员管理</span>
              </template>
              <el-menu-item index="/clazz">
                <el-icon><House /></el-icon>
                <span>班级管理</span>
              </el-menu-item>
              <el-menu-item index="/stu">
                <el-icon><User /></el-icon>
                <span>学员管理</span>
              </el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="system">
              <template #title>
                <el-icon><Setting /></el-icon>
                <span>系统信息管理</span>
              </template>
              <el-menu-item index="/dept">
                <el-icon><Help /></el-icon>
                <span>部门管理</span>
              </el-menu-item>
              <el-menu-item index="/emp">
                <el-icon><User /></el-icon>
                <span>员工管理</span>
              </el-menu-item>
            </el-sub-menu>

            <el-sub-menu index="statistics">
              <template #title>
                <el-icon><Histogram /></el-icon>
                <span>数据统计管理</span>
              </template>
              <el-menu-item index="/report/emp">
                <el-icon><InfoFilled /></el-icon>
                <span>员工信息统计</span>
              </el-menu-item>
              <el-menu-item index="/report/stu">
                <el-icon><Share /></el-icon>
                <span>学员信息统计</span>
              </el-menu-item>
              <el-menu-item index="/log">
                <el-icon><Document /></el-icon>
                <span>日志信息统计</span>
              </el-menu-item>
            </el-sub-menu>
          </el-menu>
        </el-aside>
        
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
      
    </el-container>
  </div>
</template>

<style scoped>
.header {
  background-image: linear-gradient(to right, #00547d, #007fa4, #00aaa0, #00d072, #a8eb12);
}

.title {
  color: white;
  font-size: 40px;
  font-family: 楷体;
  line-height: 60px;
  font-weight: bolder;
}

.right_tool{
  float: right;
  line-height: 60px;
}

a {
  color: white;
  text-decoration: none;
}

.aside {
  border-right: 1px solid #ccc;
  height: 730px;
  background-color: #fff;
}

.el-menu-vertical-demo {
  border-right: none;
}

.el-menu-vertical-demo :deep(.el-sub-menu__title),
.el-menu-vertical-demo :deep(.el-menu-item) {
  color: #000;
}

.el-menu-vertical-demo :deep(.el-menu-item.is-active) {
  color: #000;
}

.el-menu-vertical-demo :deep(.el-icon),
.el-menu-vertical-demo :deep(.el-sub-menu__icon-arrow) {
  color: #000;
}
</style>
