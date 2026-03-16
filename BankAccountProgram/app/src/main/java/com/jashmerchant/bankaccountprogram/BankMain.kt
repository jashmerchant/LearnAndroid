package com.jashmerchant.bankaccountprogram

fun main() {
    val jashesBankAccount = BankAccount("Jash Merchant", 1338.2)

    jashesBankAccount.deposit(100.2)
    jashesBankAccount.withdraw(1000.34)
    jashesBankAccount.displayTransactionHistory()
    jashesBankAccount.checkBalance()
}