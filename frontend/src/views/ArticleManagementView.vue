<template>
  <el-card shadow="hover" style="margin-top: 20px;">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
      <h2>文章管理</h2>
      <el-button type="primary">发布文章</el-button>
    </div>
    <el-row :gutter="20" style="margin-bottom: 20px;">
      <el-col :xs="24" :sm="12" :md="8">
        <el-input v-model="articleSearch" placeholder="请输入文章标题" @keyup.enter="handleSearch"></el-input>
      </el-col>
      <el-col :xs="24" :sm="12" :md="4">
        <el-button type="primary" @click="handleSearch">搜索</el-button>
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
      <el-pagination
        background
        layout="total, prev, pager, next"
        :total="total"
        :page-size="pageSize"
        v-model:current-page="currentPage"
        @current-change="fetchData"
      />
    </div>
  </el-card>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getArticles } from '@/api/index.js'

const articleSearch = ref('')
const articleData = ref([])
const total = ref(0)
const currentPage = ref(1)
const pageSize = 10

const fetchData = async () => {
  try {
    const params = { page: currentPage.value, pageSize }
    if (articleSearch.value.trim()) {
      params.title = articleSearch.value.trim()
    }
    const res = await getArticles(params)
    if (res.data.success) {
      articleData.value = res.data.data.list
      total.value = res.data.data.total
    }
  } catch (e) {
    ElMessage.error('加载文章数据失败')
  }
}

const handleSearch = () => {
  currentPage.value = 1
  fetchData()
}

onMounted(() => {
  fetchData()
})
</script>
