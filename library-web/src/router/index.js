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

const readerroutes=[
  {
    path:'/',
    redirect:'allreaders'
  },
  {
    path:'allreaders',
    component:() => import('@/views/reader/AllReaders')
  }
]

const homeroutes=[
  {
    path:'reader',
    component:() => import('@/views/reader/Reader'),
    children:readerroutes
  },
  {
    path:'book',
    component:() => import('@/views/book/Book')
  },
  {
    path:'borrow',
    component:() => import('@/views/borrow/Borrow')
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
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
