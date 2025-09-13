<script setup lang="ts">
import { ref } from 'vue'
import { BApp } from 'bootstrap-vue-next'

const showAlert = ref<boolean>(true)
const showModal = ref<boolean>(false)
</script>

<template>
  <BApp>
    <div class="container mt-4">
      <h1>BootstrapVueNext Test</h1>

      <BButton variant="primary" @click="showAlert = !showAlert" class="me-2">
        Toggle Alert
      </BButton>

      <BButton variant="danger" @click="showModal = true">
        Visa Modal
      </BButton>

      <BAlert v-model="showAlert" variant="success" class="mt-3">
        Det fungerar! 🎉
      </BAlert>

      <BModal v-model="showModal" title="Test Modal">
        Bootstrap Modal fungerar också!
      </BModal>
    </div>
  </BApp>
</template>