import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
      kakaoId:'dldldldldl',
  },
  mutations: {
    kakaoIdUpdate(state,payload){
        state.kakaoId = payload
    }
  },
  actions: {
  },
  getters:{
      getKakaoId(state){
          console.log(state.kakaoId)
        return state.kakaoId;
      },
  },
  modules: {
  }
})