<template>
  <div class="border-l-4 border-blue-600 min-h-full border-l-8">
      <timeline-card-component />
  </div>
</template>

<script>
import TimelineCard from '@/components/TimelineCard.vue'

export default {
  name: 'timeline-component',
  components: {
    'timeline-card-component': TimelineCard
  }
}
</script>
