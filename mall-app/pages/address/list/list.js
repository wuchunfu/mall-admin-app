// pages/address/list/list.js
import request from '../../../utils/request'
import Dialog from '@vant/weapp/dialog/dialog';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    address: null
  },
  addAddress() {
    wx.navigateTo({
      url: '/pages/address/add/add',
    })
  },
  editAddress(event) {
    wx.navigateTo({
      url: '/pages/address/edit/edit?id=' + event.currentTarget.id,
    })
  },
  deleteAddress(event) {
    let _this = this;
    Dialog.confirm({
      message: '确定删除吗？',
    }).then(() => {
      _this.delete(event);
      _this.onShow();
    });
  },
  async delete(event) {
    let res = await request.get('/address/delete', { id: event.currentTarget.id })
    if(res.data.code === 200){
      // wx.navigateTo({
      //   url: '/pages/address/list/list',
      // })
    }
  },
  onChange(event) {
    // event.detail 为当前输入的值
    console.log(event.detail);
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    let res = await request.get('/address/list',{ userId: 100030 })
    this.setData({
      address: res.data.data
    })
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