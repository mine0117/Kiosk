<template>
  <div class = "bbd">
    <div>
      <b-sidebar id="sidebar-right" backdrop title="장바구니" right shadow width=400px>
        <div class="px-2 py-2">


          <div class="d-flex mb-4" v-for="(menu, k) in basket" :key="k">
            <!-- <th>
              <b-img
                :src="menu.image"
                class="rounded-circle image"
                width="140%"
              ></b-img>
            </th>
            <th class="px-3 py-3 container">
              <tr style="font-size: 25px">
                {{
                  menu.name
                }}
              </tr>
              <tr>
                <td style="font-size: 25px">
                {{
                  numberWithCommas(menu.price)
                }}원
                </td>
                <td>
                  <b-icon
                  icon="x-circle"
                  scale="2"
                  variant="danger"
                  @click="DeleteBasket(menu)"
                  class="click"
                ></b-icon>
                </td>
              </tr>

            </th>
            <br /><br />
          </div>

        </div>
        <div class="px-2">
          <p style="font-size: 40px">총 금액 : {{ numberWithCommas(basketPrice) }}원</p> 
          <div>
            <th>
            <td>
            <b-button variant ="success" style="width: 190px; height:60px; font-size:20px;" v-b-modal.modal-1>결제하기</b-button>
            </td>
            <td>
            <b-button  variant ="danger" style="width: 190px; height:60px; font-size:20px;" v-b-modal.modal-2>취소하기</b-button>
            </td>
            </th>
            
            <b-modal id="modal-1" centered size = "lg" style="font-size: 50px">
              <div>
                    <p class = "my-4" style="font-size:50px; text-align:center;">선택해 주세요</p>
                <th>
                  <div class="col-6">
                  <td style="text-align:center;">
                    <img src="@/assets/img/takeout.png" style="height: 40vh;" @click="purchase()" class = "hover">
                    <p class = "my-4" style="font-size:35px;">포장 주문</p>
                  </td>

                  <td style="text-align:center;">
                    <img src="@/assets/img/coffee.png" style="height: 40vh;" @click="purchase()" class = "hover">
                    <p class = "my-4" style="font-size:35px;">매장 식사</p>
                  </td>
                  </div>
                </th> 
              </div>
              <template #modal-footer="{cancel}">
                <b-button style="width:70px; height:50px; font-size:15px;" variant="danger" @click="cancel()">
                  Cancel
                </b-button>
                </template>
            </b-modal>

            <b-modal id="modal-2" centered button-size="lg" @ok="purchase()" style="font-size:50px;">
              <p class = "my-4" style="font-size:50px; text-align:center;">정말 취소 하시겠습니까?</p>
                <template #modal-footer="{ okcancel, cancel }">
                <b-button style="width:70px; height:50px; font-size:15px;" variant="success" @click="okcancel()">
                  OK
                </b-button>
                <b-button style="width:70px; height:50px; font-size:15px;" variant="danger" @click="cancel()">
                  Cancel
                </b-button>
                </template>
            </b-modal>
          </div>
        </div>

      </b-sidebar>
    </div>

    <div class="mt-1 p-0">
      <div id="body" >
        <div id="content">
          <b-card no-body>
            <b-tabs
              align="center"
              active-nav-item-class="font-weight-bold text-uppercase"
              active-tab-class="font-weight-bold text-success"
              style="font-size: 40px"
              >
              <b-tab title="최근먹은메뉴">
                <div>
                  <b-tabs content-class="mt-3" pills style="font-size: 20px">
                    <br /><br />

                    <div v-if="basketRecent.length > 0" class="row overflow container-fluid">
                      <div
                        v-for="(slide, index) in basketRecent"
                        :key="index"
                        style="width: 32%; float: left"
                      >
                        <div>
                          <div
                            class="m-3 hover"
                            @click="GetMenuId(slide)"
                            v-b-toggle.sidebar-right
                          >
                            <div style="text-align: center;">
                              <img
                                style="width: 70%"
                                :src="slide.image"
                                class="rounded-circle image"
                              />
                            </div>
                            <div style="text-align: center; font-size: 40px">
                              {{ slide.name }}
                            </div>
                            <div style="text-align: center; font-size: 40px">
                              {{ numberWithCommas(slide.price) }}원
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </b-tabs>
                </div>
              </b-tab>
              <b-tab @click="seperateCate(1, 1)" title="음료">
                <div>
                  <div>
                    <b-tabs
                      content-class="mt-5"
                      align="center"
                      pills
                      style="font-size: 30px"
                    >
                      <b-tab
                        title="콜드 브루"
                        @click="seperateCate(1, 1)"
                      ></b-tab>
                      <b-tab title="리저브" @click="seperateCate(1, 2)"></b-tab>
                      <b-tab
                        title="에스프레소"
                        @click="seperateCate(1, 3)"
                      ></b-tab>
                      <b-tab title="블론드" @click="seperateCate(1, 5)"></b-tab>
                      <b-tab
                        title="프라푸치노"
                        @click="seperateCate(1, 6)"
                      ></b-tab>
                      <b-tab
                        title="블렌디드"
                        @click="seperateCate(1, 7)"
                      ></b-tab>
                      <b-tab title="피지오" @click="seperateCate(1, 8)"></b-tab>
                      <b-tab title="티바나" @click="seperateCate(1, 9)"></b-tab>
                      <b-tab
                        title="브루드 커피"
                        @click="seperateCate(1, 10)"
                      ></b-tab>
                      <b-tab title="기타" @click="seperateCate(1, 11)"></b-tab>
                      <b-tab
                        title="병음료"
                        @click="seperateCate(1, 12)"
                      ></b-tab>


                      <div class="row overflow container-fluid">
                        <div
                          v-for="(menu, i) in menusCate"
                          :key="i"
                          class="col-4"
                        >
                          <div
                            class="m-3 hover"
                            v-b-toggle.sidebar-right
                            @click="GetMenuId(menu)"
                          >
                            <div style = "text-align: center;">
                              <img
                                style="width: 70%"
                                :src="menu.image"
                                class="rounded-circle image"
                              />
                            </div>
                            <div style="text-align: center; font-size: 40px">
                              {{ menu.name }}
                            </div>
                            <div style="text-align: center; font-size: 40px">
                              {{ numberWithCommas(menu.price) }}원
                            </div>
                          </div>
                        </div>
                      </div>


                    </b-tabs>
                  </div>
                </div>
              </b-tab>
              <b-tab @click="seperateCate(2, 1)" title="푸드">
                <div>
                  <b-tabs
                    content-class="mt-3"
                    align="center"
                    pills
                    style="font-size: 30px"
                  >
                    <b-tab title="브레드" @click="seperateCate(2, 1)"></b-tab>
                    <b-tab title="케이크" @click="seperateCate(2, 2)"></b-tab>
                    <b-tab
                      title="샌드위치&샐러드"
                      @click="seperateCate(2, 3)"
                    ></b-tab>
                    <b-tab
                      title="따뜻한 푸드"
                      @click="seperateCate(2, 4)"
                    ></b-tab>
                    <b-tab
                      title="과일&요거트"
                      @click="seperateCate(2, 5)"
                    ></b-tab>
                    <b-tab
                      title="스낵&미니디저트"
                      @click="seperateCate(2, 6)"
                    ></b-tab>
                    <b-tab
                      title="아이스크림"
                      @click="seperateCate(2, 7)"
                    ></b-tab>



                    <div class="row overflow container-fluid">
                      <div
                        v-for="menu in menusCate"
                        :key="menu.id"
                        class="col-4"
                      >
                        <div
                          class="m-2 hover"
                          v-b-toggle.sidebar-right
                          @click="GetMenuId(menu)"
                        >
                          <div style="text-align: center;">
                            <img
                              style="width: 70%"
                              :src="menu.image"
                              class="rounded-circle image"
                            />
                          </div>
                          <div style="text-align: center; font-size: 40px">
                            {{ menu.name }}
                          </div>
                          <div style="text-align: center; font-size: 40px">
                            {{ numberWithCommas(menu.price) }}원
                          </div>
                        </div>
                      </div>
                    </div>




                  </b-tabs>
                </div>
              </b-tab>
            </b-tabs>
          </b-card>
        </div>
      </div>
    </div>
  <footer>푸터</footer>
  </div>
  
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";

