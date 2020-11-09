<template>
  <div class="container">
    <div class="h1 lsize text-center" @click="logout">
      Logout
    </div>
    <div class="row">
      <div class="col-lg">
        <div class="card text-center" style="width: 100%; height: 100%">
          <div class="card-body">
            <h1 class="card-title">오늘의 방문자 수</h1>
            <p class="card-text" style="font-size: 3em">
              {{ todayCount }}
            </p>
            <b-btn
              href="#"
              class="card-link"
              router-link
              :to="{ name: 'visithistory' }"
              >방문기록 확인</b-btn
            >
            <!-- <b-btn href="#" class="card-link">방문기록 확인</b-btn> -->
          </div>
        </div>
      </div>
      <div class="col-lg">
        <div class="card text-center" style="width: 100%; height: 100%">
          <div class="card-body text-secondary">
            <h1 class="card-title">메뉴 관리</h1>
            <!-- <h6 class="card-subtitle mb-2 text-muted"></h6> -->
            <p class="card-text">
              Some quick example text to build on the card title and make up the
              bulk of the card's content.
            </p>
            <b-btn
              href="#"
              class="card-link"
              router-link
              :to="{ name: 'menulist' }"
              >메뉴 관리</b-btn
            >
          </div>
        </div>
      </div>
      <div class="col-lg">
        <div class="card text-center" style="width: 100%; height: 100%">
          <div class="card-body">
            <h1 class="card-title">Card title</h1>
            <h6 class="card-subtitle mb-2 text-muted">Card subtitle</h6>
            <p class="card-text">
              Some quick example text to build on the card title and make up the
              bulk of the card's content.
            </p>
            <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg">
        <div class="card" style="width: 100%; height: 100%">
          <div class="card-body">
            <h5 class="card-title">매출액</h5>
            <h6 class="card-subtitle mb-2 text-muted">월별 매출액</h6>
            <chart></chart>
            <!-- <a href="#" class="card-link">Card link</a>
            <a href="#" class="card-link">Another link</a> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import constants from "@/lib/constants";
import Chart from "@/components/admin/Chart.vue";

const baseURL = constants.baseUrl;

export default {
  name: "DashBoard",
  components: {
    Chart,
  },
  data() {
    return {
      todayCount: 0,
    };
  },
  // beforeCreate() {
  //   const axiosConfig = {
  //     headers: {
  //       jwtToken: `${this.$cookies.get("Auth-Token")}`,
  //     },
  //   };
  //   axios
  //     .post(`${baseURL}/admin/isAdmin`, "", axiosConfig)
  //     .then((res) => {
  //       // console.log(res);
  //       if (res.data == false) {
  //         // this.$router.push({ name: "dashboard" });
  //         this.$router.push({ name: "forbidden" });
  //       }
  //     })
  //     .catch((err) => {
  //       console.log(err);
  //     });
  // },
  created() {
    this.isAdmin();
    this.getTodayVisitor();
  },
  mounted() {},
  methods: {
    isAdmin() {
      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      axios
        .post(`${baseURL}/admin/isAdmin`, "", axiosConfig)
        .then((res) => {
          console.log(res);
          if (res.data == false) {
            // this.$router.push({ name: "dashboard" });
            this.$router.push({ name: "forbidden" });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getTodayVisitor() {
      axios
        .get(`${baseURL}/admin/getvisitorcount`)
        .then((res) => {
          this.todayCount = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout() {
      this.$cookies.remove("Auth-Token");
      this.$router.push({name:"admin"})
  },
  }
}
</script>
<style scoped>
.container {
  background-color: white;
  width: 1000;
  margin: 60px auto;
  padding: 50px;
}
.row {
  margin-bottom: 50px;
}
.lsize{
  width:100px;
  margin: 20px auto;
  border: solid 0.5px black;
  background-color:green;
  color: white;
}
</style>