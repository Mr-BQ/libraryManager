import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const homeroutes=[
  {
    path:'reader',
    component:() => import('@/views/home/childviews/Reader')
  },
  {
    path:'book',
    component:() => import('@/views/home/childviews/Book')
  },
  {
    path:'borrow',
    component:() => import('@/views/home/childviews/Borrow')
  }
]

const routes = [
  // {
  //   path: '/',
  //   redirect:'/login'
  // },
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
    component: () => import('@/views/home/Home'),
    children:homeroutes
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
