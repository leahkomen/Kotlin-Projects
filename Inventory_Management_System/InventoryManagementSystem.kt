//INVENTORY MANAGEMENT SYSTEM

import java.io.File

interface InventoryItem
{
    val id : Int
    var name : String
    var quantity : Int
    var price : Double

    fun displayDetails()
}
class Book(
    override val id: Int,
    override var name: String,
    override var quantity: Int,
    override var price: Double,
    var author: String,
    var publisher: String
): InventoryItem
{
    override fun displayDetails()
    {
        println("Id=$id,Name=$name,Quantity=$quantity,Price=$price,Author=$author,Publisher=$publisher")
    }
}
class Electronics(
    override val id: Int,
    override var name: String,
    override var quantity: Int,
    override var price: Double,
    var brand: String,
    var warranty: String,
):InventoryItem
{
   override fun displayDetails()
   {
       println("Id=$id,Name=$name,Quantity=$quantity,Price=$price,Brand=$brand,Warranty=$warranty")
   }
}
class Clothing(
    override val id: Int,
    override var name: String,
    override var quantity: Int,
    override var price: Double,
    var size: String,
    var category: String
):InventoryItem{
    override fun displayDetails()
    {
        println("Id=$id,Name=$name,Quantity=$quantity,Price=$price,Size=$size,Category=$category")
    }
}
 val inventory=mutableListOf<InventoryItem>()
 val fileName="Inventory.csv"

fun addItem(list: MutableList<InventoryItem>,
            inventory: InventoryItem )
{
    list.add(inventory)
}
fun displayItem(list: MutableList<InventoryItem>)
{
    if(list.isNotEmpty())
    {
        list.forEach {it.displayDetails()}
    }
    else
    {
        println("No items found.")
    }
}
fun updateItem(list: MutableList<InventoryItem>,id:Int)
{
        val item = list.find { it.id == id }
        if (item != null)
        {
            println("Enter new name:")
            item.name = readln().uppercase()

            println("Enter new quantity:")
            val quantity = readln().toIntOrNull()
            if (quantity == null)
            {
                println("Invalid quantity. Update cancelled.")
                return
            }
            item.quantity = quantity

            println("Enter new price:")
            val price = readln().toDoubleOrNull()
            if (price == null)
            {
                println("Invalid price. Update cancelled.")
                return
            }
            item.price = price

            when(item) {
                is Book -> {
                    println("Enter new author:")
                    item.author = readln().uppercase()

                    println("Enter new publisher:")
                    item.publisher = readln().uppercase()
                }
                is Electronics -> {
                    println("Enter new brand:")
                    item.brand = readln()

                    println("Enter new warranty:")
                    item.warranty = readln()
                }
                is Clothing -> {
                    println("Enter new size:")
                    item.size = readln()

                    println("Enter new category:")
                    item.category = readln()
                }
            }
            println("Item updated successfully.")

        } else {

            println("Item not found.")
        }
}
fun deleteItem(list: MutableList<InventoryItem>,id:Int)
{
    val item=list.find { it.id == id }
    if(item != null)
    {
        println("Are you sure you want to delete this item? Yes or No")
        val choice = readln().uppercase()
        if (choice == "YES") {
            list.remove(item)
            println("Item deleted successfully.")
        } else {
            println("Deletion cancelled.")
        }
    }
    else
    {
        println("No item found.")
    }
}
fun searchItem(list: MutableList<InventoryItem>)
{
        println("=== SEARCH ITEM ===")
        println("1. Search by ID")
        println("2. Search by Name")

        println("Choose an option:")
        val choice = readln()

        when(choice) {

            "1" -> {

                println("Enter item ID:")
                val id = readln().toIntOrNull()

                if (id == null)
                {
                    println("Invalid ID.")
                    return
                }

                val item = list.find { it.id == id }

                if(item != null) {
                    println("Item found:")
                    item.displayDetails()
                }
                else {
                    println("No item found with that ID.")
                }
            }
            "2" -> {

                println("Enter item name:")
                val name = readln().uppercase()

                val item = list.find {
                    it.name.uppercase() == name
                }
                if(item != null) {
                    println("Item found:")
                    item.displayDetails()
                }
                else {
                    println("No item found with that name.")
                }
            }
            else -> {
                println("Invalid choice.")
            }
        }
}
fun saveItem(list: MutableList<InventoryItem>)
{
    val lines = mutableListOf<String>()

    for(inventory in list)
    {
        val line = when(inventory)
        {
            is Book->
                "${inventory.id},BOOK,${inventory.name},${inventory.quantity},${inventory.price},${inventory.author},${inventory.publisher}"

            is Electronics ->
                "${inventory.id},ELECTRONICS,${inventory.name},${inventory.quantity},${inventory.price},${inventory.brand},${inventory.warranty}"

            is Clothing ->
                "${inventory.id},CLOTHING,${inventory.name},${inventory.quantity},${inventory.price},${inventory.size},${inventory.category}"

            else -> ""
        }

        lines.add(line)
    }

    File(fileName).writeText(lines.joinToString("\n"))
}
fun loadItem(list: MutableList<InventoryItem>)
{
    val file = File(fileName)

    if(!file.exists()) return
    try {
        list.clear()

        val lines = file.readLines()

        for(line in lines)
        {
            val parts = line.split(",")

            when(parts[1])
            {
                "BOOK" ->
                {
                    list.add(
                        Book(
                            parts[0].toInt(),
                            parts[2],
                            parts[3].toInt(),
                            parts[4].toDouble(),
                            parts[5],
                            parts[6],
                        )
                    )
                }
                "ELECTRONICS" ->
                {
                    list.add(
                        Electronics(
                            parts[0].toInt(),
                            parts[2],
                            parts[3].toInt(),
                            parts[4].toDouble(),
                            parts[5],
                            parts[6],
                        )
                    )
                }
                "CLOTHING" ->
                {
                    list.add(
                        Clothing(
                            parts[0].toInt(),
                            parts[2],
                            parts[3].toInt(),
                            parts[4].toDouble(),
                            parts[5],
                            parts[6],
                        )
                    )
                }
            }
        }
    }catch (e:Exception){

        println("Error loading ${file.name}") }

}
fun generateId(list: MutableList<InventoryItem>): Int {

    if (list.isEmpty()) {
        return 1
    }

    return list.maxOf { it.id } + 1
}

