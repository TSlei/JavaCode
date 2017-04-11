package com.zl.enhance.reflect;

import java.util.Date;

public class ReflectPoint {
	private Date birthday =new Date(); 
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	private int x;
	public int y;
	public String str1="ball";
	public String str2="basketball";
	public String str3="itcast";
	
	public ReflectPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	//重写toString方法
	@Override
	public String toString() {
		return str1+ ":" +str2+":" +str3;
	}

	//如果不重写hashCode方法，则equals比较的还是hashcode，size还是为3
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReflectPoint other = (ReflectPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}
