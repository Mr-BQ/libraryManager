<template>
  <div class="updatereader">
    <div class="text" >Now you are <strong>updating</strong>updating a reader, please input the reader's information below.Note that some items can't be updated!</div>
    <div class="input">
      <div class="form-group cardnum">
        <div class="inputarea">
          <label for="cardnum">card number</label>
          <input type="text" class="form-control" id="cardnum"  v-model="reader.cardnum" readonly>
        </div>
      </div>
      <div class="form-group readername">
        <div class="inputarea">
          <label for="readername">name</label>
          <input type="text" class="form-control" id="readername"  v-model="reader.readername" readonly>
        </div>
        <div v-show="validating.readername && !validate.readername" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input reader's name!!</div>
      </div>
      <div class="gender">
        <div class="inputarea">
          <span>gender </span>
          <label class="radio-inline">
            <input type="radio" name="gender" id="male" value="男" v-model="reader.gender" disabled>
            <img src="@/assets/img/ye.png" alt="ye"> male
          </label>
          <label class="radio-inline">
            <input type="radio" name="gender" id="female" value="女" v-model="reader.gender" disabled>
            <img src="@/assets/img/girl.png" alt="girl"> female
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
      <div class="form-group borrownum">
        <div class="inputarea">
          <label for="borrownum">borrownum</label>
          <input type="text" class="form-control" id="borrownum" placeholder="borrow number" v-model="reader.borrownum" @blur="validateborrownum" @focus="validating.borrownum=false">
        </div>
        <div v-show="validating.borrownum && !validate.borrownum" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> borrow number should be 1 ~ 100 !!</div>
      </div>
      <div class="update">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">UPDATE</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':updatesuccess}" >Successfully update reader！</div>
  </div>
</template>

<script>
import {getReaderbyCardnum,updatereader} from "@/network/reader";
export default {
  name: "UpdateReader",
  data(){
    return{
      reader:{
        cardnum:'',
        readername:'',
        gender:'',
        department:'',
        class:'',
        grade:'',
        borrownum:''
      },
      validate:{
        department:true,
        class: true,
        grade: true,
        borrownum:true
      },
      validating:{
        department:false,
        class: false,
        grade: false,
        borrownum:false
      },
      updatesuccess:false
    }
  },
  computed:{
    validated(){
      return this.validate.department && this.validate.class && this.validate.grade && this.validate.borrownum
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validating.department = true
        this.validating.class = true
        this.validating.grade = true
        this.validating.borrownum = true
        if(this.reader.department.trim().length === 0){
          this.validate.department = false
        }
        if(this.reader.class.trim().length === 0){
          this.validate.class = false
        }
        if(!(/^[0-9]{4}$/.test(this.reader.grade))){
          this.validate.grade = false
        }
        if(!(/^[0-9]{1,3}$/.test(this.reader.borrownum))){
          this.validate.borrownum = false
        }else if(parseInt(this.reader.borrownum) < 1 || parseInt(this.reader.borrownum) > 100){
          this.validate.borrownum = false
        }
        return
      }
      else{
        updatereader(this.reader).then(res=>{
          if(res == '1'){
            this.updatesuccess = true
            setTimeout(()=>{
              this.updatesuccess = false
              this.$router.replace('/home')
            },2500)
          }
        })
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
    },
    validateborrownum(){
      this.validating.borrownum = true
      if((/^[0-9]{1,3}$/.test(this.reader.borrownum))){
        if(parseInt(this.reader.borrownum) > 0 && parseInt(this.reader.borrownum) <= 100){
          this.validate.borrownum = true
        }else{
          this.validate.borrownum = false
        }
      }
      else {
        this.validate.borrownum = false
      }
    }
  },
  mounted() {
    getReaderbyCardnum(this.$route.params.cardnum).then(res => {
      console.log(res)
      this.reader.cardnum = res.userCardNum
      this.reader.readername = res.userName
      this.reader.gender = res.userSex
      this.reader.department = res.userDepa
      this.reader.class = res.userClass
      this.reader.grade = res.grade
      this.reader.borrownum = res.userBorrowNum
    })

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
.updatereader{
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
  }
  .gender{
    margin: 15px 0;
    display: flex;
    .alertandinput();
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
  .update{
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
  overflow: hidden;
  height: 0px;
  width: 300px;
  background-color: rgba(41, 128, 185,.7);
  color: white;
  line-height: 40px;
  font-size: 20px;
  text-align: center;
  transition: opacity 500ms;
}

.toast-show{
  opacity: 1;
  height: 40px;
}

</style>