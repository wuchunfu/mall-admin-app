<template>
  <el-container class="el-container">
    <el-aside width="200px" class="el-aside">
      <Menu />
    </el-aside>
    <el-container style="display: block;">
      <Header />
      <el-main class="el-main">
        <Tabs></Tabs>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Menu from "../components/Menu";
import Header from "../components/Header";
import Tabs from "../components/Tabs";
export default {
  name: "Home",
  components: {Tabs, Header, Menu},
  data() {
    return {

    }
  },
}
</script>

<style scoped>
.el-container {
  height: 100vh;
  width: 100vw;
}
.el-aside {
  background-color: #334761;
}
.el-main {
  color: #333;
  height: 90%;
  padding: 0;
  background-color: white;
}
</style>