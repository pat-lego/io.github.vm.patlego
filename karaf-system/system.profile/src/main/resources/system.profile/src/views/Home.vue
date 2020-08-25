<template>
  <div>
    <div class='flex mb-4'>
      <div class='w-1/4'>
        <icon-component />
      </div>
      <div class='w-3/4'>
        <nav-component />
      </div>
    </div>
    <div class='flex flex-row'>
        <div class="w-1/2 flex justify-center items-center">
          <card-component :card='personal.image' />
        </div>
        <div class="w-1/2 flex items-center">
          <profile-component :profile='personal.profile' />
        </div>
    </div>
    <div class='mt-6'>
      <timeline-component />
    </div>
    <div class="relative h-40">
      <div class="absolute inset-x-0 bottom-0">
        <footer-component :footer='footer' />
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
import Timeline from '@/components/Timeline'

export default {
  name: 'home-view',
  components: {
    'icon-component': Icon,
    'nav-component': Header,
    'card-component': Card,
    'profile-component': Profile,
    'footer-component': Footer,
    'timeline-component': Timeline
  },
  data: () => {
    return {
      personal: {
        image: {
          imageUrl: 'profile.jpg',
          imageAlt: 'Patrique Legault',
          imageTitle: 'Patrique Legault'
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
      },
      footer: {
        github: 'https://github.com/pat-lego',
        twitter: 'https://twitter.com/_patlego',
        linkedin: 'https://www.linkedin.com/in/patrique-legault/',
        stackoverflow: 'https://stackoverflow.com/users/8828583/patrique-legault'
      }
    }
  }
}
</script>
