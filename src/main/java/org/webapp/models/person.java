package org.webapp.models;


import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "person")
public class person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min= 2, max = 30, message = "Name should be between 2 and 30")
    private String name;

    @Column(name = "age")
    @Min(value = 0, message = "age should be greater than 0")
    @Max(value = 100, message = "age should be down than 100")
    private int age;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be Valid")
    private String email;

    @Column(name = "address")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "You address should be in this format: Country, City, Post-index(6 digitals)")
    private String address;

    public person(){

    }
    public person(String name,int age,String email, String address){
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
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

    @Override
    public String toString() {
        return "Person{"+
                "id= "+id+
                "name= "+name+
                "age= "+age+
                "email= "+email+
                "address= "+address+
                "}";
    }
}
