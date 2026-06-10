//Banking management system

import java.io.File

class Account(
    private val accountNumber: String,
    private var pin: Int,
    private var balance: Double,
    var name:String,
    var nationalIdNumber: Int )
{
    fun getBalance(): Double
    {
        return balance
    }
    fun getAccountNumber(): String
    {
        return accountNumber
    }
    fun getPin(): Int
    {
        return pin
    }
    fun withdraw(amount: Double)
    {
        balance -= amount
    }
    fun deposit(amount: Double)
    {
        balance += amount
    }
}

    val bank = mutableListOf<Account>()
    val fileName="bank.csv"

    fun registerAccount(
        list: MutableList<Account>,
        accountNumber: String,
        pin: Int,
        balance: Double,
        name: String,
        nationalId: Int
    )
    {
        val bank = Account(accountNumber, pin, balance, name, nationalId)
        list.add(bank)
    }
fun displayAccount(list: MutableList<Account>,accountNumber: String ,pin: Int)
{
    val bank = list.find { it.getAccountNumber() == accountNumber && it.getPin() == pin }
    if (bank != null)
    {
        println("Account Number: ${bank.getAccountNumber()}")
        println("Name: ${bank.name}")
        println("National ID: ${bank.nationalIdNumber}")

        println("Are your details correct?Yes or No.")
        val choice=readln().uppercase()

        if (choice=="YES")
        {
            println("Thank you for banking with us!")
        }
        else
        {
            println("===CHANGE DETAILS===")
            println("1.Is it your name?")
            println("2.Is it your ID number?")

            println("What is it that is not correct?")
            val change=readln().toString()

            if(change !in listOf("1","2"))
            {
                println("Invalid choice .Please try again.")
            }

            when (change) {

                "1"->{
                    println("Please enter the name :")
                    val name=readln().uppercase()
                    bank.name = name

                    saveAccounts(list)
                }
                "2"->{
                    println("Please enter the national ID number: :")
                    val nationalIdNumber=readln().toInt()
                    bank.nationalIdNumber = nationalIdNumber

                    saveAccounts(list)
                }
            }
        }
    }
}
    fun checkBalance(list: MutableList<Account>, name: String) {
        val bank = list.find { it.name == name }
        if(bank!=null)
        {
            println("Please enter your pin: ")
            val pin = readln().toInt()

            if(pin== bank.getPin())
            {
                println("Your account balance is ${bank.getBalance()}")
            }
            else{
                println("Incorrect pin!Please try again.")
            }
        }
        else
        {
            println("Please enter a valid  name.")
        }
    }
    fun withdrawMoney(list: MutableList<Account>, name: String )
    {
        val bank = list.find { it.name == name }
        if (bank != null)
        {
            println("Enter your account number:")
            val accountNumber = readln().toString()

            if(accountNumber == bank.getAccountNumber())
            {
                println("Enter the amount you want to withdraw:")
                val amount = readln().toDouble()

                if(amount<bank.getBalance())
                {
                    println("Please enter your pin:")
                    val pin = readln().toInt()

                    if(pin == bank.getPin())
                    {
                        bank.withdraw(amount)

                        println("You have withdrawn $amount")
                        println("The remaining amount is ${bank.getBalance()}")
                    }
                }
                else{
                    println("You don't have enough money!Please try another amount to withdraw.")

                }
            }
            }
    }
    fun depositMoney(list: MutableList<Account>, name: String)
    {
        val bank = list.find { it.name == name }

        if (bank != null)
        {
            println("Enter the amount you want to deposit:")
            val amount = readln().toDouble()

            println("Please enter your pin:")
            val pin = readln().toInt()

            if(pin == bank.getPin())
            {
                bank.deposit(amount)

                println("You have deposited $amount")
                println("You have ${bank.getBalance()}")
            }
            else
            {
                println("Incorrect pin!Please try again.")
            }
        }
    }
