import { assert, expect } from 'chai'
import Profile from '@/components/Profile.vue'
import { shallowMount } from '@vue/test-utils'

describe('Profile.vue', () => {
  it('test profile with just name', () => {
    const wrapper = shallowMount(Profile, {
      propsData: {
        profile: {
          name: 'Pat'
        }
      }
    })
    assert.ok(wrapper)
    expect(wrapper.html()).to.contain('Name')
    expect(wrapper.html()).to.not.contain('Email')
    expect(wrapper.html()).to.not.contain('Number')
    expect(wrapper.html()).to.not.contain('Career')
    expect(wrapper.html()).to.not.contain('Location')
    expect(wrapper.html()).to.not.contain('Experience')
  })
})
