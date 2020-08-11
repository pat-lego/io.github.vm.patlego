<template>
  <div class="flex mb-4">
    <div class="w-1/4">
      <Icon />
    </div>
    <div class="w-3/4">
      <Header />
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Icon from '@/components/Icon.vue'

export default {
  name: 'Home',
  components: {
    Icon,
    Header
  }
}
</script>
