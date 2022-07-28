package com.athenahealth.collector.rules.imports.users.model;

import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class User {

    @Id
    private Integer id;
    @Transient
    private String name;
    @Transient
    private String lastName;
    private String fullName;
    @Column(name = "department")
    private String dept;
    private Integer salary;
    private Date time;
    private int age;
    private int titleId;
    @Transient
    private String title;

    public User(Integer id, String name, String lastName, String dept, Integer salary, Date time, int age, int titleId, String title) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dept = dept;
        this.salary = salary;
        this.time = time;
        this.age = age;
        this.titleId = titleId;
        this.title = title;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer()
        .append(id)
        .append(",'").append(fullName).append('\'')
        .append(",").append(age)
        .append(",'").append(dept).append('\'')
        .append(",").append(salary)
        .append(",'").append(title).append('\'');
        return sb.toString();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
}
