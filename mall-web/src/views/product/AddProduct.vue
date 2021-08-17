<template>
  <el-card shadow="never">
  <el-row :gutter="20">
    <el-col :span="20" :offset="2">
       <Steps />
      <br><br>
    </el-col>
    <el-col :span="20" :offset="2">
      <router-view></router-view>
    </el-col>
  </el-row>
  </el-card>
</template>

<script>
import Steps from "../../components/Steps";
export default {
  name: "AddProduct",
  components: {Steps},
  data() {
    return {

    }
  },
  methods: {

  }
}
</script>

<style scoped>

</style>