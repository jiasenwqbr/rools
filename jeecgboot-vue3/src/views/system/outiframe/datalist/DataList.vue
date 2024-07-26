<template>
  <iframe :src="ifurl" loading="lazy" width="100%" height="100%"></iframe>
</template>
<script lang="ts" setup name="login-mini">
  import { useUserStore } from '/@/store/modules/user';
  const userStore = useUserStore();

  // const { currentRoute } = useRouter();
  // const route = currentRoute.value;
  // console.log('route', JSON.stringify(route));
  // const access = route.query.access;
  // const refresh = route.query.refresh;

  // console.log('access:', access);
  // console.log('refresh:', refresh);
  const access = userStore.getAccess;
  const refresh = userStore.getRefresh;
  console.log('access:', access);
  console.log('refresh:', refresh);
  const ifurl = 'https://10.200.160.236:7857/data/data_list?noNavBar=true&access=' + refresh + '&refresh=' + refresh;
  defineProps({
    sessionTimeout: {
      type: Boolean,
    },
  });
</script>
