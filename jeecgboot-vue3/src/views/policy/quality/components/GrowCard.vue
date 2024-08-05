<template>
  <div class="md:flex">
    <template v-for="(item, index) in growCardList1" :key="item.title">
      <Card
        size="small"
        :loading="loading"
        :title="item.title"
        class="md:w-1/4 w-full !md:mt-0 !mt-4"
        :class="[index + 1 < 4 && '!md:mr-4']"
        :canExpan="false"
      >
        <template #extra>
          <Tag :color="item.color">{{ item.action }}</Tag>
        </template>

        <div class="py-4 px-4 flex justify-between">
          <CountTo prefix="" :startVal="1" :endVal="item.value" class="text-2xl" />
          <Icon :icon="item.icon" :size="40" />
        </div>

        <div class="p-2 px-4 flex justify-between">
          <span>总{{ item.title }}</span>
          <CountTo prefix="" :startVal="1" :endVal="item.total" />
        </div>
      </Card>
    </template>
  </div>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';
  import { CountTo } from '/@/components/CountTo/index';
  import { Icon } from '/@/components/Icon';
  import { Tag, Card } from 'ant-design-vue';
  import { getGroupCardData } from '../../../dashboard/Analysis/api';
  console.log('getGroupCardData', getGroupCardData);
  let growCardList1 = ref([]);

  getGroupCardData().then((res) => {
    if (res.success) {
      console.log(JSON.stringify(res));
      growCardList1.value = res.result;
    }
  });
  defineProps({
    loading: {
      type: Boolean,
    },
  });
</script>
