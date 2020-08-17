<template>
  <div>
    <div class='flex mb-4'>
      <div class='w-1/4'>
        <Icon />
      </div>
      <div class='w-3/4'>
        <Header />
      </div>
    </div>
    <div class='flex mb-4'>
      <div class='w-1/4 p-6'>
        <Card :card="personal.profile" />
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Icon from '@/components/Icon.vue'
import Card from '@/components/Card.vue'

export default {
  name: 'Home',
  components: {
    Icon,
    Header,
    Card
  },
  data: () => {
    return {
      personal: {
        profile: {
          imageUrl: 'https://drive.google.com/thumbnail?id=1kDBpd180V1WkBaoaRPunXG4w2PeSiZQO',
          imageAlt: 'Patrique Legault',
          imageTitle: 'This is me'
        }
      }
    }
  }
}
</script>
