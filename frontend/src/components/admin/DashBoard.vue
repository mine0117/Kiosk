<template>
  <div class="overflow-auto">
    <p class="mt-3">Current Page: {{ currentPage }}</p>
    <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      hover
    >
      <template #cell(name)="data"> {{ data.item.name }} +III </template>
      <!-- <template #cell(name)="data">
        <b class="text-info">{{ data.sid }}</b>, <b>{{ data.value.name }}</b>
      </template> -->
      <template #cell(image)="data">
        <!-- <v-img :src="data.item.image" alt="" contain height="100px" width="150px">
        </v-img> -->
        <img :src="data.item.image" />
        <!-- {{ data.item.image }} +III -->
      </template>
    </b-table>

    <!-- <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination> -->

    <!-- <table>
        <colgroup>
            <col width="15%" />

            <col width="10%" />

            <col width="30%" />

            <col width="40%" />

            <col width="10%" />

        </colgroup>
        <thead>
            <tr>
                <td>지점번호</td>
                <td>메뉴번호</td>
                <td>메뉴이름</td>
                <td>메뉴설명</td>
                <td>메뉴가격</td>
                <td>메뉴사진</td>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item,i) in items" :key=i>
                <td>{{item.sid}}</td>
                <td>{{item.menuid}}</td>
                <td>{{item.name}}</td>
                <td>{{item.description}}</td>
                <td>{{item.price}}</td>
                <td>{{item.image}}</td>
            </tr>
        </tbody>
    </table> -->

    <div class="mt-3">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        align="center"
      ></b-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import constants from "../../lib/constants";

const baseURL = constants.baseUrl;

export default {
  name: "DashBoard",
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      menuAll: {},
      items: [
        // { id: 1, first_name: "Fred", last_name: "Flintstone" },
        // { id: 2, first_name: "Wilma", last_name: "Flintstone" },
        // { id: 3, first_name: "Barney", last_name: "Rubble" },
        // { id: 4, first_name: "Betty", last_name: "Rubble" },
        // { id: 5, first_name: "Pebbles", last_name: "Flintstone" },
        // { id: 6, first_name: "Bamm Bamm", last_name: "Rubble" },
        // { id: 7, first_name: "The Great", last_name: "Gazzoo" },
        // { id: 8, first_name: "Rockhead", last_name: "Slate" },
        // { id: 9, first_name: "Pearl", last_name: "Slaghoople" },
      ],
    };
  },
  created() {
    console.log("logger - Create DashBoard");
    this.GetMenuInfo();
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
  methods: {
    GetMenuInfo() {
      axios
        .get(`${baseURL}/branch/menu`, { params: { sid: 1 } })
        .then((res) => {
          console.log(res);
          this.items = res.data.object;
          //   const menu = res.data.object;
          //   this.menuAll = res.data.object;
          //   this.menusCate1 = menu.filter((cate) => cate.category1 == 1);
          //   this.menusCate2 = menu.filter((cate) => cate.category1 == 2);
          //   console.log(this.menusCate1);
        })
        .catch((err) => console.log(err.response));
    },
  },
};
</script>
<style scoped>
</style>