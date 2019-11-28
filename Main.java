import java.util.*;

public class Main {
	private static String returnValue;
	public static void main(String[] args) {
		Item speaker = new Item("speaker", 2);
		Item panel = new Item("panel", 3);
		Item  psu = new Item("power-supply", 2);
		Item controller = new Item("panel-controller", 2);
		
		Scanner scanner = new Scanner(System.in);

		for (;;) {
			System.out.print(Color.GREEN_BRIGHT + "Inventory Managment System"+ Color.RESET);
			System.out.print(">" );
			Command command = new Command(scanner.nextLine());

			returnValue = command.execute();
			
			System.out.println(returnValue);
		}
	}
}
