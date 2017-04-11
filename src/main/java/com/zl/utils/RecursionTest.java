package com.zl.utils;

import java.io.File;

/*
 * 递归删除空文件夹，及子文件夹中的空文件夹
 * */
public class RecursionTest {
	public static void main(String[] args) {
		String path="F:/xiangmu";
		DeleteNullFile(path);
	}
	
	//删除空文件夹
	public static void DeleteNullFile(String path){
		File file=new File(path);
		File[] files=file.listFiles();
		if(files.length==0){
			String parent=file.getParent();
			System.out.println(parent);
			if(parent!=null&&file.getName()!="xiangmu"){
				System.out.println(file.getName());
				file.delete();
				DeleteNullFile(parent);
			}

		}

		//String pathi =null;
		for(int i=0;i<files.length;i++){			//&&files[i].listFiles().length==0
			if(files[i].isDirectory()){
				if(files[i].listFiles().length==0){
					String parent=files[i].getParent();
					System.out.println(files[i].getName());
					files[i].delete();
					//String parent=files[i].getParent();
					//传入新的路径，继续删除
					DeleteNullFile(parent);
					
				}else{
					//if(files[])
					String temppath=path;
					path =path+"/"+files[i].getName();
					DeleteNullFile(path);
					path=temppath;
				}
			}
		}
	}
}
