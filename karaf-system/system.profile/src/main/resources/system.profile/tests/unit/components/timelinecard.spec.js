import { assert, expect } from 'chai'
import TimelineCard from '@/components/TimelineCard'
import { shallowMount } from '@vue/test-utils'

describe('TimelineCard.vue', () => {
  it('validate content', () => {
    const wrapper = shallowMount(TimelineCard, {
      propsData: {
        card: {
          title: 'One of my tasks',
          year: 2020
        }
      }
    })
    assert.ok(wrapper)
    expect(wrapper.html()).to.contain('One of my tasks')
    expect(wrapper.html()).to.contain(2020)
  })
})
