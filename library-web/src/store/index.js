import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    subactive:'00'
  },
  mutations: {
    changeSubactive(state,payload){
      state.subactive = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
