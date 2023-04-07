package bank;

import java.util.*;
import java.io.*;
import java.time.*;

public class Saves {
	public static void saveData(Scanner scanner, SortedMap<Integer, Account> al) {
		//Attempt to open/create bank_save.txt for writing
		try {
			File save = new File("bank_save.txt");
			if (save.createNewFile()) {
				writeSave(al);
				System.out.println("in text file bank_save.txt\n");
			}
			else {
				writeSave(al);
				System.out.println("in bank_save.txt\n");
			}
		}
		catch (IOException e) {
			System.out.println("An error occurred while attempting to save.");
			e.printStackTrace();
		}
	}
	
	public static void writeSave(SortedMap<Integer, Account> al) {
		//Attempt to write to bank_save.txt
		try {
			FileWriter writer = new FileWriter("bank_save.txt");
			writer.write("Data saved on " + LocalDate.now() + "\n");
			for (Integer key : al.keySet()) {
				writer.write("\nName: " + al.get(key).getName() + "\n");
				writer.write("ID: " + al.get(key).getID() + "\n");
				writer.write("PIN: " + al.get(key).getPIN() + "\n");
				writer.write("Balance (Checking): " + al.get(key).getBalanceChecking() + "\n");
				writer.write("Balance (Savings): " + al.get(key).getBalanceSavings() + "\n");
				writer.write("Days Open: " + al.get(key).getDaysOpen() + "\n");
				writer.write("Current Cycle Day: " + al.get(key).getCycleDays() + "\n");
				writer.write("Withdraws this cycle: " + al.get(key).getSWithdraws() + "\n");
			}
			writer.close();
			System.out.print("Data saved ");
		}
		catch (IOException e) {
			System.out.println("Writing error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void loadData(SortedMap<Integer, Account> al) {
		String name, pin;
		float c_balance, s_balance;
		int id, cycle_days, days_open, s_withdraws;
		
		//Calculate days
		LocalDate today = LocalDate.now();
		int date_diff = 0;
		
		//Attempt to open bank_save.txt for reading
		try {
			File reader = new File("bank_save.txt");
			Scanner s = new Scanner(reader);
			LocalDate day = LocalDate.parse(s.nextLine().substring(14));
			date_diff = Period.between(day, today).getDays();
			
			//Remake accounts from save data
			while (s.hasNextLine()) {
				s.nextLine(); //Skip whitespace line
				name = s.nextLine().substring(6);
				id = Integer.parseInt(s.nextLine().substring(4));
				pin = s.nextLine().substring(5);
				c_balance = Float.parseFloat(s.nextLine().substring(20));
				s_balance = Float.parseFloat(s.nextLine().substring(19));
				days_open = Integer.parseInt(s.nextLine().substring(11));
				cycle_days = Integer.parseInt(s.nextLine().substring(19));
				s_withdraws = Integer.parseInt(s.nextLine().substring(22));
				
				Account a = new Account(name, pin, id, c_balance, s_balance, 
						cycle_days, days_open, s_withdraws);
				
				//Update account's cycle_days and days_open by date_diff
				a.updateDays(date_diff);
				
				al.put(id, a);
			}
			
			s.close();
			System.out.print("Data loaded from bank_save.txt\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("Error: bank_save.txt not found.");
			e.printStackTrace();
		}
	}
}
