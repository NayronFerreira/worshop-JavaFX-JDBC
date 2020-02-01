package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {
			if (conn == null) {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
				System.out.println("Conectado com sucesso!");
			}
			return conn;
		} catch (SQLException x) {
			throw new DbException(x.getMessage());
		}
	}

	public static void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("Desconectado com sucesso!");
			}
		} catch (SQLException x) {
			throw new DbException(x.getMessage());
		}
	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException x) {
			throw new DbException(x.getMessage());
		}

	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException x) {
				throw new DbException(x.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();

			} catch (SQLException x) {
				throw new DbException(x.getMessage());
			}
		}
	}
}
