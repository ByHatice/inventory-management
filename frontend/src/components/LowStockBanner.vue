<template>
  <div class="alert alert-warning alert-dismissible fade show mb-4" role="alert">
    <div class="d-flex align-items-center mb-3">
      <i class="bi bi-exclamation-triangle-fill me-2"></i>
      <strong>Lågt saldo varning!</strong>
      <span class="ms-2">{{ lowStockItems.length }} artikel{{ lowStockItems.length !== 1 ? 'ar' : '' }} under {{ threshold }}</span>
      <button
          type="button"
          class="btn-close ms-auto"
          @click="$emit('dismiss')"
          aria-label="Stäng"
      ></button>
    </div>

    <div class="row">
      <div
          v-for="item in lowStockItems.slice(0, 6)"
          :key="item.id"
          class="col-lg-2 col-md-3 col-sm-4 mb-2"
      >
        <div class="low-stock-item">
          <div class="item-info">
            <strong class="item-name">{{ item.name }}</strong>
            <div class="item-quantity text-danger">
              Nuvarande: {{ item.quantity }} {{ item.unit }}
            </div>
          </div>
          <div class="quick-update mt-2">
            <label class="form-label small mb-1">Nytt antal:</label>
            <div class="input-group input-group-sm">
              <input
                  v-model.number="updateInputs[item.id]"
                  type="number"
                  class="form-control"
                  :placeholder="item.quantity"
                  min="0"
              />
              <button
                  @click="handleUpdate(item.id)"
                  class="btn btn-outline-success"
                  type="button"
                  title="Uppdatera antal"
              >
                Uppdatera
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="lowStockItems.length > 6" class="text-muted small mt-2">
      Och {{ lowStockItems.length - 6 }} till...
    </div>
  </div>
</template>

<script>
export default {
  name: 'LowStockBanner',
  props: {
    lowStockItems: {
      type: Array,
      required: true
    },
    threshold: {
      type: Number,
      default: 10
    }
  },
  data() {
    return {
      updateInputs: {}
    }
  },
  watch: {
    lowStockItems: {
      handler() {
        this.initializeInputs()
      },
      immediate: true
    }
  },
  methods: {
    initializeInputs() {
      this.updateInputs = {}
      this.lowStockItems.forEach(item => {
        this.updateInputs[item.id] = item.quantity
      })
    },

    handleUpdate(itemId) {
      const newQuantity = this.updateInputs[itemId]

      // Validering
      if (newQuantity < 0) {
        alert('Antal får inte vara negativt!')
        return
      }

      if (newQuantity === null || newQuantity === undefined || newQuantity === '') {
        alert('Ange ett giltigt antal!')
        return
      }

      this.$emit('update-item', itemId, newQuantity)
    }
  }
}
</script>