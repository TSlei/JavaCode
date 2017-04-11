package com.zl.enhance.generic;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.zl.enhance.reflect.ReflectPoint;


//		定义多个泛型用 "," 隔开
//   	public static <K,V> V getValue(K key){return map.get(key);}

//泛型测试类
public class GenericTest {
	
	public static void main(String[] args) throws Exception{
		ArrayList<Integer> collection=new ArrayList<Integer>();
		collection.add(1);
		//collection.add("abc");
		//通过反射插入字符串数据，泛型是给java编译器看的，可以通过反射逃避泛型
		collection.getClass().getMethod("add", Object.class).invoke(collection, "abc");
		System.out.println(collection.get(1));
		
	//	printCollection(collection); //printCollection(Collection<Object> collection)  编译器报错，只能要Object类型，所以需要通配符 ?
		printCollection(collection);
		
		Class<?> y=null;
		Class<String> x = null;
		y=x;  //可以把具体类型给? ,但是不能把?给具体类型
		//x=y; 报错
		
		add(3, 5);  //int，自动装箱成Integer，这里两个数相加肯定要进行类型转换，所以自动装箱
		Number number=	add(3.5, 3);	//Number
		Object object= 	add(3, "abc");	//共同属于Object类	
		
		swap(new String[]{"abc","xyz","hello"}, 1, 2);
	//	swap(new int[]{1,3,5,4,5}, 3, 4);  //泛型T类型不能是基本数据类型，必须是引用数据类型，这里不会对int类型数组进行自动装箱，因为你可以要的就是int类型数组
		
		String obj="abc";
		String x3=autoConvert(obj);  //定义x3为什么类型，返回值就是什么类型，不需要再进行强制类型转换。
	
		copy1(new Vector<String>(), new String[10]);
		copy2(new Date[10], new String[10]);
		//copy1(new Vector<Date>(), new String[10]);  报错,Date和String冲突
		
		//dao的增删改查
		GenericDao<ReflectPoint> gdao=new GenericDao<ReflectPoint>();
		gdao.add(new ReflectPoint(3, 3));
		ReflectPoint s=gdao.findById(1);
		
		//通过反射得到泛型中定义的类型
		Vector<Date> v1=new Vector<Date>();
		Method applMethod=GenericTest.class.getMethod("applyVector", Vector.class);
		Type[] types=applMethod.getGenericParameterTypes();
		ParameterizedType pType=(ParameterizedType)types[0];
		System.out.println(pType.getRawType());		//原始类型   		class java.util.Vector
		System.out.println(pType.getActualTypeArguments()[0]);    //实际参数类型		class java.util.Date
	}
	
	public static void applyVector(Vector<Date> v1){
		
	}
	
	
	//集合到数组的拷贝
	private static <T> void copy1(Collection<T> dest,T[] src){
		
	}
	
	//数组到数组的拷贝
	private static <T> void copy2(T[] dest,T[] src){
		
	}
	
	private static <T> void fillArray(T[] a,T obj){
		for(int i=0;i<a.length;i++){
			a[i]=obj;
		}
	}
	
	private static <T> void swap(T[] a,int i,int j){
		T tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;
	}
	
	private static <T> T autoConvert(Object obj){
		return (T)obj;
	}
	
	//<T> 表示声明一种新类型   T类型
	private static <T> T add(T x,T y){
		return null;
	}
	
	
	public static void printCollection(Collection<?> collection){
		//collection.add(1);  不能调用与类型有关的方法，因为涉及到传入的参数类型不确定
		collection.size();	//与类型无关，可以调用
	}
	
	
}

/*	泛型中的?通配符的扩展,限定通配符总是包括自己
 * 	限定通配符的上边界：  Number是Integer的父类
 * 	正确：Vector<? extends Number> x=new Vector<Integer>(); 
 * 	错误：Vector<? extends Number> x=new Vector<String>();
 *
 *  限定通配符的下边界:
 * 	正确：Vector<? super Integer> x=new Vector<Number>();
 * 	错误：Vector<? super Integer> x=new Vector<Byte>();
 * 
 * */

/*	对异常如何采用泛型
 * 	private static <T extends Exception> sayHello() throws T
 *  {
 *   try}
 * 	}catch(Exception e){    //catch中必须是具体的异常不能用泛型，但是抛出异常可以用泛型
 * 		throw(T)e;
 * 	}
 *  }
 * 
 * */
 