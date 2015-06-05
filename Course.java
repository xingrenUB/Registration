public class Course {
	private final String id;
	private String name, day;
	private int seats;
	private ClassTime start, end;

	public Course(String id) {
		this.id = id;
	}

	public void setInfo(String name, String day, ClassTime start, ClassTime end, int seats) {
		this.name = name;
		this.day = day;
		this.start = start;
		this.end = end;
		this.seats = seats;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isConflicted(Course c) {
		return !(!day.equals(c.day) || 
			start.compareTo(c.end)>=0 || 
			end.compareTo(c.start)<=0);
	}

	public boolean equals(Course c) {
		return id.equals(c.id);
	}
}

