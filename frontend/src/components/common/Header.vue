<template>
  <div v-if="isHeader">
    <div>
      <b-navbar class="m-0" type="light" variant="light">
        <b-navbar-nav>
          <b-nav-item>Home</b-nav-item>

          <!-- Navbar dropdowns -->

          <b-nav-item-dropdown
            text="User"
            right
            v-if="this.$cookies.isKey('Auth-Token')"
          >
            <b-dropdown-item href="#">Account</b-dropdown-item>
            <b-dropdown-item href="#">Settings</b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item v-if="this.$cookies.isKey('Auth-Token')" @click="logout"
            >logout
          </b-nav-item>
        </b-navbar-nav>

        <a v-if="!this.$cookies.isKey('Auth-Token')">
          <a id="kakao-login-btn" @click="kakaojoin">
            <img
              src="../../assets/img/kk.png"
              style="width:50px"
              @click="kakaoInfoUpdate"
            />
          </a>
        </a>
      </b-navbar>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
// import "../../assets/css/Header-Blue.css";
const baseURL = constants.baseUrl;
export default {
  name: "Kakao",
  name: "Header",
  components: {},
  props: ["isHeader"],
  computed: {},
  watch: {},
  created() {},
  methods: {
    kakaoInfoUpdate: function(id) {
      this.$store.commit("kakaoIdUpdate", id);
    },

    kakaojoin() {
      let x = this;
      // var kakaotempToken = "";
      var a = 0;
      var kakaoToken = "";
      Kakao.Auth.login({
        success: function(authObj) {
          Kakao.API.request({
            url: "/v2/user/me",

            success: function(res) {
              x.kakao.uid = res.id;
              x.kakao.name = res.properties.nickname;
            
              axios
                .post(`${baseURL}/account/kakaologin`, x.kakao)
                .then((response) => {
                  console.log(response.data);
                  console.log("logger - check line");
                  if (response.data != "") {
                    kakaoToken = response.data;
                    x.$cookies.set("Auth-Token", kakaoToken);
                    console.log("logger - test111");
                    x.$router.go("/");
                    // x.$router.push({name: "main"}).catch((err) => {
                    //   console.log(err);
                    // });
                    console.log("logger - test222");
                  } else {
                    x.kakaoInfoUpdate(res.id);

                    x.$router.push({ name: "join" });
                  }
                })
                .catch((err) => console.log(err.response));
              console.log("hh");
            },
          });
        },

        fail: function(error) {
          alert(JSON.stringify(error));
        },
      });
      setTimeout(() => {
        console.log(a);
      }, 5);
    },

    logout: function() {
      this.$cookies.remove("Auth-Token");

      setTimeout(() => {
        this.$router.push("/").catch((err) => {
          console.log(err);
        });
        this.$router.go();
      }, 1000);
    },
  },

  data: function() {
    return {
      kakao: {
        uid: "",
        name: "",
      },
      constants,
      keyword: "",
    };
  },
};
</script>
