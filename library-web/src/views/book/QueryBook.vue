<template>
  <div class="querybook">
    <div class="back" v-show="showresult" @click="backtoquery">
      <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
      <span>Back to query</span>
    </div>
    <div class="text" v-show="!showresult">Now you are <strong>querying</strong> books, please input the book's information below.You must input at least one item.</div>
    <div class="input" v-show="!showresult">
      <div class="form-group">
        <label for="isbn">ISBN</label>
        <input type="text" class="form-control" id="isbn" placeholder="ISBN" v-model="book.bookISBN" >
      </div>
      <div class="form-group">
        <label for="bookname">name</label>
        <input type="text" class="form-control" id="bookname" placeholder="book's name" v-model="book.bookName" >
      </div>
      <div class="form-group">
        <label for="author">author</label>
        <input type="text" class="form-control" id="author" placeholder="author" v-model="book.bookAuthor" >
      </div>
      <div class="form-group">
        <label for="publishHouse">publish house</label>
        <input type="text" class="form-control" id="publishHouse" placeholder="publish house" v-model="book.publishHouse">
      </div>
      <div class="form-group">
        <label for="publishDate">publish year</label>
        <input type="text" class="form-control" id="publishDate" placeholder="publish year" v-model="book.publishDate">
      </div>
      <div class="query" @click="queryclick">
        <button  type="button" class="btn btn-primary btn-block" >QUERY</button>
      </div>
    </div>

    <div class="text-result" v-show="showresult" >Find <strong>{{ list && list.length}}</strong> records !</div>
    <div class="result" v-show="showresult">
      <div class="content">
        <table class="table table-striped table-hover" v-if="list != null">
          <thead>
          <tr>
            <th>#</th>
            <th>ISBN</th>
            <th>bookname</th>
            <th>author</th>
            <th>publish house</th>
            <th>publish year</th>
            <th>price</th>
            <th width="150px">remain</th>
            <th class="lastcol"></th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(item,index) in list" :key="index">
            <td>{{index+1}}</td>
            <td>{{item['bookISBN']}}</td>
            <td>{{item['bookName']}}</td>
            <td>{{item['bookAuthor']}}</td>
            <td>{{item['publishHouse']}}</td>
            <td>{{item['publishDate']}}</td>
            <td>{{item['price']}}</td>
            <td>{{item['remain']}}</td>
            <td class="lastcol">
              <div class="morebox">
                <div class="updateAndDelete" v-show="index===showmore">
                  <div class="update" @click="updateclick(index)">UPDATE</div>
                  <div class="delete" @click="deleteclick(index)">DELETE</div>
                </div>
                <div class="more" v-show="index!==showmore">
                  <a class="btn btn-default" href="javascript:;" role="button" @click="moreclick(index)">
                    <span class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span>
                  </a>
                </div>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="shadow" v-if="showshadow && list != null">
        <div class="confirm">
          <div class="warntext">CONFIRM TO DELETE BOOK <br/>' <strong>{{list[deleteindex].bookISBN}} {{list[deleteindex].bookName}}</strong> ' ?</div>
          <div class="choosebox">
            <div class="yes" @click="confirmdelete">CONFIRM</div>
            <div class="no" @click="showshadow=false">CANCEL</div>
          </div>
        </div>
      </div>
    </div>

    <div class="toast" :class="{'toast-show-warn':showwarn}" >Please input at least one item！!</div>
    <div class="toast" :class="{'toast-show-querying':querying}" >Querying......</div>
  </div>
</template>

<script>
import {deletebook, querybook} from "@/network/book";

