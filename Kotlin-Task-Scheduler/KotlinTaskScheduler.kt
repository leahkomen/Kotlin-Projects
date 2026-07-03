// Dependencies required (add to build.gradle.kts):
// implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

import kotlinx.coroutines.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

enum class Status{
    PENDING,
    RUNNING,
    COMPLETED,
    CANCELLED
}

data class TaskScheduler(
    val id: Int,
    var name: String,
    var delaySeconds: Long,
    var status: Status = Status.PENDING
): Serializable

val scheduler=loadTasks()
val runningJobs = mutableMapOf<Int, Job>()

fun addTask(
    list: MutableList<TaskScheduler>,
    id: Int,
    name: String,
    delaySeconds: Long)
{
    val task=TaskScheduler(id, name,delaySeconds )
    list.add(task)
}
fun displayTask(list: MutableList<TaskScheduler>,id: Int)
{
    val task=list.find { it.id == id }
    if(task!=null)
    {
        println("ID:${task.id}")
        println("Name:${task.name}")
        println("DelaySeconds:${task.delaySeconds} seconds")
        println("Status:${task.status}")
    }
    else
    {
        println("There is no such task.")
    }
}
fun displayAllTasks(list: MutableList<TaskScheduler>)
{
    if (list.isEmpty())
    {
        println("No tasks available.")
        return
    }
    for (task in list)
    {
        println("ID: ${task.id}")
        println("Name: ${task.name}")
        println("Delay: ${task.delaySeconds} seconds")
        println("Status: ${task.status}")
        println("--------------------")
    }
}
fun updateTask(list: MutableList<TaskScheduler>,id: Int)
{
    val task=list.find{it.id==id}
    if(task!=null)
    {
        if (task.status == Status.RUNNING)
        {
            println("Cannot update a running task.")
            return
        }

        println("Please enter the name of the task you want to update:")
        val name=readln().uppercase()

        println("Please enter the delaySeconds of the task you want to update:")
        val delaySeconds=readln().toLongOrNull() ?:0

        task.name=name
        task.delaySeconds=delaySeconds
    }
    else
    {
        println("There is no such task.")
    }
}
fun deleteTask(list: MutableList<TaskScheduler>, id: Int)
{
    val task=list.find { it.id==id }
    if(task!=null)
    {
        if (task.status == Status.RUNNING)
        {
            println("Cannot delete a running task.")
            return
        }

        println("Are you sure you want to delete: ${task.name},Yes or No?")
        val choice=readln().uppercase()
        if (choice=="YES")
        {
            list.remove(task)
            println("The task has been deleted successfully.")
            return
        }
        else
        {
            println("Deletion aborted.")         }
    }
}
fun executeTask(list: MutableList<TaskScheduler>,scope: CoroutineScope,id: Int)
{
    val task = list.find { it.id == id }

    if (task == null)
    {
        println("No task found with ID $id.")
        return
    }

    if (task.id in runningJobs)
    {
        println("Task '${task.name}' is already running.")
        return
    }

    val job = scope.launch {
        task.status = Status.RUNNING

        try {
            println("Task '${task.name}' started, will complete in ${task.delaySeconds} seconds...")
            delay(task.delaySeconds * 1000)

            task.status = Status.COMPLETED
            saveTasks(scheduler)
            println("Task '${task.name}' completed!")

        } catch (e: CancellationException) {
            task.status = Status.CANCELLED
            saveTasks(scheduler)
            println("Task '${task.name}' was cancelled.")

        } finally {

            runningJobs.remove(task.id)
        }
    }

    runningJobs[task.id] = job
    println("Task '${task.name}' launched successfully.")
}
fun cancelTask()
{
    println("PLease enter the ID of the task to cancel:")
    val id=readln().toIntOrNull()?:return

    val job=runningJobs[id]

    if(job!=null)
    {
        job.cancel()
        saveTasks(scheduler)
        println("The task has been cancelled.")
    }
    else
    {
        println("There is no such task.")
    }
}
fun saveTasks(list: MutableList<TaskScheduler>)
{
    FileOutputStream("tasks.dat").use {fileStream->
        ObjectOutputStream(fileStream).use{ objectStream->
            objectStream.writeObject(list)
        }
    }
}
fun loadTasks():MutableList<TaskScheduler>
{
    return try{
        FileInputStream("tasks.dat").use{ fileStream->
            ObjectInputStream(fileStream).use{ objectStream->
                objectStream.readObject() as MutableList<TaskScheduler>
            }
        }
    }catch (e: Exception)
    {
        mutableListOf()
    }
}

fun main()=runBlocking{

    while (true)
    {
        println("===TASK SCHEDULER===")
        println("1. Add Task")
        println("2. View Task by ID")
        println("3. View All Tasks")
        println("4. Update Task")
        println("5. Delete Task")
        println("6. Execute Task")
        println("7. Cancel Task")
        println("0. Exit")

        println()

        println("What do you want to do?")
        val choice=readln()

        if (choice !in listOf("0","1", "2", "3", "4", "5", "6", "7"))
        {
            println("Invalid input!")
            continue
        }

        when (choice) {
            "1"->{
                val id = (scheduler.maxOfOrNull { it.id } ?: 0) + 1

                println("Please enter the name of the task you want add:")
                val name=readln().uppercase()

                println("Please enter the delaySeconds you want your task to have:")
                val delaySeconds=readln().toLongOrNull() ?:0

                addTask(scheduler, id, name, delaySeconds)
                saveTasks(scheduler)
            }
            "2"->{
                println("Please enter the id of the task you want to view:")
                val id=readln().toIntOrNull() ?:0

                displayTask(scheduler, id)
            }
            "3"->{
                displayAllTasks(scheduler)
            }
            "4"->{
                println("Please enter the id of the task you want to update:")
                val id=readln().toInt()

                updateTask(scheduler, id)
                saveTasks(scheduler)
            }
            "5"->{
                println("Please enter the id of the task you want to delete:")
                val id=readln().toInt()

                deleteTask(scheduler, id)
                saveTasks(scheduler)
            }
            "6"->{
                println("Please enter the id of the task you want to execute:")
                val id=readln().toInt()

                executeTask(scheduler,this,id)
                saveTasks(scheduler)
            }
            "7"->{
                cancelTask()
                saveTasks(scheduler)
            }
            "0"->{
                println("Are you sure you want to quit?Yes or No")
                val choice=readln().uppercase()

                if(choice=="YES")
                {
                    println("You have exited successfully!")
                    break
                }
                else
                {
                    println("Continue doing your things.....")
                }
            }
        }
    }
}
