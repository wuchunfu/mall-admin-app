// pages/login/login.js
import request from '../../utils/request'

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  // 用户点击授权登录
  userLogin(e) {
    wx.login({
      async success (res) {
        if (res.code) {
          //发起登录请求
          let response = await request.post('/login', { 
            code: res.code,
            username: 'weappuser',
            password: '123'
          })
          if(response.data.code === 200){
            wx.setStorage({ key: "Authorization", data: response.header.Authorization});
            wx.setStorage({ key: "userId", data: response.data.data});
          }
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res)
        wx.setStorage({ key: "avatarUrl", data: res.userInfo.avatarUrl });
        wx.setStorage({ key: "nickName", data: res.userInfo.nickName });
        wx.switchTab({ url: '/pages/tabbar/home/home' })
      }
    })
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