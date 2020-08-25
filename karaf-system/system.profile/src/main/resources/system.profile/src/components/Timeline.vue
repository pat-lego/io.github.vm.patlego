<template>
  <div class='border-l-4 border-blue-600 border-l-8 p-4 timeline'>
    <div>
      <div class='timeline-card p-1'>
        <timeline-card-component />
      </div>
      <div class='timeline-card p-1'>
        <timeline-card-component />
      </div>
    </div>
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
<style lang="scss" scoped>
  .timeline {
    width: 100%;
    max-width: 500px;
    text-align: left;
    .timeline-wrapper {
      position: relative
    }
  }
  .timeline-card {
    display: flex;
    justify-content: flex-start;
  }
</style>
