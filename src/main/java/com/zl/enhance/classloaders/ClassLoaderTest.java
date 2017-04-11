package com.zl.enhance.classloaders;

import java.util.Date;


/*	类加载器
 * 	系统默认三个主要类加载器，每个类负责加载特定位置的类:   父子级关系管理图
 * 			BootStrap (父) ------> JDK安装目录下的jdk目录下的JRE/lib/rt.jar     
 * 						\
 * 					,ExtClassLoader	(子)		------>JRE/lib/ext/*.jar
 * 								\
 * 	SystemclassLoader--->	,AppClassLoader (孙)   		--------->CLASSPATH指定的所有jar或目录
 * 										/		\
 * 	(自定义的类加载器可以挂在App类加载器下) MyClassLoader		ItClassLoader   ----->传智播客指定的特殊目录
 * 	
 * 	将ClassLoaderTest类导出到D:\Soft\Java\JDK6\jre\lib\ext包下面之后，类加载器变成了  sun.misc.Launcher$ExtClassLoader
 * 
 *  当CLASSPATH指定的目录下和ext目录下同时有class文件的时候，加载的是？
 *	当前线程在加载第一个类的时候，会先把加载任务提交给父类去加载，直到提交到的类加载器没有父类的时候才真正加载，如果最上面的类加载没有找到该类，则传递给自己的自类去加载，如果找到则结束，没有找到则继续向下传递，直到回到发起者。
 *	这样的好处是可以集中管理：比如有两个类加载器    MyClassLoader和ItClassLoader,让它们分别去加载System，两个都加载到了，内存中就会出现2份字节码，集中管理可以先不加载， 让管理它们的父类或者爷爷类去加载，如果父类发现之前加载过一次，就直接把之前那份拿出来使用。
 *
 * 	类加载器也是Java类，因为其他是java类的类加载器本身也要被类加载器加载，显然必须有一个类加载器不是java类，这正是BootStrap。
 * 	Java虚拟机中的所有类装载器采用具有父子关系的树形结构进行组织，在实例化每个类装载器对象时，需要为其指定一个父级类装载器对象或者默认采用系统类装载器为其父级类加载
 * 
 * 	类加载器的委托机制：
 * 	新建一个类加载器的时候必须继承ClassLoader类并且为其指定一个父加载器。
 * 	当Java虚拟机要加载一个类时，到底派哪个类加载器去加载呢？
 * 		首先当前线程的类加载器去加载线程中的第一个类。		(Thread有一个setContextClassLoader(ClassLoader cl)方法来指定该线程的类加载器)
 * 		如果类A引用了类B，Java虚拟机将使用加载类A的类加载器来加载类B
 * 		还可以直接调用ClassLoader.loadClass()方法来指定某个类加载器去加载某各类。
 * 	每个类加载器加载类时，又先委托给其上级类加载器。
 * 		当所有祖宗类加载器没有加载到类，回到发起者类加载器，还加载不到类，则抛出ClassNotFoundException,不是再去找发起者类加载器的儿子，因为没有getChild方法，即使有，那么有多个儿子，找哪一个呢？
 * 
 * 面试题问：  能不能自己写个类叫java.lang.System
 * 答：为了不让我们写System类,类加载器采用委托机制，这样可以保证爸爸们优先,也就是总是使用爸爸们能找到的类，这样总是使用java提供的System.
 * 
 * 那怎样找我自己的System类呢？
 * 答：自己写一个类加载器，并且撇开委托机制，不能让这个加载器委托给上级,而是自己直接加载。
 * 
 * */


/*
 * 继承ClassLoader类
 * 重写findClass方法而不是loadClass方法，因为loadClass方法中有整个提交父类加载，退回给子类加载的流程，重写后会打乱流程，所以只需要重写findClass这个模块
 * 
 * */

/*
 *	Servlet是被tomcat自己的类加载器加载的 
 * 	WebappClassLoader ,	StandardClassLoader
 * 
 **/
public class ClassLoaderTest {
	
	public static void main(String[] args) throws Exception {
		System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());
		
	//	System.out.println(System.class.getClassLoader());
		
		ClassLoader loader=ClassLoaderTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader=loader.getParent();
		}
		System.out.println(loader);
		
		//System.out.println(new ClassLoaderAttachment().toString());
		//将父类中的需要加载的class文件删掉，父类找不到就会给子类去找，就会调用自己的类加载器
		Class clazz=new MyClassLoader("zllib").loadClass("ClassLoaderAttachment"); //Enhance.ClassLoaders.ClassLoaderAttachment 加不加包名都一样
		//ClassLoaderAttachment d1=(ClassLoaderAttachment)clazz.newInstance();
		Date d2=(Date)clazz.newInstance();
		System.out.println(d2);
	}
}
