package bank;

public class Account {
	private String name;
	private int id;
	private int days_open = 1;
	private float c_balance = 0;
	private float s_balance = 0;
	private int cycle_days = 1;
	private int s_withdraws = 0;
	private String pin; //length == 4, all digits
	
	//Constructors
	public Account(String n, String p, int num) {
		name = n;
		pin = p;
		id = num;
		
		//
		
		System.out.println("\nYour new account's id number is: " + id + ".\n");//Format 001, 002, etc
	}
	
	//4th variable (String) to determine if to deposit into c_bal or, into s_bal
	public Account(String n, String p, int num, float b, String w) {
		name = n;
		pin = p;
		id = num;
		
		if (w.equals("c")) {
			c_balance = b;
		}
		else if (w.equals("s")) {
			s_balance = b;
		}
		
		//
		
		System.out.println("\nYour new account's id number is: " + id + ".\n");//Format
	}
	
	//Used when loading saved data
	public Account(String n, String p, int num, float c, float s, int cd, int d, int sw) {
		name = n;
		pin = p;
		id = num;
		c_balance = c;
		s_balance = s;
		cycle_days = cd;
		days_open = d;
		s_withdraws = sw;
	}
	
	//Setters
	public void depositMoneyChecking(float m) {
		c_balance += m;
		
		System.out.println(String.format("\nThe checking balance of account "  
				+ id + " is: $%.2f\n", c_balance));
	}
	
	public void depositMoneySavings(float m) {
		s_balance += m;
		
		System.out.println(String.format("\nThe savings balance of account " 
				 + id + " is: $%.2f\n", s_balance));
	}
	
	public void withdrawMoneyChecking(float m) {
		if (m > c_balance) {
			c_balance = 0;
			System.out.println("The requested amount was more than the amount "
					+ "held within the account.");
		}
		else {
			c_balance -= m;
		}
		
		System.out.println(String.format("\nThe checking balance of account "  
				+ id + " is: $%.2f\n", c_balance));
	}
	
	public void withdrawMoneySavings(float m) {
		if (s_withdraws >= 5) {
			System.out.println("5 withdrawals from your savings account have already"
					+ "been made this 30-day cycle. Please try again after " +
					(30 - cycle_days + 1) + " day(s).\n");
			return;//Soft limit fee?
		}
		
		if (m > s_balance) {
			s_balance = 0;
			System.out.println("The requested amount was more than the amount "
					+ "held within the account.");
		}
		else {
			s_balance -= m;
		}
		
		s_withdraws++;
		
		System.out.println(String.format("\nThe savings balance of account " 
				 + id + " is: $%.2f\n", s_balance));
	}
	
	public void changeName(String n) {
		name = n;
		
		System.out.println("\nThe holder of account " + id + 
				" is now " + name + ".\n");
	}

	public void updateDays() {
		cycle_days++;
		days_open++;
		
		if (cycle_days > 30) {
			cycle_days = 1;
			s_withdraws = 0;
		}
		
		//Yearly interest //Subject to change
		if ((days_open - 1) % 365 == 0) {
			s_balance += s_balance * 0.01 * ((days_open - 1) / 365);
		}
	}
	
	//Used when loading data
	public void updateDays(int days) {
		days_open += days;
		
		if (cycle_days + days > 30) {//Math
			cycle_days = (cycle_days + days) & 30;
			s_withdraws = 0;
			//If the remainder of cycle_days / 30 is 0, then the account is on its 30th day of
			//its current cycle
			if (cycle_days == 0) {
				cycle_days = 30;
			}
		}
		
		//Yearly interest for every year that has passed //Subject to change
		if ((days_open - 1) % 365 == 0) {
			for (int i = 0; i < (days_open - 1) / 365; i++) {
				s_balance += s_balance * 0.01 * ((days_open - 1) / 365);
			}
		}
	}
	
	public void changePin(String p) {
		pin = p;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	//Require a master code to use?
	//Name shared with Helper method
	public String getPIN() {
		return pin;
	}
	
	public float getBalanceChecking() {
		return c_balance;
	}
	
	public float getBalanceSavings() {
		return s_balance;
	}
	
	public int getDaysOpen() {
		return days_open;
	}
	
	public int getCycleDays() {
		return cycle_days;
	}
	
	public int getSWithdraws() {
		return s_withdraws;
	}
	
	//Other
	public Boolean checkPIN(String p) {
		if (!pin.equals(p)) {
			System.out.println("\nIncorrect PIN\n");
		}
		
		return pin.equals(p);
	}
}
