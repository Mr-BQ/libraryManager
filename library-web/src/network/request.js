import axios from "axios";
export function request(config){
    const instance = axios.create({
        baseURL:'http://localhost:8081',
        timeout:5000
    })

    instance.interceptors.request.use(config=>{
        return config
    },error => {

    })

    instance.interceptors.response.use(res=>{
        return res.data
    },error => {

    })
    return instance(config)
}

export function post(config){
    const instance = axios.create({
        baseURL:'http://localhost:8081',
        timeout:5000,
        method:'post'
    })

    instance.interceptors.request.use(config=>{
        return config
    },error => {

    })

    instance.interceptors.response.use(res=>{
        return res.data
    },error => {

    })
    return instance(config)
}