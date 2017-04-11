package com.zl.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

public class HttpDemo {
	public static void main(String[] args) {
//		int status = GetHttpStatus.getstatus("http://oss.dinghuo123.com/images/productImage/141211/9bddcfa1-b95b-416c-8a44-1fcd186b411c.jpg");
//		System.out.println(status);
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.add("aaa");
		System.out.println();
	}

	public static int getstatus(String url) {
		URL u = null;
		int status = 0;
		try {
			u = new URL(url);
			HttpURLConnection uConnection = (HttpURLConnection) u.openConnection();
			uConnection.connect();
			status = uConnection.getResponseCode();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
