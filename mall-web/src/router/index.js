import { createWebHashHistory, createRouter } from 'vue-router';
import Login from "../views/Login";
import OrderList from "../views/order/OrderList";
import ChangePassword from "../views/user/ChangePassword";
import AddProduct from "../views/product/AddProduct";
import BrandManage from "../views/product/BrandManage";
import ProductList from "../views/product/ProductList";
import Info from "../views/product/add/Info";
import Describe from "../views/product/add/Describe";
import Logistics from "../views/product/add/Logistics";
import Service from "../views/product/add/Service";
import CategoryManage from "../views/product/CategoryManage";
import Home from "../views/Home";
import ResultPage from "../views/result/ResultPage";
import UserManage from "../views/user/UserManage";
import OrderDetail from "../views/order/OrderDetail";
import EditProduct from "../views/product/EditProduct";
import RefundProcess from "../views/order/RefundProcess";

const routes = [
    {
        path: '/',
        component: Login,
    },
    {   path: '/home',
        component: Home,
        redirect: '/product/list',
        children: [
            {
                path: '/product/list',
                name: 'productList',
                component: ProductList,
            },
            {
                path: '/product/add',
                name: 'addProduct',
                component: AddProduct,
                children: [
                    {
                        path: '/product/add/',
                        name: 'info',
                        component: Info,
                    },
                    {
                        path: '/product/describe/add',
                        name: 'describe',
                        component: Describe,
                    },
                    {
                        path: '/product/logistics/add',
                        name: 'logistics',
                        component: Logistics,
                    },
                    {
                        path: '/product/service/add',
                        name: 'service',
                        component: Service,
                    },
                ]
            },
            {
                path: '/product/edit',
                name: 'editProduct',
                component: EditProduct,
            },
            {
                path: '/product/category',
                name: 'productCategory',
                component: CategoryManage,
            },
            {
                path: '/product/brand',
                name: 'productBrand',
                component: BrandManage,
            },
            {
                path: '/order/list',
                name: 'orderList',
                component: OrderList,
            },
            {
                path: '/order/refund',
                name: 'orderRefund',
                component: RefundProcess,
            },
            {
                path: '/order/detail',
                name: 'orderDetail',
                component: OrderDetail,
            },
            {
                path: '/user/manage',
                name: 'userManage',
                component: UserManage,
            },
            {
                path: '/change/password',
                name: 'changePassword',
                component: ChangePassword,
            },
            {
                path: '/result/page',
                name: 'resultPage',
                component: ResultPage
            }
        ],
    },
]

const router = createRouter({
    history: createWebHashHistory(), routes
})

export default router;