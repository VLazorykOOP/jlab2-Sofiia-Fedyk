public class Account {
    private String ownerName;       
    private String accountNumber;    
    private double interestRate;     
    private double balance;          

    public Account(String ownerName, String accountNumber, double interestRate, double initialBalance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
        this.balance = initialBalance;
    }

    public void changeOwner(String newOwnerName) {
        this.ownerName = newOwnerName;
        System.out.println("Owner changed to: " + newOwnerName);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + " UAH. Remaining balance: " + balance + " UAH");
        return true;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount + " UAH. New balance: " + balance + " UAH");
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest applied: " + interest + " UAH. New balance: " + balance + " UAH");
    }

    public String getBalanceInWords() {
        String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        int wholePart = (int) balance;
        int cents = (int) Math.round((balance - wholePart) * 100);

        if (wholePart == 0) {
            return "zero UAH " + cents + " cents";
        }

        StringBuilder result = new StringBuilder();

        int thousands = wholePart / 1000;
        if (thousands > 0) {
            result.append(convertNumberToWords(thousands)).append(" thousand ");
        }

        int remainder = wholePart % 1000;
        if (remainder > 0) {
            result.append(convertNumberToWords(remainder));
        }

        result.append(" UAH ");

        return result.toString().trim();
    }
    private String convertNumberToWords(int number) {
        String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

        StringBuilder result = new StringBuilder();

        int hundred = number / 100;
        int remainder = number % 100;
        int ten = remainder / 10;
        int one = remainder % 10;

        if (hundred > 0) {
            result.append(ones[hundred]).append(" hundred ");
        }

        if (remainder > 0) {
            if (remainder < 10) {
                result.append(ones[remainder]);
            } else if (remainder < 20) {
                result.append(teens[remainder - 10]);
            } else {
                result.append(tens[ten]);
                if (one > 0) {
                    result.append("-").append(ones[one]);
                }
            }
        }

        return result.toString();

        
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void printAccountInfo() {
        System.out.println("Account Information");
        System.out.println("Owner: " + ownerName);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Interest rate: " + interestRate + "%");
        System.out.println("Balance: " + balance + " UAH");
        System.out.println("Balance in words: " + getBalanceInWords());
        System.out.println();
    }

    public static void main(String[] args) {
       
        Account account = new Account("Ivanenko", "UA123456789", 5.5, 1000.0);
        
        account.printAccountInfo();
        
        account.deposit(500.50);
        
        account.withdraw(200.0);
        
        account.applyInterest();
        
        account.changeOwner("Petrenko");
        
        account.printAccountInfo();
    }
}