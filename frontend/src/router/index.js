import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import MyPage from '../page/user/MyPage.vue'
import UserUpdate from '../page/user/UserUpdate.vue'
import UserInfo from '../components/User/UserInfo.vue'
import OrderList from '../components/User/OrderList.vue'

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
      component: MyPage
    },
    { // 유저업데이트
      path: '/user/update',
      name: constants.URL_TYPE.USER.USER_UPDATE,
      component: UserUpdate
    },
    { // 유저정보
      path: '/user/userinfo',
      name: constants.URL_TYPE.USER.USER_INFO,
      component: UserInfo
    },
    { // 구매내역
      path: '/user/orderlist',
      name: constants.URL_TYPE.USER.ORDER_LIST,
      component: OrderList
    },
  ]
})
