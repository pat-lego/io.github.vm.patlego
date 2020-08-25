<template>
  <div class="flex flex-row bg-gray-100 text-center h-6">
    <div class="mr-6 w-1/3">
      <router-link class="text-blue-500 hover:text-blue-800" to="/">Home</router-link>
    </div>
    <div class="mr-6 w-1/3">
      <a class="text-blue-500 hover:text-blue-800" href="https://drive.google.com/file/d/1biqtGZIjSAufZGDvYkyXvSPTl0yObzd0/view?usp=sharing" download>Resume</a>
    </div>
    <!-- <div class="mr-6 w-1/3">
      <a class="text-blue-500 hover:text-blue-800" href="#">Contact</a>
    </div> -->
  </div>
</template>
<script>
export default {
  /**
   * @author pat-lego <patrique.legault@gmail.com>
   * @version 1.0
   * @description This is the navbar component used within the site, it currently displays Home and Resume. Once the first page is complete then we will use the Contact us page
   * @memberof utils
   */
  name: 'nav-component'
}
</script>
