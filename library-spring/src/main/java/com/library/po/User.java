package com.library.po;

public class User {
    private Integer userid;
    private String userCardNum;
    private String userName;
    private String userSex;
    private String userDepa;
    private String userClass;
    private String Grade;
    private String userBorrowNum;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserCardNum() {
        return userCardNum;
    }

    public void setUserCardNum(String userCardNum) {
        this.userCardNum = userCardNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String useName) {
        this.userName = useName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserDepa() {
        return userDepa;
    }

    public void setUserDepa(String userDepa) {
        this.userDepa = userDepa;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getUserBorrowNum() {
        return userBorrowNum;
    }

    public void setUserBorrowNum(String userBorrowNum) {
        this.userBorrowNum = userBorrowNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", userCardNum='" + userCardNum + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userDepa='" + userDepa + '\'' +
                ", userClass='" + userClass + '\'' +
                ", Grade='" + Grade + '\'' +
                ", userBorrowNum='" + userBorrowNum + '\'' +
                '}';
    }
}
