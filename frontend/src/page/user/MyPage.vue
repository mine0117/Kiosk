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
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Your Name:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          required
          placeholder="Enter name"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Your gender:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.gender"
          required
          placeholder="Enter gender"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-4" label="Your age:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.age"
          required
          placeholder="Enter age"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="Your tel:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.tel"
          required
          placeholder="Enter tel"
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-5"
        label="Your learningfile:"
        label-for="input-2"
      >
        <b-form-input
          id="input-2"
          v-model="form.learningfile"
          required
          placeholder="Enter tel"
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
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
          uid: '',
          email: '',
          name: '',
          gender: '',
          age: '',
          tel: '',
          learningfile:'',
        },
        show: true
    };
  },
  created() {
    console.log("created - MyPage");
    this.getUserInfo();
  },
  methods: {
    getUserInfo() {
      console.log("method - getUserInfo");

      // const axiosConfig = {
      //   jwtToken: `eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDM3NzczMzQsInVzZXIiOnsidWlkIjoxNTA3NTAwMTg3LCJuYW1lIjoiamlueW9uZyIsImFnZSI6MH19.PDxySyxTHGydmFczoapzKvRPIRGiVY-iZl0XKT2hhxs`,
      // }
      //   const axiosConfig = {
      //     jwtToken:
      //       "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDM3OTQxMzksInVzZXIiOnsidWlkIjoxNTA3NTAwMTg3LCJuYW1lIjoiamlueW9uZyIsImFnZSI6MH19.bBTQzWK5gBcdA9UhgEuLj3LzFHUt0n5FHtmoiGENNqc",
      //   };
      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      console.log(axiosConfig);
      axios
        .post(`${constants.baseUrl}/authuser`,'',axiosConfig)
        .then((res) => {
          console.log(res.data.uid);
          this.form = res.data
        //   this.form.email = res.data.email;
        //   this.form.name = res.data.name;
        //   this.form.age = res.data.age;
        //   this.form.res.data.tel);
        //   console.log(res.data.gender);

        })
        .catch((err) => console.log(err));
    },
    // template
    onSubmit(evt) {
        evt.preventDefault()
              
              const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      console.log(this.form)
        axios.put(`${constants.baseUrl}/updateuser`,this.form,axiosConfig)
        .then((res)=>{
                alert('정보가 수정되었습니다.')
        })
        .catch((err)=>{
            console.log(err)
        })


        alert(JSON.stringify(this.form))
      },
      onReset(evt) {
        evt.preventDefault()
        // Reset our form values
        this.form.email = this.form.email
        this.form.name = this.form.name
        this.form.gender = this.form.gender
        this.form.age = this.form.age
        this.form.tel = this.form.tel 
        // Trick to reset/clear native browser form validation state
        this.show = false
        this.$nextTick(() => {
          this.show = true
        })
      }
  },
};
</script>

<style scoped>
</style>