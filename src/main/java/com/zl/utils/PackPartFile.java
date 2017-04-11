package com.zl.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class PackPartFile {
	 
	public static void main(String[] args) throws ParseException {
		String Compilepath="E:/xiangmu/p3/WEB-INF";		//编译后的目录
		String path="E:/Code/work8.5/com.shovesoft.sp2p";   //项目目录
		
		String eclipsePath=Compilepath+"/application/eclipse";
	
		//String Compilepath="E:/xiangmu/p8/WEB-INF/application";		//编译后的目录
		List<String> list=new LinkedList<String>();
		//获得所有时间内修改过的文件名
		list=getChangeFileList(path,list);
		
		//遍历删除文件
		DeleUseless(Compilepath,list);
		//删除不需要的文件夹
		DeleteEclipse(eclipsePath);
		//删除所有空文件
		int i=0;
		while(i<15){
			DeleteNullFile(Compilepath);
			i++;
		}
		
	}
	
	//E:/Code/work8.5/com.shovesoft.sp2p/app
	public static List<String> getChangeFileList(String path,List<String> list) throws ParseException{
		//获取当前目录
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		File file=new File(path);

		File[] files=file.listFiles();
		
		Date date=null;
		for(int i=0;i<files.length;i++){
			
			if(files[i].isFile()){
				date = format.parse("2015-12-16 00:00:00");//初始日期   
				//比较时间
				DiffDate(list, date, files[i]);
				
			}else if(files[i].isDirectory()&&!"eclipse".equals(files[i].getName())&&!"log".equals(files[i].getName())&&!"lib".equals(files[i].getName())
					&&!"data".equals(files[i].getName())&&!"tmp".equals(files[i].getName())&&!"tmp".equals(files[i].getName())){
				//新路径，从外往里面遍历
				getChangeFileList(path+"/"+files[i].getName(),list);
			}
			
		}
		return list;
	}
	
	//比较时间
	public static void DiffDate(List<String> list,Date selectTime,File file){
		Date lastdate=new Date(file.lastModified());
		if(lastdate.after(selectTime)){
			//file.getParentFile().getName();
			//保存文件名
			String name=file.getName();
			int index=name.indexOf(".");
			String type=name.substring(index+1);
			//java文件后缀改为class
			if("java".equals(type)){
				String filename=name.substring(0,index);
				list.add(filename+".class");
			}else{
				System.out.println(name);
				list.add(name);
			}
		}
	}
	
	//从编译后的文件中进行删除
	public static void DeleUseless(String path,List<String> list){
		File file=new File(path);
		File[] files=file.listFiles();
		//System.out.println(list.size());

		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				String name=files[i].getName();
				if(!list.contains(name)){
					//删除文件
					files[i].delete();
				}
			}
			if(files[i].isDirectory()){
				String pathi = new String(path+"/"+files[i].getName());
				//递归
				DeleUseless(pathi,list);
			}
		}
	}
	
	//删除空文件夹
	public static void DeleteNullFile(String path){
		File file=new File(path);
		File[] files=file.listFiles();
		for(int i=0;i<files.length;i++){//&&files[i].listFiles().length==0
			if(files[i].isDirectory()){
				if(files[i].listFiles().length==0){
					String parent=files[i].getParent();
					files[i].delete();
					//String parent=files[i].getParent();
					DeleteNullFile(parent);
				}else{
					String pathi = new String(path+"/"+files[i].getName());
					DeleteNullFile(pathi);
				}
			}
		}
	}
	
	public static void DeleteEclipse(String epath){
		File file=new File(epath);
		if(file.exists()){
			File[] files=file.listFiles();
			for(int i=0;i<files.length;i++){
				if(files[i].isFile()){
					files[i].delete();
				}else{
					String pathi = new String(epath+"/"+files[i].getName());
					DeleteEclipse(pathi);
				}
			}
		}
		
	}
	
}
