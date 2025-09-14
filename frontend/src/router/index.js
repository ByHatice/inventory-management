import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import NavBar from "@/components/NavBar.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/add',
      name: 'add',
      component: NavBar
    },
    {
      path: '/{id}/quantity',
      name: 'quantity',
      component: NavBar
    },
    {
      path: '/{id}/products',
        name: 'product',
        component: NavBar
    }
  ]
})

export default router