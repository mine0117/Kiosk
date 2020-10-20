<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
        <div class="right">
            <div class="search-input">
                <i class="fas fa-search"></i>
                <input v-model="keyword" type="text"/>
            </div> 
            
            <router-link v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="login-btn">
               <a
              v-if="!this.$cookies.isKey('Auth-Token')"
            
            >
              <i class="fas fa-sign-in-alt mr-1"></i>로그인
            </a> 
            </router-link>  
            
            <li class="nav-item">
            <a v-if="this.$cookies.isKey('Auth-Token')" @click="logout" class="nav-link mt-3 pl-1">
             로그아웃
            </a>
          </li>

        </div>  
        
    </div>
</template>   

<script> 
    import constants from '../../lib/constants'

    export default {
        name: 'Header',
        components: { 

        },
        props: ['isHeader'],
        computed:{
        },
        watch: {
        },
        created() {
        },
        methods : {

    logout: function() {
      this.$cookies.remove('Auth-Token');
      
      setTimeout(() => {
        this.$router.push('/').catch((err) => {
          console.log(err);
        });
        this.$router.go();
      }, 1000);
    }


        },
        data: function() {
           return {
               constants,
               keyword : "",
           }
        },

    }
</script>
