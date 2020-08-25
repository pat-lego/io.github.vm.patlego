<template>
    <div class='sm:p-0 md:p-4'>
        <div class='title'>{{card.title}}</div>
        <p slot='description' v-html="card.description" class='content'></p>
        <p class='time'>{{card.year}}</p>
    </div>
</template>
<script>
/**
  * @author pat-lego <patrique.legault@gmail.com>
  * @version 1.0
  * @description This component is used to display information within a timeline, it represents an entry within a timeline
  * @memberof components
*/
export default {
  name: 'timeline-card-component',
  props: {
    card: {
      type: Object,
      required: true
    }
  }
}
</script>
