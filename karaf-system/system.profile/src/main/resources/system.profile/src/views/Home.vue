<template>
  <div>
    <div class='flex mb-4 w-full'>
      <div class='w-1/4'>
        <icon-component />
      </div>
      <div class='w-full'>
        <nav-component />
      </div>
    </div>
    <div class='flex flex-col sm:flex-row items-center w-full'>
        <div class="w-1/2 flex justify-center items-center">
          <card-component :card='personal.image' />
        </div>
        <div class="sm:w-1/2 sm:w-1/2 flex pt-4">
          <profile-component :profile='personal.profile' />
        </div>
    </div>
    <div class='flex flex-col mt-6'>
      <timeline-component :timeline='personal.timeline' :sort='"desc"'/>
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
          imageAlt: 'Patrique Legault'
        },
        timeline: [
          {
            title: 'Software Developer',
            description: '<div class=\'m-2\'>I developed an algorithm at Laurentian University to measure the energy of various chemical structures using Mathematica</div>\n' +
                          '<div class=\'m-2\'>Deployed and tested the solution on a super computer made available by <a href=\'https://www.sharcnet.ca/my/front/\'>SharcNet</a></div>\n',
            year: 2011
          },
          {
            title: 'Technical Support Agent',
            description: '<div class=\'m-2\'>I worked with many application servers and databases in order to install Adobe LiveCycle for clients. \n' +
                                'This required me to work with various components of the organization to accomplish this task. </div>\n' +
                          '<div class=\'m-2\'>Used the Adobe Workbench utility to help clients troubleshoot processes developed for Adobe LiveCycle</div>\n',
            year: 2014
          },
          {
            title: 'Senior Systems Analyst',
            description: '<div class=\'m-2\'>Worked on various projects involving Adobe Experience Manager and Forms</div>\n' +
                          '<div class=\'m-2\'>Used tools such as Jira, Bitbucket, Maven and VS Code to develop OSGi services and AEM components</div>\n' +
                          '<div class=\'m-2\'>Used various development tools to create integrations between client systems and Adobe Sign</div>\n',
            year: 2016
          },
          {
            title: 'Senior Developer',
            description: '<div class=\'m-2\'>Managed product development with a team of developers and used Agile methodologies to create a quick and iterative delivery cycle</div>\n' +
                          '<div class=\'m-2\'>Used various tools to develop a deployment pipeline such as TravisCI, Ruby, Amazon ECS, Amazon ECR, Amazon RDS, Docker, Flyway DB Migration</div>\n' +
                          '<div class=\'m-2\'>Used various tools to develop a testing pipeline such as TravisCI, Selenium, JUnit</div>\n' +
                          '<div class=\'m-2\'>Worked as a full stack developer leveraging Angular, Java and Postgres as the main technologies for the application</div>\n',
            year: 2018
          },
          {
            title: 'Software Consultant',
            description: '<div class=\'m-2\'>Leveraging Adobe Experience Manager to create web content for businesses</div>\n',
            year: 2019
          }
        ],
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
