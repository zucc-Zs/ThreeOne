package cn.edu.zucc.personplan.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.beans.PropertyVetoException;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class DBUtil2 {
	private static DBUtil2 dbUtil2;
	private static ComboPooledDataSource dataSource;

	static {
		dbUtil2 = new DBUtil2();
	}

	public DBUtil2() {
		try {
			dataSource = new ComboPooledDataSource();
			dataSource.setUser("root");
			dataSource.setPassword("");
			dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/final?useUnicode=true&characterEncoding=UTF-8");
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setInitialPoolSize(2);
			dataSource.setMinPoolSize(1);
			dataSource.setMaxPoolSize(10);
			dataSource.setMaxStatements(50);
			dataSource.setMaxIdleTime(60);
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
	}

	public final static DBUtil2 getInstance() {
		return dbUtil2;
	}

	public final static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("无法从数据源获取连接 ", e);
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			con = DBUtil2.getInstance().getConnection();
			java.sql.ResultSet rs=con.createStatement().executeQuery("select id from tbl_logger_visit");
			while(rs.next())
				System.out.println(rs.getString(1));
		} catch (Exception e) {
		} finally {
			if (con != null)
				con.close();
		}
	}
}