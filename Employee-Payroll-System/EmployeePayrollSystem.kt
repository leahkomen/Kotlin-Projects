//EMPLOYEE PAYROLL SYSTEM.
import java.io.File

abstract class Employee(
    val id: Int,
    var fullName: String,
    var email: String,
    var phoneNumber: Long,
    var department: String,
    var position: String
)
 {
     abstract fun calculateSalary(): Double
 }
 class FullTimeEmployee(
     id: Int,
     fullName: String,
     email: String,
     phoneNumber: Long,
     department: String,
     position: String,
      val basicSalary: Double
 ):Employee(id, fullName, email, phoneNumber, department, position)
 {
    override fun calculateSalary(): Double
    {
        return basicSalary
    }
 }

 class PartTimeEmployee(
     id: Int,
     fullName: String,
     email: String,
     phoneNumber: Long,
     department: String,
     position: String,
     val hoursWorked: Double,
     val hoursRate: Double
 ):Employee(id, fullName, email, phoneNumber, department, position)
 {
     override fun calculateSalary(): Double
     {
         return hoursWorked*hoursRate
     }
 }
 class ContractEmployee(
     id: Int,
     fullName: String,
     email: String,
     phoneNumber: Long,
     department: String,
     position: String,
     val contractAmount: Double,
     val numberOfProjects:Int
 ):Employee(id, fullName, email, phoneNumber, department, position)
 {
     override fun calculateSalary(): Double
     {
         return contractAmount*numberOfProjects
     }
 }

val employees=mutableListOf<Employee>()
val fileName="employee.csv"

fun addEmployee(
    list: MutableList<Employee>,
    employee: Employee)
{
    list.add(employee)
}
fun viewEmployee(list: MutableList<Employee>,id:Int)
{
    val employee=list.find { it.id == id }
    if (employee!=null)
    {
        println("ID: ${employee.id}")
        println("Name: ${employee.fullName}")
        println("Email: ${employee.email}")
        println("PhoneNumber: ${employee.phoneNumber}")
        println("Department: ${employee.department}")
        println("Position: ${employee.position}")
        println("Salary: ${employee.calculateSalary()}")
    }
    else
    {
        println("No employee found.")
    }
}

fun updateEmployee(list: MutableList<Employee>,id: Int)
{
    val employee=list.find { it.id == id }
    if (employee!=null)
    {
        println("Please enter your full names:")
        val fullName=readln().uppercase()

        println("Please enter your email address:")
        val email=readln().lowercase()

        println("Please enter your phone number:")
        val phoneNumber=readln().toLong()

        println("Please enter your department:")
        val department=readln().uppercase()

        println("Please enter your position in the company:")
        val position=readln().uppercase()

        employee.fullName=fullName
        employee.email=email
        employee.phoneNumber=phoneNumber
        employee.department=department
        employee.position=position
    }
    else
    {
        println("No employee found.")
    }
}
fun deleteEmployee(list: MutableList<Employee>,fullName: String)
{
    val employee=list.find { it.fullName == fullName }
    if (employee!=null)
    {
        println("Are you sure you want to delete this employee from the system?")
        val choice=readln().uppercase()
        if (choice=="YES")
        {
            list.remove(employee)
            println("You have successfully deleted the employee from the system.")
        }
        else
        {
            println("It is nice to have them in our company.")
        }
    }
}
 fun calculateEmployeeSalary(list: MutableList<Employee>,id: Int)
 {
     val employee=list.find { it.id == id }
     if (employee!=null)
     {
         println("Employee: ${employee.fullName}")
         println("Salary: ${employee.calculateSalary()}")
     }
     else
     {
         println("No employee found.")
     }
 }
