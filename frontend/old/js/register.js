new Vue({
    el:'#app',

    data() {
        // 🔥 自定义出生日期验证规则（核心：不能晚于本机当前时间）
        const checkBirthday = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请选择出生日期'));
            }
            // 获取当前本机时间
            const now = new Date();
            // 获取选择的出生日期
            const selectDate = new Date(value);
            if (selectDate > now) {
                callback(new Error('出生日期不能晚于当前时间'));
            } else {
                callback();
            }
        };

        return {
            registerForm: {
                username: '',
                email: '',
                birthday: '',
                password: ''
            },

            rules: {
                username: [
                    { required: true, message: '用户名不能为空', trigger: 'blur' }
                ],

                email: [
                    { required: true, message: '邮箱不能为空', trigger: 'blur' },
                    { type: 'email', message: '邮箱格式错误', trigger: 'blur' }
                ],

                // 🔥 应用自定义生日验证
                birthday: [
                    { validator: checkBirthday, trigger: 'change' }
                ],

                password: [
                    { required: true, message: '密码不能为空', trigger: 'blur' }
                ]
            }
        }
    },

    methods: {
        register() {
            this.$refs.registerForm.validate(valid => {
                if(valid) {
                    this.$message.success('注册成功');
                    // 🔥 注册成功1秒后跳转到登录页
                    setTimeout(() => {
                        window.location.href = 'login.html';
                    }, 1000);
                }
            })
        },
        // 🔥 新增返回登录页方法
        goLogin() {
            window.location.href = 'login.html';
        }
    }
})
