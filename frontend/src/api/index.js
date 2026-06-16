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
export const updateAvatar = (data) => api.post('/auth/avatar', data)

// Data APIs
export const getUsers = (params) => api.get('/users', { params })
export const getUserByName = (name) => api.get(`/users/${name}`)
export const updateProfile = (data) => api.put('/users/profile', data)
export const getArticles = (params) => api.get('/articles', { params })

// File API
export const uploadAvatar = (file) => {
  const formData = new FormData()
  formData.append('file', file)
  return api.post('/file/upload', formData, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}
