import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './assets/navbar.css'
import './assets/footer.css'
import './assets/lowStockBanner.css'
import './assets/home.css'

createApp(App).use(router).mount('#app')