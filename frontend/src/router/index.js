import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import NavBar from "@/components/NavBar.vue";
import AddItem from "@/views/AddItem.vue";

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
      component: AddItem
    },
    {
      path: '/{id}/quantity',
      name: 'quantity',
      component: NavBar
    },
    {
      path: '/items/products',
        name: 'product',
        component: NavBar
    }
  ]
})

export default router