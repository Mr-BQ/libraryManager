<template>
  <div class="updatebook">
    <div class="text" >Now you are <strong>updating</strong> a book, please input the book's information below.Note that some items can't be updated!</div>
    <div class="input">
      <div class="form-group">
        <div class="inputarea">
          <label for="bookISBN">ISBN</label>
          <input type="text" class="form-control" id="bookISBN" placeholder="ISBN" v-model="book.bookISBN" readonly>
        </div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="bookName">name</label>
          <input type="text" class="form-control" id="bookName" placeholder="book's name" v-model="book.bookName" readonly>
        </div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="bookAuthor">author</label>
          <input type="text" class="form-control" id="bookAuthor" placeholder="author" v-model="book.bookAuthor" readonly>
        </div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="publishHouse">publish house</label>
          <input type="text" class="form-control" id="publishHouse" placeholder="publish house" v-model="book.publishHouse" readonly>
        </div>
      </div>
      <div class="form-group">
        <div class="inputarea">
          <label for="publishDate">publish year</label>
          <input type="text" class="form-control" id="publishDate" placeholder="publish year" v-model="book.publishDate" readonly>
        </div>
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
      <div class="update">
        <button  type="button" class="btn btn-primary btn-block" :class="{disabled:!validated}" @click="checkinput">UPDATE</button>
      </div>
    </div>
    <div class="toast" :class="{'toast-show':updatesuccess}" >Successfully update book！</div>
  </div>
</template>

<script>

import {getbookbyisbn,updatebook} from "@/network/book";
import {updatereader} from "@/network/reader";

export default {
  name: "UpdateBook",
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
        price:true,
        remain:true
      },
      validating:{
        price:false,
        remain:false
      },
      updatesuccess:false
    }
  },
  computed:{
    validated(){
      return this.validate.price && this.validate.remain
    }
  },
  methods:{
    checkinput(){
      if(!this.validated){
        this.validateprice();
        this.validateremain();
        return
      }
      else{
        updatebook(this.book).then(res=>{
          if(res == '1'){
            this.updatesuccess = true
            setTimeout(()=>{
              this.updatesuccess = false
              this.$router.replace('/home/book/allbooks')
            },2500)
          }
        })
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
  mounted() {
    getbookbyisbn(this.$route.params.isbn).then(res=>{
      this.book = res
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
.updatebook{
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