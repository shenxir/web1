   new Vue({
        el:'#app',
        data(){
            return{
                theme:'blue',
                showUsers:false,
                showArticle:false,
                showHome:true,
                currentDate: new Date(),
                search:'',
                articleSearch:'',
                tableData:[
                    { id:1, name:'张三', age:20, email:'zhangsan@qq.com' },
                    { id:2, name:'李四', age:21, email:'lisi@qq.com' },
                    { id:3, name:'王五', age:22, email:'wangwu@qq.com' },
                    { id:4, name:'赵六', age:23, email:'zhaoliu@qq.com' },
                    { id:5, name:'小明', age:24, email:'xiaoming@qq.com' }
                ],
                articleData:[
                    { id:1, title:'Vue前端开发实战', author:'admin', time:'2025-01-01' },
                    { id:2, title:'ElementUI使用指南', author:'test', time:'2025-01-02' },
                    { id:3, title:'响应式布局设计', author:'editor', time:'2025-01-03' },
                    { id:4, title:'JavaScript基础语法', author:'writer', time:'2025-01-04' },
                    { id:5, title:'CSS高级技巧', author:'dev', time:'2025-01-05' }
                ]
            }
        },
        methods:{
            changeTheme(type){
                this.theme=type;
            },
            logout(){
                this.$message.success('退出成功，即将返回登录页');
                setTimeout(() => {
                    window.location.href = 'login.html';
                }, 1000);
            }
        }
    })