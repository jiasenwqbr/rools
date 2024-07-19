<template>
  <div></div>
</template>
<script lang="ts" setup name="login-mini">
  import { onMounted, ref, toRaw } from 'vue';

  import { useUserStore } from '/@/store/modules/user';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { useI18n } from '/@/hooks/web/useI18n';

  const { notification } = useMessage();
  const userStore = useUserStore();
  const { t } = useI18n();

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
          password: 'normal_admin123',
          username: 'normal_admin',
          captcha: '2',
          checkKey: '2',
          mode: 'none', //不要默认的错误提示
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
