<template>
  <div class="home">
    <NavBar />

    <div class="container-fluid">
      <div class="row">
        <div class="col-3">
          <LowStockSidebar @item-updated="loadItems" />
        </div>

        <div class="col-9">
          <h2>Alla artiklar ({{ items.length }})</h2>

          <div v-if="loading">Laddar...</div>

          <div v-else class="row">
            <div v-for="item in items" :key="item.id" class="col-4 mb-3">
              <div class="card">
                <div class="card-body">
                  <h6>{{ item.name }}</h6>
                  <p>{{ item.quantity }} {{ item.unit }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <Footer />
  </div>
</template>

<script>
import NavBar from '../components/NavBar.vue'
import Footer from '../components/Footer.vue'
import LowStockSidebar from '../components/LowStockSidebar.vue'
import apiService from '../services/api.js'

export default {
  name: 'Home',
  components: {
    NavBar,
    Footer,
    LowStockSidebar
  },
  data() {
    return {
      items: [],
      loading: true
    }
  },
  async mounted() {
    await this.loadItems()
  },
  methods: {
    async loadItems() {
      try {
        this.loading = true
        this.items = await apiService.getAllItems()
      } catch (error) {
        console.error('Fel:', error)
      } finally {
        this.loading = false
      }
    }
  }
}
</script>