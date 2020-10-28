<template>
  <div class="container" style="background-color: white">
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="Email address:"
        label-for="input-1"
        description="We'll never share your email with anyone else."
      >
      
        <b-form-input
          id="input-1"
          v-model="form.email"
          type="email"
          :state=emailState
          required
          placeholder="Enter email"
        ></b-form-input>
          <b-form-invalid-feedback id="input-live-feedback">
          Your email doesn't match an email form.
        </b-form-invalid-feedback>
      </b-form-group>
      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          :state="nameState"
          required
          aria-describedby="input-live-help input-live-feedback"
          placeholder="Enter name"
        ></b-form-input>

      </b-form-group>

      <b-form-group id="input-group-3" label="Your gender:" label-for="input-2">
        <b-form-input
          id="input-3"
          v-model="form.gender"
          required
          placeholder="Enter gender"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-4" label="Your age:" label-for="input-2">
        <b-form-input
          id="input-4"
          v-model="form.age"
          required
          placeholder="Enter age"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="Your tel:" label-for="input-2">
        <b-form-input
          id="input-5"
          v-model="form.tel"
          required
          placeholder="Enter tel"
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-6"
        label="Your learningfile:"
        label-for="input-2"
      >
        <b-form-input
          id="input-7"
          v-model="form.learningfile"
          required
          placeholder="Enter tel"
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary" :disabled="!(emailState&&nameState)">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>
<script>
import axios from "axios";
import constants from "../../lib/constants";

export default {
  name: "MyPage",
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
      show: true,
    };
  },
  created() {
    console.log("created - MyPage");
    this.getUserInfo();
  },
  computed: {
    emailState(){
    const regExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/

      return regExp.test(this.form.email)
    },
    nameState() {

      return this.form.name.length > 2 ? true : false;
    },
  },
  methods: {
    getUserInfo() {
      console.log("method - getUserInfo");

      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      console.log(axiosConfig);
      axios
        .post(`${constants.baseUrl}/authuser`, "", axiosConfig)
        .then((res) => {
          console.log(res.data.uid);
          this.form = res.data;
        })
        .catch((err) => console.log(err));
    },
    // template
    onSubmit(evt) {
      evt.preventDefault();

      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      console.log(this.form);
      axios
        .put(`${constants.baseUrl}/updateuser`, this.form, axiosConfig)
        .then((res) => {
          alert("정보가 수정되었습니다.");
        })
        .catch((err) => {
          console.log(err);
        });

      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.email = this.form.email;
      this.form.name = this.form.name;
      this.form.gender = this.form.gender;
      this.form.age = this.form.age;
      this.form.tel = this.form.tel;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>

<style scoped>
</style>