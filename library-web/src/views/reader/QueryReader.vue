<template>
  <div class="queryreader">
    <div class="text" v-show="true">Now you are <strong>querying</strong> readers, please input the reader's information below.You must input at least one item.</div>
    <div class="input" v-show="true">
      <div class="form-group cardnum">
          <label for="cardnum">card number</label>
          <input type="text" class="form-control" id="cardnum" placeholder="card number" v-model="reader.cardnum" >
      </div>
      <div class="form-group readername">
          <label for="readername">name</label>
          <input type="text" class="form-control" id="readername" placeholder="reader's name" v-model="reader.readername"  >
      </div>
      <div class="gender">
          <span>gender </span>
          <label class="radio-inline">
            <input type="radio" name="gender" id="male" value="男" v-model="reader.gender" >
            <img src="@/assets/img/ye.png" alt="ye"> male
          </label>
          <label class="radio-inline">
            <input type="radio" name="gender" id="female" value="女" v-model="reader.gender" >
            <img src="@/assets/img/girl.png" alt="girl"> female
          </label>
      </div>
      <div class="form-group department">
          <label for="department">department</label>
          <input type="text" class="form-control" id="department" placeholder="department" v-model="reader.department">
      </div>
      <div class="form-group class">
          <label for="class">class</label>
          <input type="text" class="form-control" id="class" placeholder="class" v-model="reader.class">
      </div>
      <div class="form-group grade">
          <label for="grade">grade</label>
          <input type="text" class="form-control" id="grade" placeholder="grade" v-model="reader.grade">
      </div>
      <div class="form-group borrownum">
          <label for="borrownum">borrow number</label>
          <input type="text" class="form-control" id="borrownum" placeholder="borrow number" v-model="reader.borrownum">
      </div>
      <div class="query" @click="queryclick">
        <button  type="button" class="btn btn-primary btn-block" @click="">QUERY</button>
      </div>
    </div>
    <div class="text" v-show="false">Find <strong>{{list.length}}</strong> records !</div>

    <div class="result" v-show="false">
      <table class="table table-striped table-hover" v-if="list != null">
        <thead>
        <tr>
          <th>#</th>
          <th>cardnum</th>
          <th>name</th>
          <th>gender</th>
          <th>department</th>
          <th>class</th>
          <th>grade</th>
          <th width="150px">borrow num</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in list" :key="index">
          <td>{{index+1}}</td>
          <td>{{item['userCardNum']}}</td>
          <td>{{item['userName']}}</td>
          <td>{{item['userSex']}}</td>
          <td>{{item['userDepa']}}</td>
          <td>{{item['userClass']}}</td>
          <td>{{item['grade']}}</td>
          <td>{{item['userBorrowNum']}}</td>
        </tr>
        </tbody>

      </table>
    </div>
    <div class="toast" :class="{'toast-show-warn':warn}" >Please input at least one item！!</div>
    <div class="toast" :class="{'toast-show-querying':querying}" >Querying......</div>
  </div>
</template>

<script>
import {getallreaders,queryreader} from "@/network/reader";

export default {
  name: "QueryReader",
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
    warn:false,
    querying:false,
    list:null
  }
  },
  methods:{
    queryclick(){
      this.querying = true;
      queryreader(this.reader).then(res=>{

      })
    }
  },
  mounted(){
    getallreaders().then(res=>{
      this.list = res
      console.log(this.list);
    })
  }
}
</script>

<style scoped lang="less">
@fontfamily:-apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
.queryreader{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.inputareastyle{
  input{
    font-weight: normal;
  }
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
  width: 40%;
  label{
    font-family: @fontfamily;
    font-weight: lighter;
    font-size: 20px;
  }
  .form-group{
  }
  .gender{
    margin: 15px 0;
    display: flex;
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
  .query{
    width: 20%;
    margin:20px 40%;
  }
}
.result{
  width: 100%;
  height: 622px;
  overflow: scroll;
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