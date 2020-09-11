import { assert, expect } from 'chai'
import { mount } from '@vue/test-utils'
import Timeline from '@/components/Timeline.vue'
import TimelineCard from '@/components/TimelineCard'

describe('Timeline.vue', () => {
  it('testing sortTimeline asc', () => {
    const wrapper = mount(Timeline, {
      propsData: {
        sort: 'asc',
        timeline: [
          {
            title: 'Software Developer',
            description: ['I developed an algorithm at Laurentian University to measure the energy of various chemical structures using Mathematica',
              'Deployed and tested the solution on a super computer made available by <a href=\'https://www.sharcnet.ca/my/front/\'>SharcNet</a>'],
            year: 2011
          },
          {
            title: 'Technical Support Agent',
            description: ['I worked with many application servers and databases in order to install Adobe LiveCycle for clients. \n' +
            'This required me to work with various components of the organization to accomplish this task.',
            'Used the Adobe Workbench utility to help clients troubleshoot processes developed for Adobe LiveCycle'],
            year: 2014
          },
          {
            title: 'Senior Systems Analyst',
            description: ['Worked on various projects involving Adobe Experience Manager and Forms',
              'Used tools such as Jira, Bitbucket, Maven and VS Code to develop OSGi services and AEM components',
              'Used various development tools to create integrations between client systems and Adobe Sign'],
            year: 2016
          }
        ]
      },
      components: {
        TimelineCard
      }
    })
    assert.ok(wrapper) // Truthy
    const data = wrapper.vm.sortTimeline
    expect(data).to.eql([
      {
        title: 'Software Developer',
        description: ['I developed an algorithm at Laurentian University to measure the energy of various chemical structures using Mathematica',
          'Deployed and tested the solution on a super computer made available by <a href=\'https://www.sharcnet.ca/my/front/\'>SharcNet</a>'],
        year: 2011
      },
      {
        title: 'Technical Support Agent',
        description: ['I worked with many application servers and databases in order to install Adobe LiveCycle for clients. \n' +
        'This required me to work with various components of the organization to accomplish this task.',
        'Used the Adobe Workbench utility to help clients troubleshoot processes developed for Adobe LiveCycle'],
        year: 2014
      },
      {
        title: 'Senior Systems Analyst',
        description: ['Worked on various projects involving Adobe Experience Manager and Forms',
          'Used tools such as Jira, Bitbucket, Maven and VS Code to develop OSGi services and AEM components',
          'Used various development tools to create integrations between client systems and Adobe Sign'],
        year: 2016
      }
    ])
  })
  it('testing sortTimeline desc', () => {
    const wrapper = mount(Timeline, {
      propsData: {
        sort: 'desc',
        timeline: [
          {
            title: 'Software Developer',
            description: ['I developed an algorithm at Laurentian University to measure the energy of various chemical structures using Mathematica',
              'Deployed and tested the solution on a super computer made available by <a href=\'https://www.sharcnet.ca/my/front/\'>SharcNet</a>'],
            year: 2011
          },
          {
            title: 'Technical Support Agent',
            description: ['I worked with many application servers and databases in order to install Adobe LiveCycle for clients. \n' +
            'This required me to work with various components of the organization to accomplish this task.',
            'Used the Adobe Workbench utility to help clients troubleshoot processes developed for Adobe LiveCycle'],
            year: 2014
          },
          {
            title: 'Senior Systems Analyst',
            description: ['Worked on various projects involving Adobe Experience Manager and Forms',
              'Used tools such as Jira, Bitbucket, Maven and VS Code to develop OSGi services and AEM components',
              'Used various development tools to create integrations between client systems and Adobe Sign'],
            year: 2016
          }
        ]
      },
      components: {
        TimelineCard
      }
    })
    assert.ok(wrapper) // Truthy
    const data = wrapper.vm.sortTimeline
    expect(data).to.eql([
      {
        title: 'Senior Systems Analyst',
        description: ['Worked on various projects involving Adobe Experience Manager and Forms',
          'Used tools such as Jira, Bitbucket, Maven and VS Code to develop OSGi services and AEM components',
          'Used various development tools to create integrations between client systems and Adobe Sign'],
        year: 2016
      },
      {
        title: 'Technical Support Agent',
        description: ['I worked with many application servers and databases in order to install Adobe LiveCycle for clients. \n' +
        'This required me to work with various components of the organization to accomplish this task.',
        'Used the Adobe Workbench utility to help clients troubleshoot processes developed for Adobe LiveCycle'],
        year: 2014
      },
      {
        title: 'Software Developer',
        description: ['I developed an algorithm at Laurentian University to measure the energy of various chemical structures using Mathematica',
          'Deployed and tested the solution on a super computer made available by <a href=\'https://www.sharcnet.ca/my/front/\'>SharcNet</a>'],
        year: 2011
      }
    ])
  })
})
