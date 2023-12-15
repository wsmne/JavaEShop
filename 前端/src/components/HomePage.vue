
<template>
  <div>
    <nav>
      <label>Buy</label> |
      <router-link to="/">登录</router-link> |
      <router-link to="/">注销</router-link> |
      <label>Admin</label>

    </nav>
    <header>
      <h1>电商网站</h1>
    </header>


    <section class="flex-container">
      <!-- 左侧 70% 显示商品列表 -->
      <div class="product-list">
        <h2>商品列表</h2>
        <table>
          <thead>
            <tr>
              <th>商品编号</th>
              <th>商品名称</th>
              <th>商品描述</th>
              <th>价格</th>
              <th>库存</th>
              <th>选择数量</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="product in products" :key="product.id">
              <td>{{ product.id }}</td>
              <td>{{ product.name }}</td>
              <td>{{ product.description }}</td>
              <td>{{ product.price }}</td>
              <td>{{ product.inventory }}</td>
              <td>
                <div>
                  <input type="number" v-model="product.quantity" min=1 :max=product.inventory style="width: 80px;">
                </div>
              </td>
              <td>
                <button @click="addToCart(product)">加入购物车</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- 右侧 30% 显示购物车 -->
      <div class="shopping-cart">
        <h2>购物车 UserId:{{ this.order.uid }}</h2>
        <table>
          <thead>
            <tr>
              <th>商品编号</th>
              <th>价格</th>
              <th>数量</th>
              <th>总价格</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in cart" :key="item.id">
              <td>{{ item.pid }}</td>
              <td>{{ item.price }}</td>
              <td>{{ item.quantity }}</td>
              <td>{{ item.quantity * item.price }}</td>
              <td>
                <button @click="del(item)">删除</button>|
                <button @click="checkout(item)">结算</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      products: [],
      cart: [],
      order: { // 用于收集新商品信息
        uid: null,
        pid: null,
        quantity: null,
        price: null,
      },
      uuu: null,
    };
  },
  created() {
    this.$eventBus.$on('updateLoginUserId', (newUserId) => {
      this.order.uid = newUserId;
      this.fetchCart();
    });
  },
  mounted() {
    // 在组件创建时调用 Axios 获取商品列表和购物车数据
    this.fetchProducts();
    // this.fetchCart();
  },
  methods: {
    addToCart(product) {
      this.order.quantity = parseInt(product.quantity);
      if (this.order.quantity > 0 && this.order.quantity < product.inventory && this.order.uid != 0) {
        this.order.pid = product.id;
        axios.post('http://47.113.199.228:8080/carts', this.order);
        this.order.pid = null;
        this.order.quantity = null;
        product.quantity = null;
        setTimeout(() => {
          this.fetchCart();
        }, 50);
      }
    },
    del(item) {
      const uid = this.order.uid;
      const pid = item.pid;

      // 发送 DELETE 请求
      axios.delete('http://47.113.199.228:8080/carts', {
        data: {
          uid: uid,
          pid: pid,
        }
      }).then(response => {
        // 请求成功的处理逻辑
        console.log(response.data);
        // 这里可以添加其他操作，例如更新页面或显示成功消息
        this.cart = this.cart.filter(cart => cart.pid !== item.pid);
      });

    },
    checkout(item) {
      // 发送 PUT 请求
      const uid = this.order.uid;
      const pid = item.pid;
      const quantity = item.quantity
      axios.put('http://47.113.199.228:8080/carts', {
        uid: uid,
        pid: pid,
        quantity: quantity,
        price: item.price
      }).then(response => {
        console.log('Order submitted successfully:', response.data);
        setTimeout(() => {
          this.fetchProducts();
          this.fetchCart();
        }, 100);

      });
    },
    fetchProducts() {
      axios.get('http://47.113.199.228:8080/goods')
        .then(response => {
          // 在这里处理响应数据
          this.products = response.data.data;
        });
    },
    fetchCart() {
      // 从后端获取购物车数据
      axios.get(`http://47.113.199.228:8080/carts/${this.order.uid}`)
        .then(response => {
          this.cart = response.data.data;
        });
    }
  }
}
</script>

<style>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
}

header {
  background-color: #333;
  color: #fff;
  padding: 10px;
  text-align: center;
}

nav {
  background-color: #555;
  color: #fff;
  padding: 10px;
  text-align: center;
}

section {
  padding: 20px;
}

article {
  margin-bottom: 20px;
  padding: 10px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}

/* 可以在这里添加样式，使用 scoped 可以确保样式只对当前组件生效 */

.flex-container {
  display: flex;
  justify-content: space-between;
  /* 使两个表格之间有空隙 */
}

.product-list {
  width: 65%;
}

.shopping-cart {
  width: 30%;
}




table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  border: 2px solid #ddd;
  /* 增粗表格的竖线 */
  padding: 10px;
  text-align: left;
  text-align: center;
  /* 居中对齐 */
}

th {
  background-color: #007bff;
  color: #fff;
}

/* 左侧表格边框颜色 */
.product-table {
  border: 2px solid #007bff;
}

/* 右侧表格边框颜色 */
.cart-table {
  border: 2px solid #28a745;
}

button {
  cursor: pointer;
  padding: 5px 10px;
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