import java.util.ArrayList;
import java.io.Console;

public class Registration {
	static private CourseSQL cq = new CourseSQL();
	static private Console console = System.console();
	static private String sql;

	public static void main(String[] args) {
		cq.connect();
		operate();
		boolean b = true;
		while (b) {
			b = ConsoleUtil.isYes("Do you want to continue (Y/N)? ");
			if (b) operate();
		}
	}

	public static void operate() {
		int studentId = readStudentId();
		Course c0 = readClassId();
		ArrayList<Course> courseList = cq.getCourseList(studentId);
		boolean enrolled = false, conflicted = false;
		sql = "SELECT count(*) as count, seats FROM registration JOIN courses ON class=id WHERE class=\'" + c0.getId() + "\' HAVING count>=seats";
		if (cq.query(sql)>0) {
			System.out.println("This class is full.");
			return;
		}
		for (Course c : courseList) {
			if (c.equals(c0)) {
				enrolled = true;
				Boolean b = ConsoleUtil.isYes("You\'r currently enrolled in this class. Do you want to drop it (Y/N)? ");
				if (b) {
					sql = "DELETE FROM registration WHERE class=\'" + c0.getId() + "\' AND student=" + studentId;
					cq.update(sql);
				}
				return;
			}
			if (c.isConflicted(c0)) {
				conflicted = true;
				System.out.println("This class conflicts with your current schedule.");
				return;
			}
		}
		Boolean b = ConsoleUtil.isYes("Do you want to register for this class (Y/N)? ");
		if (b) {
			sql = "INSERT INTO registration (class, student) VALUE (\'" + c0.getId() + "\', " + studentId + ")";
			cq.update(sql);
		}
	}

	public static boolean findStudent(int id) {
		String sql = "SELECT * FROM students WHERE id=" + id;
		int i = cq.query(sql);
		if (i==0) {
			System.out.println("Student ID not found.");
			return false;
		}
		return true;
	}

	public static int readStudentId() {
		String input = console.readLine("Please enter your Student ID: ");
		if (!input.matches("^[1-9][0-9]{0,7}$")) {
			System.out.println("Not a valid student ID.");
			return readStudentId();
		}
		int studentId = Integer.parseInt(input);
		if (!findStudent(studentId)) {
			return readStudentId();
		}
		return studentId;
	}

	public static Course readClassId() {
		String classId = console.readLine("Please enter class ID: ");
		Course c0 = cq.getCourse(classId);
		if (c0==null) {
			System.out.println("Not a valid class ID.");
			return readClassId();
		}
		return c0;
	}
}