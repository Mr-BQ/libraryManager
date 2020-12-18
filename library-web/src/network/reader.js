import {request,post} from "@/network/request";
export function getallreaders(){
    return request({
        url:'/findAllUser'
    })
}

export function checkcardnum(cardnum){
    return request({
        url:'/checkcardnum',
        params:{
            cardnum
        }
    })
}

export function adduser(user){
    return post({
        url:'/adduser',
        params:{
            userCardNum:user.cardnum,
            userName:user.readername,
            userSex:user.gender,
            userDepa:user.department,
            userClass:user.class,
            Grade:user.grade
        }
    })
}