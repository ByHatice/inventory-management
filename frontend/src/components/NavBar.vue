<script>
export default {
  name: 'NavBar',
  data() {
    return {
      searchTerm: '',
      navbarOpen: false
    }
  },
  methods: {
    handleSearch() {
      this.$root.$emit('navbar-search', this.searchTerm)
    },
    toggleNavbar() {
      this.navbarOpen = !this.navbarOpen
    }
  }
}
</script>


<template>
  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-2">
    <router-link to="/itmes" class="navbar-brand">
      <span class="ms-2">Lagerhantering</span>
    </router-link>

    <button
        class="navbar-toggler"
        type="button"
        @click="toggleNavbar"
        :aria-expanded="navbarOpen"
        aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse p-2" :class="{ show: navbarOpen }">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link to="/" class="nav-link" :class="{ active: $route.path === '/' }">
           Justera antal
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link" :class="{ active: $route.path === '/add' }">
            Lägg till artikel
          </router-link>
        </li>
      </ul>

      <form class="d-flex" @submit.prevent="handleSearch">
        <input
            v-model="searchTerm"
            class="form-control me-2"
            type="search"
            placeholder="Sök artiklar..."
            aria-label="Search"
        >
        <button class="btn btn-outline-light" type="submit">Sök</button>
      </form>
    </div>
  </nav>
  <!-- End Navbar -->
</template>