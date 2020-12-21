import {request,post} from "@/network/request";

export function getallbooks(){
    return request({
        url:'/findAllBook'
    })
}

export function querybook(book){
    return post({
        url:'/queryBook',
        params:book
    })
}

export function checkisbn(isbn){
    return request({
        url:'/checkISBN',
        params:{
            isbn
        }
    })
}

export function addbook(book){
    return post({
        url:'/addBook',
        params:book
    })
}

export function getbookbyisbn(isbn){
    return request({
        url:'/findBookByISBN',
        params:{
            isbn
        }
    })
}

export function updatebook(book){
    return post({
        url:'/updateBook',
        params:book
    })
}

export function deletebook(isbn){
    return post({
        url:'/deleteBook',
        params:{
            isbn
        }
    })
}
