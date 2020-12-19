<template>
  <div class="allreaders" >
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
        <th class="lastcol"></th>
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
</template>

<script>
import {getallreaders} from "@/network/reader";

export default {
  name: "AllReaders",
  data(){
    return{
      list:null,
      showmore:-1
    }
  },
  mounted() {
    getallreaders().then(res=>{
      this.list = res
      console.log(this.list);
    })
  },
  methods:{
    moreclick(index){
      this.showmore = index
    },
    updateclick(index){
      let cardnum = this.list[index].userCardNum
      this.$router.replace({
        path:`/home/reader/updatereader/${cardnum}`
      })
    },
    deleteclick(index){

    }
  }
}
</script>

<style scoped lang="less">
  .allreaders{
    height: 100%;
    overflow: scroll;
  }
  .table{
    thead{
      font-size: 16px;
    }
  }

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
  table{
    .lastcol{
      padding:0;
      width: 140px;
    }
    td{
      width: auto;
    }
    tbody{
      td{
        height: 40px;
        line-height: 40px;
      }
    }
  }
</style>