        new Vue({
            el: '#app',
            data() {
                return {
                    form: {
                        username: '',
                        oldPassword: '',
                        newPassword: '',
                        confirmPassword: ''
                    },
                    rules: {} // 空规则，在created中赋值（修复this指向）
                }
            },
            created() {
                // 修复：正确绑定this的验证规则
                this.rules = {
                    username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
                    oldPassword: [{ required: true, message: '旧密码不能为空', trigger: 'blur' }],
                    newPassword: [{ required: true, message: '新密码不能为空', trigger: 'blur' }],
                    confirmPassword: [
                        { required: true, message: '确认密码不能为空', trigger: 'blur' },
                        { validator: this.checkPassword, trigger: 'blur' }
                    ]
                }
            },
            methods: {
                // 密码一致性验证（修复this指向）
                checkPassword(rule, value, callback) {
                    if (value !== this.form.newPassword) {
                        callback(new Error('两次输入的密码不一致'));
                    } else {
                        callback();
                    }
                },
                // 修改密码（点击生效+弹窗）
                submit() {
                    this.$refs.forgotForm.validate(valid => {
                        if (valid) {
                            this.$message.success('密码修改成功！即将返回登录页');
                            setTimeout(() => {
                                window.location.href = 'login.html';
                            }, 1000);
                        } else {
                            // 验证失败红色弹窗
                            this.$message.error('请完善表单信息后再提交！');
                        }
                    });
                },
                // 返回登录（点击立即生效）
                goLogin() {
                    this.$message.info('返回登录页面');
                    window.location.href = 'login.html';
                }
            }
        });