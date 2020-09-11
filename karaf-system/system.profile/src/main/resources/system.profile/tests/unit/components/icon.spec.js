import { assert, expect } from 'chai'
import Icon from '@/components/Icon.vue'
import { shallowMount } from '@vue/test-utils'

describe('Icon.vue', () => {
  it('validate name', () => {
    const wrapper = shallowMount(Icon, {
      stubs: ['router-link']
    })
    assert.ok(wrapper)
    expect(wrapper.html()).to.contain('Pat Lego')
  })
})
