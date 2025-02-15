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
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: [
          '6:00',
          '7:00',
          '8:00',
          '9:00',
          '10:00',
          '11:00',
          '12:00',
          '13:00',
          '14:00',
          '15:00',
          '16:00',
          '17:00',
          '18:00',
          '19:00',
          '20:00',
          '21:00',
          '22:00',
          '23:00',
        ],
        splitLine: {
          show: true,
          lineStyle: {
            width: 1,
            type: 'solid',
            color: 'rgba(226,226,226,0.5)',
          },
        },
        axisTick: {
          show: false,
        },
      },
      yAxis: [
        {
          type: 'value',
          max: 8000,
          splitNumber: 4,
          axisTick: {
            show: false,
          },
          splitArea: {
            show: true,
            areaStyle: {
              color: ['rgba(255,255,255,0.2)', 'rgba(226,226,226,0.2)'],
            },
          },
        },
      ],
      grid: { left: '1%', right: '1%', top: '2  %', bottom: 0, containLabel: true },
      series: [
        {
          smooth: true,
          data: [111, 222, 400, 1800, 3333, 5555, 6666, 3333, 1400, 3600, 6666, 4444, 2222, 1111, 400, 200, 500, 333, 222, 111],
          type: 'line',
          areaStyle: {},
          itemStyle: {
            color: '#5ab1ef',
          },
        },
        {
          smooth: true,
          data: [33, 66, 88, 333, 333, 500, 1800, 300, 120, 1300, 2200, 1100, 2221, 1201, 390, 198, 60, 30, 22, 11],
          type: 'line',
          areaStyle: {},
          itemStyle: {
            color: getThemeColor.value,
          },
        },
      ],
    });
  };
  watchEffect(() => {
    init();
  });
</script>
