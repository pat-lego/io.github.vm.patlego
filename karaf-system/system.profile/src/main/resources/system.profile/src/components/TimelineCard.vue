<template>
  <div>
    <div class='title'>{{card.title}}</div>
    <div v-for='desc in card.description' :key='desc' class='content'>
      <div class='m-1 md:m-2' v-html="desc"></div>
    </div>
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
