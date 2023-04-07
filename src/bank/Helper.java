package bank;

import java.util.Scanner;

public class Helper {
	//Ask for string input
	public static String getString(Scanner scanner) {
		String input = scanner.nextLine();
		
		if (input.equals("")) {
			while (input.equals("")) {
				System.out.print("\nPlease type in the requested input: ");
				input = scanner.nextLine();
			}
		}
		
		return input;
	}
	
	//Ask for integer input
	public static int getInt(Scanner scanner) {
		String i_str = scanner.nextLine();
		
		while (true) {
			Boolean is_int = true;
			
			for (int j = 0; j < i_str.length(); j++) {
				if (!Character.isDigit(i_str.charAt(j))) {
					is_int = false;
					break;
				}
			}
			
			//Input was not a number, or was empty
			if (!is_int || i_str.equals("")) {
				System.out.print("\nPlease input an integer greater than 0: ");
				i_str = scanner.nextLine();
			}
			else if (is_int) {
				return Integer.parseInt(i_str);
			}
		}
	}
	
	//Ask for float input
	public static float getFloat(Scanner scanner) {
		String f_str = scanner.nextLine();
		
		while (true) {
			Boolean is_float = true;
			int decimals = 0;
				
			for (int i = 0; i < f_str.length(); i++) {
				//Neither a digit nor possibly a decimal
				if (!Character.isDigit(f_str.charAt(i)) 
						&& f_str.charAt(i) != '.') {//equals
					is_float = false;
					break;
				}
				//Neither a digit nor a decimal
				else if (!Character.isDigit(f_str.charAt(i)) 
						&& f_str.charAt(i) == '.' && decimals == 1) {//Not a decimal
					is_float = false;
					break;
				}
				//Found a decimal
				else if (f_str.charAt(i) == '.') {
					decimals++;
				}
			}
				
			//Input was not a number, or was empty
			if (!is_float || f_str.equals("")) {
				System.out.print("\nPlease input a number greater than 0.00: ");
				f_str = scanner.nextLine();
			}
			//If the input was 0
			else if (Float.parseFloat(f_str) == 0) {
				System.out.print("\nPlease input a number greater than 0.00: ");
				f_str = scanner.nextLine();
			}
			else if (is_float) {
				return (float) (Math.round(Float.parseFloat(f_str) * 100.0) / 100.0);//Check math -> Cut off after 2 decimals
			}
		}
	}
	
	//Ask for PIN input
	//Name shared with Account method
	public static String getPIN(Scanner scanner) {
		String pin;
		
		while (true) {
			Boolean is_pin = true;
			pin = getString(scanner);
			
			//Input was not 4 characters long
			if (pin.length() != 4) {
				System.out.print("\nPlease type in only 4 digits: ");
				continue;
			}
			
			for (int i = 0; i < pin.length(); i++) {
				if (!Character.isDigit(pin.charAt(i))) {
					is_pin = false;
					break;
				}
			}
			
			//Input was not digits
			if (!is_pin) {
				System.out.print("\nPlease type in only 4 digits: ");
				continue;
			}
			else if (is_pin) {
				return pin;
			}
		}
	}
}
