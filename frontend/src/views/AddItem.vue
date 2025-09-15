<template>
  <div class="add-item-page">
    <NavBar />

    <div class="container" style="margin-top: 3rem; margin-bottom: 7rem;">
      <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
          <div class="card shadow-sm">
            <div class="card-header d-flex justify-content-between align-items-center">
              <h3 class="mb-0">Lägg till ny artikel</h3>
              <router-link to="/" class="btn-close" aria-label="Stäng"></router-link>
            </div>
            <div class="card-body">
              <form @submit.prevent="submitForm">
                <div class="mb-3">
                  <label for="name" class="form-label">Artikelnamn*</label>
                  <input
                      v-model="form.name"
                      id="name"
                      name="itemName"
                      type="text"
                      class="form-control"
                      placeholder="t.ex. Munskydd"
                      required
                  />
                  <div v-if="errors.name" class="invalid-feedback">
                    {{ errors.name }}
                  </div>
                </div>

                <div class="mb-3">
                  <label for="quantity" class="form-label">Antal*</label>
                  <input
                      v-model.number="form.quantity"
                      id="quantity"
                      name="itemQuantity"
                      type="number"
                      class="form-control"
                      placeholder="200"
                      min="0"
                      required
                  />
                  <div v-if="errors.quantity" class="invalid-feedback">
                    {{ errors.quantity }}
                  </div>
                </div>

                <div class="mb-4">
                  <label for="unit" class="form-label">Enhet*</label>
                  <input
                      v-model="form.unit"
                      id="unit"
                      name="itemUnit"
                      type="text"
                      class="form-control"
                      placeholder="st, kg, liter, etc."
                      required
                  />
                  <div v-if="errors.unit" class="invalid-feedback">
                    {{ errors.unit }}
                  </div>
                </div>

                <div class="d-grid gap-2">
                  <button
                      type="submit"
                      class="btn btn-primary"
                      :disabled="loading"
                  >
                    <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
                    {{ loading ? 'Sparar...' : 'Spara artikel' }}
                  </button>

                  <button
                      type="button"
                      class="btn btn-outline-secondary"
                      @click="resetForm"
                  >
                    Rensa formulär
                  </button>
                </div>
              </form>

              <div v-if="success" class="alert alert-success mt-3">
                <i class="bi bi-check-circle me-2"></i>
                Artikel skapad!
                <router-link to="/" class="alert-link">Gå tillbaka till listan</router-link>
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
import apiService from '../services/api.js'
import NavBar from '../components/NavBar.vue'
import Footer from '../components/Footer.vue'

export default {
  name: 'AddItem',
  components: {
    NavBar,
    Footer
  },
  data() {
    return {
      form: {
        name: '',
        quantity: null,
        unit: ''
      },
      errors: {},
      loading: false,
      success: false
    }
  },
  methods: {
    async submitForm() {
      this.errors = {}
      this.success = false

      if (!this.validateForm()) {
        return
      }

      try {
        this.loading = true
        await apiService.createItem(this.form)
        this.success = true
        this.resetForm()
      } catch (error) {
        alert(`Kunde inte skapa artikel: ${error.message}`)
      } finally {
        this.loading = false
      }
    },

    validateForm() {
      let isValid = true

      if (!this.form.name?.trim()) {
        this.errors.name = 'Artikelnamn är obligatoriskt'
        isValid = false
      }

      if (this.form.quantity === null || this.form.quantity < 0) {
        this.errors.quantity = 'Antal måste vara 0 eller högre'
        isValid = false
      }

      if (!this.form.unit?.trim()) {
        this.errors.unit = 'Enhet är obligatorisk'
        isValid = false
      }

      return isValid
    },

    resetForm() {
      this.form = {
        name: '',
        quantity: null,
        unit: ''
      }
      this.errors = {}
    }
  }
}
</script>