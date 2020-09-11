import { assert, expect } from 'chai'
import Card from '@/components/Card.vue'
import { shallowMount } from '@vue/test-utils'

describe('Card.vue with card', () => {
  it('render card', () => {
    const wrapper = shallowMount(Card, {
      propsData: {
        card: {
          imageUrl: 'profile.jpg',
          imageAlt: 'Alternative Text',
          imageTitle: 'This is my title'
        }
      }
    })
    assert.ok(wrapper)
    expect(wrapper.html()).to.contain('This is my title')
    expect(wrapper.html()).to.contain('Alternative Text')
  })

  it('render card without card', () => {
    const wrapper = shallowMount(Card)
    assert.ok(wrapper)
    expect(wrapper.html()).to.not.contain('This is my title')
    expect(wrapper.html()).to.not.contain('Alternative Text')
  })
})
