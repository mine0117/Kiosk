<template>
  <div class="user" id="join">
    <div id="app">
      <div class="container">
        <div class="row">
          <div
            class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3"
          >
            <h1>Signup</h1>
            <hr />
            <div class="form-group">
              <label for="firstName">Name</label>
              <input
                type="text"
                v-model="name"
                id="name"
                class="form-control"
                placeholder="이름을 입력해주세요"
              />
            </div>
            <div class="form-group">
              <label for="lastName">Email</label>
              <input
                type="text"
                v-model="email"
                id="email"
                placeholder="이메일을 입력해주세요"
                class="form-control"
              />
            </div>
            <div class="form-group">
              <label for="email">전화번호</label>
              <input
                type="text"
                v-model="tel"
                id="tel"
                class="form-control"
                placeholder="전화번호를 입력해주세요"
              />
            </div>
            <div class="form-group">
              <label for="lastName">나이</label>
              <input
                type="text"
                v-model="age"
                id="age"
                placeholder="나이를 입력해주세요"
                class="form-control"
              />
            </div>




            <div class="form-group">
              <label for="password">얼굴인식 해주세요</label>
              <input
                type="text"
                v-model="learningfile"
                id="learningfile"
                placeholder="파일를 입력해주세요"
                class="form-control"
              />
              <p></p>
            </div>
            <div class="row">
              <div
                class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 form-group"
              >
                <label for="yes">
                  <input
                    type="radio"
                    name="gender"
                    value="female"
                    id="gender"
                    v-model="gender"
                  />
                  남
                </label>
                <label for="no">
                  <input
                    type="radio"
                    name="gender"
                    value="male"
                    id="gender"
                    v-model="gender"
                  />
                  여
                </label>
              </div>
            </div>
          </div>
        </div>
        <label>
          <input v-model="isTerm" type="checkbox" id="term" />
          <span>약관에 동의합니다</span>
        </label>
        <button class="btn" @click="sendSignupFrom()">
          Submit!
        </button>
        
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/user.scss";
import axios from "axios";
import constants from '../../lib/constants';
import { mapGetters } from "vuex";

export default {
  components: {},
  created() {},
  computed: {
    ...mapGetters(["getKakaoId"]),
  },
  methods: {
    sendSignupFrom() {
      var kakaoToken = "";
      var user = {
        uid: this.getKakaoId,
        email: this.email,
        name: this.name,
        gender: this.gender,
        age: this.age,
        tel: this.tel,
        learningfile: this.learningfile,
      };
      axios
        .post(`${constants.baseUrl}/account/signup`, user)
        .then((Response) => {
          kakaoToken = Response.data;
          console.log(Response.data);
          this.$cookies.set("Auth-Token", kakaoToken);
          this.$router.push("/");
        })
        .catch((Error) => {
          console.log(Error);
        });
    },
  },
  watch: {},
  data: () => {
    return {
      uid: "",
      email: "",
      name: "",
      gender: "",
      age: "",
      tel: "",
      learningfile: "",
      isTerm: false,
    };
  },
};
</script>

<style>
#join.user {
  background: url(../../assets/img/123.jpg);

  background-repeat: no-repeat;

  background-size: auto;
}
</style>
