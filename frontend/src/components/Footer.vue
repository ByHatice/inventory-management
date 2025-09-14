<template>
  <footer class="footer text-center mt-auto" >
    <div class="container">
      <p>Copyright &copy; {{ currentYear }}. All rights reserved.</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
  computed: {
    currentYear() {
      return new Date().getFullYear()
    }
  }
}
</script>