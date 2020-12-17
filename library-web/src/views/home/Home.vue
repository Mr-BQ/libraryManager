<template>
  <div class="home">
    <div class="topbar">
      <div class="logo">
        <img src="@/assets/img/sweat.png" alt="chabuduodel">
      </div>
      <div class="title">library Manage System</div>
      <div class="user">
        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
        <span>{{uname}}</span>
        <button type="button" class="btn btn-default">sign out</button>
      </div>
    </div>
    <div class="menu">
      <ul class="nav nav-pills nav-stacked">
       <li :class="{active:currentactive===0}" @click="listclick(0)">
         <a href="javascript:;">
           <span :class="{glyphicon:true,'glyphicon-menu-right':currentactive!==0,'glyphicon-menu-down':currentactive===0}" aria-hidden="true"></span> Reader Management
         </a>
       </li>
       <li :class="{active:currentactive===1}" @click="listclick(1)">
         <a href="javascript:;">
           <span :class="{glyphicon:true,'glyphicon-menu-right':currentactive!==1,'glyphicon-menu-down':currentactive===1}" aria-hidden="true"></span> Book Management
         </a>
       </li>
       <li :class="{active:currentactive===2}" @click="listclick(2)">
         <a href="javascript:;">
           <span :class="{glyphicon:true,'glyphicon-menu-right':currentactive!==2,'glyphicon-menu-down':currentactive===2}" aria-hidden="true"></span> Borrow Management
         </a>
       </li>
      </ul>
    </div>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      uname:'shilei',
      currentactive:0
    }
  },
  methods:{
    listclick(index){
      this.currentactive = index
      switch (index){
        case 0:this.$router.replace('/home/reader');break;
        case 1:this.$router.replace('/home/book');break;
        case 2:this.$router.replace('/home/borrow');break;
      }


    }
  }
}
</script>

<style scoped lang="less">
  @topbarheight:62px;
  @fontfamily:-apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
  .home{
    font-family: @fontfamily;
    font-weight: lighter;
    display: flex;
    flex-wrap: wrap;
  }
  .topbar{
    height: @topbarheight;
    background-color: rgb(36, 41, 46);
    width: 100%;
    .logo{
      float: left;
      height: 100%;
      line-height: @topbarheight;
      //background-color: #ff8198;
      margin-left: 24px;
      img{
        height: 60%;
      }
    }
    .title{
      float: left;
      margin-left: 10px;
      color: rgba(255,255,255);
      font-size: 20px;
      line-height: @topbarheight;
    }
    .user{
      padding: 0 20px;
      float: right;
      font-size: 20px;
      //background-color: #bbffaa;
      line-height: @topbarheight;
      color: rgb(255,255,255);
      span{
        margin: 0 4px;
      }
      .btn{
        height: 30px;
        line-height: 15px;
        font-family: @fontfamily;
        font-weight: lighter;
        margin-bottom: 6px;
        margin-left: 20px;
        background-color: transparent;
        color: rgb(255,255,255);
      }
      .btn:hover{
        background-color: #1e90ff;
      }
    }
  }
  .menu{
    height: 660px;
    width: 20%;
    background-color: #fff;
    border-right: 1px solid #ced6e0;
    color: #0366d6;
    font-weight: normal;
    font-size: 18px;
    li{
      margin: 5px 0;
    }
  }
</style>