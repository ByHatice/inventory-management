import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import AddItem from "@/views/AddItem.vue"

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
    }
  ]
})

export default router