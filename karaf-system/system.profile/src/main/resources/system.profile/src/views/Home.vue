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
    <div class='flex flex-row'>
        <div class="w-1/2 flex justify-center items-center">
          <Card :card='personal.image' />
        </div>
        <div class="w-1/2 flex items-center">
          <Profile :profile='personal.profile' />
        </div>
    </div>
    <div class="relative h-40">
      <div class="absolute inset-x-0 bottom-0">
        <Footer />
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
import Footer from '@/components/Footer.vue'

export default {
  name: 'Home',
  components: {
    Icon,
    Header,
    Card,
    Profile,
    Footer
  },
  data: () => {
    return {
      personal: {
        image: {
          imageUrl: 'profile.jpg',
          imageAlt: 'Patrique Legault',
          imageTitle: 'This is me'
        },
        profile: {
          name: 'Patrique Legault',
          career: 'Developer',
          location: 'Ottawa',
          email: 'patrique.legault@gmail.com',
          number: '613.914.2477',
          experience: [
            {
              name: 'Java'
            },
            {
              name: 'Vue.js',
              link: 'https://vuejs.org/'
            },
            {
              name: 'Adobe Experience Manager',
              link: 'https://www.adobe.com/ca/marketing/experience-manager.html'
            }
          ]
        }
      }
    }
  }
}
</script>
