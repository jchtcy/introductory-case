import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import tagsView from './modules/tagsView'
import auth from './modules/auth'
import permission from './modules/permission'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    permission,
    tagsView,
    auth
  },
  getters
})

export default store
