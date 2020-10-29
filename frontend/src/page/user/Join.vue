<template>
  <div class="user" id="join">
    <h1 style="text-align: center">회원가입</h1>

    <!-- 이름 -->
    <b-form-group
      id="input-group-1"
      label="Name"
      label-size="lg"
      label-for="input-1"
      valid-feedback="Good!"
      invalid-feedback="2글자 이상"
      
    >
      <b-input-group size="lg">
        <b-input-group-prepend is-text>
          <b-icon icon="person-fill"></b-icon>
        </b-input-group-prepend>
        <b-form-input
          id="input-1"
          v-model="name"
          required
          aria-describedby="input-live-help input-live-feedback"
          placeholder="Enter name"
        ></b-form-input>
      </b-input-group>
      <!-- <b-form-invalid-feedback id="input-live-feedback">
          2글자 이상
        </b-form-invalid-feedback> -->
    </b-form-group>

    <!-- 이메일 -->
    <b-form-group
      id="input-group-2"
      label="Email"
      label-size="lg"
      label-for="input-2"
      description="We'll never share your email with anyone else."
      valid-feedback="Good!"
      invalid-feedback="이메일 형식에 맞춰 쓰세요."
    >
      <b-input-group size="lg">
        <b-input-group-prepend is-text>
          <b-icon icon="envelope"></b-icon>
        </b-input-group-prepend>
        <b-form-input
          id="input-2"
          v-model="email"
          type="email"
          required
          placeholder="me@example.com"
        ></b-form-input>
      </b-input-group>
      <b-form-invalid-feedback id="input-live-feedback">
        Your email doesn't match an email form.
      </b-form-invalid-feedback>
    </b-form-group>

    <!-- 성별 -->
    <b-form-group
      id="input-group-3"
      label="Gender"
      label-size="lg"
      label-for="input-3"
    >
      <b-form-input
        id="input-3"
        v-model="gender"
        required
        placeholder="Enter gender"
      ></b-form-input>
    </b-form-group>

    <!-- 나이 -->
    <b-form-group
      id="input-group-4"
      label="Age"
      label-size="lg"
      label-for="input-4"
    >
      <b-form-input
        id="input-4"
        v-model="age"
        type="number"
        required
        placeholder="Enter age"
      ></b-form-input>
    </b-form-group>

    <!-- 전화번호 -->
    <b-form-group
      id="input-group-5"
      label="Tel"
      label-size="lg"
      label-for="input-5"
    >
      <b-input-group size="lg">
        <b-input-group-prepend is-text>
          <b-icon icon="telephone-fill"></b-icon>
        </b-input-group-prepend>
        <b-form-input
          id="input-5"
          v-model="tel"
          required
          placeholder="Enter tel"
        ></b-form-input>
      </b-input-group>
    </b-form-group>

    <!-- 파일 -->
    <b-form-group
      id="input-group-6"
      label="Images"
      label-size="lg"
      label-for="input-6"
    >
      <b-input-group size="lg">
        <b-input-group-prepend is-text>
          <b-icon icon="images"></b-icon>
        </b-input-group-prepend>
        <b-form-input
          id="input-6"
          v-model="learningfile"
          required
          placeholder="Enter images"
        ></b-form-input>
      </b-input-group>
    </b-form-group>

    <b-row>
      <b-col lg="4" class="pb-2"></b-col>
      <b-col lg="4" class="pb-2"
        ><b-button type="submit" variant="success">수정하기</b-button></b-col
      >
      <b-col lg="4" class="pb-2"></b-col>
    </b-row>

    <!-- <b-button type="reset" variant="danger">Reset</b-button> -->
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

<style scoped>
h1 {
  margin-bottom: 50px;
}
.container {
  background-color: #faebd7;
  /* padding: 0 30px 0 30px; */
}

@media (min-width: 768px) {
  .container {
    padding: 70px 30px 50px 30px;
    width: 700px;
  }
}

@media (min-width: 992px) {
  .container {
    padding: 70px 50px 50px 50px;
    width: 700px;
  }
}

#input-group-1 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
}

#input-group-2 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
  margin-bottom: 0;
}

#input-group-3 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
  margin-bottom: 0;
}

#input-group-4 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
  margin-bottom: 0;
}
#input-group-5 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
  margin-bottom: 0;
}
#input-group-6 {
  padding: 10px 20px 10px 20px;
  border: 1px solid gray;
  margin-bottom: 50px;
}

/* @media (min-width: 1200px) {
  .container {
    width: 700px;
  }
} */
</style>
