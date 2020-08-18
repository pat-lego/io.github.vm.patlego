<template>
    <div class="flex flex-col border">
        <div class="w-3/4">
            <div v-if="typeof profile.name !== undefined">
                <p>Name</p> <h4>{{profile.name}}</h4>
            </div>
        </div>
    </div>
</template>
<script>
export default {
  props: {
    profile: Object
  }
}
</script>
