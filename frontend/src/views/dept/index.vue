<script setup>
import { onMounted, ref } from 'vue'
import axios from 'axios'
import {queryAllApi} from '@/api/dept'

onMounted(()=>{
  search();
});

const search = async() => {
  const result=await queryAllApi();
  if(result.code==1){
    deptList.value=result.data;
  }
}

const deptList = ref([

])
</script>

<template>
  <div class="dept-container">
    <h1 class="title">部门管理</h1>

    <el-button type="primary" class="add-btn">
      + 新增部门
    </el-button>

    <el-table :data="deptList" border style="width: 100%">
      <el-table-column type="index" label="序号" width="80" align="center" />
      <el-table-column prop="name" label="部门名称" align="center" />
      <el-table-column prop="updateTime" label="最后操作时间" align="center" />
      <el-table-column label="操作" align="center" width="180">
        <template #default>
          <el-button type="primary" link class="action-btn">编辑</el-button>
          <el-button type="primary" link class="action-btn">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped>
.dept-container {
  padding: 20px;
}

.title {
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 20px;
  border-left: 5px solid #409eff;
  padding-left: 10px;
}

.add-btn {
  margin-bottom: 20px;
}

.action-btn {
  color: #e6a23c;
}
</style>
