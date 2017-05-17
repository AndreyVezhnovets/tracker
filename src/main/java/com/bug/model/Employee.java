package com.bug.model;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "mail" ,nullable = false, unique = true)
    private String mail;
    @Column(name = "password" ,nullable = false)
    private String password;
    @Column(name = "name" ,nullable = false)
    private String name;
    @Column(name = "surname" ,nullable = false)
    private String surname;
    @Column(name = "position" ,nullable = false)
    private String position;

    public Employee(String name, String surname,String mail, String position, String password) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public Employee(String mail, String name, String surname, String position) {
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public Employee() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (!mail.equals(employee.mail)) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (!password.equals(employee.password)) return false;
        if (position != null ? !position.equals(employee.position) : employee.position != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mail.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
