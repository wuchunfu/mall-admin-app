// pages/tabbar/cart/cart.js
import Dialog from '@vant/weapp/dialog/dialog';
import request from '../../../utils/request'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cartItem: null,
    totalPrice: null,
    show: null,
    description: ''
  },

  // 查看商品详情
  onClick: function (event) {
    wx.navigateTo({
      url: '/pages/product/detail/detail?id=' + event.currentTarget.id
    })   
  },

  // 点击删除按钮，出现提示
  onClickDelete: function (event) {
    let _this = this;
    Dialog.confirm({
      message: '确定删除吗？',
    }).then(() => {
      _this.delete(event);
      _this.onShow();
    });
  },

  // 删除购物车中的商品
  async delete (event) {
    let res = await request.get('/cart/delete',{
      productId: event.currentTarget.id,
      userId: wx.getStorageSync('userId')
    })
    if(res.data.code === 200){ console.log(res) }
  },

  // 提交订单
  async submitOrder () {
    let res = await request.get('/order/create',{
      userId: wx.getStorageSync('userId')
    })
    if(res.data.code === 200){ 
      console.log(res)
      this.onShow();
      wx.navigateTo({
        url: '/pages/result/pay/pay',
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  onSubmit() {
    wx.showToast({
      title: '点击结算',
      icon: 'none'
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: async function () {
    this.getTabBar().init();
    let res = await request.get('/cart/info',{
      userId: wx.getStorageSync('userId')
    })
    if(res.data.code === 200){
      this.setData({
        cartItem: res.data.data.cartItem,
        totalPrice: res.data.data.totalPrice*100,
        show: false,
      })
    }
    if(res.data.message === '购物车竟然是空的'){
      this.setData({
        show: true,
        description: res.data.message
      })
    }
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