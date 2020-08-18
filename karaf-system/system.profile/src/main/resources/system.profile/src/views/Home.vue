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
        <Card :card='personal.image' />
      </div>
      <div>
        <div class='p-6 w-3/4 flex'>
          <Profile :profile='personal.profile' />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Header from '@/components/Header.vue'
import Icon from '@/components/Icon.vue'
import Card from '@/components/Card.vue'
import Profile from '@/components/Profile.vue'

export default {
  name: 'Home',
  components: {
    Icon,
    Header,
    Card,
    Profile
  },
  data: () => {
    return {
      personal: {
        image: {
          imageUrl:
            'profile.jpg',
          imageAlt: 'Patrique Legault',
          imageTitle: 'This is me'
        },
        profile: {
          name: 'Patrique Legault'
        }
      }
    }
  }
}
</script>
