<template>
  <div class="borrow">
    <div class="text" >Now you are <strong>adding</strong> a borrow record, please input the reader's card number and book's ISBN below.</div>
    <div class="input">
      <div class="form-group">
        <div class="inputarea">
          <label for="bookisbn">book ISBN</label>
          <input type="text" class="form-control" id="bookisbn" placeholder="ISBN" v-model="borrow.bookISBN" @blur="validatebookISBN" @focus="validating.bookISBN=false">
        </div>
        <div v-show="validating.bookISBN && validate.bookISBN === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ISBN is OK!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN must be 13 digits!!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Remain is less than 1, can not be borrowed!! </div>
        <div v-show="validating.bookISBN && validate.bookISBN === 3" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN does not exist!! </div>

      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="cardnum">reader card number</label>
          <input type="text" class="form-control" id="cardnum" placeholder="reader card number" v-model="borrow.userCardNum" @blur="validatecardNum" @focus="validating.cardNum=false">
        </div>
        <div v-show="validating.cardNum && validate.cardNum === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Card number is OK!</div>
        <div v-show="validating.cardNum && validate.cardNum === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Card number must be 10 digits!!</div>
        <div v-show="validating.cardNum && validate.cardNum === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Borrow number less than 1,can not borrow!!</div>
        <div v-show="validating.cardNum && validate.cardNum === 3" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Card number does not exist!!</div>

      </div>
      <div class="add">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">ADD</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':addsuccess}" >Successfully add borrow record！</div>
  </div>
</template>

<script>
import {checkremain, checkBorrownum, addborrow} from "@/network/borrow";

export default {
  name: "Borrow",
  data(){
    return{
      borrow:{
        userCardNum:'',
        bookISBN:''
      },
      validate:{
        bookISBN:'',
        cardNum:''
      },
      validating:{
        bookISBN:false,
        cardNum:false
      },
      addsuccess:false
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validatebookISBN()
        this.validatecardNum()
        return
      }else{
        addborrow(this.borrow).then(res=>{
          if(res == 1){
            this.addsuccess = true
            setTimeout(()=>{
              this.addsuccess = false
              this.$router.replace('/refresh')
            },2500)
          }
        })
      }

    },
    validatebookISBN(){
      this.validating.bookISBN = true;
      if( !(/^[0-9]{13}$/.test(this.borrow.bookISBN))){
        this.validate.bookISBN = 1;
      }else{
        checkremain(this.borrow.bookISBN).then(res=>{
          console.log(res);
          if(res.length === 0){
            this.validate.bookISBN = 3
          }else if(parseInt(res) < 1){
            this.validate.bookISBN = 2
          }else{
            this.validate.bookISBN = 0
          }
        })
      }
    },
    validatecardNum(){
      this.validating.cardNum = true;
      if( !(/^[0-9]{10}$/.test(this.borrow.userCardNum)) ){
        this.validate.cardNum = 1;
      }else{
        checkBorrownum(this.borrow.userCardNum).then(res=>{
          console.log(res);
          if(res.length === 0){
            this.validate.cardNum = 3;
          }else if(parseInt(res) < 1){
            this.validate.cardNum = 2;
          } else{
            this.validate.cardNum = 0;
          }
        })
      }
    }
  },
  computed:{
    validated(){
      return this.validate.bookISBN === 0 && this.validate.cardNum === 0
    }
  },
  mounted() {
    this.$store.commit('changeSubactive','22')
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
.borrow{
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 660px;
  overflow-y: scroll;
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