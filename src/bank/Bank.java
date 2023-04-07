package bank;

import java.util.*;
import java.time.*;

class Task extends TimerTask {
	public SortedMap<Integer, Account> al;
	
	public Task(SortedMap<Integer, Account> sm) {
		al = sm;
	}
	
	LocalTime midnight = LocalTime.parse("00:00:00");
	LocalDate recorded_date = LocalDate.now();
	
	public void run() {
		//Every midnight, update every Account's cycle_days by 1
		//If an Account's cycle_days is updated to 31, reset it to 1
		if (midnight.compareTo(LocalTime.now()) <= 0 
				&& recorded_date.compareTo(LocalDate.now()) == -1) {
			for (Integer key : al.keySet()) {
				al.get(key).updateDays();
			}
		}
	}
}

public class Bank {
	//Keeps track of what the next account id number should be
	public static int num_accounts = 1;
	
	public static void main_menu(Scanner scanner, SortedMap<Integer, Account> al) {
		System.out.println("Main Menu"); //
		
		while (true) {
			System.out.println("1. Open an account");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. View account details");
			System.out.println("5. Change account details");
			System.out.println("6. Close account");
			System.out.println("7. Save data"); //
			System.out.println("8. Quit");
			System.out.print("Option: ");
			
			String option_str = scanner.nextLine();
			
			//Make sure that the user inputs a 1-character string that is a digit
			if (option_str.length() > 1 || !Character.isDigit(option_str.charAt(0))) {
				System.out.println("\nNot a number.\n");
				continue;
			}
			
			int option = Integer.parseInt(option_str);
			
			System.out.println("");
			
			switch(option) {
				case 1:
					Manage.openAccount(scanner, al, num_accounts);
					num_accounts++;
					break;
				case 2:
					Manage.accountDeposit(scanner, al);
					break;
				case 3:
					Manage.accountWithdraw(scanner, al);
					break;
				case 4:
					Manage.accountDetails(scanner, al);
					break;
				case 5:
					Manage.accountModify(scanner, al);
					break;
				case 6:
					Manage.closeAccount(scanner, al);
					break;
				case 7:
					Saves.saveData(scanner, al);
					break;
				case 8:
					return;
				default:
					System.out.println("Please type a number from 1 to 8.\n");
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Hello\n");
		
		Scanner scanner = new Scanner(System.in);
		
		//Sorted Map of every existing account (Key is account ID, Value is Account object)
		SortedMap<Integer, Account> account_list = new TreeMap<Integer, Account>();
		
		//Load
		System.out.print("Do you wish to load data from bank_save.txt? [y/n]: ");
		String load_option = scanner.nextLine();
		
		switch (load_option) {
		case "y":
			Saves.loadData(account_list);
			System.out.println("");
			break;
		case "n":
			System.out.println("");
			break;
		default:
			System.out.println("\nPlease type 'y' or 'n'.\n");
			break;
		}
		
		//Create a timer instance
		Timer timer = new Timer();
		
		//Create task to be scheduled
		Task task = new Task(account_list);
		
		//Schedule the timer instance
		timer.schedule(task, 1000, 1000);
		
		//b.main_menu();
		main_menu(scanner, account_list);
		
		task.cancel();
		
		timer.cancel();
		
		scanner.close();
		
		System.out.println("Good-bye");
	}
}
