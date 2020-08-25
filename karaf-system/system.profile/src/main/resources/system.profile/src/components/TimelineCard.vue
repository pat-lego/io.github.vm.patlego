<template>
    <div class='flex flex-col border-solid border-4 rounded-md'>
        <div class='flex flex-row pr-4 pl-4 pt-1 pb-1'>
            <div class="pr-1 bold">
              Name
            </div>
            <div>
              Patrique
            </div>
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
