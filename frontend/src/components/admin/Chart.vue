<template>
  <canvas id="planet-chart"></canvas>
</template>
<script>
import axios from "axios";
import constants from "@/lib/constants";
import Chart from "chart.js";
import planetChartData from "../../chart-data";

const baseURL = constants.baseUrl;
const ctx = document.getElementById('planet-chart')
export default {
  name: "Chart",
  data() {
    return {
      planetChartData: planetChartData,
      type: "line",
      data: {
        labels: [
          "1월",
          "2월",
          "3월",
          "4월",
          "5월",
          "6월",
          "7월",
          "8월",
          "9월",
          "10월",
          "11월",
          "12월",
        ],
        datasets: [
        //   {
        //     // one line graph
        //     label: "월별 매출액",
        //     data: [0, 0, 1, 2, 67, 62, 27, 14],
        //     backgroundColor: [
        //       "rgba(54,73,93,.5)", // Blue
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //       "rgba(54,73,93,.5)",
        //     ],
        //     borderColor: [
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //       "#36495d",
        //     ],
        //     borderWidth: 3,
        //   },

          { // another line graph
            label: '월별 매출액',
            data: [900, 910,100, 6.7,100, 116.4, 50.7, 49.2],
            backgroundColor: [
              'rgba(71, 183,132,.5)', // Green
            ],
            borderColor: [
              '#47b784',
            ],
            borderWidth: 3
          }
        ],
      },
      options: {
        responsive: true,
        lineTension: 1,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                padding: 25,
              },
            },
          ],
        },
      },
    };
  },
  created(){
    this.getMoneyInfo()
  },
   mounted(){
    this.createChart('planet-chart', this.planetChartData);
  },
  methods:{
    createChart(chartId, chartData) {
    const ctx = document.getElementById(chartId);
    
    const myChart = new Chart(ctx, {
      type: this.type,
      data: this.data,
      options: this.options,
    });
  },
  getMoneyInfo(){
    axios
    .get(`${baseURL}/admin/chart`)
    .then((res)=>{
        console.log(res)
    }).catch((err)=>{
        console.log(err)
    })
},
},

}
</script>
<style scoped>
</style>