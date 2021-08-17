<template>
  <el-card shadow="never">
    <el-row>
      <el-col :span="16" :offset="4">
        <el-form ref="form" :model="product">
          <el-form-item label="发货地址">
            <el-input v-model="product.sendAddress" style="width: 60%;"></el-input>
          </el-form-item>
          <el-form-item label="快递类型">
            <el-input v-model="product.parcelType" style="width: 60%;"></el-input>
          </el-form-item>
          <br><br>
          <el-form-item style="text-align: center;">
            <el-button type="primary" @click="toPrevious">上一步</el-button>
            <el-button type="primary" @click="onSubmit">已完成，下一步</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "Logistics",
  data() {
    return {
      product: {
        sendAddress: '',
        parcelType: ''
      }
    }
  },
  mounted() {
    this.getRouterInfo();
  },
  methods: {
    getRouterInfo(){
      if (this.$route.params.sendAddress != null){
        this.product.sendAddress = this.$route.params.sendAddress;
      }
      if (this.$route.params.parcelType != null){
        this.product.parcelType = this.$route.params.parcelType;
      }
      console.log("AAA" + this.$route.params.imageUrl);
    },
    toPrevious() {
      this.$store.commit('setActive', 1)
      this.$router.push({
        name: 'describe',
        params: {
          pictureUrl: this.$route.params.imageUrl,
        }
      });
    },
    onSubmit() {
      this.$store.commit('setActive', 3)
      this.$router.push({
        name: 'service',
        params: {
          categoryId: this.$route.params.categoryId,
          kind: this.$route.params.kind,
          title: this.$route.params.title,
          brandId: this.$route.params.brandId,
          name: this.$route.params.name,
          origin: this.$route.params.origin,
          productionDate: this.$route.params.productionDate,
          price: this.$route.params.price,
          amount: this.$route.params.amount,
          pictureUrl: this.$route.params.pictureUrl,
          sendAddress: this.product.sendAddress,
          parcelType: this.product.parcelType
        }
      });
    }
  }
}
</script>

<style scoped>

</style>