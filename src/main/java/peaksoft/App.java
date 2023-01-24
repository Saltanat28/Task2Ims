package peaksoft;

import peaksoft.Dao.EmployeeDao;
import peaksoft.Dao.EmployeeImpl;
import peaksoft.Dao.JobDao;
import peaksoft.Dao.JobDaoImpl;
import peaksoft.model.Employee;
import peaksoft.model.Job;
import peaksoft.serivce.EmployeeService;
import peaksoft.serivce.EmployeeServiceImpl;
import peaksoft.serivce.JobService;
import peaksoft.serivce.JobServiceImpl;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        JobService jobService = new JobServiceImpl();
        EmployeeDao employeeDao = new EmployeeImpl();
        while (true) {

            System.out.println("""
                                    ~~~~~~Jobs~~~~~~
                                    "1.Create table job." 
                                    "2.Add jobs to table." 
                                    "3.Get job by id." 
                                    "4.Sort by experience." 
                                    "5.Delete description column." 
                                    "6.Get job by employee id
                                    "~~~~~~Employee~~~~~~~~"
                                    7.Crate table employee
                                    8.Add employees
                                    9.Get employee by id
                                    10.Get employee by position
                                    11.Get all employees
                                    12.Find by email
                                    13.Update employee
                                    14.clean table 
                                    15.Drop table
                    """
            );
            int number = new Scanner(System.in).nextInt();

            switch (number) {
                case 1 -> System.out.println(jobService.createJobTable());
                case 2 -> {
                    System.out.println(jobService.addJob(new Job(1L, "Mentor", "Java", "Backed developer", 1)));
                    System.out.println(jobService.addJob(new Job(2L, "Management", "Management", "controlling", 2)));
                    System.out.println(jobService.addJob(new Job(3L, "Instructor", "JavaScript", "Frontend", 2)));
                }
                case 3 -> System.out.println(jobService.getJobById(2L));
                case 4 -> System.out.println(jobService.sortByExperience("desc"));
                case 5 -> System.out.println(jobService.deleteDescriptionColumn());
                case 6 -> System.out.println(jobService.getJobByEmployeeId(2L));


                case 7-> employeeDao.createEmployee();
                case 8-> {employeeDao.addEmployee(new Employee(1L, "Asylbek", "Adilov", 15, "a@email.com", 2));
                      employeeDao.addEmployee(new Employee(2L, "Nuriza", "Muratova", 16, "nurizz@email.com", 1));
                      employeeDao.addEmployee(new Employee(3L, "Eliza", "AShirbaeva", 17, "eliz@email.com", 3));
                      employeeDao.addEmployee(new Employee(4L, "Aygerim", "Bektenova", 18, "ay@email.com", 21));
            }
            case 9-> System.out.println(employeeDao.getEmployeeById(2L));
            case 10-> System.out.println(employeeDao.getEmployeeByPosition("Mentor"));
            case 11-> System.out.println(employeeDao.getAllEmployees());
            case 12-> System.out.println(employeeDao.findByEmail("\"nurizz@email.com\" "));
            case 13-> employeeDao.updateEmployee(1L, new Employee(1L,"Kanykey", "Askarova",17,"k@email.com",2));
            case 14-> employeeDao.cleanTable();
            case 15-> employeeDao.dropTable();
            }

        }
    }
}
