<template>
  <el-card shadow="hover" style="margin-top: 20px;">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
      <h2>文章管理</h2>
      <el-button type="primary">发布文章</el-button>
    </div>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :xs="24" :sm="12" :md="8">
        <el-input v-model="articleSearch" placeholder="请输入文章标题"></el-input>
      </el-col>
      <el-col :xs="24" :sm="12" :md="4">
        <el-button type="primary">搜索</el-button>
      </el-col>
    </el-row>
    <el-table :data="articleData" border stripe style="width: 100%;">
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column prop="title" label="文章标题"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="time" label="发布时间"></el-table-column>
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <el-button type="primary" size="small">编辑</el-button>
          <el-button type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 20px; text-align: center;">
      <el-pagination background layout="total, prev, pager, next" :total="60" :page-size="5" />
    </div>
  </el-card>
</template>

<script setup>
import { ref } from 'vue'

const articleSearch = ref('')

const articleData = ref([
  { id: 1, title: 'Vue前端开发实战', author: 'admin', time: '2025-01-01' },
  { id: 2, title: 'ElementUI使用指南', author: 'test', time: '2025-01-02' },
  { id: 3, title: '响应式布局设计', author: 'editor', time: '2025-01-03' },
  { id: 4, title: 'JavaScript基础语法', author: 'writer', time: '2025-01-04' },
  { id: 5, title: 'CSS高级技巧', author: 'dev', time: '2025-01-05' }
])
</script>