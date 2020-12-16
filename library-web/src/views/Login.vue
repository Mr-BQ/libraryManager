<template>
  <div class="login">
    <div class="outter">
      <div class="backimg">
        <img src="@/assets/img/background.svg" alt="backimg">
      </div>
      <div class="loginform">
        <div class="logo">
          <img src="@/assets/img/sweat.png" alt="chabuduodele">
        </div>
        <div class="text">
          <p>Sign in to Library Manage System</p>
        </div>
        <div class="inputarea">
          <div class="inputUsername">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="Username" v-model="username">
          </div>
          <div class="inputPassword">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password" v-model="password">
          </div>
          <div class="signin">
            <button type="button" class="btn btn-success" @click="signin">Sign in</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {checkin} from "@/network/login";

export default {
  name: "Login",
  data(){
    return{
      username:'',
      password:''
    }
  },
  methods:{
    signin(){
      checkin(this.username,this.password).then(res=>{
        
      })
    }
  }
}
</script>

<style scoped lang="less">
  .outter{
    width: 100%;
    height: 600px;
    background-color: #ced6e0;
    display: flex;
    position: absolute;
    top: 10%;
    left: 50%;
    transform: translate(-50%,0);
    align-items: center;
    justify-content: space-around;
    @media screen and (max-width:800px ){
      justify-content: center;
    }
  }
  .backimg{
    width: 50%;
    @media screen and (max-width:1100px ){
      width: 30%;
    }
    @media screen and (max-width:800px ){
      display: none;
    }
    img{
      width: 100%;
    }
  }
  .loginform{
    width: 30%;
    //background-color: #bfa;
    display: flex;
    flex-direction: column;
    align-items: center;

    @media screen and (max-width:1100px ){
      width: 40%;
    }
    @media screen and (max-width:800px ){
      width: 60%;
    }
    .text{
      font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
      font-size: 24px;
      color: #24292e;
      text-align: center;
      font-weight: lighter;
      margin:20px 0;
    }
    .inputarea{
      width: 100%;
      height:40%;
      background-color: rgb(246, 248, 250);
      border: 1px solid #eaecef;
      border-radius: 6px 6px 6px 6px;
      display: flex;
      flex-direction: column;
      //background-color: #31b0d5;
      align-items: center;
      padding-top: 20px;
      .inputUsername{
        width: 90%;
      }
      .inputPassword{
        margin:15px 0;
        width: 90%;
      }
      .signin{
        width: 90%;
        margin-bottom: 20px;
        button{
          width: 100%;
        }
      }
    }
  }
</style>