fun saveAccounts(list: MutableList<Account>)
{
    val lines=mutableListOf<String>()
    for(bank in list)
    {
        val line="${bank.getAccountNumber()},${bank.getPin()},${bank.getBalance()},${bank.name},${bank.nationalIdNumber}"
        lines.add(line)
    }
    File(fileName).writeText(lines.joinToString("\n"))
}
fun loadAccounts()
{
    val file=File(fileName)

    if (file.exists())
    {
        try {
            bank.clear()

            val lines = file.readLines()
            for (line in lines)
            {
                val parts=line.split(",")
                if (parts.size == 5)
                {
                    bank.add(
                        Account(
                            accountNumber = parts[0].toString(),
                            pin = parts[1].toInt(),
                            balance = parts[2].toDouble(),
                            name = parts[3].toString(),
                            nationalIdNumber = parts[4].toInt()
                        )
                    )
                }
            }
        }catch (e: Exception)
        {
            println("Error loading: ${file.name}")
        }
    }
}
var accountCounter = 0
val counterFile = "counter.txt"

fun loadCounter()
{
    val file = File(counterFile)
    if(file.exists())
    {
        accountCounter = file.readText().toIntOrNull() ?: 0
    }
}

fun saveCounter()
{
    File(counterFile).writeText(accountCounter.toString())
}
fun deleteAccount(list: MutableList<Account>,accountNumber: String,pin: Int)
{
    val bank=list.find { it.getAccountNumber() == accountNumber && it.getPin() == pin }
    if (bank != null)
    {
        println("Are you sure you want to delete your account? Yes or No")
        val choice=readln().uppercase()
        if (choice=="YES")
        {
            list.remove(bank)
            saveAccounts(list)

            println("It was nice having you as our customer!I hope you will you will consider us someday again.")
        }
        else
        {
            println("It is okay.Continue banking with us.")
        }
    }
}

fun main()
{
    loadAccounts()
    loadCounter()

    while(true)
    {
        println("===FAVOUR BANK===")
        println("1.Register account.")
        println("2.Display the details you gave to register your account. ")
        println("3.Check your account balance.")
        println("4.Withdraw money from your account.")
        println("5.Deposit money to your account.")
        println("6.Delete your account.")
        println("7.Exit.")

        println("Welcome to Favour Bank.What can we do for you today?")
        val choice= readln().toString()

        if(choice !in listOf("1","2","3","4","5","6","7"))
        {
            println("Invalid choice!")
            continue
        }

        when(choice)
        {
            "1"->{
                println("Please enter your name:")
                val name= readln().uppercase()

                println("Enter your national id number:")
                val nationalId= readln().toIntOrNull()

                if(nationalId!=null)
                {
                    accountCounter++
                    val accountNumber= "01-001-${(accountCounter).toString().padStart(7,'0')}"
                    saveCounter()

                    println("Your account number is $accountNumber")

                    println("Please enter your pin number for your account:")
                    val pin= readln().toInt()


                    registerAccount(bank,accountNumber,pin, 0.0,name, nationalId)
                }
                saveAccounts(bank)
            }
            "2"->{
                println("Please enter your account number:")
                val accountNumber= readln().toString()

                println("Please enter your pin number for your account:")
                val pin= readln().toInt()

                displayAccount(bank,accountNumber,pin)
            }
            "3"->{
                println("Please enter your name:")
                val name= readln().uppercase()

                checkBalance(bank, name)
                saveAccounts(bank)
            }
            "4"->{
                println("Please enter your name:")
                val name= readln().uppercase()

                withdrawMoney(bank, name)
                saveAccounts(bank)
            }
            "5"->{
                println("Please enter your name:")
                val name= readln().uppercase()

                depositMoney(bank, name)
                saveAccounts(bank)
            }
            "6"->{
                println("Please enter your account number:")
                val accountNumber= readln()

                println("Please enter your pin number for your account:")
                val pin= readln().toInt()

                deleteAccount(bank, accountNumber,pin)
            }
            "7"->{
                println("Are you sure you want to exit?yes or no ")
                val choice=readln().uppercase()

                if (choice=="YES")
                {
                    println("Thank you for choosing Favour Bank!I hope you enjoyed banking with us.")
                    break
                }
                else{
                    println("Continue enjoying our services.....")
                }
            }
        }
    }

}