fun saveEmployee(list: MutableList<Employee>)
{
    val lines = mutableListOf<String>()

    for(employee in list)
    {
        val line = when(employee)
        {
            is FullTimeEmployee ->
                "${employee.id},FULL TIME,${employee.fullName},${employee.email},${employee.phoneNumber},${employee.department},${employee.position},${employee.basicSalary}"

            is PartTimeEmployee ->
                "${employee.id},PART TIME,${employee.fullName},${employee.email},${employee.phoneNumber},${employee.department},${employee.position},${employee.hoursWorked},${employee.hoursRate}"

            is ContractEmployee ->
                "${employee.id},CONTRACT,${employee.fullName},${employee.email},${employee.phoneNumber},${employee.department},${employee.position},${employee.contractAmount},${employee.numberOfProjects}"

            else -> ""
        }

        lines.add(line)
    }

    File(fileName).writeText(lines.joinToString("\n"))
}
fun loadEmployee(list: MutableList<Employee>)
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
                "FULL TIME" ->
                {
                    list.add(
                        FullTimeEmployee(
                            parts[0].toInt(),
                            parts[2],
                            parts[3],
                            parts[4].toLong(),
                            parts[5],
                            parts[6],
                            parts[7].toDouble()
                        )
                    )
                }

                "PART TIME" ->
                {
                    list.add(
                        PartTimeEmployee(
                            parts[0].toInt(),
                            parts[2],
                            parts[3],
                            parts[4].toLong(),
                            parts[5],
                            parts[6],
                            parts[7].toDouble(),
                            parts[8].toDouble()
                        )
                    )
                }

                "CONTRACT" ->
                {
                    list.add(
                        ContractEmployee(
                            parts[0].toInt(),
                            parts[2],
                            parts[3],
                            parts[4].toLong(),
                            parts[5],
                            parts[6],
                            parts[7].toDouble(),
                            parts[8].toInt()
                        )
                    )
                }
            }
        }
    }catch (e:Exception){

        println("Error loading ${file.name}")
    }

}

 fun main()
 {
     loadEmployee(employees)

     while(true)
     {
         println("====EMPLOYEE PAYROLL SYSTEM===")
         println("1.Add employee to the company.")
         println("2.View the employee details.")
         println("3.Update employee to the system.")
         println("4.Delete employee from the system.")
         println("5.Calculate the employees salary.")
         println("6.Exit.")

         println("What do you want to do today?")
         val choice=readln()

         if (choice !in listOf("1","2","3","4","5","6"))
         {
             println("Invalid input!")
             continue
         }
         when(choice){
             "1"->{
                 val id = (employees.maxOfOrNull { it.id } ?: 0) + 1

                 println("Please enter your full name:")
                 val fullName=readln().uppercase()

                 println("Please enter your email address:")
                 val email=readln().lowercase()

                 println("Please enter your phone number:")
                 val phoneNumber=readln().toLong()

                 println("Please enter your department:")
                 val department=readln().uppercase()

                 println("Please enter your position in the company:")
                 val position=readln().uppercase()

                 println("==EMPLOYEE TYPE==")
                 println("1.Full Time.")
                 println("2.Part Time.")
                 println("3.Contract.")

                 println("Please enter your type of job:")
                 val type=readln()

                 when(type){
                     "1"->{
                         println("Please enter basic salary:")
                         val basicSalary = readln().toDouble()

                         val employee = FullTimeEmployee(
                             id,
                             fullName,
                             email,
                             phoneNumber,
                             department,
                             position,
                             basicSalary
                         )
                         addEmployee(employees, employee)
                     }
                     "2"->{
                         println("Please enter hours worked:")
                         val hoursWorked = readln().toDouble()

                         println("Please enter hourly rate:")
                         val hourlyRate = readln().toDouble()

                         val employee = PartTimeEmployee(
                             id,
                             fullName,
                             email,
                             phoneNumber,
                             department,
                             position,
                             hoursWorked,
                             hourlyRate
                         )

                         addEmployee(employees, employee)
                     }
                     "3" -> {
                         println("Enter contract amount:")
                         val contractAmount = readln().toDouble()

                         println("Enter number of projects:")
                         val projects = readln().toInt()

                         val employee = ContractEmployee(
                             id,
                             fullName,
                             email,
                             phoneNumber,
                             department,
                             position,
                             contractAmount,
                             projects
                         )

                         addEmployee(employees, employee)
                     }
                 }
                 saveEmployee(employees)
             }
             "2"->{
                 println("Please enter your id:")
                 val id=readln().toInt()

                 viewEmployee(employees,id)
             }
             "3"->{
                 println("Please enter your id:")
                 val id=readln().toInt()

                 updateEmployee(employees,id)
                 saveEmployee(employees)
             }
             "4"->{
                 println("Please enter your full name:")
                 val fullName=readln().uppercase()

                 deleteEmployee(employees,fullName)
                 saveEmployee(employees)
             }
             "5"->{
                 println("Please enter your id:")
                 val id=readln().toInt()

                 calculateEmployeeSalary(employees,id)
                 saveEmployee(employees)
             }
             "6"->{
                 println("Are you sure you want to exit?")
                 val choice=readln().uppercase()
                 if (choice=="YES")
                 {
                     println("You have exited successfully!")
                     break
                 }
                 else
                 {
                     println("Continue browsing....")
                 }
             }
         }
     }
 }
