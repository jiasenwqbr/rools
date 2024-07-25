<template>
  <div></div>
</template>
<script lang="ts" setup name="login-mini">
  import { onMounted, ref, toRaw } from 'vue';

  import { useUserStore } from '/@/store/modules/user';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useRouter } from 'vue-router';
  const { notification } = useMessage();
  const userStore = useUserStore();
  const { t } = useI18n();

  const { currentRoute } = useRouter();
  const route = currentRoute.value;
  console.log('route', JSON.stringify(route));
  const access = route.query.access;
  const refresh = route.query.refresh;

  console.log('access:', access);
  console.log('refresh:', refresh);

  const loginLoading = ref<boolean>(false);
  defineProps({
    sessionTimeout: {
      type: Boolean,
    },
  });

  async function accountLogin() {
    try {
      await userStore.doLogout2();
    } catch (error) {}
    try {
      loginLoading.value = true;
      const { userInfo } = await userStore.login(
        toRaw({
          password: 'Jxsn#123456',
          username: 'admin',
          captcha: '1',
          checkKey: '1',
          mode: 'none', //不要默认的错误提示
          access: access,
          refresh: refresh,
        })
      );
      if (userInfo) {
        notification.success({
          message: t('sys.login.loginSuccessTitle'),
          description: `${t('sys.login.loginSuccessDesc')}: ${userInfo.realname}`,
          duration: 3,
        });
      }
    } catch (error) {
      notification.error({
        message: t('sys.api.errorTip'),
        description: error.message || t('sys.login.networkExceptionMsg'),
        duration: 3,
      });
    } finally {
      loginLoading.value = false;
    }
  }

  onMounted(() => {
    //加载验证码
    accountLogin();
  });
</script>
