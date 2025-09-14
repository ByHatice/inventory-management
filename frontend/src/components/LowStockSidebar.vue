<template>
  <div class="sidebar">
    <h5 class="text-danger">Lågt saldo</h5>

    <div v-if="items.length === 0" class="text-muted">
      Inga artiklar med lågt saldo
    </div>

    <div v-else>
      <div v-for="item in items" :key="item.id" class="sidebar-item">
        <strong>{{ item.name }}</strong><br>
        <span class="text-danger">{{ item.quantity }} {{ item.unit }}</span>

        <div class="update-section">
          <input
              v-model.number="quantities[item.id]"
              type="number"
              class="form-control form-control-sm"
              min="0"
          >
          <button
              @click="updateItem(item.id)"
              class="btn btn-sm btn-primary"
          >
            Uppdatera
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import apiService from '../services/api.js'

export default {
  name: 'LowStockSidebar',
  data() {
    return {
      items: [],
      quantities: {}
    }
  },
  async mounted() {
    await this.loadLowStockItems()
  },
  methods: {
    async loadLowStockItems() {
      try {
        this.items = await apiService.getLowStockItems(10)
        // Initiera quantities
        this.items.forEach(item => {
          this.quantities[item.id] = item.quantity
        })
      } catch (error) {
        console.error('Kunde inte ladda lågsaldo-artiklar:', error)
      }
    },

    async updateItem(itemId) {
      try {
        const newQuantity = this.quantities[itemId]
        await apiService.updateQuantity(itemId, newQuantity)
        // Ladda om listan
        await this.loadLowStockItems()
        // Emittera event så Home.vue kan uppdatera sin lista
        this.$emit('item-updated')
      } catch (error) {
        alert('Kunde inte uppdatera artikel')
      }
    }
  }
}
</script>