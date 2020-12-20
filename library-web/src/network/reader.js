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

export function getReaderbyCardnum(cardnum){
    return request({
        url:'/findUserByCardNum',
        params:{
            cardnum
        }
    })
}

export function updatereader(user){
    return post({
        url:'/updateUser',
        params:{
            userCardNum:user.cardnum,
            userDepa:user.department,
            userClass:user.class,
            Grade:user.grade,
            userBorrowNum:user.borrownum
        }

    })
}

export function queryreader(user){
    return post({
        url:'/queryUser',
        params:{
            // userCardNum:user.cardnum.length===0?null:user.cardnum,
            // userName:user.readername.length===0?null:user.readername,
            // userSex:user.gender.length===0?null:user.gender,
            // userDepa:user.department.length===0?null:user.department,
            // userClass:user.class.length===0?null:user.class,
            // Grade:user.grade.length===0?null:user.grade,
            // userBorrowNum:user.borrownum.length===0?null:user.borrownum
            userCardNum:user.cardnum,
            userName:user.readername,
            userSex:user.gender,
            userDepa:user.department,
            userClass:user.class,
            Grade:user.grade,
            userBorrowNum:user.borrownum
        }
    })
}