import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies';
/* eslint-disable no-new */

Vue.use(VueCookies);
new Vue({
  el: '#app',
  router,
  store, 
  components: { App }, 
  template: '<App/>'
})
 