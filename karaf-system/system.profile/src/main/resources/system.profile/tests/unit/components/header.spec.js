import { assert, expect } from 'chai'
import Header from '@/components/Header.vue'
import { shallowMount } from '@vue/test-utils'

describe('Header.vue', () => {
  it('header tabs', () => {
    const wrapper = shallowMount(Header, {
      stubs: ['router-link']
    })
    assert.ok(wrapper)
    expect(wrapper.html().match(new RegExp('header-item', 'g') || []).length).to.eql(2)
  })
})
