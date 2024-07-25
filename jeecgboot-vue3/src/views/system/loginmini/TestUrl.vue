<template>
  <div>testurl {{ access }} {{ refresh }}</div>
</template>
<script lang="ts" setup name="login-mini">
  import { useRouter } from 'vue-router';
  import { useUserStore } from '/@/store/modules/user';
  const userStore = useUserStore();

  // const { currentRoute } = useRouter();
  // const route = currentRoute.value;
  // console.log('route', JSON.stringify(route));
  // const access = route.query.access;
  // const refresh = route.query.refresh;

  // console.log('access:', access);
  // console.log('refresh:', refresh);
  const access = userStore.getAccess();
  const refresh = userStore.getRefresh();
  console.log('access:', access);
  console.log('refresh:', refresh);

  defineProps({
    sessionTimeout: {
      type: Boolean,
    },
  });
</script>
