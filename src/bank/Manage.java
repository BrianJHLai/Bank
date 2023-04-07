package bank;

import java.util.*;

public class Manage {
	//Makes a new Account, and adds it to the account_list map
	public static void openAccount(Scanner scanner, SortedMap<Integer, Account> al, int na) {
		//Get account holder's name
		System.out.print("Please input your name: ");
		String name = Helper.getString(scanner);
		
		//Get and check pin
		System.out.print("Please type in 4 digits for your PIN: ");
		String pin = Helper.getPIN(scanner);
		
		int id = na;
		Account a;
		
		while (true) {
			//Determine if account holder wants to deposit money
			//while opening a new account
			System.out.print("Do you wish to immedietely deposit "
					+ "money into your account? [y/n]: ");
			String answer = Helper.getString(scanner);
			
			switch(answer.toLowerCase()) {
				case "y":
					while (true) {
						//Determine which account to deposit into
						System.out.print("Deposit into your checking account, "
								+ "or into your savings account? [c/s]: ");
						String which = Helper.getString(scanner);
						float deposit;
						
						switch(which.toLowerCase()) {
							case "c":
								System.out.print("How much?: ");
								deposit = Helper.getFloat(scanner);
								a = new Account(name, pin, id, deposit, "c");
								al.put(id, a);
								return;
							case "s":
								System.out.print("How much?: ");
								deposit = Helper.getFloat(scanner);
								a = new Account(name, pin, id, deposit, "s");
								al.put(id, a);
								return;
							//case "cs":
							default:
								System.out.println("\nPlease type 'c' or 's'.\n");
								break;
						}
					}
				case "n":
					a = new Account(name, pin, id);
					al.put(id, a);
					return;
				default:
					System.out.println("\nPlease type 'y' or 'n'.\n");
					break;
			}
		}
	}
		
	public static void accountDeposit(Scanner scanner, SortedMap<Integer, Account> al) {
		//Check if there is at least 1 open account
		if (al.size() == 0) {
			System.out.println("There are currently no open accounts.\n");
			return;
		}
		
		//Find account
		int id;
		System.out.print("Please type in the id number of your account: ");
		id = Helper.getInt(scanner);
		
		//Check if account is open
		if (!al.containsKey(id)) {
			System.out.println("\nThere is no open account with the id number of " + id + ".\n");
			return;
		}
		
		//Get and check PIN
		System.out.print("Please type in your 4-digit PIN: ");
		String pin = Helper.getPIN(scanner);
		if (!al.get(id).checkPIN(pin)) {
			return;
		}
		
		while (true) {
			//Display amounts in both checking and savings
			System.out.println("\nChecking Account Balance: $%.2f" + al.get(id).getBalanceChecking());
			System.out.println("Savings Account Balance: $%.2f" + al.get(id).getBalanceSavings() + "\n");
			
			//Determine which account to deposit into
			System.out.print("Deposit into your checking account, "
					+ "or into your savings account? [c/s]: ");
			String which = Helper.getString(scanner);
			float deposit;
			
			switch(which.toLowerCase()) {
				case "c":
					System.out.print("How much?: ");
					deposit = Helper.getFloat(scanner);
					al.get(id).depositMoneyChecking(deposit);
					return;
				case "s":
					System.out.print("How much?: ");
					deposit = Helper.getFloat(scanner);
					al.get(id).depositMoneySavings(deposit);
					return;
				default:
					System.out.println("\nPlease type 'c' or 's'.\n");
					break;
			}
		}
	}
		
	public static void accountWithdraw(Scanner scanner, SortedMap<Integer, Account> al) {
		//Check if there is at least 1 open account
		if (al.size() == 0) {
			System.out.println("There are currently no open accounts.\n");
			return;
		}
				
		//Find account
		int id;
		System.out.print("Please type in the id number of your account: ");
		id = Helper.getInt(scanner);
		
		//Check if account is open
		if (!al.containsKey(id)) {
			System.out.println("\nThere is no open account with the id number of " + id + ".\n");
			return;
		}
		
		//Get and check PIN
		System.out.print("Please type in your 4-digit PIN: ");
		String pin = Helper.getPIN(scanner);
		if (!al.get(id).checkPIN(pin)) {
			return;
		}
				
		while (true) {
			//Display amounts in both checking and savings
			System.out.println("\nChecking Account Balance: $%.2f" + al.get(id).getBalanceChecking());
			System.out.println("Savings Account Balance: $%.2f" + al.get(id).getBalanceSavings() + "\n");
			
			//Determine which account to deposit into
			System.out.print("Withdraw from your checking account, "
					+ "or from your savings account? [c/s]: ");
			String which = Helper.getString(scanner);
			float withdraw;
					
			switch(which.toLowerCase()) {
				case "c":
					System.out.print("How much?: ");
					withdraw = Helper.getFloat(scanner);
					al.get(id).withdrawMoneyChecking(withdraw);
					return;
				case "s":
					System.out.print("How much?: ");
					withdraw = Helper.getFloat(scanner);
					al.get(id).withdrawMoneySavings(withdraw);
					return;
				default:
					System.out.println("\nPlease type 'c' or 's'.\n");
					break;
			}
		}
	}
		
