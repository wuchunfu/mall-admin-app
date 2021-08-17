<template>
  <el-result icon="success" class="result" title="发布成功" subTitle="请根据提示进行操作">
    <template #extra>
      <el-button type="primary" size="medium" @click="toIndex">返回</el-button>
    </template>
  </el-result>
</template>

<script>
export default {
  name: "ResultPage",
  methods: {
    toIndex() {
      this.$router.push({ path: '/product/list' })
    }
  }
}
</script>

<style scoped>
.result{
  margin-top: 100px;
}
</style>