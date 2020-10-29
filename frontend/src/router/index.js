import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import MyPage from '../page/user/MyPage.vue'

// 포스트
import List from '../page/post/List.vue'
import Main from '../page/post/Main.vue'

Vue.use(Router) 
 
export default new Router({
  mode: 'history',
  routes: [   
    // 로그인/가입
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },

    // 회원가입 
    {
      path: '/user/join',
      // name: constants.URL_TYPE.USER.JOIN,
      name: 'join',
      component: Join
    },
    // { 
    //   path: '/user/signup',
    //   name: constants.URL_TYPE.USER.SIGNUP,
    //   component: Singup
    // },

    // 포스트
    { 
      path: '/list',
      name: constants.URL_TYPE.POST.LIST,
      component: List,
    },

    { 
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: Main,
    },
    // { 
    //   path: '/main',
    //   name: constants.URL_TYPE.POST.MAIN,
    //   component: Main,
    // },
    { // 마이페이지
      path: '/user/mypage',
      name: constants.URL_TYPE.USER.MYPAGE,
      // name: 'Join',
      component: MyPage
    },
  
  ]
})
