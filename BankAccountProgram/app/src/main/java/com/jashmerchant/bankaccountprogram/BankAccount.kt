package com.jashmerchant.bankaccountprogram

class BankAccount(
    private var accountHolder: String,
    private var balance: Double) {
    private val transactionHistory = mutableListOf<String>()
    fun deposit(amount: Double) {
        balance += amount
        transactionHistory.add("$amount was deposited. Ending balance $balance")
    }

    fun withdraw(amount: Double) {
        balance -= amount
        transactionHistory.add("$amount was withdrawn. Ending balance $balance")
    }

    fun displayTransactionHistory() {
        for (transaction in transactionHistory) {
            println(transaction)
        }
    }
    fun checkBalance() {
        println("${accountHolder}'s current balance is $$balance")
    }
}