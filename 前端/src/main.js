import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'bootstrap/dist/css/bootstrap.min.css';

Vue.prototype.$eventBus = new Vue();


Vue.prototype.$LoginUserId = 0;

Vue.config.productionTip = false



new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')



/* new Vue({
  el: '#app',
  router,
  template: '<router-view></router-view>'
}); */
