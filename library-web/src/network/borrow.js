import {post, request} from "@/network/request";

export function getallborrow(){
    return request({
        url:'/findAllBorrow'
    })
}
export function queryborrow(borrow){
    return post({
        url:'/queryBorrow',
        params:borrow
    })
}

export function checkremain(isbn){
    return request({
        url:'/checkRemain',
        params:{
            isbn
        }
    })
}

export function checkBorrownum(cardnum){
    return request({
        url:'checkBorrownum',
        params:{
            cardnum
        }
    })
}

export function addborrow(borrow){
    return post({
        url:'/addBorrow',
        params:borrow
    })
}

export function returnBook(borrow){
    return post({
        url:'/returnBook',
        params:borrow
    })
}