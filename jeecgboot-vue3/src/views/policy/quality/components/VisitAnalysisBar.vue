<template>
  <div ref="chartRef" :style="{ height, width }"></div>
</template>
<script lang="ts" setup>
  import { onMounted, ref, Ref, watchEffect } from 'vue';
  import { useECharts } from '/@/hooks/web/useECharts';
  import { basicProps } from './props';
  import { useRootSetting } from '/@/hooks/setting/useRootSetting';

  defineProps({
    ...basicProps,
  });

  const chartRef = ref<HTMLDivElement | null>(null);
  const { setOptions } = useECharts(chartRef as Ref<HTMLDivElement>);
  const { getThemeColor } = useRootSetting();
  const init = () => {
    setOptions({
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          lineStyle: {
            width: 1,
            color: getThemeColor.value,
          },
        },
      },
      grid: { left: '1%', right: '1%', top: '2  %', bottom: 0, containLabel: true },
      xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      },
      yAxis: {
        type: 'value',
        max: 800,
        splitNumber: 4,
      },
      series: [
        {
          data: [300, 200, 333, 500, 320, 420, 320, 210, 300, 510, 600, 320, 480],
          type: 'bar',
          barMaxWidth: 80,
          color: getThemeColor.value,
        },
      ],
    });
  };
  watchEffect(() => {
    init();
  });
</script>
