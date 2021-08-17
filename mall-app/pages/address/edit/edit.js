// pages/address/edit/edit.js
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
    addressId: ''
  },
  showPopup() {
    this.setData({ show: true });
  },
  onClickConfirm(event) {
    this.setData({ show: false });
    this.setData({ province: event.detail.values[0].name });
    this.setData({ city: event.detail.values[1].name });
    this.setData({ district: event.detail.values[2].name });
    this.setData({ area: event.detail.values[0].name + " " + event.detail.values[1].name + " " + event.detail.values[2].name});
  },
  onClickcancel() {
    this.setData({ show: false });
  },
  onClickChange() {
    
  },
  async submitForm(options) {
    let res = await request.get('/address/update',{
      name: this.data.name,
      mobile: this.data.mobile,
      province: this.data.province,
      city: this.data.city,
      district: this.data.district,
      detailedAddress: this.data.detailedAddress,
      isDefault: 0,
      id: this.data.addressId
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
  onLoad: async function (options) {
    this.setData({
      addressId: options.id
    })
    let res = await request.get('/address/info',{ id: options.id });
    if(res.data.code === 200){
      var response = res.data.data;
      this.setData({
        name: response.name,
        mobile: response.mobile,
        province: response.province,
        city: response.city,
        district: response.district,
        detailedAddress: response.detailedAddress,
        area: response.province + ' ' + response.city + ' ' + response.district
    })
    }
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