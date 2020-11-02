<template>
  <div class="container">
    <div class="menu menuEffects">
      <div class="menuContent">
        <div style="width :50%; float:left;" @click="Member()">회원</div>
        <div style="width :50%; float:left;">비회원</div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";

const baseURL = constants.baseUrl;
export default {
  name: "KioskMain",

  methods: {
    Member() {
      axios
        .get(baseURL + "/kiosk/recog")
        .then((Response) => {})
        .catch((Error) => {
          console.log(Error);
        });
    },
  },
};
</script>

<style scoped>
input {
  display: none;
}

.menu {
  position: fixed;
  width: 100vw;
  height: 100%;
  top: 0;
  left: 0;
  background: rgba(255, 135, 2);
}

.menu label {
  width: 30px;
  height: 30px;
  /* position: absolute; */
  right: 20px;
  top: 20px;
  background-size: 100%;
  cursor: pointer;
}

.menu .menuContent {
  position: relative;
  top: 50%;
  font-size: 100px;
  text-align: center;
  padding-bottom: 20px;
  margin-top: -170px;
}

.menu div {
  list-style: none;
  padding: 0;
  margin: 0 auto;
}

.menu div {
  display: block;
  color: white;
  text-decoration: none;
  transition: color 0.2s;
  font-family: Trebuchet MS;
  text-transform: uppercase;
  padding: 10px 0;
}

.menu div:hover {
  color: gray;
}
</style>
