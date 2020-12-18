<template>
  <div class="addreader">
    <div class="text">Now you are adding a reader to the reader database , please input the reader's information below.</div>
    <div class="input">
      <div class="form-group cardnum">
        <div class="inputarea">
          <label for="cardnum">card number</label>
          <input type="text" class="form-control" id="cardnum" placeholder="card number">
        </div>
        <div v-show="false" class="alert alert-success" role="alert"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> card number is OK!</div>
        <div v-show="true" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> card number must be 11 digits!!</div>
        <div v-show="false" class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> card number already exists!!</div>
      </div>
      <div class="form-group readername">
        <div class="inputarea">
          <label for="readername">name</label>
          <input type="text" class="form-control" id="readername" placeholder="reader's name">
        </div>
      </div>
      <div class="gender">
        <div class="inputarea">
          <span>gender </span>
          <label class="radio-inline">
            <input type="radio" name="gender" id="male" value="male">
            <img src="@/assets/img/ye.png" alt="ye"> male
          </label>
          <label class="radio-inline">
            <input type="radio" name="gender" id="female" value="female">
            <img src="@/assets/img/girl.png" alt="ye"> female
          </label>
        </div>

      </div>
      <div class="form-group department">
        <div class="inputarea">
          <label for="department">department</label>
          <input type="text" class="form-control" id="department" placeholder="department">
        </div>

      </div>
      <div class="form-group class">
        <div class="inputarea">
          <label for="department">class</label>
          <input type="text" class="form-control" id="class" placeholder="class">
        </div>

      </div>
      <div class="form-group grade">
        <div class="inputarea">
          <label for="department">grade</label>
          <input type="text" class="form-control" id="grade" placeholder="grade">
        </div>

      </div>
      <div class="add">
        <button type="button" class="btn btn-primary btn-block disabled">ADD</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
name: "AddReader"
}
</script>

<style scoped lang="less">
@fontfamily:-apple-system,BlinkMacSystemFont,Segoe UI,Helvetica,Arial,sans-serif,Apple Color Emoji,Segoe UI Emoji;
  .addreader{
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .text{
    width: 100%;
    background-color:rgba(26, 188, 156,.8);
    color: #000;
    font-size: 20px;
    padding: 5px 20px;
  }
  .input{
    padding: 10px 0;
    margin: 20px 0;
    width: 50%;
    label{
      font-family: @fontfamily;
      font-weight: lighter;
      font-size: 20px;
    }
    .form-group{
      display: flex;

      .inputarea{

        width: 60%;
      }
      .alert{
        padding:0;
        margin: 0;
        height: 34px;
        line-height: 34px;
        align-self: flex-end;
        padding:0 10px;
        margin-left: 10px;
      }
    }
    .gender{
      margin: 15px 0;
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
      margin:20px 30%;
    }
  }




</style>