package by.training.exerciseaccount.entity;

public class Account {
    private int accountId;
    private double balance;
    private boolean isBlocked;

    public Account(int accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return this.balance <= -1000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (Double.compare(account.balance, balance) != 0) return false;
        return isBlocked == account.isBlocked;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountId;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isBlocked ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
