<template>
  <div class="borrowrecords">
    <div class="content">
      <table class="table table-striped table-hover"  v-show="list != null">
        <thead>
        <tr>
          <th>#</th>
          <th>card number</th>
          <th>reader name</th>
          <th>ISBN</th>
          <th>book name</th>
          <th>borrow date</th>
          <th>date to return </th>
          <th>return date</th>
<!--          <th class="lastcol"></th>-->
        </tr>
        </thead>
        <tbody>
        <tr v-for="(item,index) in list" :key="index">
          <td>{{index+1}}</td>
          <td>{{item['userCardNum']}}</td>
          <td>{{item['userName']}}</td>
          <td>{{item['bookISBN']}}</td>
          <td>{{item['bookName']}}</td>
          <td>{{item['borrowDate']}}</td>
          <td>{{item['dateToReturn']}}</td>
          <td>{{item['returnDate']}}</td>
<!--          <td class="lastcol">-->
<!--            <div class="morebox">-->
<!--              <div class="updateAndDelete" v-show="index===showmore">-->
<!--                <div class="update" @click="updateclick(index)">UPDATE</div>-->
<!--                <div class="delete" @click="deleteclick(index)">DELETE</div>-->
<!--              </div>-->
<!--              <div class="more" v-show="index!==showmore">-->
<!--                <a class="btn btn-default" href="javascript:;" role="button" @click="moreclick(index)">-->
<!--                  <span class="glyphicon glyphicon-option-horizontal" aria-hidden="true"></span>-->
<!--                </a>-->
<!--              </div>-->
<!--            </div>-->
<!--          </td>-->
        </tr>
        </tbody>

      </table>
    </div>
<!--    <div class="shadow" v-if="showshadow && list != null">-->
<!--      <div class="confirm">-->
<!--        <div class="warntext">CONFIRM TO DELETE BOOK <br/>' <strong>{{list[deleteindex].bookISBN}} {{list[deleteindex].bookName}}</strong> ' ?</div>-->
<!--        <div class="choosebox">-->
<!--          <div class="yes" @click="confirmdelete">CONFIRM</div>-->
<!--          <div class="no" @click="showshadow=false">CANCEL</div>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
  </div>
</template>

<script>
import {getallborrow} from "@/network/borrow";

export default {
name: "BorrowRecords",
  data(){
    return{
      list:null
    }
  },
  mounted() {
    this.$store.commit('changeSubactive','20')
    getallborrow().then(res=>{
      this.list = res
    })
  }
}
</script>

<style scoped lang="less">
.borrowrecords{
  height: 100%;
  position: relative;
}
.content{
  height: 100%;
  overflow: scroll;
}
//.shadow{
//  position: absolute;
//  z-index: 10;
//  left: 0;
//  top: 0;
//  height: 100%;
//  width: 100%;
//  background-color: rgba(0, 0, 0,.8);
//  .confirm{
//    height: 30%;
//    width: 40%;
//    padding:20px;
//    background-color: rgba(189, 195, 199,1.0);
//    position: absolute;
//    left: 50%;
//    top: 50%;
//    transform: translate(-50%,-50%);
//    border: 5px solid rgba(52, 73, 94,1.0);
//    border-radius: 5px;
//    text-align: center;
//    .warntext{
//      font-family: Barlow;
//      color: rgba(44, 62, 80,1.0);
//      font-size: 20px;
//    }
//    .choosebox{
//      display: flex;
//      justify-content: space-around;
//      margin:20px;
//      div{
//        cursor: pointer;
//        border: 1px solid black;
//        font-size: 20px;
//        border-radius: 3px;
//        padding:5px;
//      }
//      .yes:hover{
//        background-color: rgba(192, 57, 43,1.0);
//        color: rgba(236, 240, 241,1.0);
//      }
//      .no:hover{
//        background-color: rgba(149, 165, 166,1.0);
//      }
//    }
//  }
//}

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
    td{
      height: 40px;
      line-height: 40px;
    }
    //.morebox{
    //  display: flex;
    //  height: 100%;
    //  justify-content: center;
    //  align-items: center;
    //  .updateAndDelete{
    //    cursor: pointer;
    //    display: flex;
    //    height: 35px;
    //    font-weight: normal;
    //    line-height: 35px;
    //    .update{
    //      border: 1px solid #2c3e50;
    //      padding:0 10px;
    //      border-radius: 3px 0 0 3px;
    //      flex: 1;
    //      &:hover{
    //        background-color: #bdc3c7;
    //      }
    //    }
    //    .delete{
    //      border: 1px solid #2c3e50;
    //      padding: 0 10px;
    //      border-radius: 0 3px 3px 0;
    //      border-left: none;
    //      flex: 1;
    //      &:hover{
    //        background-color: #bdc3c7;
    //      }
    //    }
    //  }
    //  .more{
    //    a{
    //      padding:0;
    //      margin:0;
    //      height:25px;
    //      width: 30px;
    //      line-height: 25px;
    //      border:1px solid #34495e;
    //      background-color: transparent;
    //      border-radius: 3px;
    //      font-size: 20px;
    //      &:hover{
    //        background-color: #bdc3c7;
    //      }
    //    }
    //  }
    //}
  }
}
</style>