<template>
  <div>
    <Kakao class="kakao"/>
    <div class="sections-menu">
      <span
        class="menu-point"
        :class="{ active: activeSection == index }"
        @click="scrollToSection(index)"
        v-for="(offset, index) in offsets"
        :key="index"
      >
      </span>
    </div>
    <section class="fullpage">
      <div class="card bg-dark text-white" style="overflow: hidden;">
        <img src="@/assets/img/2.jpg" style="height: 100vh; opacity:0.3" alt="main">
        <div class="card-img-overlay">
          <p class="card-text maintext">키오스크<br />완성은<br />얼굴</p>
        </div>
      </div>
    </section>

    <section class="fullpage">
      
    </section>

    <section class="fullpage">
      <div class="card bg-dark text-white" style="overflow: hidden;">
        <img src="@/assets/img/1.png" style="height: 100vh; opacity:0.3" alt="main">
        <div class="card-img-overlay">
          <p class="card-text maintext">키오스크<br />완성은<br />얼굴</p>
        </div>
      </div>
    </section>

    <section class="fullpage">
      
    </section>
  </div>
</template>

<script>
// import axios from "axios";
import Kakao from  "../user/Kakao"

export default {
  // name: "main",
  components: {
    Kakao
  },
  data() {
    return {
      inMove: false,
      activeSection: 0,
      offsets: [0,1,2,3],
      touchStartY: 0,
    };
  },
  created() {
    this.calculateSectionOffsets();
    
    window.addEventListener('DOMMouseScroll', this.handleMouseWheelDOM);  // Mozilla Firefox
    window.addEventListener('mousewheel', this.handleMouseWheel, { passive: false }); // Other browsers
    
    window.addEventListener('touchstart', this.touchStart, { passive: false }); // mobile devices
    window.addEventListener('touchmove', this.touchMove, { passive: false }); // mobile devices
  },
  destroyed() {
    window.removeEventListener('mousewheel', this.handleMouseWheel, { passive: false });  // Other browsers
    window.removeEventListener('DOMMouseScroll', this.handleMouseWheelDOM); // Mozilla Firefox
    
    window.removeEventListener('touchstart', this.touchStart); // mobile devices
    window.removeEventListener('touchmove', this.touchMove); // mobile devices
  },
  methods: {
    calculateSectionOffsets() {
      let sections = document.getElementsByTagName('section');
      let length = sections.length;
      
      for(let i = 0; i < length; i++) {
        let sectionOffset = sections[i].offsetTop;
        this.offsets.push(sectionOffset);
      }
    },
    handleMouseWheel: function(e) {
      
      if (e.wheelDelta < 30 && !this.inMove) {
        this.moveUp();
      } else if (e.wheelDelta > 30 && !this.inMove) {
        this.moveDown();
      }
        
      e.preventDefault();
      return false;
    },
    handleMouseWheelDOM: function(e) {
      
      if (e.detail > 0 && !this.inMove) {
        this.moveUp();
      } else if (e.detail < 0 && !this.inMove) {
        this.moveDown();
      }
      
      return false;
    },
    moveDown() {
      this.inMove = true;
      this.activeSection--;
        
      if(this.activeSection < 0) this.activeSection = this.offsets.length - 1;
        
      this.scrollToSection(this.activeSection, true);
    },
    moveUp() {
      this.inMove = true;
      this.activeSection++;
        
      if(this.activeSection > this.offsets.length - 1) this.activeSection = 0;
        
      this.scrollToSection(this.activeSection, true);
    },
    scrollToSection(id, force = false) {
      if(this.inMove && !force) return false;
      
      this.activeSection = id;
      this.inMove = true;
      document.getElementsByTagName('section')[id].scrollIntoView({behavior: 'smooth'});
      
      setTimeout(() => {
        this.inMove = false;
      }, 400);
      
    },
    touchStart(e) {
      e.preventDefault();
      
      this.touchStartY = e.touches[0].clientY;
    },
    touchMove(e) {
      if(this.inMove) return false;
      e.preventDefault();
      
      const currentY = e.touches[0].clientY;
      
      if(this.touchStartY < currentY) {
        this.moveDown();
      } else {
        this.moveUp();
      }
      
      this.touchStartY = 0;
      return false;
    }
  },
};
</script>

<style scoped>
.fullpage {
  height: 100vh;
  width: 100%;
  background-color: #68929E;
}
.sections-menu {
  position: fixed;
  right: 1rem;
  top: 50%;
  transform: translateY(-50%);
}
.sections-menu .menu-point {
  width: 10px;
  height: 10px;
  background-color: #fff;
  display: block;
  margin: 1rem 0;
  opacity: 0.6;
  transition: 0.4s ease all;
}
.sections-menu .menu-point.active {
  opacity: 1;
  transform: scale(1.5);
}
.maintext {
  position: relative;
  top: 100px;
  left: 100px;
  font-size: 100px;
}
.kakao {
  position: fixed;
  top: 7vh;
  right: 7vw;
  z-index: 1;
}
</style>