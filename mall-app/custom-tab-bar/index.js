// custom-tab-bar/index.js
Component({
  data: {
    active: 0,
    list: [
      {
        "url": "/pages/tabbar/home/home",
        "icon": "home-o",
        "text": "首页"
      },
      {
        "url": "/pages/tabbar/classify/classify",
        "icon": "apps-o",
        "text": "分类"
      },
      {
        "url": "/pages/tabbar/cart/cart",
        "icon": "cart-o",
        "text": "购物车"
      },
      {
        "url": "/pages/tabbar/mine/mine",
        "icon": "user-o",
        "text": "我的"
      }
    ]
  },
  methods: {
    onChange(e) {
        console.log(e,'e')
        this.setData({ active: e.detail });
        wx.switchTab({
          url: this.data.list[e.detail].url
        });
    },
    init() {
        const page = getCurrentPages().pop();
        this.setData({
        　  active: this.data.list.findIndex(item => item.url === `/${page.route}`)
        });
    }
   }
  })