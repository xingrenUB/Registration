import java.sql.*;
import java.io.Console;

public class SQLUtil {
	protected static Statement stmt;
	private static String driver = "com.mysql.jdbc.Driver";
	private static String address = "127.0.0.1:3306";
	private static String db = "jdbc:mysql://" + address + "/test";
	private static Console console = System.console();

	public static void main(String[] args) {
		connect();
		query("SELECT * FROM courses");
	}

	public static void setAddress(String newAddress) {
		db = "jdbc:mysql://" + newAddress + "/test";
	}

	public static String getAddress(String address) {
		return address;
	}

	public static void connect() {
		boolean b = ConsoleUtil.isYes("Is your MySQL installed at " + address + " (Y/N)? ");
		if (!b) {
			String newAddress = console.readLine("Please enter MySQL address: ");
			setAddress(newAddress);
		}
		String user = console.readLine("Username: ");
		String pw = new String(console.readPassword("Password: "));
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(db, user, pw);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("Unable to connect server. " + e.getMessage());
			connect();
		}
	}

	public static int query(String sql) {
		int k = 0;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData meta_data = rs.getMetaData();
			int[] columnWidth = new int[meta_data.getColumnCount()];
			String format;
			for (int i = 1; i <= meta_data.getColumnCount(); i++) {
				columnWidth[i-1] = meta_data.getColumnDisplaySize(i);
				format = "%-" + columnWidth[i-1] + "s\t";
				System.out.printf(format, meta_data.getColumnLabel(i));
			}
			System.out.println();
			while (rs.next()) {
				for (int i = 1; i <= meta_data.getColumnCount(); i++) {
					format = "%-" + columnWidth[i-1] + "s\t";
					System.out.printf(format, rs.getString(i));
				}
				System.out.println();
				k++;
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Query error:" + e.getMessage());
		}
		return k;
	}

	public static int update(String sql) {
		try {
			int i = stmt.executeUpdate(sql);
			System.out.println(i + " record(s) affected.");
			return i;
		} catch (Exception e) {
			System.out.println("Update error:" + e.getMessage());
			return -1;
		}
	}
}