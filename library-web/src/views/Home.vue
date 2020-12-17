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
           <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:currentactive===0,disactivelist:currentactive!==0}">
             <li role="presentation"><a href="javascript:;" :class="{active:subactive==='00'}" @click="sublistclick('00')">See All Readers</a></li>
             <li role="presentation"><a href="javascript:;" :class="{active:subactive==='01'}" @click="sublistclick('01')">Query Reader</a></li>
             <li role="presentation"><a href="javascript:;" :class="{active:subactive==='02'}" @click="sublistclick('02')">Add Reader</a></li>
           </ul>
       </li>
       <li :class="{active:currentactive===1}" @click="listclick(1)">
         <a href="javascript:;">
           <span :class="{glyphicon:true,'glyphicon-menu-right':currentactive!==1,'glyphicon-menu-down':currentactive===1}" aria-hidden="true"></span> Book Management
         </a>
         <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:currentactive===1,disactivelist:currentactive!==1}">
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='10'}" @click="sublistclick('10')">See All Books</a></li>
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='11'}" @click="sublistclick('11')">Query Book</a></li>
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='12'}" @click="sublistclick('12')">Add Book</a></li>
         </ul>
       </li>
       <li :class="{active:currentactive===2}" @click="listclick(2)">
         <a href="javascript:;">
           <span :class="{glyphicon:true,'glyphicon-menu-right':currentactive!==2,'glyphicon-menu-down':currentactive===2}" aria-hidden="true"></span> Borrow Management
         </a>
         <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:currentactive===2,disactivelist:currentactive!==2}">
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='20'}" @click="sublistclick('20')">See All Books</a></li>
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='21'}" @click="sublistclick('21')">Query Book</a></li>
           <li role="presentation"><a href="javascript:;" :class="{active:subactive==='22'}" @click="sublistclick('22')">Add Book</a></li>
         </ul>
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
      currentactive:0,
      subactive:'00'
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
    },
    sublistclick(index){
      this.subactive = index
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
      background-color: #fff;
      margin: 5px 0;
    }
    .sublist{
      font-size: 14px;
      transition: all .8s ease-in-out;
      li{
        margin:0 20px;
      }
      .active{
        background-color: #fff;
        font-weight: bold;
        border-bottom: 2px solid #95a5a6;

      }
    }
  }

  .activelist{
    height:150px;
    opacity: 100%;
  }
  .disactivelist{
    height: 0;
    overflow: hidden;
    opacity: 0;
  }

  .content{
    flex:1;
  }
</style>