fun main()
{
    loadItem(inventory)

    while(true)
    {
        println("===INVENTORY SYSTEM===")
        println("1.Add Book.")
        println("2.Add Electronics.")
        println("3.Add Clothing.")
        println("4.Display Items.")
        println("5.Update Stock.")
        println("6.Remove an item.")
        println("7.Search an item.")
        println("8.Exit.")

        println("What do you want to do today?")
        val choice=readln()

        if (choice !in listOf("1","2","3","4","5","6","7","8"))
        {
            println("Invalid input!")
            continue
        }
        when(choice)
        {
            "1"->{
                val id=generateId(inventory)
                println("Please enter the name of the Book:")
                val name=readln().uppercase()

                println("Please enter the quantity of Books:")
                val quantity=readln().toIntOrNull()
                if (quantity == null)
                {
                    println("Invalid quantity. Item not added.")
                    continue
                }

                println("Please enter the price of Book:")
                val price=readln().toDoubleOrNull()
                if (price == null)
                {
                    println("Invalid price. Item not added.")
                    continue
                }

                println("Please enter the author of the Book:")
                val author=readln().uppercase()

                println("Please enter the publisher of the Book:")
                val publisher=readln().uppercase()

                val book=Book(id,name,quantity,price,author,publisher)
                addItem(inventory,book)
                saveItem(inventory)
            }
            "2"->{
                val id=generateId(inventory)
                println("Please enter the name of the Electronics:")
                val name=readln().uppercase()

                println("Please enter the quantity of Electronics:")
                val quantity=readln().toIntOrNull()
                if (quantity == null)
                {
                    println("Invalid quantity. Item not added.")
                    continue
                }

                println("Please enter the price of the Electronics:")
                val price=readln().toDoubleOrNull()
                if (price == null)
                {
                    println("Invalid price. Item not added.")
                    continue
                }

                println("Please enter the brand of the Electronics:")
                val brand=readln().uppercase()

                println("Please enter the warranty of the Electronics:")
                val warranty=readln().uppercase()

                val electronics=Electronics(id,name,quantity,price,brand,warranty)
                addItem(inventory,electronics)
                saveItem(inventory)
            }
            "3"->{
                val id=generateId(inventory)

                println("Please enter the name of the Clothes:")
                val name=readln().uppercase()

                println("Please enter the quantity of the Clothes:")
                val quantity=readln().toIntOrNull()
                if (quantity == null)
                {
                    println("Invalid quantity. Item not added.")
                    continue
                }

                println("Please enter the price of the Clothes:")
                val price=readln().toDoubleOrNull()
                if (price == null)
                {
                    println("Invalid price. Item not added.")
                    continue
                }

                println("Please enter the size of the Clothes:")
                val size=readln()

                println("Please enter the category of the Clothes:")
                val category=readln()

                val clothes=Clothing(id,name,quantity,price,size,category)
                addItem(inventory,clothes)
                saveItem(inventory)
            }
            "4"->{
                displayItem(inventory)
            }
            "5"->{
               println("Please enter the id of the item:")
                val id=readln().toIntOrNull()
                if (id == null)
                {
                    println("Invalid id.")
                    continue
                }

                updateItem(inventory,id)
                saveItem(inventory)
            }
            "6"->{
                println("Please enter the id of the item:")
                val id=readln().toIntOrNull()
                if (id == null)
                {
                    println("Invalid id.")
                    continue
                }

                deleteItem(inventory,id)
                saveItem(inventory)
            }
            "7"->{
                searchItem(inventory)
            }
            "8"->{
                println("Are you sure you want to exit?Yes or No.")
                val choice=readln().uppercase()
                if (choice=="YES")
                {
                    println("I hope you enjoyed using it.")
                    break
                }
                else
                {
                    println("Continue using the inventory.")
                }
            }
        }
    }
}
