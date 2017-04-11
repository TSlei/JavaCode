package com.zl.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Excel2DBUtil {

	// 将文本中的数据批量读取插入数据库
	public void insertValue2DB() {
		try {
			FileReader fr = new FileReader("e:\\abc.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = null;
			HashMap<String, String> map = new HashMap<String, String>();
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				
				// 例如： java ,c++ , c#, PHP
				for (String name : line.split(",")) {
					// 将一行中的数据用逗号分隔，然后一一取出，然后将取出的数据去掉回车换行符，然后保存到map中
					name = name.replaceAll(" ", "");
					name = name.replaceAll("\r", "");
					name = name.replaceAll("\n", "");
					map.put(name, name);
				}
			}

			for (String str : map.values()) {
				// 向数据库一行行插数据，写insert语句

			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 用逗号分隔文本中每行的数据
	public void insert() {
		try {
			FileReader fr = new FileReader("e:\\tag.txt");
			BufferedReader br = new BufferedReader(fr);

			String line = null;
			while (true) {
				line = br.readLine();

				if (line == null) {
					break;
				}
				if (line.trim().isEmpty()) {
					continue;
				}

				String[] list = line.split(",");
				List<String> lists=new ArrayList<String>();
				for(String s:list){
					lists.add(s);
				}
				
				// 下面写处理动作
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
