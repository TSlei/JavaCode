package com.zl.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//根据年龄排序
public class ComparableTest2 {
	
	public static void main(String[] args) {
		List<User> ulist = new ArrayList<User>();
		ulist.add(new User("wangbo",29,1,"长沙"));
		ulist.add(new User("lisi",44,1,"株洲"));
		ulist.add(new User("wangwu",19,1,"衡阳"));
		ulist.add(new User("lihua",36,1,"长沙"));
		ulist.add(new User("zhaoliu",19,1,"岳阳"));
		//岳阳和衡阳的age一样,谁先add,谁就在前面,被先遍历出
		Collections.sort(ulist);
		
		for(User u:ulist){
		    System.out.println(u.getName()+"\t"+u.getAge()+"\t"+u.getAddress());
		} 
	}
	
}
