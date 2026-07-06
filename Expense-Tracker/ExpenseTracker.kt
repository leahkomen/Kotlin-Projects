//Expense Tracker
import java.io.File

data class Expense(
    val id: Int,
    var category: String,
    var description: String,
    var amount: Double,
    var date: String
)
val expenses=mutableListOf<Expense>()
val fileName="expensetracker.csv"

fun addExpenses(
    list: MutableList<Expense>,
    id: Int,
    category: String,
    description: String,
    amount: Double,
    date: String
)
{
    val expense=Expense(id, category, description, amount, date)
    list.add(expense)
}
fun displayExpense(list: MutableList<Expense>,category: String)
{
    val filtered=list.filter { it.category == category }
  if(filtered.isEmpty())
  {
      println("No expenses found in this category")
  }
    else{
        filtered.forEach {
            println("ID:${it.id},category:${it.category}, description:${it.description}, amount:${it.amount}")
        }
  }

}
fun updateExpense(list: MutableList<Expense>,id: Int)
{
    val expenses=list.find { it.id==id }
    if(expenses != null)
    {
        println("Please enter the category:")
        val category=readln().uppercase()

        println("Please enter the description:")
        val description=readln().uppercase()

        println("Please enter the amount:")
        val amount=readln().toDouble()

        println("Please enter the date:")
        val date=readln()

        expenses.category=category
        expenses.description=description
        expenses.amount = amount
        expenses.date = date

        println("You have successfully changed your expense tracker!")
    }
    else {
        println("No expenses found")
    }
}
fun deleteExpense(list: MutableList<Expense>, id: Int)
{
    val expenses = list.find { it.id == id }
    if (expenses != null)
    {
        println("Are you sure you want to delete this expense? Yes or No")
        val choice = readln().uppercase()

        if (choice == "YES")
        {
            list.remove(expenses)
            println("You deleted the item successfully from your expense tracker")
        }
        else
        {
            println("Okay, the expense was not deleted.")
        }
    }
    else
    {
        println("No expense found")
    }
}
fun categorizeExpense(list: MutableList<Expense>)
{
    val grouped=list.groupBy { it.category}
    grouped.forEach {
        (category,items)->
        val total=items.sumOf { it.amount }

        println("The category of the item is: $category")
        println("The total amount is: $total")
    }
}
fun calculateExpense(list: MutableList<Expense>)
{
    val total=list.sumOf { it.amount }
    println("Total expenses: $total")
}
fun generateReports(list: MutableList<Expense>)
{
    println("===EXPENSE REPORT===")
    val totalExpenses=list.size
    val totalAmount=list.sumOf { it.amount }
    println("Total amount of expenses: $totalExpenses")
    println("Total amount spent: $totalAmount")

    println("===BY CATEGORY===")
    val grouped=list.groupBy { it.category}
    grouped.forEach {
            (category,items)->
        val total=items.sumOf { it.amount }

        println("The category of the item is: $category")
        println("The total amount is: $total")
    }
}
fun saveExpenses(list: MutableList<Expense>)
{
    val lines=mutableListOf<String>()
    for ( expenses in list)
    {
        val line ="${expenses.id},${expenses.category},${expenses.description},${expenses.amount},${expenses.date}"
        lines.add(line)
    }
    File(fileName).writeText(lines.joinToString("\n"))
}
fun loadExpenses(list: MutableList<Expense>)
{
    val file = File(fileName)
    if(file.exists())
    {
        try {
            expenses.clear()
            val lines = file.readLines()
            for(line in lines)
            {
                val parts = line.split(",")
                if(parts.size == 5)
                {
                    expenses.add(
                        Expense(
                            id = parts[0].toInt(),
                            category = parts[1],
                            description = parts[2],
                            amount = parts[3].toDouble(),
                            date = parts[4]
                        )
                    )
                }
            }
        }catch (e: Exception){
            println("Error loading ${file.name}")
        }
    }
}
fun getCategoryFromUser(): String
{
    while(true)
    {
        println("==EXPENSE CATEGORY==")
    println("1.FOOD")
    println("2.TRANSPORT")
    println("3.SHOPPING")
    println("4.EDUCATION")
    println("5.MEDICAL")
    println("6.ENTERTAINMENT")
    println("7.RENT")
    println("8.UTILITIES")
    println("9.EMERGENCY")
    println("10.OTHER")

    println("Choose the category of the expense:")
    val choice=readln().toString()

    if(choice !in listOf("1", "2", "3", "4", "5", "6", "7", "8","9","10"))
    {
        println("Please enter a valid input.")
        continue
    }
    return when(choice){
        "1"->"FOOD"
        "2"->"TRANSPORT"
        "3"->"SHOPPING"
        "4"->"EDUCATION"
        "5"->"MEDICAL"
        "6"->"ENTERTAINMENT"
        "7"->"RENT"
        "8"->"UTILITIES"
        "9"->"EMERGENCY"
        else -> "OTHER"
    }
    }
    
}

fun main()
{
    loadExpenses(expenses)

    while (true)
    {
        println("===EXPENSE TRACKER===")
        println("1.Add things to your expense tracker.")
        println("2.Display things that is on your expense tracker.")
        println("3.Update things on your expense tracker.")
        println("4.Delete things on your expense tracker.")
        println("5.View the category your  things  are on expense tracker.")
        println("6.Calculate the total amount  spent on things in  expense tracker.")
        println("7.Generate the reports on your spending.")
        println("8.Exit")

        println("What do you want to do today?")
        val choice=readln().toString()

        if(choice !in listOf("1", "2", "3", "4", "5", "6", "7", "8"))
        {
            println("Please enter a valid input .")
            continue
        }

        when(choice)
        {
            "1"->{
                val category=getCategoryFromUser()

                println("Enter description:")
                val description= readln().uppercase()

                println("Enter amount:")
                val amount = readln().toDouble()

                println("Enter date:")
                val date= readln()

                val id = (expenses.maxOfOrNull { it.id } ?: 0) + 1 
                addExpenses(expenses,id,category,description,amount,date)

                println("Expenses added successfully")
                saveExpenses(expenses)
            }
            "2"->{
                val category=getCategoryFromUser()

                displayExpense(expenses,category)
            }
            "3"->{
                println("please enter the id of the item you want to update:")
                val id=readln().toInt()

                updateExpense(expenses,id)
                saveExpenses(expenses)
            }
            "4"->{
                println("Please enter the id of the item you want to delete:")
                val id=readln().toInt()

                deleteExpense(expenses,id)
                saveExpenses(expenses)
            }
            "5"->{
                categorizeExpense(expenses)
            }
            "6"->{
                calculateExpense(expenses)
            }
            "7"->{
                generateReports(expenses)
            }
            "8"->{
                println("Are you sure you want to exit?Yes or No")
                val exit=readln().uppercase()

                if(exit=="YES")
                {
                    println("You have successfully exited!")
                    break
                }
                else
                {
                    println("Continue doing your expenses....")
                }
            }
        }
    }
}
