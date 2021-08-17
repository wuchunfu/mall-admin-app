<template>
  <el-card shadow="never">
    <el-row>
      <el-col :span="16" :offset="4">
        <el-form ref="form" :model="product">
          <el-form-item label="售后服务">
            <el-checkbox-group v-model="product.salesService">
              <el-checkbox label="提供发票" name="type"></el-checkbox>
              <el-checkbox label="保修服务" name="type"></el-checkbox>
              <el-checkbox label="退换货承诺" name="type"></el-checkbox>
              <el-checkbox label="服务承诺：该类商品，必须支持【七天退货】服务" name="type" style="margin-left: 68px;"></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <br><br>
          <el-form-item style="text-align: center;">
            <el-button type="primary" @click="toPrevious">上一步</el-button>
            <el-button type="primary" @click="onSubmit">提交商品</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "Service",
  data() {
    return {
      product: {
        salesService: [],
      }
    }
  },
  methods: {
    toPrevious() {
      this.$store.commit('setActive', 2)
      this.$router.push({
        name: 'logistics',
        params: {
          sendAddress: this.$route.params.sendAddress,
          parcelType: this.$route.params.parcelType,
        }
      });
    },
    onSubmit() {
      this.$store.commit('setActive', 4)
      console.log(localStorage.getItem("USER_ID"))
      let salesServiceStr = '';
      for (let i = 0; i < this.product.salesService.length; i++) {
        salesServiceStr = salesServiceStr + this.product.salesService[i] + ',';
      }
      this.$axios.get('/product/create', {
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
          imageUrl: this.$route.params.pictureUrl,
          sendAddress: this.$route.params.sendAddress,
          parcelType: this.$route.params.parcelType,
          salesService: salesServiceStr,
          creatorId: localStorage.getItem('USER_ID')
        }
      }).then((response) => {
          if (response.data.code === 200){
            this.$router.push({path: '/result/page'})
        }
        console.log(response.data.data)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>

</style>