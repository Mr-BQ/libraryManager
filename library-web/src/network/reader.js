import {request} from "@/network/request";
export function getallreaders(){
    return request({
        url:'/findAllUser'
    })
}