const baseURL = constants.baseUrl;

export default {
  name: "List",
  components: {},
  watch: {},
  computed: {
    basketCheck() {
      if (this.$route.params.basket == "") {
        return false;
      } else {
        return true;
      }
    },
  },
  data() {
    return {
      test: true,
      menusCate: {},
      menuAll: {},
      y: 1,
      basket: [],
      basketRecent: {},
      modalShow: false,
      basketPrice: 0,
      uid: "",
    };
  },
  created() {
    this.authUser();

    this.GetMenuInfo();
  },
  methods: {
    purchase() {
      console.log("logger - purchase out of method " + this.uid);
      var IMP = window.IMP;
      var msg;
      var b = this.basket;

      b[0].uid = this.uid;
      console.log(b);
      // var tempUid = this.uid;
      let x = this;
      IMP.init("imp40062977");
      IMP.request_pay(
        {
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "merchant_" + new Date().getTime(),
          name: "스타벅스 학하동점",
          amount: this.basketPrice,
          buyer_email: "iamport@siot.do",
          buyer_name: "스타벅스",
          buyer_tel: "010-1234-5678",
          buyer_addr: "서울특별시 강남구 삼성동",
          buyer_postcode: "123-456",
        },
        function (rsp) {
          if (rsp.success) {
            var msg = "결제가 완료되었습니다.";
            alert(msg);
            msg += "\n고유ID : " + rsp.imp_uid;
            msg += "\n상점 거래ID : " + rsp.merchant_uid;
            msg += "\n결제 금액 : " + rsp.paid_amount + "원";

            console.log(b);
            alert("logger - purchase inner " + b);
            axios
              .post(baseURL + "/create/order", b)
              .then((response) => {
                x.$router.push("/");
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            var msg = "결제에 실패하였습니다.";
            msg += "에러내용 : " + rsp.error_msg;
          }
          alert(msg);
        }
      );
    },
  
    authUser() {
      console.log("method - authUser");
      const axiosConfig = {
        headers: {
          jwtToken: `${this.$cookies.get("Auth-Token")}`,
        },
      };
      axios
        .post(`${constants.baseUrl}/authuser`, "", axiosConfig)
        .then((res) => {
          this.uid = res.data.uid;
          this.GetMenuListRecent();
        })
        .catch((err) => console.log(err));
    },
    GetMenuInfo() {
      axios
        .get(baseURL + "/branch/menu", { params: { sid: 1 } })
        .then((res) => {
          this.menuAll = res.data.object;
        })
        .catch((err) => console.log(err.response));
    },
    GetMenuId(res) {
      this.basketPrice += res.price;
      this.basket.push(res);
    },
    GetMenuListRecent() {
      axios
        .get(`${baseURL}/order/mymenu`, { params: { uid: this.uid, sid: 1 } })
        .then((res) => {
          this.basketRecent = res.data;
        })
        .catch((err) => console.log(err.response));
    },
    seperateCate(a, b) {
      var tmpCate = this.menuAll.filter((cate) => cate.category1 == a);
      this.menusCate = tmpCate.filter((cate) => cate.category2 == b);
    },
    DeleteBasket(Menu) {
      var tmpBasket = this.basket;
      const a = Menu.menuid;
      function findMenuid(bb) {
        return bb.menuid === a;
      }
      var i = tmpBasket.findIndex(findMenuid);
      this.basketPrice -= Menu.price;
      tmpBasket = tmpBasket.splice(i, 1);
    },
    okcancel() {
      this.$router.push("/");
    },
    numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  }
}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Jua&display=swap");
* {
  font-family: "Jua", sans-serif;
  font-size: 13px;
}
.hover:hover {
  background-color: #eee;
  cursor: pointer;
}
.click:hover {
  /* background-color: #ff0040; */
  cursor: pointer;
}
.overflow {
  height: 80vh;
  overflow-x: hidden;
  overflow-y: auto;
}
footer{width:100%;height:90px;background:#ddd;margin-top:auto}
.wrap{text-align:center;display:flex;flex-direction:column;height:100%}
</style>
