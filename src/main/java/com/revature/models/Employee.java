package com.revature.models;

public class Employee {


     int id;
     String firstname;
     String lastname;

     String password;
     String username;
     String job_title;

     String status;


    public Employee(){

    }

    public Employee(int id,String firstname,String lastname,String username,String password,String job_title,String status){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;

        this.username = username;
        this.password = password;
        this.job_title = job_title;
        this.status = status;
    }

    public Employee(String firstname,String lastname,String username,String password,String job_title,String status){
        this.firstname = firstname;
        this.lastname = lastname;

        this.username = username;
        this.password = password;
        this.job_title = job_title;
        this.status = status;
}
    public Employee(String username,String password){


        this.username = username;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +

                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", job_title='" + job_title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
