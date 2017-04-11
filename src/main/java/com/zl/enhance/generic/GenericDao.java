package com.zl.enhance.generic;

import java.util.Set;

//dap  data access object --> crud
public class GenericDao<E> {
	
	public  void add(E x){
		
	}
	
	public E findById(int id){
		return null;
	}
	
	public void delete(E obj){
		
	}
	
	public void delete(int id){
		
	}
	
	public void update(E obj){
		
	}
	
	
//	public static void update2(E obj){
//		方法报错，因为泛型是定义在对象上的，而static对象在对象new之前就可以调用，所以对象上定义的泛型E没有作用到这里,静态方法要用到泛型就必须自己在方法上定义一个
//	}
	
	
	public E findByUserName(String name){
		return null;
	}
	
	public Set<E> findByConditions(String where){
		return null;
	}
}
