<template>
  <el-card shadow="never">
    <el-row>
      <el-col :span="16" :offset="4">
        <el-form ref="form" :model="product">
          <el-form-item label="商品图片">
            <el-upload
                action="http://localhost:8800/product/upload"
                :headers="{'Authorization': getHeader}"
                list-type="picture-card"
                limit="1"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
                :on-success="handleAlbumSuccess"
                name="image">
              <i class="el-icon-plus"></i>
              <el-dialog v-model="dialogVisible">
                <img width="100%" :src="product.imageUrl" alt="">
              </el-dialog>
            </el-upload>
          </el-form-item>
          <br>
          <el-form-item>
            <el-alert
                title="最多上传4张图，首张图为主图，商品主图大小不能超过3MB，支持任意格式的图片"
                type="info"
                show-icon>
            </el-alert>
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
  name: "Describe",
  data() {
    return {
      product: {
        imageUrl: '',
      },
      dialogVisible: false,
      text: '',
    }
  },
  mounted() {
    this.getRouterInfo();
  },
  computed: {
    getHeader: {
      get() {
        return this.$store.state.token
      },
      // set(val) {
      //   this.$store.state.token = val
      // }
    },
  },
  methods: {
    getRouterInfo(){
      if (this.$route.params.imageUr != null){
        this.product.imageUrl = this.$route.params.imageUr;
      }
    },
    toPrevious() {
      this.$store.commit('setActive', 0)
      this.$router.push({
        name: 'info',
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
        }
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleAlbumSuccess(response) {
      // res表示后端的返回值，其中应包含文件上传后的url路径
      if (response.code === 200){
        this.product.imageUrl = response.data;
      }
      console.log(response.data);
    },
    onSubmit() {
      this.$store.commit('setActive', 2)
      this.$router.push({
        name: 'logistics',
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
          pictureUrl: this.product.imageUrl,
        }
      });
    }
  }
}
</script>

<style scoped>

</style>