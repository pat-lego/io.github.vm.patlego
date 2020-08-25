<template>
  <div class='flex'>
    <div class='timeline relative w-4/5  m-auto m-10'>
      <ul class='m-0 p-0'>
        <li v-for="entry in sortTimeline" :key="entry.title" class='relative w-1/2 sm:p-6 md:p-10'>
          <timeline-card-component :card="entry"/>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
/**
  * @author pat-lego <patrique.legault@gmail.com>
  * @version 1.0
  * @description This component is used to render a timeline of TimelineCard component in order to depict a history of items
  * @memberof components
*/
import TimelineCard from '@/components/TimelineCard.vue'

export default {
  name: 'timeline-component',
  components: {
    'timeline-card-component': TimelineCard
  },
  props: {
    timeline: {
      type: Array,
      required: false
    },
    sort: {
      type: String,
      required: false
    }
  },
  computed: {
    sortTimeline: function () {
      if (!this.timeline) {
        return undefined
      }
      if (!this.sort) {
        return this.timeline
      }
      var copy = this.timeline
      if (this.sort === 'asc') {
        return copy.sort(function (a, b) {
          if (a.year < b.year) {
            return -1
          }
          if (a.year > b.year) {
            return 1
          }
          return 0
        })
      } else if (this.sort === 'desc') {
        return copy.sort(function (a, b) {
          if (a.year > b.year) {
            return -1
          }
          if (a.year < b.year) {
            return 1
          }
          return 0
        })
      } else {
        return this.timeline
      }
    }
  }
}
</script>
<style lang='scss' scoped>
  .timeline {
    ul {
      li:nth-child(odd) {
        float: left;
        text-align: right;
        clear: both;
      }
      li:nth-child(even) {
        float: right;
        text-align: left;
        clear: both;
      }
      @media only screen and (min-width: 600px) {
        li:nth-child(odd):before {
          content: '';
          position: absolute;
          width: 1rem;
          height: 1rem;
          background-color: rgba(233,33,99,1);
          border-radius: 50%;
          box-shadow: 0 0 0 2px rgba(233,33,9,0.2);
          top: 4.5rem;
          right: -0.6rem;
        }
      }
      @media only screen and (min-width: 600px) {
        li:nth-child(even):before {
          content: '';
          position: absolute;
          width: 1rem;
          height: 1rem;
          background-color: rgba(233,33,99,1);
          border-radius: 50%;
          box-shadow: 0 0 0 2px rgba(233,33,9,0.2);
          top: 4.5rem;
          left: -0.4rem;
        }
      }
    }
  }
  .timeline:before {
    content: '';
    position: absolute;
    left: 50%;
    width: 2px;
    height: 100%;
    @media only screen and (min-width: 600px) {
      background: #c5c5c5;
    }
  }
</style>
<!-- Share style with child component -->
<style scoped>
  .timeline ul li:nth-child(odd) >>> .time {
    position: absolute;
    top: 3.5rem;
    right: -165px;
    margin: 0px;
    padding: 1rem 2rem;
    background: rgba(233,33,99,1);
    color: #fff;
    font-weight: bold;
    border-radius: 18px;
  }
  .timeline ul li:nth-child(even) >>> .time {
    position: absolute;
    top: 3.5rem;
    left: -165px;
    margin: 0px;
    padding: 1rem 2rem;
    background: rgba(233,33,99,1);
    color: #fff;
    font-weight: bold;
    border-radius: 18px;
  }
  .timeline ul li >>> .title {
    font-weight: bold;
    margin: 2rem;
  }

  .timeline ul li >>> .content {
    font-style: italic;
  }
</style>
