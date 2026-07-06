class Calculator
{
    fun add(a: Double,b: Double): Double
    {
        return a+b
    }
    fun subtract(a: Double,b: Double): Double
    {
        return a-b
    }
    fun multiply(a: Double,b: Double): Double
    {
        return a*b
    }
    fun divide(a: Double,b: Double): Double
    {
        return a/b
    }
}

fun main() {
    var option = ""

    val calc = Calculator()

    val history=mutableListOf<String>()

    while (true) {
        println("===CALCULATOR MENU===")

        println("1.Start the Calculator")
        println("2.View History")
        println("3.Delete history")
        println("4.Exit")

        println("CHOOSE WHAT YOU WANT TO DO:")
        option = readln().lowercase()


        when(option)
        {
            "1" ->
            {
                var operation = ""

                while (operation != "exit") {
                    println("===CALCULATOR ===")
                    println("Choose an operation you want to perform :+,-,*,/,exit")
                    operation = readln()
                    if(operation!in listOf("+","-","*","/","exit") )
                    {
                        println("Invalid operation.Choose from:+,-,*,/,exit")
                        continue
                    }

                    if (operation == "exit") {
                        println("Calculator closed")
                        break
                    }

                    println("Enter your first number:")
                    val num1 = readln().toDoubleOrNull()

                    println("Enter your second number:")
                    val num2 = readln().toDoubleOrNull()

                    if (num1 != null && num2 != null) {
                        when (operation) {
                            "+" -> {
                                val result=calc.add(num1,num2)
                                println("Result: $result")
                                history.add("$num1 + $num2 = $result")
                            }
                            "-" -> {
                                val result=calc.subtract(num1,num2)
                                println("Result: $result")
                                history.add("$num1 - $num2 = $result")
                            }
                            "*" -> {
                                val result=calc.multiply(num1,num2)
                                println("Result: $result")
                                history.add("$num1 * $num2 = $result")
                            }
                            "/" -> {
                                if (num2 == 0.0) {
                                    println("Cannot divide by zero")
                                } else
                                {
                                    val result=calc.divide(num1,num2)
                                    println("Result: $result")
                                    history.add("$num1 / $num2 = $result")
                                }
                            }
                        }
                    }
                    else
                    {
                        println("Please enter a valid number")
                    }
                }

            }
            "2" ->{
                if (history.isEmpty())
                {
                    println("There is nothing yet.")
                }
                else
                {
                    for (item in history)
                    {
                        println(item)
                    }
                }
            }
            "3" ->{
                if (history.isNotEmpty())
                {
                    history.clear()
                    println("History deleted.")
                }
                else
                {
                    println("You have nothing in your history to delete")
                }
            }
            "4" ->{
                println("Have a blessed day!!.")
                break
            }
        }
    }
}



