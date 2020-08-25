<template>
  <div class="w-64 h-full rounded-lg bg-gray-100 border hover:shadow-lg duration-500">
    <div class="flex flex-col items-center">
      <div class="p-4">
        <img :src="require('../assets/images/' + card.imageUrl)" :alt="card.imageAlt" />
      </div>
      <h4 class="font-bold text-center">{{card.imageTitle}}</h4>
    </div>
  </div>
</template>
<script>
export default {
  /**
   * @author pat-lego <patrique.legault@gmail.com>
   * @version 1.0
   * @description Displays an image within a given css frame
   * @memberof components
   */
  name: 'card-component',
  props: {
    card: {
      type: Object
    }
  }
}
</script>
