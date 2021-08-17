<template>
  <el-card shadow="never">
    <el-row>
      <el-col :span="16" :offset="4">
        <el-form ref="form" :model="product">
          <el-form-item label="选择类目">
            <el-cascader v-model="categoryValue"
                         :options="product.options"
                         placeholder="请选择"
                         @change="changeCategoryId"
                         clearable />
          </el-form-item>
          <el-form-item label="商品类型">
            <el-radio-group v-model="product.kind">
              <el-radio label="1">全新</el-radio>
              <el-radio label="0">二手</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="商品标题">
            <el-input v-model="product.title"
                      maxlength="30" style="width: 80%;"
                      show-word-limit></el-input>
          </el-form-item>
          <el-form-item label="品牌" label-width="68px">
            <el-select v-model="brandValue"
                       @change="changeBrandId"
                       placeholder="请选择">
              <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="商品名称">
            <el-input v-model="product.name" maxlength="20" style="width: 60%;" show-word-limit></el-input>
          </el-form-item>
          <el-form-item label="产地" label-width="68px">
            <el-input v-model="product.origin" style="width: 60%;"></el-input>
          </el-form-item>
          <el-form-item label="生产日期">
            <el-date-picker
                v-model="product.productionDate"
                type="datetime"
                placeholder="选择日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="售价" label-width="68px">
            <el-input v-model="product.price" style="width: 30%;">
              <template #append>元</template>
            </el-input>
          </el-form-item>
          <el-form-item label="库存" label-width="68px">
            <el-input v-model="product.amount" style="width: 30%;">
              <template #append>件</template>
            </el-input>
          </el-form-item>
          <br><br>
          <el-form-item style="text-align: center;">
            <el-button type="primary" @click="onSubmit">已完成，下一步</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {
      categoryValue: null,
      brandValue: null,
      product: {
        options: null,
        categoryId: '',
        kind: '',
        title: '',
        brandId: '',
        name: '',
        origin: '',
        productionDate: '',
        price: '',
        amount: ''
      },
      options: null,
    }
  },
  mounted() {
    this.getRouterInfo();
    this.getCategory();
    this.getBrand();
  },
  methods: {
    changeCategoryId() {
      this.product.categoryId = this.categoryValue[2];
    },
    changeBrandId() {
      this.product.brandId = this.brandValue;
    },
    getRouterInfo() {
        this.categoryValue = this.$route.params.categoryId;
        this.product.kind = this.$route.params.kind;
        this.product.title = this.$route.params.title;
        this.brandValue = this.$route.params.brandId;
        this.product.name = this.$route.params.name;
        this.product.origin = this.$route.params.origin;
        this.product.productionDate = this.$route.params.productionDate;
        this.product.price = this.$route.params.price;
        this.product.amount = this.$route.params.amount;
    },
    getCategory() {
      this.$axios.get('/category/options').then((response) => {
        this.product.options = response.data.data;
        console.log(response.data.data)
      }).catch((error) => {
        console.log(error)
      })
    },
    getBrand() {
      this.$axios.get('/brand/options').then((response) => {
        this.options = response.data.data;
        console.log(response.data.data)
      }).catch((error) => {
        console.log(error)
      })
    },
    onSubmit() {
      this.$store.commit('setActive', 1)
      this.$router.push({
        name: 'describe',
        params: {
          categoryId: this.product.categoryId,
          kind: this.product.kind,
          title: this.product.title,
          brandId: this.product.brandId,
          name: this.product.name,
          origin: this.product.origin,
          productionDate: this.product.productionDate,
          price: this.product.price,
          amount: this.product.amount,
        }
      });
    }
  },
}
</script>

<style scoped>

</style>