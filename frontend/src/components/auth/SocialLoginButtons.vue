<template>
  <button class="social-btn qq">
    <i class="fa-brands fa-qq"></i>
    QQ登录
  </button>
  <button class="social-btn wechat">
    <i class="fa-brands fa-weixin"></i>
    微信登录
  </button>
  <button class="social-btn alipay">
    <i class="fa-brands fa-alipay"></i>
    支付宝登录
  </button>
</template>

<style scoped>
.social-btn {
  width: 100%;
  height: 52px;
  border: none;
  border-radius: 10px;
  color: #ffffff;
  font-size: 16px;
  cursor: pointer;
  transition: 0.3s;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 12px;
}

.social-btn i {
  font-size: 20px;
}

.qq {
  background: #409EFF;
}

.wechat {
  background: #67C23A;
}

.alipay {
  background: #909399;
}

.social-btn:hover {
  transform: translateY(-2px);
  opacity: 0.9;
}
</style>