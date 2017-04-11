package com.zl.sort;

public class User implements Comparable<User>{
	
    private String name;
    private int age;
    private int gender;
    private String address;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public int getGender() {
        return gender;
    }
    
    public void setGender(int gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public User() {
        super();
    }
    
    public User(String name, int age, int gender, String address) {
        super();
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
   
    @Override
    public int compareTo(User o) {
        if(o!=null){
            if(this.getAge()>o.getAge()){
               return 1;
            }else if(this.getAge()==o.getAge()){
               return 0;
            }
       }
        return -1;
    }
}