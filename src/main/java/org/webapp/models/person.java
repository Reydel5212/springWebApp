package org.webapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min= 2, max = 30, message = "Name should be between 2 and 30")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "age should be greater than 0")
    @Max(value = 100, message = "age should be down than 100")
    @Column(name = "age")
    private int age;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    @Column(name = "birthday")
    private Date birthday;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be Valid")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "You address should be in this format: Country, City, Post-index(6 digitals)")
    @Column(name = "address")
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date create_date;

    public person(){

    }

    public person(String name, int age, Date birthday, String email, String address){
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    //birthday
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public Date getBirthday() { return birthday; }

    //email
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    //address
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    //created_day
    public void setCreate_date(Date create_date){
        this.create_date = create_date;
    }
    public Date getCreate_date(){
        return create_date;
    }


    @Override
    public String toString() {
        return "Person{ "+
                " id= "+id+
                " name= "+name+
                " age= "+age+
                " email= "+email+
                " address= "+address+
                " }";
    }
}
