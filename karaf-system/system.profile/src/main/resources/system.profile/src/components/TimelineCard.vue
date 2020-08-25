<template>
    <div class='flex flex-row border-solid border-4 rounded-md'>
        <div>
            <h4>Name</h4>
            <p>Patrique</p>
        </div>
    </div>
</template>
<script>
export default {
  name: 'timeline-card-component',
  props: {
    cardInfo: {
      type: Object
    }
  }
}
</script>
