// pages/product/detail/detail.js
import Toast from '@vant/weapp/toast/toast'
import request from '../../../utils/request'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    product: null,
    productId: null,
    show: false,
    salesServiceList: null,
    starStatus: 0,
  },
  async onClickStar() {
    let res = await request.get('/collection/collect',{ 
      productId: this.data.productId, 
      userId: wx.getStorageSync('userId')
    });
    if(res.data.code === 200){
      Toast.success('收藏成功');
    }
  },
  onClickCart() {
    wx.switchTab({
      url: '/pages/tabbar/cart/cart',
    });
  },
  async onClickAddCart() {
    let res = await request.get('/cart/add',{
      productId: this.data.productId,
      userId: wx.getStorageSync('userId')
    });
    if(res.data.code === 200){
      Toast.success('添加成功');
    }
  },

  sorry() {
    wx.showToast({
      title: '暂无后续逻辑~',
      icon: 'none',
    });
  },
  showPopup() {
    this.setData({ show: true });
  },
  onClose() {
    this.setData({ show: false });
  },
  onFinish() {
    this.setData({ show: false });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function (options) {
    console.log(options.id)
    this.setData({
      productId: options.id
    });
    let res = await request.get('/product/detail',{ id: options.id });
    console.log("AAA")
    console.log(res.data.data)
    this.setData({
        product: res.data.data,
        salesServiceList: res.data.data.salesService.split(',')
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})