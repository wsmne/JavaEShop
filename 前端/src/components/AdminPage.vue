<template>
  <div>
    <nav>
      <label>Buy</label> |
      <router-link to="/">登录</router-link> |
      <router-link to="/">注销</router-link> |
      <label>Admin</label>

    </nav>
    <header>
      <h1>电商系统管理</h1>
      <div class="management-buttons">
        <!-- 商品、用户、订单管理按钮 -->
        <button @click="switchView('products')">商品管理</button>
        <button @click="switchView('users')">用户管理</button>
        <button @click="switchView('orders')">成交单</button>
      </div>
    </header>
    <div class="maxi-container">
      <div v-show="currentView === 'products'" class="main-container">
        <!-- 左侧商品信息表 -->
        <div class="products-list">
          <div style="display: flex; align-items: center;">
            <label style=" width:120px;" for="search">查询:</label>
            <input type="text" v-model="searchQuery" placeholder="为空则查询全部">
            <button style=" width:120px;" @click="searchGoods">查询</button>
          </div>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>商品名称</th>
                <th>分类</th>
                <th>描述</th>
                <th>价格</th>
                <th>库存</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <!-- 商品数据行，你可以从后端获取数据来动态生成这些行 -->
              <tr v-for="product in products" :key="product.id">
                <td>{{ product.id }}</td>
                <td>{{ product.name }}</td>
                <td>{{ product.kind }}</td>
                <td>{{ product.description }}</td>
                <td>{{ product.price }}</td>
                <td>{{ product.inventory }}</td>
                <td>
                  <button @click="editId(product.id)">编辑</button>|<button @click="deleteItem(product.id)">删除</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

        <!-- 右侧添加商品的表单 -->
        <div class="add-product-form">
          <!-- 显示 "Open Form" 按钮 -->
          <label>操作商品</label>
          <!-- 弹窗表单 -->
          <div>
            <div>
              <!-- 表单内容 -->
              <form id="aa">
                <label for="name">Name:</label>
                <input type="text" v-model="Product.name" required>

                <label for="kind">Kind:</label>
                <input type="text" v-model="Product.kind" required>

                <label for="description">Description:</label>
                <input type="text" v-model="Product.description" required>

                <label for="price">Price:</label>
                <input type="number" v-model="Product.price" required>

                <label for="inventory">Inventory:</label>
                <input type="number" v-model="Product.inventory" required>


                <div class="form-row">
                  <label for="id">id(仅作修改使用，选择左侧编辑按钮，输入信息后自动添加)</label>
                  <input type="text" v-model="Product.id" required readonly>
                  <button type="button" @click="clearForm()">清空</button>
                  <button type="button" @click="editItem()" :disabled="!Product.id">修改</button>
                  <button type="submit" @click="addItem()" :disabled="!isValidForm()">增加</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div v-show="currentView === 'users'">
        <div class="users-list">
          <div style="display: flex;align-items: center;">
            <label style=" width:170px;" for="search">按用户名搜索:</label>
            <input type="text" v-model="searchQuery2" placeholder="为空则查询全部">
            <button style=" width:120px;" @click="searchUser">查询</button>
          </div>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>用户名</th>
                <th>密码</th>
                <th>身份</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <!-- 商品数据行，你可以从后端获取数据来动态生成这些行 -->
              <tr v-for="user in users" :key="user.id">
                <td>{{ user.id }}</td>
                <td>{{ user.username }}</td>
                <td>{{ user.password }}</td>
                <td>{{ user.type === 1 ? '管理员' : '普通用户' }}</td>
                <td>
                  <button @click="deleteUserById(user.id)">删除</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

      </div>

      <div v-show="currentView === 'orders'" class="main-container">

        <div class="products-list">
          <table>
            <thead>
              <tr>
                <th>用户ID</th>
                <th>商品ID</th>
                <th>单价</th>
                <th>数量</th>
                <th>总价</th>
              </tr>
            </thead>
            <tbody>
              <!-- 商品数据行，你可以从后端获取数据来动态生成这些行 -->
              <tr v-for="order in orders" :key="order.uid">
                <td>{{ order.uid }}</td>
                <td>{{ order.pid }}</td>
                <td>{{ order.price }}</td>
                <td>{{ order.quantity }}</td>
                <td>{{ order.quantity * order.price }}</td>
              </tr>
            </tbody>
          </table>
        </div>

      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios'
