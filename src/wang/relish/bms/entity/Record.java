package wang.relish.bms.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Relish on 2016/6/29.
 */
@Entity
@Table(name = "record", schema = "", catalog = "bms")
public class Record {
    private int id;
    private int adminId;
    private int userId;
    private int bookId;

    private Book book;
    private User user;
    private User admin;

    private int type;
    private Timestamp borrowTime;
    private Timestamp returnTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "admin_id", nullable = false)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "borrow_time", nullable = true)
    public Timestamp getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Timestamp borrowTime) {
        this.borrowTime = borrowTime;
    }

    @Basic
    @Column(name = "return_time", nullable = true)
    public Timestamp getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record that = (Record) o;

        if (id != that.id) return false;
        if (adminId != that.adminId) return false;
        if (userId != that.userId) return false;
        if (bookId != that.bookId) return false;
        if (type != that.type) return false;
        if (borrowTime != null ? !borrowTime.equals(that.borrowTime) : that.borrowTime != null) return false;
        if (returnTime != null ? !returnTime.equals(that.returnTime) : that.returnTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + adminId;
        result = 31 * result + userId;
        result = 31 * result + bookId;
        result = 31 * result + type;
        result = 31 * result + (borrowTime != null ? borrowTime.hashCode() : 0);
        result = 31 * result + (returnTime != null ? returnTime.hashCode() : 0);
        return result;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
