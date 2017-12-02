package sample.Controllers;

public class UserAccount {

    private int id;
    private String userName;
    private String userSurname;
    private String userPosition;
    private String date;
    private String salary;

    public UserAccount(String date, String salary){
        this.date=date;
        this.salary=salary;
    }


    public UserAccount(int id, String userName, String userSurname, String userPosition) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPosition = userPosition;
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String email) {
        this.userSurname = email;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String firstName) {
        this.userPosition = firstName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}