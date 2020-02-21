//package com.examples.ezoo.jdbc;
//
//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.DatabaseMetaData;
//
//public class TestConnection {
//	public static void main(String[] args) {
//		try {
//			Class.forName("or.postgresql.Driver");
//			
//			String url = "jbc:postgresql://localhost:5432/eZoo";
//			String user = "postgres";
//			String pass = "b85cr23vv8v4Cf7Bnq83C";
//			
//			Connection conn = DriverManager.getConnection(url, user, pass);
//			
//			DatabaseMetaData meta = conn.getMetaData();
//			
//			System.out.println(meta);
//
//			conn.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
