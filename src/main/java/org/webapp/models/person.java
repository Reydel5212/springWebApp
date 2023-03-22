package org.webapp.models;


import javax.validation.constraints.*;

public class person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min= 2, max = 30, message = "Name should be between 2 and 30")
    private String name;

    @Min(value = 0, message = "age should be greater than 0")
    @Max(value = 100, message = "age should be down than 100")
    private int age;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be Valid")
    private String email;
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "You address should be in this format: Country, City, Post-index(6 digitals)")
    private String address;

    public person(){

    }
    public person(int id,int age,String name,String email, String address){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = getAddress();
    }

    //id
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    //name
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    //age
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    //email
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
}
