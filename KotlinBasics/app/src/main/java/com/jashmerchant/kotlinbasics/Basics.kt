package com.jashmerchant.kotlinbasics

fun main(){
    print("Please enter your name: ")
    val name: String = readln()

    print("Please enter your age: ")
    val age: Int = readln().toInt()

    println("Hello $name of age $age!")
}