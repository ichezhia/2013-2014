import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileChecker {
	User user = new User();
	
	public TextFileChecker() throws FileNotFoundException {
		user.setTotalRooms(0);
		Scanner file = new Scanner(new File("src/data.txt"));
		
		while(file.hasNext()) {
			  user.setTotalRooms(user.getTotalRooms()+1);
			  file.nextLine();
		}
	}
}