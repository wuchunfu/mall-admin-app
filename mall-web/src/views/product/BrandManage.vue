<template>
  <el-row>
    <el-col :span="24">
      <el-card shadow="never">
        <el-form ref="query" :model="query">
          <el-form-item prop="name">
            <el-input v-model="query.name"
                      size="small"
                      style="width: 20%;"
                      placeholder="请输入品牌名称" />
            <el-button size="small"
                       type="primary"
                       icon="el-icon-search"
                       @click="selectBrand"
                       style="margin-left: 36px;">查询</el-button>
            <el-button size="small"
                       type="primary"
                       @click="resetForm('query')">重置</el-button>
            <el-button size="small"
                       type="primary"
                       @click="createClick"
                       style="margin-left: 100px;">新建</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
    <el-col :span="24"><br>
      <el-card shadow="never" style="border-bottom: none;">
      <el-table
          ref="multipleTable"
          :data="tableData"
          @selection-change="handleSelectionChange"
          stripe
          style="width: 100%" border>
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="id"
            label="编号"
            width="100">
        </el-table-column>
        <el-table-column
            prop="name"
            label="品牌名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="sort"
            label="排序"
            width="150">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建时间">
          <template #default="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
          </template>
        </el-table-column>
        <el-table-column
            label="操作">
          <template #default="scope">
            <el-button
                size="mini"
                @click="updateClick(scope.$index, scope.row)">修改
            </el-button>
            <el-button
                size="mini"
                type="danger"
                @click="deleteBrand(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      </el-card>
    </el-col>
    <el-col :span="24">
      <el-card shadow="never" style="border-top: none;">
      <el-pagination
          background
          @current-change="handleCurrentChange"
          @prev-click="handleCurrentChangePrev"
          @next-click="handleCurrentChangeNext"
          :currentPage="currentPage"
          :page-size="size"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
      </el-card>
    </el-col>
    <!-- Form -->
    <el-dialog :title="dialogTitle" width="30%" v-model="dialogFormVisible" center>
      <el-form :model="brand" >
        <el-form-item label="品牌名称" :label-width="formLabelWidth">
          <el-input v-model="brand.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="品牌排序" :label-width="formLabelWidth">
          <el-input v-model="brand.sort" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" v-show="updateButton" @click="updateBrand">确 定</el-button>
      <el-button type="primary" v-show="createButton" @click="createBrand">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </el-row>
</template>

<script>
export default {
  name: "BrandManage",
  data() {
    return {
      query: {
        name: ''
      },
      brand: {
        id: '',
        name: '',
        sort: ''
      },
      tableData: null,
      multipleSelection: [],
      total: null,
      currentPage: 1,
      size: 5,
      dialogFormVisible: false,
      formLabelWidth: '80px',
      dialogTitle: '',
      updateButton: false,
      createButton: false
    }
  },
  mounted() {
    this.selectBrand();
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleCurrentChangePrev(val) {
      this.currentPage = val;
      console.log(`上一页: ${val}`);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.selectBrand();
      console.log(`当前页: ${val}`);
    },
    handleCurrentChangeNext(val) {
      this.currentPage = val;
      console.log(`下一页: ${val}`);
    },

    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    // 更新品牌
    updateClick(index, row){
      console.log(index);
      this.dialogFormVisible = true;
      this.dialogTitle = '修改品牌';
      this.updateButton = true;
      this.createButton = false;
      this.brand.id = row.id;
      this.brand.name = row.name;
      this.brand.sort = row.sort;
    },

    // 创建品牌
    createClick(index, row){
      console.log(index,row)
      this.dialogFormVisible = true;
      this.dialogTitle = '新建品牌';
      this.createButton = true;
      this.updateButton = false;
    },

    // 修改品牌
    updateBrand(){
      this.$axios.get('/brand/update', {
        params: {
          id: this.brand.id,
          name: this.brand.name,
          sort: this.brand.sort
        }
      }).then((response) => {
        if (response.data.code === 200){
          this.$message({
            message: '修改成功',
            type: 'success'
          });
          this.selectBrand();
        } else {
          this.$message.error('修改失败');
        }
      }).catch((error) => {
        console.log(error);
      })
      this.brand.name = '';
      this.brand.sort = '';
      this.dialogFormVisible = false;
    },

    // 创建品牌
    createBrand() {
      this.$axios.get('/brand/create', {
        params: {
          name: this.brand.name,
          sort: this.brand.sort
        }
      }).then((response) => {
         if (response.data.code === 200){
           this.$message({
             message: '创建成功',
             type: 'success'
           });
           this.brand.name = '';
           this.brand.sort = '';
           this.selectBrand();
         } else {
           this.$message.error('创建失败');
         }
      }).catch((error) => {
         console.log(error);
      })
      this.dialogFormVisible = false;
    },

    // 删除品牌
    deleteBrand(index, row) {
      this.$axios.get('/brand/delete', {
        params: {
          id: row.id,
        }
      }).then((response) => {
        if (response.data.code === 200){
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.selectBrand();
        } else {
          this.$message.error('删除失败');
        }
      }).catch((error) => {
        console.log(error);
      })
    },

    // 查询品牌
    selectBrand() {
      this.$axios.get('/brand/list',{
        params: {
          pageNum: this.currentPage,
          pageSize: this.size,
          name: this.query.name,
        }
      }).then((response) => {
        if (response.data.code === 200){
          this.total = response.data.data.total;
          this.tableData = response.data.data.list;
        } else {
          this.$message.error('创建失败');
        }
      }).catch((error) => {
        console.log(error);
      })
    },
  }
}
</script>

<style scoped>

</style>