// Dependencies required (add to build.gradle.kts):
// implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
// plugin: kotlin("plugin.serialization") version "2.2.0"



import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.File

class JsonDatabase<T>(private val fileName: String, private val serializer: KSerializer<T>)
{
    private var items: MutableList<T> = mutableListOf()

    fun save()
    {
        val jsonString = Json.encodeToString(ListSerializer(serializer), items)
        File(fileName).writeText(jsonString)
    }

    fun load()
    {
        try {

            val jsonString = File(fileName).readText()
            items = Json.decodeFromString(ListSerializer(serializer), jsonString).toMutableList()
        }catch (e:Exception)
        {
            println("No existing database found. Starting with an empty database.")
        }
    }

    fun add(item: T)
    {
        items.add(item)
        save()
    }

    fun find(predicate: (T) -> Boolean): T?
    {
        return items.find(predicate)
    }

    fun update(predicate: (T) -> Boolean, newItem: T)
    {
        val index = items.indexOfFirst(predicate)

        if (index != -1)
        {
            items[index] = newItem
            println("The item has been updated successfully!")
            save()
        }
        else
        {
            println("No matching item found.")
        }
    }

    fun delete(predicate: (T) -> Boolean)
    {
        val item = items.find(predicate)

        if (item != null)
        {
            items.remove(item)
            println("The item has been deleted successfully!")
            save()
        }
        else
        {
            println("There is no such item!")
        }
    }

    fun listAll(): List<T>
    {
        return items
    }
}

@Serializable
data class School(
    val id: Int,
    var name: String,
    var gender: String,
    var age: Int,
    var course: String,
)

fun main()
{
    val db=JsonDatabase<School>(
        "school.json",
        School.serializer()
    )
    db.load()

    while(true)
    {
        println()
        println("===JSON DATABASE ENGINE===")
        println()
        println("1.Add to the database")
        println("2.View  an individual item in the database")
        println("3.Update from the database")
        println("4.Delete from the database")
        println("5.View all the things in the database")
        println("6.Filter the items in the database")
        println("7.Sort items in the database")
        println("8.Exit")
        println()
        println("What do you want to do?")
        val choice=readln()

        if (choice !in listOf("1","2","3","4","5","6","7","8"))
        {
            println("Invalid choice!Please provide a valid choice!")
            continue
        }

        when(choice){
            "1"->{
                val all = db.listAll()
                val id = (all.maxOfOrNull { it.id } ?: 0) + 1
                println("Auto-generated ID: $id")

                println("Please enter your name:")
                val name=readln().uppercase()

                println("Please enter your gender:")
                val gender=readln().uppercase()

                println("Please enter your age:")
                val age=readln().toIntOrNull() ?:0

                println("Please enter your course:")
                val course=readln().uppercase()

                val school = School(id,name,gender,age,course)
                db.add(school)
                println("Student added successfully!")
                println("Your ID is $id")
            }
            "2" -> {
            val all = db.listAll()

            if (all.isEmpty())
            {
                println("The database is empty.")
            }
            else
            {
                println("Here are all items (with their position numbers):")
                for (i in all.indices)
                {
                    println("$i: ${all[i]}")
                }
                println("Please enter the position number of the item you want to view:")
                val index = readln().toIntOrNull()

                if (index == null || index < 0 || index >= all.size)
                {
                    println("Invalid position.")
                }
                else
                {
                    println(all[index])
                }
            }
        }
            "3" -> {
                val all = db.listAll()

                if (all.isEmpty())
                {
                    println("The database is empty.")
                }
                else
                {
                    println("Here are all items (with their position numbers):")
                    for (i in all.indices)
                    {
                        println("$i: ${all[i]}")
                    }

                    println("Enter the position number of the item you want to update:")
                    val index = readln().toIntOrNull()

                    if (index == null || index < 0 || index >= all.size)
                    {
                        println("Invalid position.")
                    }
                    else
                    {
                        val oldItem = all[index]

                        println("Please enter the new id:")
                        val id = readln().toIntOrNull() ?: 0

                        println("Please enter the new name:")
                        val name = readln().uppercase()

                        println("Please enter the new gender:")
                        val gender = readln().uppercase()

                        println("Please enter the new age:")
                        val age = readln().toIntOrNull() ?: 0

                        println("Please enter the new course:")
                        val course = readln().uppercase()

                        val newItem = School(id, name, gender, age, course)
                        db.update({ it == oldItem }, newItem)
                    }
                }
            }
            "4" -> {
                val all = db.listAll()

                if (all.isEmpty())
                {
                    println("The database is empty.")
                }
                else
                {
                    println("Here are all items (with their position numbers):")
                    for (i in all.indices)
                    {
                        println("$i: ${all[i]}")
                    }

                    println("Enter the position number of the item you want to delete:")
                    val index = readln().toIntOrNull()

                    if (index == null || index < 0 || index >= all.size)
                    {
                        println("Invalid position.")
                    }
                    else
                    {
                        println("Are you sure want to delete the item?Yes or No")
                        val choice=readln().uppercase()

                        if (choice=="YES")
                        {

                            val itemToDelete = all[index]
                            db.delete { it == itemToDelete }
                        }
                        else
                        {
                            println("Oops!You almost deleted your data.Be careful next time...")
                        }
                    }
                }
            }
            "5"->{
                val all = db.listAll()
                if (all.isEmpty())
                {
                    println("The database is empty.")
                }
                else
                {
                    println("Here are all items (with their position numbers)")
                    for (i in all.indices)
                    {
                        println("$i: ${all[i]}")
                    }
                }
            }
            "6" -> {
                println("Enter the course to filter by:")
                val courseFilter = readln()

                val filtered = db.listAll().filter { it.course.equals(courseFilter, ignoreCase = true) }

                if (filtered.isEmpty())
                {
                    println("No students found in that course.")
                }
                else
                {
                    for (item in filtered)
                    {
                        println(item)
                    }
                }
            }
            "7" -> {
                println("Sort by: 1-Age  2-Name")
                val sortChoice = readln()

                val sorted = when (sortChoice) {
                    "1" -> db.listAll().sortedBy { it.age }
                    "2" -> db.listAll().sortedBy { it.name }
                    else -> db.listAll()
                }

                for (item in sorted)
                {
                    println(item)
                }
            }
            "8"->{
                println("Are you sure you want to quit? Yes or No")
                val choice=readln().uppercase()
                if(choice=="YES")
                {
                    println("Thank you for serving with us.Have a lovely day!")
                    break
                }
                else
                {
                    println("It is okay.Continue browsing!")
                }
            }
        }
    }
}