export default {
  name: "QueryBook",
  data(){
    return{
      book:{
        bookISBN:'',
        bookName:'',
        bookAuthor:'',
        publishHouse:'',
        publishDate:''
      },
      showmore:-1,
      showwarn:false,
      querying:false,
      list:null,
      showresult:false,
      showshadow:false,
      deleteindex:0
    }
  },
  methods:{
    queryclick(){
      if(this.warn){
        this.showwarn = true
        setTimeout(()=>{
          this.showwarn = false
        },1500)
      }else{
        this.querying = true;
        querybook(this.book).then(res=>{
          this.list = res
          setTimeout(()=>{
            this.querying = false
            this.showresult = true
          },1000)
        })
      }
    },
    moreclick(index){
      this.showmore = index
    },
    updateclick(index){
      let isbn = this.list[index].bookISBN
      this.$router.replace({
        path:`/home/book/updatebook/${isbn}`
      })
    },
    deleteclick(index){
      this.showshadow = true
      this.deleteindex = index
    },
    confirmdelete(){
      deletebook(this.list[this.deleteindex].bookISBN).then(res=>{
        this.list.splice(this.deleteindex,1)
        this.showshadow = false
        this.showmore = -1
      })
    },
    backtoquery(){
      this.$router.replace('/refresh')
      this.$store.commit('changeCurrentactive',1)
    }
  },
  mounted(){
    this.$store.commit('changeSubactive','11')
  },
  computed:{
    warn(){
      for(let key in this.book){
        if(this.book[key].length !== 0){
          return false;
        }
      }
      return true;
    }
  }
}
</script>

<style scoped lang="less">
@fontfamily:-apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
.querybook{
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  position: relative;
}
.inputareastyle{
  input{
    font-weight: normal;
  }
}
.back{
  //background-color: #bbffaa;
  cursor: pointer;
  width: 20%;
  span{
    margin-left: 10px;
    font-size: 20px;
  }
  span:nth-child(2){
    font-weight: bold;
  }
  &:hover{
    color: #3498db;
  }
}
.text,.text-result{
  width: 60%;
  background-color:rgba(26, 188, 156,.8);
  color: #000;
  font-size: 20px;
  padding: 5px 20px;
}
.text{
  width: 80%;
  margin:0 auto;
}
.input{
  padding: 10px 0;
  margin: 0 auto;
  width: 40%;
  label{
    font-family: @fontfamily;
    font-weight: lighter;
    font-size: 20px;
  }
  .form-group{
  }
  .query{
    width: 20%;
    margin:20px 40%;
  }
}
.result{
  width: 100%;
  height: 622px;
  overflow: scroll;
  .content{
    .table{
      thead{
        font-size: 16px;
      }
      .lastcol{
        padding:0;
        width: 140px;
      }
      td{
        width: auto;
      }
      tbody{
        .morebox{
          display: flex;
          height: 100%;
          justify-content: center;
          align-items: center;
          .updateAndDelete{
            cursor: pointer;
            display: flex;
            height: 35px;
            font-weight: normal;
            line-height: 35px;
            .update{
              border: 1px solid #2c3e50;
              padding:0 10px;
              border-radius: 3px 0 0 3px;
              flex: 1;
              &:hover{
                background-color: #bdc3c7;
              }
            }
            .delete{
              border: 1px solid #2c3e50;
              padding: 0 10px;
              border-radius: 0 3px 3px 0;
              border-left: none;
              flex: 1;
              &:hover{
                background-color: #bdc3c7;
              }
            }
          }
          .more{
            a{
              padding:0;
              margin:0;
              height:25px;
              width: 30px;
              line-height: 25px;
              border:1px solid #34495e;
              background-color: transparent;
              border-radius: 3px;
              font-size: 20px;
              &:hover{
                background-color: #bdc3c7;
              }
            }
          }
        }
        td{
          height: 40px;
          line-height: 40px;
        }
      }
    }
  }



}
.shadow{
  position: absolute;
  z-index: 10;
  left: 0;
  top: 0;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0,.8);
  .confirm{
    height: 30%;
    width: 40%;
    padding:20px;
    background-color: rgba(189, 195, 199,1.0);
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
    border: 5px solid rgba(52, 73, 94,1.0);
    border-radius: 5px;
    text-align: center;
    .warntext{
      font-family: Barlow;
      color: rgba(44, 62, 80,1.0);
      font-size: 20px;
    }
    .choosebox{
      display: flex;
      justify-content: space-around;
      margin:20px;
      div{
        cursor: pointer;
        border: 1px solid black;
        font-size: 20px;
        border-radius: 3px;
        padding:5px;
      }
      .yes:hover{
        background-color: rgba(192, 57, 43,1.0);
        color: rgba(236, 240, 241,1.0);
      }
      .no:hover{
        background-color: rgba(149, 165, 166,1.0);
      }
    }
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

.toast-show-querying{
  opacity: 1;
  height: 40px;
  background-color: rgba(44, 62, 80,.7);
}

.toast-show-warn{
  opacity: 1;
  height: 40px;
  background-color: rgba(231, 76, 60,.7);
}
</style>