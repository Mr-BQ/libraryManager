import {post} from "@/network/request";
export function checkin(username,password){
    return post({
        url:'/checkin',
        params:{
            username,
            password
        }
    })
}