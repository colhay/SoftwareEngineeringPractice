package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email or startingBalance is invalid.
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
        if (isAmountValid(startingBalance)){
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Starting Balance: " + startingBalance + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * Withdraws the amount from the account balance.
     * @param amount the double to withdraw.
     * @post reduces the balance by amount if amount is non-negative, non-zero, and smaller than balance
     * @throws IllegalArgumentException if amount is invalid or greater than the balance.
     */
    public void withdraw (double amount)  {
        if (isAmountValid(amount)){
            if (amount < getBalance()) {
                balance -= amount;
            }
            else {
                throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdraw");
            }
        }
        else {
            throw new IllegalArgumentException("Amount: " + amount + " is invalid, cannot withdraw");
        }
    }

    /**
     * Checks if email is valid.
     * @param email the String to be validated.
     * @return      the boolean representing the validation result.
     * @post        returns true if the amount is positive and has 2 decimal points or less.
     */
    public static boolean isEmailValid(String email){
        int firstIndexOfAt = email.indexOf('@');
        int lastIndexOfAt = email.lastIndexOf('@');

        if (firstIndexOfAt == -1) return false; // check that @ was found
        if (firstIndexOfAt != lastIndexOfAt) return false; // check that there is only 1 @

        // get prefix and domain
        String prefix = email.substring(0, firstIndexOfAt);
        String domain = email.substring(firstIndexOfAt+1);

        // validate prefix
        if (prefix.length() == 0) return false; // check that the prefix is not empty

        // validate domain
        if (domain.indexOf('.') == -1) return false; // check that . was found in the domain
        if (domain.indexOf('.') == 0) return false; // check that . is not the first character of the domain
        if (domain.indexOf('.') == domain.length()) return false; // check that . is not the last character of the domain

        return true;
    }

    /**
     * Checks if amount is valid.
     * @param amount the double to be validated.
     * @return      the boolean representing the validation result.
     */
    public static boolean isAmountValid(double amount){
        if (amount < 0) return false; // check if amount is negative

        // convert amount to String and get the decimal places
        String amountString = Double.toString(amount);
        if (amountString.indexOf('.') != -1) {
            int decimalPlaces = amountString.length() - amountString.indexOf('.') - 1;
            if (decimalPlaces > 2) return false;
        }

        return true;
    }
}
