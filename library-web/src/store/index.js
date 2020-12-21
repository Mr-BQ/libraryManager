import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    currentactive:0,
    subactive:'00'
  },
  mutations: {
    changeCurrentactive(state,payload){
      state.currentactive = payload
    },
    changeSubactive(state,payload){
      state.subactive = payload
    }
  },
  actions: {
  },
  modules: {
  }
})
