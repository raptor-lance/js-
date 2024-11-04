// BankApplication.java

// Abstract class BankAccount
abstract class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    protected double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public abstract double calculateInterest();

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Current Balance: $" + balance);
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount + " | New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}

// Interface BankServices
interface BankServices {
    void openAccount();
    void closeAccount();
    double calculateFees();
}

// SavingsAccount class
class SavingsAccount extends BankAccount implements BankServices {
    private static final double INTEREST_RATE = 0.04; // 4%
    private static final double MAINTENANCE_FEE = 5.0;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public void openAccount() {
        System.out.println("Savings Account opened successfully.");
    }

    @Override
    public void closeAccount() {
        System.out.println("Savings Account closed successfully.");
    }

    @Override
    public double calculateFees() {
        return MAINTENANCE_FEE;
    }
}

// CurrentAccount class
class CurrentAccount extends BankAccount implements BankServices {
    private static final double INTEREST_RATE = 0.0; // No interest
    private static final double TRANSACTION_FEE = 10.0;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance) {
        super(accountNumber, accountHolderName, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * INTEREST_RATE;
    }

    @Override
    public void openAccount() {
        System.out.println("Current Account opened successfully.");
    }

    @Override
    public void closeAccount() {
        System.out.println("Current Account closed successfully.");
    }

    @Override
    public double calculateFees() {
        return TRANSACTION_FEE;
    }
}

// Main class
public class BankApplication {
    public static void main(String[] args) {
        BankAccount savings = new SavingsAccount("12345", "John Doe", 1000);
        BankAccount current = new CurrentAccount("67890", "Jane Smith", 2000);

        System.out.println("\n--- Savings Account ---");
        savings.displayAccountDetails();
        savings.deposit(500);
        savings.withdraw(300);
        System.out.println("Interest Earned: $" + savings.calculateInterest());
        System.out.println("Fees Deducted: $" + ((BankServices) savings).calculateFees());
        savings.displayAccountDetails();

        System.out.println("\n--- Current Account ---");
        current.displayAccountDetails();
        current.deposit(1000);
        current.withdraw(500);
        System.out.println("Interest Earned: $" + current.calculateInterest());
        System.out.println("Fees Deducted: $" + ((BankServices) current).calculateFees());
        current.displayAccountDetails();
    }
}
