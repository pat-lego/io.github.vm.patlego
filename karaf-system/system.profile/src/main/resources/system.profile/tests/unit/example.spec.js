import { expect } from 'chai'
import { shallowMount } from '@vue/test-utils'
import Footer from '@/components/Footer.vue'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

describe('Footer.vue', () => {
  it('props should appear', () => {
    const footer = {
      linkedin: 'my linkedin',
      github: 'my github',
      stackoverflow: 'my stackoverflow',
      twitter: 'my twitter'
    }
    const wrapper = shallowMount(Footer, {
      propsData: { footer },
      components: {
        FontAwesomeIcon
      }
    })
    expect(wrapper.html()).to.include(footer.linkedin)
    expect(wrapper.html()).to.include(footer.twitter)
    expect(wrapper.html()).to.include(footer.stackoverflow)
    expect(wrapper.html()).to.include(footer.github)
  })
})
