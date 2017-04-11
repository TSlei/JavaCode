package com.zl.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	
	public String OSS_BUCKET_NAME = "ydhres";

	public static final String url = "jdbc:mysql://192.168.1.200:3306/ydh";
	public static final String name = "com.mysql.jdbc.Driver";
	public static final String user = "root";
	public static final String password = "ydh20130815";
	
	public Connection conn = null;
	
	private PreparedStatement ConnectToMysql(String sql) {
		PreparedStatement pst = null;
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);
			pst = conn.prepareStatement(sql);
			return pst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pst;
	}

	public void close(Connection conn, PreparedStatement pst) {
		try {
			pst.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateSize(String updateSql, long contentLength, Long dbid, String imgUrl) throws SQLException{
		PreparedStatement pst = ConnectToMysql(updateSql);
		pst.setLong(1, contentLength);
		pst.setLong(2, dbid);
		pst.setString(3, imgUrl);
		pst.executeUpdate();
	}
	
	public ResultSet queryAll(String querySql) throws SQLException{
		PreparedStatement pst = ConnectToMysql(querySql);
		ResultSet rs = pst.executeQuery();
		return rs;
	}

}