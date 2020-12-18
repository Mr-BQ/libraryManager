<template>
  <div class="addreader">
    <div class="text">Now you are adding a reader to the reader database , please input the reader's information below.</div>
    <div class="input">
      <div class="form-group cardnum">
        <div class="inputarea">
          <label for="cardnum">card number</label>
          <input type="text" class="form-control" id="cardnum" placeholder="card number" v-model="reader.cardnum" @blur="validatecardnum" @focus="validating.cardnum=false">
        </div>
        <div v-show="validating.cardnum && validate.cardnum === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> card number is OK!</div>
        <div v-show="validating.cardnum && validate.cardnum === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> card number must be 10 digits!!</div>
        <div v-show="validating.cardnum && validate.cardnum === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> card number already exists!!</div>
      </div>
      <div class="form-group readername">
        <div class="inputarea">
          <label for="readername">name</label>
          <input type="text" class="form-control" id="readername" placeholder="reader's name" v-model="reader.readername" @blur="validatename" @focus="validating.readername=false">
        </div>
        <div v-show="validating.readername && !validate.readername" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input reader's name!!</div>
      </div>
      <div class="gender">
        <div class="inputarea">
          <span>gender </span>
          <label class="radio-inline">
            <input type="radio" name="gender" id="male" value="男" v-model="reader.gender" @blur="validategender" @focus="validating.gender=false">
            <img src="@/assets/img/ye.png" alt="ye"> male
          </label>
          <label class="radio-inline">
            <input type="radio" name="gender" id="female" value="女" v-model="reader.gender" @blur="validategender" @focus="validating.gender=false">
            <img src="@/assets/img/girl.png" alt="ye"> female
          </label>
        </div>
        <div v-show="validating.gender && !validate.gender" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please choose gender!!</div>
      </div>
      <div class="form-group department">
        <div class="inputarea">
          <label for="department">department</label>
          <input type="text" class="form-control" id="department" placeholder="department" v-model="reader.department" @blur="validatedepa" @focus="validating.department=false">
        </div>
        <div v-show="validating.department && !validate.department" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input reader's department!!</div>
      </div>
      <div class="form-group class">
        <div class="inputarea">
          <label for="department">class</label>
          <input type="text" class="form-control" id="class" placeholder="class" v-model="reader.class" @blur="validateclass" @focus="validating.class=false">
        </div>
        <div v-show="validating.class && !validate.class" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input reader's class!!</div>
      </div>
      <div class="form-group grade">
        <div class="inputarea">
          <label for="department">grade</label>
          <input type="text" class="form-control" id="grade" placeholder="grade" v-model="reader.grade" @blur="validategrade" @focus="validating.grade=false">
        </div>
        <div v-show="validating.grade && !validate.grade" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> grade must be 4 digits！！forexample:2017</div>
      </div>
      <div class="add">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">ADD</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':addsuccess}" >Successfully add reader！</div>
  </div>
</template>

<script>
import {checkcardnum,adduser} from "@/network/reader";

