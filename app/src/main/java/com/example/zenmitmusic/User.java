package com.example.zenmitmusic;

public class User{
    private String name;
    private String phonee;
    private String email;
    private String password;

    public User(){

    }
    public User(String email, String password){
        this.email=email;
        this.password=password;

    }
    public User(String name,String phonee,String email, String password){
        this.email=email;
        this.name=name;
        this.password=password;
        this.phonee=phonee;




    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonee() {
        return phonee;
    }

    public void setPhonee(String phonee) {
        this.phonee = phonee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
