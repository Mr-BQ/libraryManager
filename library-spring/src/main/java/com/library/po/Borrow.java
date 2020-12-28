package com.library.po;

public class Borrow {
    private Integer id;
    private String userCardNum;
    private String userName;
    private String bookISBN;
    private String bookName;
    private String borrowDate;
    private String dateToReturn;
    private String returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDateToReturn() {
        return dateToReturn;
    }

    public void setDateToReturn(String dateToReturn) {
        this.dateToReturn = dateToReturn;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", userCardNum='" + userCardNum + '\'' +
                ", userName='" + userName + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowDate='" + borrowDate + '\'' +
                ", dateToReturn='" + dateToReturn + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
