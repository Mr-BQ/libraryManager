<template>
  <div class="return">
    <div class="text" >Now you are <strong>returning</strong> a book, please input the reader's card number and book's ISBN below.</div>
    <div class="input">
      <div class="form-group">
        <div class="inputarea">
          <label for="bookisbn">book ISBN</label>
          <input type="text" class="form-control" id="bookisbn" placeholder="ISBN" v-model="borrow.bookISBN" @blur="validatebookISBN" @focus="validating.bookISBN=false">
        </div>
        <div v-show="validating.bookISBN && validate.bookISBN === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ISBN is OK!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN must be 13 digits!!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN does not exist!! </div>

      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="cardnum">reader card number</label>
          <input type="text" class="form-control" id="cardnum" placeholder="reader card number" v-model="borrow.userCardNum" @blur="validatecardNum" @focus="validating.cardNum=false">
        </div>
        <div v-show="validating.cardNum && validate.cardNum === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Card number is OK!</div>
        <div v-show="validating.cardNum && validate.cardNum === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Card number must be 10 digits!!</div>
        <div v-show="validating.cardNum && validate.cardNum === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Card number does not exist!!</div>

      </div>
      <div class="add">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">RETURN BOOK</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':addsuccess}" >Successfully return book！</div>
    <div class="toast" :class="{'toast-show-fail':addfail}" >{{failmsg}}</div>
  </div>
</template>

<script>
import {returnBook, checkBorrownum, checkremain} from "@/network/borrow";

export default {
  name: "Return",
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
      addsuccess:false,
      addfail:false,
      failmsg:''
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validatebookISBN()
        this.validatecardNum()
        return
      }else{
        returnBook(this.borrow).then(res=>{
          if(res == 1){
            this.addsuccess = true
            setTimeout(()=>{
              this.addsuccess = false
              this.$router.replace('/refresh')
            },2500)
          }else{
            if(res == 0){
              this.failmsg = 'Unable to find the relevant borrowing records, return failed!!'
            }else{
              this.failmsg = 'the book has been returned!!'
            }
            this.addfail = true
            setTimeout(()=>{
              this.addfail = false
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
            this.validate.cardNum = 2;
          }else{
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
    this.$store.commit('changeSubactive','23')
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
.return{
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

.toast-show-fail{
  opacity: 1;
  height: 40px;
  background-color: rgba(231, 76, 60,.7);
}



</style>