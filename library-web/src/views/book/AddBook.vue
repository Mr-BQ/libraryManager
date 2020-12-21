<template>
  <div class="addbook">
    <div class="text" >Now you are <strong>adding</strong> a book to the book database , please input the book's information below.</div>
    <div class="input">
      <div class="form-group">
        <div class="inputarea">
          <label for="bookISBN">ISBN</label>
          <input type="text" class="form-control" id="bookISBN" placeholder="ISBN" v-model="book.bookISBN" @blur="validatebookISBN" @focus="validating.bookISBN=false">
        </div>
        <div v-show="validating.bookISBN && validate.bookISBN === 0" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ISBN is OK!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 1" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN must be 13 digits!!</div>
        <div v-show="validating.bookISBN && validate.bookISBN === 2" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> ISBN already exists!!</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="bookName">name</label>
          <input type="text" class="form-control" id="bookName" placeholder="book's name" v-model="book.bookName" @blur="validatebookName" @focus="validating.bookName=false">
        </div>
        <div v-show="validating.bookName && !validate.bookName" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input book's name!!</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="bookAuthor">author</label>
          <input type="text" class="form-control" id="bookAuthor" placeholder="author" v-model="book.bookAuthor" @blur="validatebookAuthor" @focus="validating.bookAuthor=false">
        </div>
        <div v-show="validating.bookAuthor && !validate.bookAuthor" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input book's author!!</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="publishHouse">publish house</label>
          <input type="text" class="form-control" id="publishHouse" placeholder="publish house" v-model="book.publishHouse" @blur="validatepublishHouse" @focus="validating.publishHouse=false">
        </div>
        <div v-show="validating.publishHouse && !validate.publishHouse" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> please input book's publish house!!</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="publishDate">publish year</label>
          <input type="text" class="form-control" id="publishDate" placeholder="publish year" v-model="book.publishDate" @blur="validatepublishDate" @focus="validating.publishDate=false">
        </div>
        <div v-show="validating.publishDate && !validate.publishDate" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> publish year must be 4 digits！！for example:2017</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="price">price</label>
          <input type="text" class="form-control" id="price" placeholder="price" v-model="book.price" @blur="validateprice" @focus="validating.price=false">
        </div>
        <div v-show="validating.price && !validate.price" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> price must be number!</div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="remain">remain</label>
          <input type="text" class="form-control" id="remain" placeholder="remain" v-model="book.remain" @blur="validateremain" @focus="validating.remain=false">
        </div>
        <div v-show="validating.remain && !validate.remain" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> remain must be integer!</div>
      </div>
      <div class="add">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">ADD</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':addsuccess}" >Successfully add book！</div>
  </div>
</template>

<script>
import {checkisbn,addbook} from "@/network/book";

export default {
  name: "AddBook",
  data(){
    return{
      book:{
        bookISBN:'',
        bookName:'',
        bookAuthor:'',
        publishHouse:'',
        publishDate:'',
        price:'',
        remain:''
      },
      validate:{
        bookISBN:'',
        bookName:false,
        bookAuthor:false,
        publishHouse:false,
        publishDate: false,
        price:false,
        remain:false
      },
      validating:{
        bookISBN:false,
        bookName:false,
        bookAuthor:false,
        publishHouse:false,
        publishDate: false,
        price:false,
        remain:false
      },
      addsuccess:false
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validatebookISBN();
        this.validatebookName();
        this.validatebookAuthor();
        this.validatepublishHouse();
        this.validatepublishDate();
        this.validateprice();
        this.validateremain();
        return
      }
      else{
        addbook(this.book).then((res)=>{
          if(res == '1'){
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
      if( !(/^[0-9]{13}$/.test(this.book.bookISBN))){
        this.validate.bookISBN = 1;
      }else{
        checkisbn(this.book.bookISBN).then(res=>{
          console.log(res)
          if(res == 'yes'){
            this.validate.bookISBN = 2;
          }
          else{
            this.validate.bookISBN = 0;
          }
        })
      }
    },
    validatebookName(){
      this.validating.bookName = true
      if(this.book.bookName.trim().length === 0){
        this.validate.bookName = false
      }else{
        this.validate.bookName = true
      }

    },
    validatebookAuthor(){
      this.validating.bookAuthor = true
      if(this.book.bookAuthor.trim().length === 0){
        this.validate.bookAuthor = false
      }else{
        this.validate.bookAuthor = true
      }

    },
    validatepublishHouse(){
      this.validating.publishHouse = true
      if(this.book.publishHouse.trim().length === 0){
        this.validate.publishHouse = false
      }else{
        this.validate.publishHouse = true
      }
    },
    validatepublishDate(){
      this.validating.publishDate = true
      if(!(/^[0-9]{4}$/.test(this.book.publishDate))){
        this.validate.publishDate = false
      }else{
        this.validate.publishDate = true
      }
    },
    validateprice(){
      this.validating.price = true
      if(!(/^\d+(\.\d+)?$/.test(this.book.price))){
        this.validate.price = false
      }else{
        this.validate.price = true
      }
    },
    validateremain(){
      this.validating.remain = true
      if(!(/^\d+$/.test(this.book.remain))){
        this.validate.remain = false
      }else{
        this.validate.remain = true
      }
    }

  },
  computed:{
    validated(){
      return this.validate.bookISBN == '0' && this.validate.bookName && this.validate.bookAuthor && this.validate.publishHouse && this.validate.publishDate && this.validate.price && this.validate.remain
    }
  },
  mounted() {
    this.$store.commit('changeSubactive','12')
    this.$store.commit('changeCurrentactive',1)
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
.addbook{
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