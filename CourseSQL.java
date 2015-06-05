import java.util.ArrayList;
import java.sql.*;

public class CourseSQL extends SQLUtil {
	public static Course getCourse(String classId) {
		Course c = null;
		String sql = "SELECT id, name, day, start, end, seats FROM courses WHERE id=\'" + classId + "\'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String id = rs.getString("id");
				c = new Course(id);
				String name = rs.getString("name");
				String day = rs.getString("day");
				String startTime = rs.getString("start");
				String endTime = rs.getString("end");
				int seats = rs.getInt("seats");
				ClassTime start = new ClassTime(startTime);
				ClassTime end = new ClassTime(endTime);
				c.setInfo(name, day, start, end, seats);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Query error in getCourse(): " + e.getMessage());
		}
		return c;
	}

	public static ArrayList<Course> getCourseList(int studentId) {
		ArrayList<Course> courseList = new ArrayList<Course>();
		String sql = "SELECT id, name, day, start, end, seats FROM registration JOIN courses ON class=id WHERE student=\'" + studentId + "\'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString("id");
				Course c = new Course(id);
				String name = rs.getString("name");
				String day = rs.getString("day");
				String startTime = rs.getString("start");
				String endTime = rs.getString("end");
				int seats = rs.getInt("seats");
				ClassTime start = new ClassTime(startTime);
				ClassTime end = new ClassTime(endTime);
				c.setInfo(name, day, start, end, seats);
				courseList.add(c);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Query error in getCourseList(): " + e.getMessage());
		}
		return courseList;
	}
}