export default {
  data() {
    return {
      products: [],
      users: [],
      orders: [],
      Product: { // 用于收集新商品信息
        name: '',
        kind: '',
        description: '',
        price: null,
        inventory: null
      },
      searchQuery: '',
      searchQuery2: '',
      currentView: 'products',
    }
  },
  mounted() {
    // 当组件挂载时调用此函数
    this.fetchGoodsData();
    this.fetchUsersData();
    this.fetchOrdersData();
  },
  methods: {
    switchView(view) {
      // 切换显示的内容
      this.currentView = view;
    },
    fetchGoodsData() {
      // 向47.113.199.228:8080/goods发送GET请求
      axios.get('http://47.113.199.228:8080/goods')
        .then(response => {
          // 在这里处理响应数据
          this.products = response.data.data;
        });
    },
    fetchUsersData() {
      axios.get('http://47.113.199.228:8080/users')
        .then(response => {
          // 在这里处理响应数据
          this.users = response.data.data;
        });
    },
    fetchOrdersData() {
      axios.get('http://47.113.199.228:8080/deals')
        .then(response => {
          // 在这里处理响应数据
          this.orders = response.data.data;
        });
    },
    editItem(productId) {
      // 编辑操作，你可以在这里添加相关逻辑
      console.log('编辑操作，产品信息:', productId);
      // 发送 PUT 请求
      axios.put(`http://47.113.199.228:8080/goods`, this.Product).then(response => {
        console.log('Form submitted successfully:', response.data);
        this.fetchGoodsData();
        this.Product = {
          name: '',
          kind: '',
          description: '',
          price: null,
          inventory: null
        };
      });
    },
    clearForm() {
      this.Product = {
        name: '',
        kind: '',
        description: '',
        price: null,
        inventory: null
      };
    },
    deleteItem(productId) {
      if (confirm('确定要删除吗？')) {
        // 使用 Axios 发送 DELETE 请求
        axios.delete(`http://47.113.199.228:8080/${productId}`)
          .then(response => {
            // 请求成功的处理逻辑
            console.log(response.data);
            // 这里可以添加其他操作，例如更新页面或显示成功消息
            this.products = this.products.filter(product => product.id !== productId);
          })
          .catch(error => {
            // 请求失败的处理逻辑
            console.error(error);
            // 这里可以添加其他操作，例如显示错误消息
          });
      }
    },
    deleteUserById(userId) {
      if (confirm('确定要删除吗？')) {
        // 使用 Axios 发送 DELETE 请求
        axios.delete(`http://47.113.199.228:8080/users/${userId}`)
          .then(response => {
            // 请求成功的处理逻辑
            console.log(response.data);
            // 这里可以添加其他操作，例如更新页面或显示成功消息
            this.users = this.users.filter(user => user.id !== userId);
          });
      }
    },
    addItem() {
      // 处理表单提交逻辑...
      axios.post('http://47.113.199.228:8080/goods', this.Product)
        .then(response => {
          console.log('Form submitted successfully:', response.data);
          // 可以在这里添加其他操作，如关闭表单等
          this.fetchGoodsData();
          this.Product = {
            name: '',
            kind: '',
            description: '',
            price: null,
            inventory: null
          };
        });
    },
    isValidForm() {
      // 检查除 id 以外的所有字段是否有值
      return (
        this.Product.name !== '' &&
        this.Product.kind !== '' &&
        this.Product.description !== '' &&
        this.Product.price !== null &&
        this.Product.inventory !== null
      );
    },
    editId(productId) {
      // 根据 productId 从 products 中找到对应商品
      this.Product.id = productId;
    },
    searchGoods() {
      if (this.searchQuery == '') this.fetchGoodsData();
      else {
        const yourPathVariable = this.searchQuery; // 替换为实际的路径变量值
        axios.get(`http://47.113.199.228:8080/${yourPathVariable}`)
          .then(response => {
            // 处理响应数据，更新 products 列表
            this.products = response.data.data;
          });
      }
    },
    searchUser() {
      if (this.searchQuery2 == '') this.fetchUsersData();
      else {
        const yourPathVariable = this.searchQuery2; // 替换为实际的路径变量值
        axios.get(`http://47.113.199.228:8080/users/${yourPathVariable}`)
          .then(response => {
            // 处理响应数据，更新 users 列表
            this.users = response.data.data;
          });
      }
    }
  }
}
</script>


<style scoped>
.main-container {
  display: flex;
}

.products-list {
  flex: 7;
  /* 占 70% 宽度 */
  padding: 20px;
}

.add-product-form {
  flex: 3;
  /* 占 30% 宽度 */
  padding: 20px;
}

.add-product-form button {
  margin-bottom: 10px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
  /* 将文本居中 */
}

th {
  background-color: #007bff;
  color: #fff;
}

#aa {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  position: relative;
  width: 400px;
  margin-left: auto;
  /* 将表单右移一些 */
  margin-right: auto;
}

.form-row label,
.form-row input,
.form-row button {
  margin-bottom: 0;
  /* 取消默认的垂直间距 */
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
