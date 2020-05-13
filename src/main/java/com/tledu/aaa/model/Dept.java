package com.tledu.aaa.model;
//部门
public class Dept {
private int id;
private String name;
private  String phone;
private String tel;
private User user;
private Branch branch;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Branch getBranch() {
	return branch;
}
public void setBranch(Branch branch) {
	this.branch = branch;
}
@Override
public String toString() {
	return "Dept [id=" + id + ", name=" + name + ", phone=" + phone + ", tel="
			+ tel + ", user=" + user + ", branch=" + branch + "]";
}

}
