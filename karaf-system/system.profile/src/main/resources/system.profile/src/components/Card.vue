<template>
  <div class="w-48 rounded-lg bg-gray-100 border hover:shadow-lg duration-500">
    <div class="flex flex-col">
      <div class="p-4">
        <img :src="card.imageUrl" :alt="card.imageAlt" />
      </div>
      <h4 class="font-bold">{{card.imageTitle}}</h4>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    card: Object
  }
}
</script>
