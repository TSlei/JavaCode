package com.zl.mysql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class QueryFromProductDB {

	// local port
	static int localport = 1234;
	// remote mysql server
	static String remotehost = "rdsqejfvbqejfvb.mysql.rds.aliyuncs.com";
	// remote mysql port
	static int remoteport = 3306;

	// ssh connect username
	static String user = "zhanglei";
	// ssh connect password
	static String password = "dinghuo123";
	// ssh server
	static String host = "112.124.104.7";
	// ssh access port
	static int port = 22;

	static JSch jsch = null;

	static Session session = null;

	public static void go() {

		try {
			jsch = new JSch();
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			// print ssh server version information
			System.out.println(session.getServerVersion());
			int assinged_port = session.setPortForwardingL(localport, remotehost, remoteport);
			System.out.println(assinged_port);
			System.out.println("localhost:" + assinged_port + " -> " + remotehost + ":" + remoteport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getResult() throws SQLException, IOException {
		Connection conn = null;
		ResultSet rs = null;
		Statement st = null;
		ArrayList<String> list = new ArrayList<String>();
		go();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:" + localport + "/ydh", "readonly",
					"ydhreadonly");
			st = conn.createStatement();
			String sql = "SELECT DISTINCT(img.fimgUrl) , product.fcode,product.fname " + "FROM t_product_img img "
					+ "JOIN t_product product ON img.fproductId = product.fid AND product.fdbid = img.fdbid "
					+ "WHERE product.fdbid = 74940 " + "AND product.fdelete = 0 AND img.fdelete = 0 "
					+ "ORDER BY product.fcode, img.ftype;";
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String code = rs.getString(2);
				String name = rs.getString(3);

				System.out.println("productCode = " + code + "  productName = " + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		File file = new File("E:\\customerImg\\productName2.txt");
		FileWriter fWriter = new FileWriter(file);
		Writer writer = new BufferedWriter(fWriter);
		for (String s : list) {
			writer.write(s);
			writer.write("\r\n");
		}
		writer.flush();
		writer.close();
		session.disconnect();
		return rs;
	}

	public static void main(String[] args) throws Exception {
		getResult();
		session.disconnect();
	}
}