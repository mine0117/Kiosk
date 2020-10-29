<template>
  <div id="kakao-login-btn" @click="kakaojoin">
    <div>{{ this.$store.state.kakaoId }}</div>
    <div @click="kakaoInfoUpdate">jenkins 되냐 안되냐 돼라 제발 ㅇㅇㅇㅇㅇ</div>
    <img
      src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
      width="222"
    />
  </div>
</template>

<script>
import axios from "axios";
import constants from '../../lib/constants';


const baseURL = constants.baseUrl;

export default {
  name: "Kakao",
  components: {},
  computed: {},
  watch: {},
  created() {}, 
  methods: {
        kakaoInfoUpdate: function(id) {
        this.$store.commit('kakaoIdUpdate',id)
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
                  if (response.data != "") {
                    kakaoToken = response.data;
                    x.$cookies.set("Auth-Token", kakaoToken);
                    x.$router.push("/");
                  } else {        
                    x.kakaoInfoUpdate(res.id);
                    x.$router.push({ name: "join" });
                  }
                }).catch(err => console.log(err.response))
              
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
  },
  data: () => {
    return {
      kakao: {
        uid: "",
        name: "",
      },
    };
  },
};
</script>
