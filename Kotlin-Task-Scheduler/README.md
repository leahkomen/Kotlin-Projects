# Task Scheduler
A console-based task scheduler built in Kotlin using coroutines, allowing tasks to run asynchronously with delays, be cancelled mid-execution, and persist across sessions via Java serialization.

## Features

- **Add tasks** — create a task with a name and a delay (in seconds) before it completes.
- **View a task by ID** — display the details of a single task.
- **View all tasks** — list every task currently in the scheduler.
- **Update tasks** — change a task's name and delay, as long as it isn't currently running.
- **Delete tasks** — remove a task by ID, with a confirmation prompt, as long as it isn't currently running.
- **Execute tasks** — launch a task asynchronously using Kotlin coroutines; the task runs in the background for its configured delay before completing.
- **Input validation** — invalid (non-numeric) IDs when updating, deleting, or executing a task are caught and reported instead of crashing the program.
- **Cancel running tasks** — cancel a task that is currently executing before its delay completes.
- **Persistent storage** — tasks and their statuses are saved to `tasks.dat` using Java serialization, and reloaded automatically on startup.
- **Task status tracking** — each task has a status of `PENDING`, `RUNNING`, `COMPLETED`, or `CANCELLED`.

## How it works
Each task is represented by a `TaskScheduler` data class holding an ID, name, delay, and status, and implements `Serializable` so the full task list can be written to and read from disk.
Tasks are executed using `CoroutineScope.launch`, which starts the task's delay on a coroutine without blocking the main menu loop. Running tasks are tracked in a `runningJobs` map (task ID → `Job`), which allows a task to be cancelled mid-execution using Kotlin's coroutine cancellation (`job.cancel()`), caught via `CancellationException` inside the running coroutine. Task status and the task list are saved to disk whenever a task completes, is cancelled, or the scheduler state otherwise changes.
Users interact through a menu-driven interface to add, view, update, delete, execute, and cancel tasks, or exit (with confirmation) once done.

## Run it

```bash
kotlinc TaskScheduler.kt -include-runtime -d taskscheduler.jar
java -jar taskscheduler.jar
```

Requires `kotlinx-coroutines-core` to be available on the classpath (see dependency comment at the top of the file if building with Gradle).

## Requirements
- Kotlin
- Kotlin Coroutines (`kotlinx-coroutines-core`)
- JVM

## Concepts Practiced

- Coroutines and Structured Concurrency
- Asynchronous Task Execution and Cancellation
- Enums
- Data Classes
- Java Serialization (`Serializable`, `ObjectOutputStream`/`ObjectInputStream`)
- Mutable Collections and Maps
- Console Application Development
