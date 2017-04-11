package com.zl.enhance.annotation;



@ItAnnotation(annotationAttr=@MetaAnnotation("flx"),color="red",value="abc",arrayAttr={1,2,3})  //arrayAttr=1  数组中只有一个元素，可以省略大括号
public class AnnotationTest {
	
	@SuppressWarnings("deprecation")   //告诉编译器不要警告
	@ItAnnotation("xyz")
	public static void main(String[] args) {
		System.runFinalizersOnExit(true);
		//判断ItAnnotation这个注解在不在
		if(AnnotationTest.class.isAnnotationPresent(ItAnnotation.class)){
			ItAnnotation annotation=(ItAnnotation)AnnotationTest.class.getAnnotation(ItAnnotation.class);
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.arrayAttr().length);
			System.out.println(annotation.lamp().nextLamp());
			System.out.println(annotation.annotationAttr().value());
		}
	}
	
	
	@Deprecated    //提示该方法过时
	public static void sayHello(){
		System.out.println("hi,World");
	}
}
