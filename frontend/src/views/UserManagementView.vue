<template>
  <el-card shadow="hover" style="margin-top: 20px;">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
      <h2>用户管理</h2>
      <el-button type="primary">新增用户</el-button>
    </div>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :xs="24" :sm="12" :md="8">
        <el-input v-model="search" placeholder="请输入用户名"></el-input>
      </el-col>
      <el-col :xs="24" :sm="12" :md="4">
        <el-button type="primary">搜索</el-button>
      </el-col>
    </el-row>
    <el-table :data="tableData" border stripe style="width: 100%;">
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <el-button type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px; text-align: center;">
      <el-pagination background layout="total, prev, pager, next" :total="50" :page-size="5" />
    </div>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'

const search = ref('')

const tableData = ref([
  { id: 1, name: '张三', age: 20, email: 'zhangsan@qq.com' },
  { id: 2, name: '李四', age: 21, email: 'lisi@qq.com' },
  { id: 3, name: '王五', age: 22, email: 'wangwu@qq.com' },
  { id: 4, name: '赵六', age: 23, email: 'zhaoliu@qq.com' },
  { id: 5, name: '小明', age: 24, email: 'xiaoming@qq.com' }
])
</script>