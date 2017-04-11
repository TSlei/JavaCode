package com.zl.enhance.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

/* equals()中通常比较的hashcode值，而hashcode值通常是根据内存地址计算出来的，对于ArrayList会依次存放的是对象的引用，
 * 对于HashSet，会进行一个equals方法比较，如果存在相同值就不存储，要想存储必须删除里面相等的那个值。
 * 这里rp1和rp3是不等的，如果想要让rp1和rp3相等必须重写equals方法。
 * 
 * 3.hashcode()好处？
 *	答：自己重写equals方法就要重写hashCode方法，因为默认的equals方法比较的hashCode值。
 *	HashSet就是采用哈希算法存取对象的集合，它内部采用对某个数字n进行取余的方式对哈希码进行分组和划分对象的存储区域，Object类中定义了一个hashCode方法来返回java对象的哈希码，当从HashSt集合中查找某个对象时，java系统首先调用对象的hashCode方法获取该对象的哈希码，然后根据哈希码找到相应的存储区域，最后取出该存储区域内的每个元素与该对象进行equals方法比较，这样不用遍历集合中所有元素就可以得到结论，可见HashSet集合具有很好的对象索引性能，但是HashSet集合存储对象的效率相对要低些，因为向Hash集合中添加一个对象时，要先算出对象的哈希码和根据这个哈希码确定对象在集合中的存放位置。
 *	为了保证一个类的实例对象能在HashSet中正常存储，要求这个类的两个实力对象用equals方法比较结果相等时，它们的哈希码也必须相等，也就是说，如果obj1.equals(obj2)的结果为true，那么下面的结果也要为true，obj1.hashCode()=obj2.hashCode().可以说hashCode就是内存地址。
 * 	要想对象的hashCode方法有价值，前提对象必须存储在hash集合中，而不是ArrayList。
 *
 * */
public class ReflectTest2 {
	
	public static void main(String[] args) throws Exception {
		//Collection collection=new ArrayList();  //打印size()	4
		//Collection collection=new HashSet();	//3，重写equals方法之后打印2，rp1和rp2相等
		//Collection collection=new ArrayList();
		
		//从配置文件中读取集合类
		//InputStream is=new FileInputStream("config.properties"); //放到项目的根目录下
		//在bin下的class文件目录里面查找，编译之后就会把properties文件放到class文件目录里面
	//	InputStream is=ReflectTest2.class.getClassLoader().getResourceAsStream("Enhance/Reflect/config.properties");
		InputStream is=ReflectTest2.class.getResourceAsStream("config.properties");//代表相对路径，相对class文件路径。如果前面加"/"就代表相对class文件的根
		Properties props=new Properties();
		props.load(is);
		is.close();
		String className=props.getProperty("className");
		Collection collection=(Collection)Class.forName(className).newInstance();
		
		
		
		ReflectPoint rp1=new ReflectPoint(3, 3);
		ReflectPoint rp2=new ReflectPoint(5, 5);
		ReflectPoint rp3=new ReflectPoint(3, 3);
		
		collection.add(rp1);
		collection.add(rp2);
		collection.add(rp3);
		collection.add(rp1);
		
		
		//rp1.y=7;    //如果现在修改y的值，则不能remove掉rp1对象了，会造成内存泄漏，因为rp1对象的hashcode值变化了，remove的时候用的是原来的hashcode值，而现在对象被放到了内存的其他地方，remove的时候指向的是原来的地址，所以不能remove掉。如果集合不是Hash集合，则可以删，hashcode无意义
		//collection.remove(rp1);
		
		System.out.println(collection.size());
	}
}
