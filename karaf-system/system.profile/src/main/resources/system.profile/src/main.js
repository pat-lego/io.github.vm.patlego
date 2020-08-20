import Vue from 'vue'
import App from './App.vue'
import router from './router'

import './assets/styles/index.css'

// Font Awesome
import './font-awesome.js'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
