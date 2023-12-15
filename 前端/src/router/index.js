import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

import HomePage from '../components/HomePage.vue';
import LoginPage from '../components/LoginPage.vue';
import AdminPage from '../components/AdminPage.vue';

const routes = [
  /* {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import( '../views/AboutView.vue')
  } */
  { path: '/', name: 'login',component: LoginPage },
  { path: '/buy',name: '/buy', component: HomePage },
  { path: '/admin',name: 'admin', component: AdminPage },
  
]

const router = new VueRouter({
  routes
})




export default router
