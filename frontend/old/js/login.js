new Vue({
    el: '#app',

    data() {
        return {
            loginForm: {
                username: '',
                password: ''
            }
        }
    },

    methods: {
        login() {
            if(this.loginForm.username === '' || this.loginForm.password === '') {
                this.$message.error('请输入用户名和密码');
                return;
            }

            this.$message.success('登录成功');

            setTimeout(() => {
                window.location.href = 'main.html';
            },1000)
        },
          
        goRegister(){

        window.location.href='register.html';
        },


        goForgot(){

            window.location.href='forgot.html';

        }
  
    }
})