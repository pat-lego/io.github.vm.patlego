<template>
    <div class='p-4'>
        <div class='title'>What project?</div>
        <p class='content'>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>
        <p class='time'>2019</p>
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
    cardInfo: {
      type: Object
    }
  }
}
</script>
