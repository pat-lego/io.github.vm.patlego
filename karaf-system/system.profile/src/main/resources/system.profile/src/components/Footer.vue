<template>
    <div class="flex flex-row bg-gray-100 h-20">
        <div v-if="footer" class="flex flex-row items-center m-4">
            <div v-if="footer.github" class="pr-8">
                <a :href="footer.github" target="_blank"><font-awesome-icon class="text-4xl hover:text-blue-300" :icon="['fab', 'github']" /></a>
            </div>
            <div v-if="footer.twitter" class="pr-8">
                <a :href="footer.twitter" target="_blank"><font-awesome-icon class="text-4xl hover:text-blue-300" :icon="['fab', 'twitter']" /></a>
            </div>
            <div v-if="footer.linkedin" class="pr-8">
                <a :href="footer.linkedin" target="_blank"><font-awesome-icon class="text-4xl hover:text-blue-300" :icon="['fab', 'linkedin']" /></a>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  props: {
    footer: Object
  }
}
</script>
