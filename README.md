# Bank
Self-made project in Java, attempting to simulate a bank's database of customers' accounts

Account Attributes
1. name - Account holder name
2. id - A number associated with the Account, used to keep track of which Account object a user wishes to interact with
3. days_open - How many days that the Account object has existed
4. c_balance - How much money is within the Account's checking balance
5. s_balance - How much money is within the Account's savings balance
6. cycle_days - How many days the Account has been into a 30-day cycle; resets to 1 once the attribute hits 31
7. s_withdrawals - How many withdrawals from the savings balance have been made in the current cycle. Hard limit of 5 per cycle. Resets to 0 upon the start of a new cycle
9. pin - A 4-character long string comprised of digits that acts as a password for the Account

Main Menu Options
1. Open an account - Creates an Account object
  -Asks for user input for account holder name and a 4-digit PIN
  -Asks the user if a deposit should immedietely be made, to cchecking or savings, and how much
  -Upon making the account, user is informed of the new account's id number
  
2. Deposit - Increase an account's balance
  -Asks user to input an account's id number, as well as its associated PIN
    -Iputting an id number not associated with any existing open account will return the user to the main menu
  -Asks user 
  -Correct PIN will output the details listed above; incorrect PIN returns user to the main menu

3. Withdraw - Decrease an account's balance
  -Asks user to input an account's id number, as well as its associated PIN
    -Iputting an id number not associated with any existing open account will return the user to the main menu
    -Correct PIN will output the details listed above; incorrect PIN returns user to the main menu

4. View account details - View an account's holder name, checking balance, and savings balance
  -Asks user to input an account's id number, as well as its associated PIN
    -Iputting an id number not associated with any existing open account will return the user to the main menu
    -Correct PIN will output the details listed above; incorrect PIN returns user to the main menu

5. Modify account details - Allows a user to change the account holder and/or PIN of an account
  -Asks user to input an account's id number, as well as its associated PIN
    -Iputting an id number not associated with any existing open account will return the user to the main menu
    -Correct PIN will allow the user to change any of the details listed above; incorrect PIN returns user to the main menu
  -Asks the user which detail they wish to change
    -Changing the account holder simply requires the user to input a string
    -Changing the PIN requires the user to first input the original PIN, then a new 4-digit PIN
      -Inputting the incorrect original PIN will return the user to the main menu

6. Close an account - Deletes an Account object
  -Asks the user for the id number of the account that they wish to close, as well as its associated PIN
    -Iputting an id number not associated with any existing open account will return the user to the main menu
    -Inputting the correct PIN will result in the account being closed (deleted); incorrect PIN will return user to the main menu

7. Save - Saves every currectly open Account objects' attributes to a 'bank_save.txt' file, as well as the date that the save was made

8. Load - Asked on start-up, replying 'y' will result in the recreation of accounts that were saved in 'bank_save.txt'
  -The load will occur before the user is given access to the main menu
  -The load will also update every account's cycle_days, days_open, s_withdrawals, and s_balance attributes based on how much time has 
  passed since the save was made
