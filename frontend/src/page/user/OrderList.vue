<template>
  <div class="container">
    <div class="col-9" style="float:left;">
      <div
        class="row test  mb-3"
        style="height: 100px"
        v-for="(menu, i) in Mymenu"
        :key="i"
        data-holder-rendered="true"
      >
        <div class="col-1">{{ i + 1 }}</div>
        <!-- <div class="col-4"><b-img class="" :src="menu.image" height=""></b-img></div> -->
        <div class="col-2">{{ menu.orderdate }}</div>
        <div class="col-3">{{ menu.name }}</div>
        <div class="col-2">{{ menu.price }}원</div>

        <!-- <td><b-img :src="menu.image" width="100%"></b-img></td>
      <td style="padding-top:50px;">{{ menu.name }}</td>
      <td style="padding-top:50px;">{{ menu.price }}원</td> -->
        <!-- <td style="padding-top:50px;">{{ menu.orderdate }}</td> -->
      </div>
    </div>

    <div class="col-2" style="float:right;">
        많이 구매한 상점 내역<br>

        총액 : 
    
    </div>
  </div>
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";

const baseURL = constants.baseUrl;

export default {
  name: "OrderList",
  data() {
    return {
      form: {
        uid: "",
        email: "",
        name: "",
        gender: "",
        age: "",
        tel: "",
        learningfile: "",
      },
      Mymenu: [],
      MymenuDate: [],
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      // console.log("method - getUserInfo");

      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      console.log(axiosConfig);
      axios
        .post(`${constants.baseUrl}/authuser`, "", axiosConfig)
        .then((res) => {
          // console.log(res.data.uid);
          this.form = res.data;
          this.getUserOrderList2();
        })
        .catch((err) => console.log(err));
    },







    
    getUserOrderList() {
      axios
        .get(baseURL + "/get/orderlist", {
          params: { sid: 1, uid: this.form.uid },
        })
        .then((res) => {
          this.Mymenu = res.data.object;

          for (let i = 0; i < this.Mymenu.length; i++) {
            const day = this.MymenuDate[i].orderdate;
            this.Mymenu[i].orderdate = day;
          }
          console.log(this.Mymenu);
        })
        .catch((err) => console.log(err.response));
    },
    getUserOrderList2() {
      axios
        .get(baseURL + "/get/orderlist2", {
          params: { sid: 1, uid: this.form.uid },
        })
        .then((res) => {
          this.MymenuDate = res.data.object;
          this.getUserOrderList();
        })
        .catch((err) => console.log(err.response));
    },
  },
};
</script>

<style scoped>
.row > div {
  display: flex;
  align-items: center;
  justify-content: center;
}
.row {
  font-size: 20px;
}
.test {
  border: 3px solid black;
  border-radius: 20px;
}
</style>