	public static void accountDetails(Scanner scanner, SortedMap<Integer, Account> al) {
		//Check if there is at least 1 open account
		if (al.size() == 0) {
			System.out.println("There are currently no open accounts.\n");
			return;
		}
						
		//Find account
		int id;
		System.out.print("Please type in the id number of your account: ");
		id = Helper.getInt(scanner);
		
		//Check if account is open
		if (!al.containsKey(id)) {
			System.out.println("\nThere is no open account with the id number of " + id + ".\n");
			return;
		}
		
		//Get and check PIN
		System.out.print("Please type in your 4-digit PIN: ");
		String pin = Helper.getPIN(scanner);
		if (!al.get(id).checkPIN(pin)) {
			return;
		}
		
		//Print out account name, balances
		System.out.println("\nAccount Holder: " + al.get(id).getName());
		System.out.println("Checking Account Balance: $%.2f" + al.get(id).getBalanceChecking());
		System.out.println("Savings Account Balance: $%.2f" + al.get(id).getBalanceSavings() + "\n");
	}
		
	public static void accountModify(Scanner scanner, SortedMap<Integer, Account> al) {
		//Check if there is at least 1 open account
		if (al.size() == 0) {
			System.out.println("There are currently no open accounts.\n");
			return;
		}
								
		//Find account
		int id;
		System.out.print("Please type in the id number of your account: ");
		id = Helper.getInt(scanner);
		
		//Check if account is open
		if (!al.containsKey(id)) {
			System.out.println("\nThere is no open account with the id number of " + id + ".\n");
			return;
		}
		
		//Get and check PIN
		System.out.print("Please type in your 4-digit PIN: ");
		String pin = Helper.getPIN(scanner);
		if (!al.get(id).checkPIN(pin)) {
			return;
		}
		else {
			//For spacing*
			System.out.println("");
		}
		
		String option_str;
		
		while (true) {
			System.out.println("Which account detail do you wish to modify?");
			System.out.println("1. Account holder");
			System.out.println("2. PIN");
			System.out.println("3. Quit");
			System.out.print("Option: ");
			
			option_str = Helper.getString(scanner);
			
			//Make sure that the user inputs a 1-character string that is a digit
			if (option_str.length() > 1 || !Character.isDigit(option_str.charAt(0))) {
				System.out.println("\nNot a number.\n");
				continue;
			}
			
			int option = Integer.parseInt(option_str);
			
			System.out.print("");
			
			switch(option) {
				case 1:
					System.out.print("Please type in the name of the new account holder: ");//Wording
					String n = Helper.getString(scanner);
					al.get(id).changeName(n);
					break;
				case 2:
					System.out.print("Please type in your 4-digit PIN: ");
					pin = Helper.getPIN(scanner);
					
					//Check inputed PIN
					if (!al.get(id).checkPIN(pin)) {
						break;
					}
					
					System.out.print("Please type in your new 4-digit PIN: ");
					String p = Helper.getPIN(scanner);
					al.get(id).changePin(p);
					break;//return instead?
				case 3:
					return;
				default:
					System.out.println("Please type a number from 1 to 3.");
					break;
			}
		}
	}
		
	public static void closeAccount(Scanner scanner, SortedMap<Integer, Account> al) {
		//Check if there is at least 1 open account
		if (al.size() == 0) {
			System.out.println("There are currently no open accounts.\n");
			return;
		}
								
		//Find account
		int id;
		System.out.print("Please type in the id number of your account that you wish to close: ");
		id = Helper.getInt(scanner);
		
		//Check if account is open
		if (!al.containsKey(id)) {
			System.out.println("\nThere is no open account with the id number of " + id + ".\n");
			return;
		}
		
		//Get and check PIN
		System.out.print("Please type in your 4-digit PIN: ");
		String pin = Helper.getPIN(scanner);
		if (!al.get(id).checkPIN(pin)) {
			return;
		}
		
		//Close account
		al.remove(id);//Check if correct
		
		System.out.println("\nAccount " + id + " has now been closed.\n");

	}
}
