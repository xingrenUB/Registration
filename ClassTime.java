public class ClassTime implements Comparable<ClassTime>{
	private int hr, min;

	public ClassTime (int h, int m) {
		hr = h;
		min = m;
	}

	public ClassTime (String s) {
		String[] t = s.split(":");
		hr = Integer.parseInt(t[0]);
		min = Integer.parseInt(t[1]);
	}

	public int compareTo(ClassTime t) {
		if (hr>t.hr) return 1;
		if (hr<t.hr) return -1;
		if (min>t.min) return 1;
		if (min<t.min) return -1;
		return 0;
	}
}
