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
        <button type="button" class="btn btn-default" @click="signout">sign out</button>
      </div>
    </div>
    <div class="mid">
      <div class="menu">
        <ul class="nav nav-pills nav-stacked">
          <li :class="{active:this.$store.state.currentactive===0}" @click="listclick(0)">
            <a href="javascript:;">
              <span :class="{glyphicon:true,'glyphicon-menu-right':this.$store.state.currentactive!==0,'glyphicon-menu-down':this.$store.state.currentactive===0}" aria-hidden="true"></span> Reader Management
            </a>
            <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:this.$store.state.currentactive===0,disactivelist:this.$store.state.currentactive!==0}">
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='00'}" @click="sublistclick('00')">See All Readers</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='01'}" @click="sublistclick('01')">Query Reader</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='02'}" @click="sublistclick('02')">Add Reader</a></li>
            </ul>
          </li>
          <li :class="{active:this.$store.state.currentactive===1}" @click="listclick(1)">
            <a href="javascript:;">
              <span :class="{glyphicon:true,'glyphicon-menu-right':this.$store.state.currentactive!==1,'glyphicon-menu-down':this.$store.state.currentactive===1}" aria-hidden="true"></span> Book Management
            </a>
            <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:this.$store.state.currentactive===1,disactivelist:this.$store.state.currentactive!==1}">
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='10'}" @click="sublistclick('10')">See All Books</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='11'}" @click="sublistclick('11')">Query Book</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='12'}" @click="sublistclick('12')">Add Book</a></li>
            </ul>
          </li>
          <li :class="{active:this.$store.state.currentactive===2}" @click="listclick(2)">
            <a href="javascript:;">
              <span :class="{glyphicon:true,'glyphicon-menu-right':this.$store.state.currentactive!==2,'glyphicon-menu-down':this.$store.state.currentactive===2}" aria-hidden="true"></span> Borrow Management
            </a>
            <ul class="nav nav-pills nav-stacked sublist" :class="{activelist:this.$store.state.currentactive===2,disactivelist:this.$store.state.currentactive!==2}">
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='20'}" @click="sublistclick('20')">Borrowing Records</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='21'}" @click="sublistclick('21')">Query</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='22'}" @click="sublistclick('22')">Borrow</a></li>
              <li role="presentation"><a href="javascript:;" :class="{active:this.$store.state.subactive==='23'}" @click="sublistclick('23')">Return</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return{
    }
  },
  methods:{
    listclick(index){
      this.$store.commit('changeCurrentactive',index)

    },
    sublistclick(index){
      this.$store.commit('changeSubactive',index)
      switch (index){
        case '00':this.$router.replace('/home/reader/allreaders');break;
        case '01':this.$router.replace('/home/reader/queryreader');break;
        case '02':this.$router.replace('/home/reader/addreader');break;
        case '10':this.$router.replace('/home/book/allbooks');break;
        case '11':this.$router.replace('/home/book/querybook');break;
        case '12':this.$router.replace('/home/book/addbook');break;
        case '20':this.$router.replace('/home/borrow/allborrow');break;
        case '21':this.$router.replace('/home/borrow/queryborrow');break;
        case '22':this.$router.replace('/home/borrow/borrow');break;
        case '23':this.$router.replace('/home/borrow/return');break;
      }
    },
    signout(){
      window.sessionStorage.removeItem('username')
      this.$router.replace('/login')
    }
  },
  computed:{
    uname(){
      return window.sessionStorage.getItem('username')
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
    flex-direction: column;
    .mid{
      display: flex;
    }
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
    height: 660px;
    flex:1;
  }
</style>