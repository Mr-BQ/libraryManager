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
        <th>borrow num</th>
      </tr>
      </thead>
      <tbody>
        <tr v-for="(item,index) in list" :key="index">
        <td>{{item['userid']}}</td>
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
</template>

<script>
import {getallreaders} from "@/network/reader";

export default {
  name: "AllReaders",
  data(){
    return{
      list:null
    }
  },
  mounted() {
    getallreaders().then(res=>{
      this.list = res
      console.log(this.list);
      // console.log('res = ' + res)
      // console.log('list = ' + this.list)
    })
  }
}
</script>

<style scoped lang="less">

  .table{
    //width: 100%;
  }
</style>