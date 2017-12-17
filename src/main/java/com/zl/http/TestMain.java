package com.zl.http;

import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
//对接口进行测试
public class TestMain {

	
	public void test() throws Exception{
        String httpOrgCreateTest = "https://101.71.61.221:8443";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("action","login");
        createMap.put("username","azkaban");
        createMap.put("password","@WSX4rfv");
        
        System.out.println(OKHTTPUtil.postForm(httpOrgCreateTest, createMap));
    }
	
    public static void main(String[] args) throws Exception{
        TestMain main = new TestMain();
        main.test();
    }
}