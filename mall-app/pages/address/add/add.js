// pages/address/add/add.js
import { areaList } from '@vant/area-data';
import request from '../../../utils/request';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    show: false,
    areaList,
    name: '',
    mobile: '',
    province: '',
    city: '',
    district: '',
    detailedAddress: '',
    isDefault: '',
    area: '',
    editId: ''
  },
  showPopup() {
    this.setData({ show: true });
  },
  onClickcancel() {
    this.setData({ show: false });
  },
  onClickConfirm(event) {
    this.setData({ show: false });
    this.setData({ province: event.detail.values[0].name });
    this.setData({ city: event.detail.values[1].name });
    this.setData({ district: event.detail.values[2].name });
    this.setData({ area: event.detail.values[0].name + " " + event.detail.values[1].name + " " + event.detail.values[2].name});
  },
  async submitForm(options) {
    let res = await request.get('/address/add',{
      name: this.data.name,
      mobile: this.data.mobile,
      province: this.data.province,
      city: this.data.city,
      district: this.data.district,
      detailedAddress: this.data.detailedAddress,
      isDefault: 0,
      userId: '100030'
    })
    if(res.data.code === 200){
      wx.navigateTo({
        url: '/pages/address/list/list',
      })
    }
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