export default {
  name: "AddReader",
  data(){
    return{
      reader:{
        cardnum:'',
        readername:'',
        gender:'',
        department:'',
        class:'',
        grade:''
      },
      validate:{
        cardnum:'',
        readername:false,
        gender:false,
        department:false,
        class: false,
        grade: false
      },
      validating:{
        cardnum:false,
        readername:false,
        gender:false,
        department:false,
        class: false,
        grade: false
      },
      addsuccess:false
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validating.cardnum = true
        this.validating.readername = true
        this.validating.department = true
        this.validating.class = true
        this.validating.grade = true
        if(!this.validate.gender){
          this.validating.gender = true
        }
        if( !(/^[0-9]{10}$/.test(this.reader.cardnum)) ){
          this.validate.cardnum = 1;
        }
        if(this.reader.readername.trim().length === 0){
          this.validate.readername = false
        }
        if(this.reader.department.trim().length === 0){
          this.validate.department = false
        }
        if(this.reader.class.trim().length === 0){
          this.validate.class = false
        }
        if(!(/^[0-9]{4}$/.test(this.reader.grade))){
          this.validate.grade = false
        }
        return
      }
      else{
        adduser(this.reader).then((res)=>{
          if(res == '1'){
            this.addsuccess = true
            setTimeout(()=>{
              this.addsuccess = false
            },1500)
          }
        })
      }

    },
    validatecardnum(){
      this.validating.cardnum = true;
      if( !(/^[0-9]{10}$/.test(this.reader.cardnum)) ){
        this.validate.cardnum = 1;
      }else{
        checkcardnum(this.reader.cardnum).then(res=>{
          if(res == 'yes'){
            this.validate.cardnum = 2;
          }
          else{
            this.validate.cardnum = 0;
          }
        })
      }
    },
    validatename(){
      this.validating.readername = true
      if(this.reader.readername.trim().length === 0){
        this.validate.readername = false
      }else{
        this.validate.readername = true
      }

    },
    validategender(){
      if(this.reader.gender.length !== 0){
        this.validate.gender = true
      }else {
        this.validate.gender = false
      }
    },
    validatedepa(){
      this.validating.department = true
      if(this.reader.department.trim().length === 0){
        this.validate.department = false
      }else{
        this.validate.department = true
      }
    },
    validateclass(){
      this.validating.class = true;
      if(this.reader.class.trim().length === 0){
        this.validate.class = false
      }else{
        this.validate.class = true
      }

    },
    validategrade(){
      this.validating.grade = true
      if(!(/^[0-9]{4}$/.test(this.reader.grade))){
        this.validate.grade = false
      }else{
        this.validate.grade = true
      }
    }
  },
  computed:{
    validated(){
      return this.validate.cardnum == '0' && this.validate.readername && this.validate.gender && this.validate.department && this.validate.class && this.validate.grade
    }
  }
}
</script>

<style scoped lang="less">
@fontfamily:-apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
  .alertandinput{
    .inputarea{
      width: 60%;
      input{
        font-weight: normal;
      }
    }
    .alert{
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      padding:0;
      margin: 0;
      height: 34px;
      line-height: 34px;
      align-self: flex-end;
      padding:0 10px;
      margin-left: 10px;
      font-size: 15px;
      span{
        font-size: 15px;
      }
    }
  }
  .addreader{
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .text{
    width: 80%;
    background-color:rgba(26, 188, 156,.8);
    color: #000;
    font-size: 20px;
    padding: 5px 20px;
  }
  .input{
    padding: 10px 0;
    margin: 20px 0;
    width: 70%;
    label{
      font-family: @fontfamily;
      font-weight: lighter;
      font-size: 20px;
    }
    .form-group{
      display: flex;
      .alertandinput();
      //.inputarea{
      //
      //  width: 60%;
      //}
      //.alert{
      //  padding:0;
      //  margin: 0;
      //  height: 34px;
      //  line-height: 34px;
      //  align-self: flex-end;
      //  padding:0 10px;
      //  margin-left: 10px;
      //}
    }
    .gender{
      margin: 15px 0;
      display: flex;
      .alertandinput();
      //.inputarea{
      //  width: 60%;
      //}
      //.alert{
      //  padding:0;
      //  margin: 0;
      //  height: 34px;
      //  line-height: 34px;
      //  align-self: flex-end;
      //  padding:0 10px;
      //  margin-left: 10px;
      //}
      span{
        font-size: 20px;
      }
      label{
        margin: 0 10px;
        font-size: 15px;
        img{
          height: 25px;
        }
      }
    }
    .add{
      width: 20%;
      margin:20px 20%;
    }
  }


  .toast{

    opacity: 0;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    height: 40px;
    width: 300px;
    background-color: rgba(41, 128, 185,.7);
    color: white;
    line-height: 40px;
    font-size: 20px;
    text-align: center;
    transition: opacity 500ms ease-in-out;
  }

  .toast-show{

    opacity: 1;
  }



</style>