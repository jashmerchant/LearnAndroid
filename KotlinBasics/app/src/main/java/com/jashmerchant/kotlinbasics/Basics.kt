package com.jashmerchant.kotlinbasics

fun main(){
    makeCoffee(nameAndAge())
}

fun makeCoffee(name: String) {
    println("$name, here's your coffee")
}

fun nameAndAge(): String {
    print("Please enter your name: ")
    val name: String = readln()

    print("Please enter your age: ")
    val age: Int = readln().toInt()

    println("Hello $name of age $age!")

    return name
}