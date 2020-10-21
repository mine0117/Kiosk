import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCookies from 'vue-cookies';
import store from './store'

/* eslint-disable no-new */

Vue.use(VueCookies);
new Vue({
  el: '#app',
  router,
  store, 
  components: { App }, 
  template: '<App/>'
})
 