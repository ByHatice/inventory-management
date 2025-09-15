<template>
  <div class="home">
    <NavBar />

    <div class="container-fluid">
      <!-- Lågsaldo banner -->
      <LowStockBanner
          v-if="lowStockItems.length > 0"
          :low-stock-items="lowStockItems"
          :threshold="lowStockThreshold"
          @update-item="handleQuickUpdate"
          @dismiss="dismissBanner"
      />

      <!-- Huvudinnehåll -->
      <div class="main-content">
        <div class="d-flex justify-content-between align-items-center mb-4">
          <h2>Alla artiklar ({{ items.length }})</h2>
        </div>

        <div v-if="loading">Laddar...</div>

        <div v-else class="row">
          <div v-for="item in items" :key="item.id" class="col-lg-4 col-md-6 mb-3">
            <div class="card h-100" :class="{ 'border-warning': isLowStock(item) }">
              <div class="card-body">
                <div v-if="editingItem === item.id">
                  <!-- Redigeringsläge -->
                  <div class="mb-2">
                    <input
                        v-model.number="editForm.quantity"
                        type="number"
                        class="form-control form-control-sm"
                        placeholder="Antal"
                        min="0"
                    >
                  </div>
                  <div class="d-flex gap-2">
                    <button
                        @click="saveEdit(item.id)"
                        class="btn btn-sm btn-success"
                        :disabled="saving"
                    >
                      {{ saving ? 'Sparar...' : 'Spara' }}
                    </button>
                    <button
                        @click="cancelEdit"
                        class="btn btn-sm btn-secondary"
                    >
                      Avbryt
                    </button>
                  </div>
                </div>

                <div v-else>
                  <!-- Visningsläge -->
                  <div class="d-flex justify-content-between align-items-start mb-2">
                    <h6 class="card-title mb-0">{{ item.name }}</h6>
                    <span v-if="isLowStock(item)" class="badge bg-warning text-dark">Lågt</span>
                  </div>
                  <p class="card-text">
                    <strong class="fs-5">{{ item.quantity }}</strong>
                    <span class="text-muted">{{ item.unit }}</span>
                  </p>
                  <div class="d-flex gap-2">
                    <button
                        @click="startEdit(item)"
                        class="btn btn-sm btn-outline-primary"
                    >
                      Redigera antal
                    </button>
                    <button
                        @click="deleteItem(item.id)"
                        class="btn btn-sm btn-outline-danger"
                        :disabled="deleting === item.id"
                    >
                      {{ deleting === item.id ? 'Tar bort...' : 'Ta bort' }}
                    </button>
                  </div>
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
import LowStockBanner from '../components/LowStockBanner.vue'
import apiService from '../services/api.js'

export default {
  name: 'Home',
  components: {
    NavBar,
    Footer,
    LowStockBanner
  },
  data() {
    return {
      items: [],
      loading: true,
      editingItem: null,
      editForm: {
        name: '',
        quantity: null,
        unit: ''
      },
      saving: false,
      deleting: null,
      lowStockThreshold: 10,
      bannerDismissed: false
    }
  },
  computed: {
    lowStockItems() {
      if (this.bannerDismissed) return []
      return this.items.filter(item => this.isLowStock(item))
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
    },

    isLowStock(item) {
      return item.quantity < this.lowStockThreshold
    },

    startEdit(item) {
      this.editingItem = item.id
      this.editForm = {
        name: item.name,
        quantity: item.quantity,
        unit: item.unit
      }
    },

    cancelEdit() {
      this.editingItem = null
      this.editForm = {
        name: '',
        quantity: null,
        unit: ''
      }
    },

    async saveEdit(itemId) {
      try {
        this.saving = true

        if (!this.editForm.name.trim() || !this.editForm.unit.trim() || this.editForm.quantity < 0) {
          alert('Fyll i alla fält korrekt')
          return
        }

        await apiService.updateQuantity(itemId, this.editForm.quantity)

        const itemIndex = this.items.findIndex(item => item.id === itemId)
        if (itemIndex !== -1) {
          this.items[itemIndex].quantity = this.editForm.quantity
        }

        this.cancelEdit()
      } catch (error) {
        alert('Kunde inte spara ändringar')
      } finally {
        this.saving = false
      }
    },

    async deleteItem(itemId) {
      if (!confirm('Är du säker på att du vill ta bort denna artikel?')) {
        return
      }

      try {
        this.deleting = itemId
        await apiService.deleteItem(itemId)
        this.items = this.items.filter(item => item.id !== itemId)
      } catch (error) {
        alert('Kunde inte ta bort artikel')
      } finally {
        this.deleting = null
      }
    },

    async handleQuickUpdate(itemId, newQuantity) {
      try {
        await apiService.updateQuantity(itemId, newQuantity)
        const itemIndex = this.items.findIndex(item => item.id === itemId)
        if (itemIndex !== -1) {
          this.items[itemIndex].quantity = newQuantity
        }
      } catch (error) {
        alert('Kunde inte uppdatera artikel')
      }
    },

    dismissBanner() {
      this.bannerDismissed = true
    }
  }
}
</script>