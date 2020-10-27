<template>
  <div class="container mt-5 p-0">
    <b-card no-body>
      <b-tabs
        align="center"
        active-nav-item-class="font-weight-bold text-uppercase"
        active-tab-class="font-weight-bold text-success"
        style="font-size: 40px"
      >
        <b-tab title="음료">
          <div>
            <b-tabs content-class="mt-3" pills style="font-size: 20px">
              <b-tab title="콜드 브루" @click="y = 1"></b-tab>
              <b-tab title="리저브" @click="y = 2"></b-tab>
              <b-tab title="에스프레소" @click="y = 3"></b-tab>
              <b-tab title="블론드" @click="y = 5"></b-tab>
              <b-tab title="프라푸치노" @click="y = 6"></b-tab>
              <b-tab title="블렌디드" @click="y = 7"></b-tab>
              <b-tab title="피지오" @click="y = 8"></b-tab>
              <b-tab title="티바나" @click="y = 9"></b-tab>
              <b-tab title="브루드 커피" @click="y = 10"></b-tab>
              <b-tab title="기타" @click="y = 11"></b-tab>
              <b-tab title="병음료" @click="y = 12"></b-tab>

              <br /><br />
              <div>
                <div
                  v-for="(menu, i) in menusCate1"
                  :key="i"
                  style="width: 32%; float: left"
                >
                  <!-- class="row col-3 m-2" -->
                  <div v-if="menu.category2 == y">
                    <div class="m-3">
                      <div>
                        <img
                          style="width: 100%"
                          :src="menu.image"
                          class="rounded-circle"
                        />
                      </div>
                      <div style="text-align: center; font-size:20px;">
                        {{ menu.name }}
                      </div>
                      <div style="text-align: center; font-size:20px;">
                        {{ menu.price }}원
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </b-tabs>
          </div>
        </b-tab>
        <b-tab title="푸드">
          <div>
            <b-tabs content-class="mt-3" pills style="font-size: 20px">
              <b-tab title="브레드" @click="y = 1"></b-tab>
              <b-tab title="케이크" @click="y = 2"></b-tab>
              <b-tab title="샌드위치&샐러드" @click="y = 3"></b-tab>
              <b-tab title="따뜻한 푸드" @click="y = 4"></b-tab>
              <b-tab title="과일&요거트" @click="y = 5"></b-tab>
              <b-tab title="스낵&미니디저트" @click="y = 6"></b-tab>
              <b-tab title="아이스크림" @click="y = 7"></b-tab>

              <br /><br />
              <div class="">
                <div
                  v-for="(menu, i) in menusCate2"
                  :key="i"
                  style="width: 32%; float: left"
                >
                  <!-- class="row col-3 m-2" -->
                  <div v-if="menu.category2 == y">
                    <div class="m-4">
                      <div>
                        <img
                          style="width: 100%"
                          :src="menu.image"
                          class="rounded-circle"
                        />
                      </div>
                      <div style="text-align: center; font-size:20px;">
                        {{ menu.name }}
                      </div>
                      <div style="text-align: center; font-size:20px;">
                        {{ menu.price }}원
                      </div>
                      <br /><br />
                    </div>
                  </div>
                </div>
              </div>
            </b-tabs>
          </div>
        </b-tab>
        <b-tab title="Thrid">
          <div>
            <b-tabs content-class="mt-3" pills>
              <b-tab title="First" active><p>I'm the first tab</p></b-tab>
              <b-tab title="Second">
                <button class="btn" @click="test2">히히힣</button>
              </b-tab>
            </b-tabs>
          </div>
        </b-tab>
        
      </b-tabs>
    </b-card>
  </div>
</template>
 
<script>
import axios from "axios";
import constants from "../../lib/constants";
import { BCarousel } from "bootstrap-vue";
const baseURL = constants.baseUrl;

export default {
  name: "List",
  components: { BCarousel },
  watch: {},
  data() {
    return {
      menusCate1: {},
      menusCate2: {},
      y: 1,
    };
  },
  created() {
    this.GetMenuInfo();
  },
  methods: {
    test() {
      axios
        .get(baseURL + "/test")
        .then((res) => console.log(res))
        .catch((err) => console.log(err.response));
    },
    GetMenuInfo() {
      axios
        .get(baseURL + "/branch/menu", { params: { sid: 1 } })
        .then((res) => {
          const menu = res.data.object;
          this.menusCate1 = menu.filter((cate) => cate.category1 == 1);
          this.menusCate2 = menu.filter((cate) => cate.category1 == 2);
          console.log(this.menusCate1);
        })
        .catch((err) => console.log(err.response));
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
* {
  font-family: "Jua", sans-serif;
  font-size: 13px;
}
.display {
  font-family: "Jua", sans-serif;
  font-size: 25px;
}
</style>