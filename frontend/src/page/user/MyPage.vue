<template>
  <b-container>
    <h1 class="border-bottom border-secondary" style="text-align: center">
      <strong>MY PAGE</strong>
    </h1>
    <div>
      <b-card no-body>
        <b-tabs pills card vertical>
          <b-tab title="회원 정보" active><b-card-text><user-info /></b-card-text></b-tab>
          <b-tab title="구매 내역"><b-card-text><order-list /></b-card-text></b-tab>
        </b-tabs>
      </b-card>
    </div>

    <!-- <b-container>
      <b-row>
        <b-col class="border border-secondary text-center" md="3">
          <b-row>
            <b-col><router-link :to="{name:'userinfo'}">회원정보</router-link></b-col>
          </b-row>
            <b-row>
            <b-col><router-link :to="{name:'orderlist'}">구매내역</router-link></b-col>
          </b-row>
          
        </b-col>
        <b-col md="9"><router-view></router-view></b-col>
      </b-row>
    </b-container> -->
    <!-- <router-link :to="{name:'userinfo'}">회원정보</router-link>
    <router-link :to="{name:'orderlist'}">구매내역</router-link> -->
    
    <!-- <order-list></order-list>
    <user-info></user-info> -->
      
  </b-container>
</template>

<script>
import OrderList from '../../components/User/OrderList.vue'
import UserInfo from '../../components/User/UserInfo.vue'

export default {
  name: "MyPage",
  components:{
    OrderList,
    UserInfo,
  },
};
</script>

<style scoped>
.container {
  background-color: #faebd7;
  padding: 70px 30px 50px 30px;
}
h1{
  margin-bottom:50px;
}
</style>
