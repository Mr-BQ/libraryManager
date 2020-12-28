import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const originalReplace = VueRouter.prototype.replace
VueRouter.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch(err => err)
}


const homeroutes=[
  {
    path:'/',
    redirect:'reader/allreaders'
  },
  {
    path:'reader/allreaders',
    component:() => import('@/views/reader/AllReaders')
  },
  {
    path:'reader/queryreader',
    component:() => import('@/views/reader/QueryReader')
  },
  {
    path:'reader/addreader',
    component:() => import('@/views/reader/AddReader')
  },
  {
    path:'reader/updatereader/:cardnum',
    component:() => import('@/views/reader/UpdateReader')
  },
  {
    path:'book/allbooks',
    component:() => import('@/views/book/AllBooks')
  },
  {
    path:'book/querybook',
    component:() => import('@/views/book/QueryBook')
  },
  {
    path:'book/addbook',
    component:() => import('@/views/book/AddBook')
  },
  {
    path:'book/updatebook/:isbn',
    component:() => import('@/views/book/UpdateBook')
  },
  {
    path:'borrow/allborrow',
    component:() => import('@/views/borrow/BorrowRecords')
  },
  {
    path:'borrow/queryborrow',
    component:() => import('@/views/borrow/Query')
  },
  {
    path:'borrow/borrow',
    component:() => import('@/views/borrow/Borrow')
  },
  {
    path:'borrow/return',
    component:() => import('@/views/borrow/Return')
  }


]

const routes = [
  {
    path: '/',
    redirect:'/login'
  },
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('@/views/Login')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/views/Home'),
    children:homeroutes
  },
  {
    path:'/refresh',
    name:'refresh',
    component:() => import('@/components/Refresh')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
