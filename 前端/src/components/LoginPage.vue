<template>
  <div>
    <nav>
      <label>Buy</label> |
      <router-link to="/">登录</router-link> |
      <router-link to="/">注销</router-link> |
      <label>Admin</label>

    </nav>
    <h1>登录</h1>

    <body id="k">
      <form @submit.prevent="login">
        <h2>欢迎登录</h2>
        <label for="username">用户名:</label>
        <input v-model="username" type="text" id="username" name="username" required>

        <label for="password">密码:</label>
        <input v-model="password" type="password" id="password" name="password" required>
        <div style="align-items: center;display: flex;justify-content: center;">
          <button type="button" @click="signup">注册</button>
          <button type="submit" @click="login">登录</button>
        </div>

      </form>

      <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
    </body>
  </div>
</template>
  
<script>
import axios from 'axios';

export default {
  data() {
    return {
      username: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    signup() {
      if (this.username === '') {
        this.clearForm();
        alert("用户名不可为空");
        return;
      }
      if (this.password === '') {
        alert("密码不可为空");
        return;
      }
      axios.post('http://47.113.199.228:8080/login', {
        username: this.username,
        password: this.password
      })
        .then(response => {
          if (response.data.code == 1) {
            alert('注册失败:用户名重复');
          }
          else {
            axios.post(`http://47.113.199.228:8080/users`, {
              username: this.username,
              password: this.password
            })
            alert("注册成功")
          }
          this.clearForm();
        });
    },
    login() {
      if (this.username === '') {
        this.clearForm();
        alert("用户名不可为空");
        return;
      }
      axios.post('http://47.113.199.228:8080/login', {
        username: this.username,
        password: this.password
      })
        .then(response => {
          // 在这里处理响应数据
          if (response.data.code == 1) {
            // 路由到购买界面
            if (response.data.data.type == 0) {
              this.$eventBus.$emit('updateLoginUserId', response.data.data.id);
              if (this.$route.path !== '/buy') {
                this.$router.push('/buy');
              }
            } else if (response.data.data.type === 1) {
              if (this.$route.path !== '/admin') {
                // 只有在用户不在 '/admin' 路由上时才导航
                this.$router.push('/admin');
              }
            }
          }
          else {
            // 登录失败
            this.errorMessage = response.data.message; // 根据后端返回的错误信息处理
            alert('登录失败:用户名或密码错误');
            this.clearForm();
          }
        });

    },
    clearForm() {
      // 清空表单
      this.username = '';
      this.password = '';
    }
  }
};
</script>

<style>
#k {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50vh;
}

form {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 300px;
}

label {
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 16px;
  box-sizing: border-box;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  background-color: #007bff;
  color: #fff;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}


nav {
  padding: 30px;
  align-items: center;
  justify-content: center;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>


