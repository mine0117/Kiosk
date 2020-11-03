<template>
  <div class="container">
    <p class="mt-3">Current Page: {{ currentPage }}</p>
    <add-menu></add-menu>
    <b-table
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      :fields="fields"
      small
      hover
    >
        <template #cell(actions)="row">
          <b-btn v-text="row.item.menuid">hhhhhhhhhhhhh</b-btn>
          <b-btn @click="test">ggggg</b-btn>
        </template>

      <template #cell(name)="data"> {{ data.item.name }} </template>
      <!-- <template #cell(name)="data">
        <b class="text-info">{{ data.sid }}</b>, <b>{{ data.value.name }}</b>
      </template> -->
      <template #cell(image)="data">
        <!-- <v-img :src="data.item.image" alt="" contain height="100px" width="150px">
        </v-img> -->
        <b-img :src="data.item.image" rounded />
        <!-- {{ data.item.image }} +III -->
      </template>

    </b-table>
    <div class="mt-3">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        align="center"
        pills
        size="lg"
      ></b-pagination>
    </div>
        <!-- Info modal -->
    <b-modal :id="infoModal.id" :title="infoModal.title" ok-only @hide="resetInfoModal">
      <pre>{{ infoModal.content }}</pre>
    </b-modal>
    
  </div>
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";
import AddMenu from '@/components/admin/AddMenu.vue'
const baseURL = constants.baseUrl;

export default {
  name: "UpdateMenu",
    components:{
    AddMenu,
  },
  data() {
    return {
      infoModal: {
          id: 'info-modal',
          title: '',
          content: ''
        },
        
      fields:[
        // {key:'삭제버튼'},
        { key: 'actions', label: 'Actions' },
        {
          key:'sid',
        label:'지점 번호'
        },
        {
          key:'menuid',
        label:'메뉴 번호'
        },
        {
          key:'name',
        label:'메뉴 이름'
        },
         {
          key:'description',
        label:'메뉴 설명'
        },
        {
          key:'price',
        label:'메뉴 가격'
        },
        {
          key:'image',
        label:'메뉴 번호'
        },
        {
          key:'kcal',
        // label:'kcal'
        },
        {
        key:'category1',
        label:'category1'
        },
        {
        key:'category2',
        label:'category2'
        },
      
      ],
      perPage: 10,
      currentPage: 1,
      menuAll: {},
      items: [
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
    test(){
      alert('hi!')
    },
    info(item, index, button) {
        this.infoModal.title = `Row index: ${index}`
        this.infoModal.content = JSON.stringify(item, null, 2)
        this.$root.$emit('bv::show::modal', this.infoModal.id, button)
      },
            resetInfoModal() {
        this.infoModal.title = ''
        this.infoModal.content = ''
      },
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
.container {
  width: 1400px;
  margin: 100px auto;
  padding: 20px;
}
img {
  width: 100px;
}
</style>