<template>
  <div v-if="profile" class="flex flex-col mb-4 p-6">
    <div v-if="profile.name" class="flex flex-row border-b-2">
        <div class="pr-4 italic">Name</div>
        <div class="font-bold">{{profile.name}}</div>
    </div>
    <div v-if="profile.career" class="flex flex-row pt-2">
        <div class="pr-4 italic">Email</div>
        <div class="font-bold">{{profile.email}}</div>
    </div>
    <div v-if="profile.number" class="flex flex-row">
        <div class="pr-4 italic">Number</div>
        <div class="font-bold">{{profile.number}}</div>
    </div>
    <div v-if="profile.career" class="flex flex-row">
        <div class="pr-4 italic">Career</div>
        <div class="font-bold">{{profile.career}}</div>
    </div>
    <div v-if="profile.location" class="flex flex-row">
        <div class="pr-4 italic">Location</div>
        <div class="font-bold">{{profile.location}}</div>
    </div>
    <div v-if="profile.experience" class="flex flex-row">
        <div class="pr-4 italic">Experience</div>
        <div v-for="exp in profile.experience" :key="exp.name" class="font-bold">
          <a class="pr-1 hover:text-blue-500" :href="exp.link" target="_blank">{{exp.name}}</a>
        </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    profile: Object
  },
  methods: {
    getExperience: function (experience) {
      if (experience) {
        return experience.toString()
      }
    }
  }
}
</script>
