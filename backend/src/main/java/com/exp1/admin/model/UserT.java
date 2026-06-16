package com.exp1.admin.model;

import java.util.Date;

public class UserT {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Date birthday;
    private Float money;
    private String avatar;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public Float getMoney() { return money; }
    public void setMoney(Float money) { this.money = money; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
}
