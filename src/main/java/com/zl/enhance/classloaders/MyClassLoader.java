package com.zl.enhance.classloaders;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MyClassLoader extends ClassLoader{
	
	private String classDir;
	
	public static void main(String[] args) throws Exception {
		String srcPath=args[0];
		String destDir=args[1];
		FileInputStream fis=new FileInputStream(srcPath);
		String destFileName=srcPath.substring(srcPath.lastIndexOf('\\')+1);
		String destPath=destDir+"\\"+destFileName;
		FileOutputStream fos=new FileOutputStream(destPath);
		cypher(fis, fos);
		fis.close();
		fos.close();
	}
	
	//该方法既用于加密也用于解密，一个数异或一个数再异或这个数，还是等于原来的数
	private static void cypher(InputStream ips,OutputStream ops) throws IOException{
		int b=-1;
		while((b=ips.read())!=-1){
			ops.write(b ^ 0xff);  //b代表读到的一个字节，然后进行异或运算
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFileName=classDir+"\\"+name.substring(name.lastIndexOf('.')+1)+".class";
		try {
			FileInputStream fis=new FileInputStream(classFileName);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			try {
				cypher(fis, bos);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("我的类加载器");
			byte[] bytes=bos.toByteArray();
			return defineClass(bytes, 0, bytes.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	
	public MyClassLoader(){
		
	}
	
	public MyClassLoader(String classDir){
		this.classDir=classDir;
	}
}
