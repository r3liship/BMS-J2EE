package wang.relish.bms.entity;

/**
 * 完整记录
 * Created by Relish on 2016/7/2.
 */
public class BorrowRecord {
    Record r;
    User a;
    Book b;
    User u;

    public Record getR() {
        return r;
    }

    public void setR(Record r) {
        this.r = r;
    }

    public User getA() {
        return a;
    }

    public void setA(User a) {
        this.a = a;
    }

    public Book getB() {
        return b;
    }

    public void setB(Book b) {
        this.b = b;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    Integer recordId;
    Integer adminId;
    Integer bookId;
    Integer userId;
    String adminName;
    String bookName;
    String userName;
    Integer inventory;
    String borrowTime;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
