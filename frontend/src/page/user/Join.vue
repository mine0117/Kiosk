<template>
  <div class="user" id="join">
    <div class="wrapC table">
      <div class="middle">
        <h1>회원가입</h1>
        <div class="form-wrap">
          <div class="input-wrap">
            <input
              v-model="name"
              id="name"
              placeholder="이름을 입력해주세요"
              type="text"
            />
          </div>

          <div class="input-wrap">
            <input
              v-model="email"
              id="email"
              placeholder="이메일을 입력해주세요"
              type="text"
            />
          </div>

          <div class="input-wrap">
            <input
              v-model="gender"
              id="gender"
              placeholder="성별을 입력해주세요"
              type="text"
            />
          </div>
          <div class="input-wrap">
            <input
              v-model="age"
              id="age"
              placeholder="나이를 입력해주세요"
              type="text"
            />
          </div>
          <div class="input-wrap">
            <input
              v-model="tel"
              id="tel"
              placeholder="전화번호를 입력해주세요"
              type="text"
            />
          </div>
          <div class="input-wrap">
            <input
              v-model="learningfile"
              id="learningfile"
              placeholder="파일를 입력해주세요"
              type="text"
            />
          </div>
        </div>

        <label>
          <input v-model="isTerm" type="checkbox" id="term" />
          <span>약관에 동의합니다</span>
        </label>

        <span class="go-term">약관 보기</span>

        <button class="btn" @click="sendSignupFrom()">
          <span> 작성완료 </span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/user.scss";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  components: {},
  created() {},
  computed: {
    ...mapGetters(["getKakaoId",
    "getBaseUrl"]),
  },
  methods: {
    sendSignupFrom() {
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
        .post(`${this.getBaseUrl}/account/signup`, user)
        .then((Response) => {
          console.log(Response.data);
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


