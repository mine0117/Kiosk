<template>
  <div class="p-3 mx-auto">
    <video id="video" width="480" height="360" autoplay></video>
    <canvas
      id="canvas"
      width="480"
      height="360"
      style="display: none;"
    ></canvas>
    <b-btn @click="snap">촬영</b-btn>
  </div>
</template>

<script>
import axios from "axios";
import constants from "../../lib/constants";
const baseURL = constants.baseUrl;
export default {
  name: "FaceCheck",
  data: () => {
    return {
      imagebase64: [],
      howmany: 5,
    };
  },
  mounted() {
    this.start();
  },
  methods: {
    start() {
      var video = document.getElementById("video");
      if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices
          .getUserMedia({ video: true })
          .then(function(stream) {
            video.srcObject = stream;
            video.play();
          });
      }
    },

    tmp(cnt) {
      var canvas = document.getElementById("canvas");
      var context = canvas.getContext("2d");
      context.drawImage(video, 0, 0, 480, 360);
      var dataURL = canvas.toDataURL();

      this.imagebase64.push(dataURL);
      setTimeout(() => {
        if (cnt == this.howmany - 1) {
          console.log(this.imagebase64);
          axios
            .post(baseURL + "/kiosk/recog", this.imagebase64)
            .then((response) => {
              this.imagebase64 = [];
            })
            .catch((err) => {
              console.log(err);
            });
        }
      }, 500);
    },

    snap() {
      for (let i = 0; i < this.howmany; i++) {
        this.tmp(i);
      }
    },
  },
};
</script>

<style scoped>
.cover-container {
  margin: 300px auto;
  background-color: rgba(0, 0, 0, 0.7);
  border-right: solid 3px black;
  border-bottom: solid 3px black;
}
span {
  margin: 0 auto;
}

.bcenter {
  width: 50%;
  margin: 0 auto;
}

</style>
