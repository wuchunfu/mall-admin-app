import axios from "axios";
import { ElMessage } from 'element-plus';
import router from "../router";

axios.defaults.baseURL = "http://localhost:8800";

const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json;charset=utf-8'
    }
});

request.interceptors.request.use(config => {
    config.headers['Authorization'] = localStorage.getItem('token')
    return config
})

request.interceptors.response.use(response => {
    let res = response.data;
    if (res.code === 200){
        return response;
    } else {
        ElMessage.error(res.message);
        return Promise.reject(res.message)
    }
},error => {
    if (error.response.status === 401 || error.response.status === 500){
        router.push('/');
    }
    ElMessage.error(error.message);
    return Promise.reject(error.message)
})

export default request;