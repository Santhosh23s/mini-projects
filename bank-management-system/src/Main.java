import java.util.HashMap;
import java.util.Scanner;
import java.util.HashMap;

abstract class Account {
    // Instance variables
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    // Constructor
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        // Initialize variables
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Abstract methods (must be implemented by subclasses)
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount) throws InsufficientBalanceException;

    // Concrete method
    public double getBalance() {
        return balance;
    }

    public void displayAccountInfo() {
        // Print account details
        System.out.println(STR."Account Number: \{accountNumber}");
        System.out.println(STR."Account Holder: \{accountHolder}");
        System.out.println(STR."Balance: \{balance}");
    }
}

class SavingsAccount extends Account {
    private static final double MIN_BALANCE = 500.0;

    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }

    @Override
    public void deposit(double amount) {
        if(amount <= 0){
            System.out.println("Amount must be positive");
            return;
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: ₹" + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        // Check: amount > 0
        if(amount>0){
            // Check: balance - amount >= MIN_BALANCE
            double check = balance - amount;
            if(check < MIN_BALANCE){
                // If check fails, throw InsufficientBalanceException
                throw new InsufficientBalanceException("Transaction declined: minimum balance of ₹500 must be maintained.");
            }
            else{
                // Else, deduct amount and print success message
                balance-=amount;
                System.out.println("Withdrawn ₹" + amount + " successfully. Balance: ₹" + balance);
            }
        }
    }
}

class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolder,
    double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolder, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        if(amount <= 0){
            System.out.println("Amount must be positive");
            return;  // Exit method
        }

        balance += amount;
        System.out.println("Deposit successful. New balance: ₹" + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if(amount <= 0) {
            System.out.println("Amount must be positive");
            return;
        }

        // Check if withdrawal exceeds overdraft limit
        if(balance - amount < -overdraftLimit) {
            throw new InsufficientBalanceException("Overdraft limit exceeded");
        }

        balance -= amount;

        if(balance < 0) {
            System.out.println("Overdraft utilized: ₹" + Math.abs(balance));
        }

        System.out.println("Withdrawal successful. Balance: ₹" + balance);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}


 class BankManager {
    private HashMap<String, Account> accounts;

    public BankManager() {
        accounts = new HashMap<>();
    }

    // Create new account
    public void createAccount(String type, String accNum, String holder,
                              double initialBalance, double overdraft) {
        SavingsAccount savingsAccount;
        CurrentAccount currentAccount;
        // If type == "savings", create SavingsAccount
        if(type.equals("savings")){
            savingsAccount = new SavingsAccount(accNum,holder,initialBalance);
            accounts.put(accNum,savingsAccount);
        }
        // If type == "current", create CurrentAccount
        else if(type.equals("current")){
            currentAccount = new CurrentAccount(accNum,holder,initialBalance,overdraft);
            accounts.put(accNum,currentAccount);
        }
        // Store in HashMap: accounts.put(accNum, accountObject)
    }

    // Deposit money
    public void deposit(String accNum, double amount) {
        Account ac = accounts.get(accNum);

        if(ac != null) {
            ac.deposit(amount);
        } else {
            System.out.println("Account not found");
        }
    }

     public void withdraw(String accNum, double amount) {
         Account ac = accounts.get(accNum);

         if(ac != null) {
             try {
                 ac.withdraw(amount);
             } catch(InsufficientBalanceException e) {
                 System.out.println(e.getMessage());
             }
         } else {
             System.out.println("Account not found");
         }
     }

     public void checkBalance(String accNum) {
         Account ac = accounts.get(accNum);

         if(ac != null) {
             System.out.println("Current Balance: ₹" + ac.getBalance());
         } else {
             System.out.println("Account not found");
         }
     }

    // Display all accounts
    public void displayAllAccounts() {
        // Loop through HashMap and print all account details
        System.out.println("Bank Account Details: ");
        for (String acc : accounts.keySet()) {
            Account account = accounts.get(acc);

            if (account == null) {
                System.out.println("Invalid account entry for key: " + acc);
                continue;
            }

            System.out.println("Account Number: " + account.accountNumber);
            System.out.println("Account Holder: " + account.accountHolder);
            System.out.println("Account Balance: " + account.balance);
            System.out.println();
        }

    }
}
public class Main{
public static void main(String[] args) {
    BankManager bank = new BankManager();  // Create ONCE
    Scanner sc = new Scanner(System.in);

    while(true) {
        System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. Display All Accounts");
        System.out.println("6. Exit");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        sc.nextLine();  // Consume newline

        switch(choice) {
            case 1:
                System.out.print("Account type (savings/current): ");
                String accountType = sc.nextLine().toLowerCase();

                System.out.print("Account Number: ");
                String accountNum = sc.nextLine();

                System.out.print("Account Holder: ");
                String accountName = sc.nextLine();

                System.out.print("Initial Balance: ");
                double accBalance = sc.nextDouble();

                if(accountType.equals("savings")) {
                    bank.createAccount(accountType, accountNum, accountName, accBalance, 0);
                    System.out.println("Savings account created successfully!");
                } else if(accountType.equals("current")) {
                    System.out.print("Overdraft Limit: ");
                    double overDraft = sc.nextDouble();
                    bank.createAccount(accountType, accountNum, accountName, accBalance, overDraft);
                    System.out.println("Current account created successfully!");
                } else {
                    System.out.println("Invalid account type!");
                }
                break;

            case 2:
                sc.nextLine();  // Consume newline
                System.out.print("Enter Account Number: ");
                String depAccNum = sc.nextLine();
                System.out.print("Enter Deposit Amount: ");
                double depositAmount = sc.nextDouble();
                bank.deposit(depAccNum, depositAmount);
                break;

            case 3:
                sc.nextLine();
                System.out.print("Enter Account Number: ");
                String withAccNum = sc.nextLine();
                System.out.print("Enter Withdraw Amount: ");
                double withdrawAmount = sc.nextDouble();
                bank.withdraw(withAccNum, withdrawAmount);
                break;

            case 4:
                sc.nextLine();
                System.out.print("Enter Account Number: ");
                String balAccNum = sc.nextLine();
                bank.checkBalance(balAccNum);
                break;

            case 5:
                bank.displayAllAccounts();
                break;

            case 6:
                System.out.println("Thank you for using our bank!");
                sc.close();
                System.exit(0);

            default:
                System.out.println("Invalid choice!");
        }
    }
}
        }