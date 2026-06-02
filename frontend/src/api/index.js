import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
  timeout: 5000,
  headers: { 'Content-Type': 'application/json' }
})

// Auth APIs
export const login = (data) => api.post('/auth/login', data)
export const register = (data) => api.post('/auth/register', data)
export const changePassword = (data) => api.post('/auth/changePassword', data)

// Data APIs
export const getUsers = () => api.get('/users')
export const getArticles = () => api.get('/articles')
