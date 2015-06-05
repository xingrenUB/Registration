import java.io.Console;

public class ConsoleUtil {
	private static Console console = System.console();
	
	public static void main(String[] args) {
		System.out.println(isYes("Give me your answer (Y/N):"));
	}
	
	public static boolean isYes(String s) {
		String answer = console.readLine(s);
		if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes"))
			return true;
		else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no"))
			return false;
		return isYes(s);
	}
}