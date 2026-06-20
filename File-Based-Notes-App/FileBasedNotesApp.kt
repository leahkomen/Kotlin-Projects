import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.io.FileInputStream
import java.io.ObjectInputStream

data class Note(
    val id: Int,
    var title: String,
    var content: String,
    var dateCreated: String
): Serializable{
    fun displayNote()
    {
        println("===========NOTE===========")
        println("Id=$id")
        println("Title=$title")
        println("Content=$content")
        println("DateCreated=$dateCreated")
        println("==========================")
    }
}

val notes= mutableListOf<Note>()

fun addNotes(
    list: MutableList<Note>,
    id: Int,
    title: String,
    content: String,
    dateCreated: String
)
{
    val note= Note(id, title, content, dateCreated)
    list.add(note)
    println("Note added successfully.")
}
fun viewAllNotes(list: MutableList<Note>)
{
    if (list.isEmpty())
    {
        println("No notes found.")
    }
    else
    {
        for (note in list)
        {
           note.displayNote()
        }
    }
}
fun updateNotes(list: MutableList<Note>,id: Int)
{
    val note=list.find { it.id == id }
    if(note!=null)
    {
        println("Please enter the title of the note:")
        note.title = readln().uppercase()

        println("Please enter the content of the note:")
        note.content = readln()

        println("Please enter the date  that the note was created:")
        note.dateCreated = readln()

        println("The note has been updated successfully.")
    }
    else
    {
        println("Note not found")
    }
}
fun deleteNotes(list: MutableList<Note>,id: Int)
{
    val note=list.find{it.id==id}
    if(note!=null)
    {
        println("Are you sure you want to delete this note?Yes or No")
        val choice=readln().uppercase()

        if (choice == "YES")
        {
            list.remove(note)
            println("Note has been deleted successfully.")
        }
        else
        {
            println("Continue using the note app...... ")
        }
    }
    else
    {
        println("Note not found")
    }
}
fun searchNotes(list: MutableList<Note>)
{
    println("==SEARCHING NOTES==")
    println("1.Search by Id.")
    println("2.Search by Title.")

    println("Please choose a way to search your notes:")
    val choice=readln()

    when(choice){
        "1"->{
            println("Please enter the id of the notes you want to search:")
            val id= readln().toIntOrNull()
            if (id==null)
            {
                println("Invalid input.")
                return
            }

            val item=list.find { it.id == id }
            if(item!= null)
            {
                item.displayNote()
            }
            else
            {
                println("Note not found.")
            }
        }
        "2"->{
            println("Please enter the title of the notes you want to search:")
            val title= readln().uppercase()

            val item=list.find { it.title.uppercase()==title }
            if(item!=null)
            {
               item.displayNote()
            }
            else
            {
                println("Note not found.")
            }
        }
        else -> {
            println("Invalid search option.")
        }
    }
}
fun generateId(list: MutableList<Note>): Int
{
   if(list.isEmpty())
   {
       return 1
   }
    return list.maxOf { it.id }+1
}
fun saveNotes(list: MutableList<Note>)
{
        FileOutputStream("notes.dat").use { file ->
            ObjectOutputStream(file).use { objectStream ->
                objectStream.writeObject(list)
            }
        }
}
fun loadNotes():MutableList<Note>
{
    return try {
        FileInputStream("notes.dat").use { file ->
            ObjectInputStream(file).use { objectStream ->
                objectStream.readObject() as MutableList<Note>
            }
        }
    } catch (e: Exception) {
        mutableListOf()
    }
}

fun main()
{
    notes.addAll(loadNotes())
    
    while (true)
    {
        println("===FILE NOTES CONSOLE APP===")
        println("1.Add notes.")
        println("2.View all  notes.")
        println("3.Update notes.")
        println("4.Delete notes.")
        println("5.Search notes.")
        println("6.Exit.")
        println("----------------------------------------------")

        println("What do you want to do?")
        val choice=readln()

        if (choice !in listOf("1", "2", "3", "4", "5", "6"))
        {
            println("Invalid input!")
            continue
        }
        when(choice){
            "1"->{
                val id=generateId(notes)
                println("Please enter the title of the notes you want to add:")
                val title= readln().uppercase()

                println("Please enter the content of the notes you want to add:")
                val content= readln()

                println("Please enter the date of the notes you want to add:")
                val date= readln()

                addNotes(notes, id, title, content, date)
                saveNotes(notes)
            }
            "2"->{
                viewAllNotes(notes)
            }
            "3"->{
                println("Please enter the id of the notes you want to update:")
                val id=readln().toIntOrNull()
                if(id==null)
                {
                    println("Invalid input!")
                }
                else
                {
                    updateNotes(notes, id)
                    saveNotes(notes)
                }
            }
            "4"->{
                println("Please enter the id of the notes you want to delete:")
                val id=readln().toIntOrNull()
                if (id==null)
                {
                    println("Invalid input!")
                }
                else{
                    deleteNotes(notes, id)
                    saveNotes(notes)
                }
            }
            "5"->{
                searchNotes(notes)
            }
            "6"->{
                println("Are you sure you want to exit?Yes Or No.")
                val choice=readln().uppercase()

                if (choice=="YES")
                {
                    println("See you next time!")
                    break
                }
                else
                {
                    println("Continue using the notes console app.....")
                }
            }
        }
    }
}
