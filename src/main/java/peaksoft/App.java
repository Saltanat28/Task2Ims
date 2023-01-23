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

        System.out.println("" +
                "~~~~~~Jobs~~~~~~" +
                "1.Cteate table job." +
                "2.Add jobs to table." +
                "3.Get job by id." +
                "4.Sort by experience." +
                "5.Delete description column." +
                "6.Get job by employee id.");
        int number = new Scanner(System.in).nextInt();
        int number2 = new Scanner(System.in).nextInt();
        switch (number) {
            case 1 -> System.out.println(jobService.createJobTable());
            case 2 -> {
                 System.out.println(jobService.addJob(new Job( 1L,"Mentor", "Java", "Backed developer", 1)));
                 System.out.println(jobService.addJob(new Job(2L, "Management", "Management", "controlling", 2)));
                 System.out.println(jobService.addJob(new Job(3L, "Instructor", "JavaScript", "Frontend", 2)));
                 }
            case 3-> System.out.println(jobService.getJobById(1L));
            case 4-> System.out.println(jobService.sortByExperience("desc"));
            case 5-> System.out.println(jobService.deleteDescriptionColumn());
            case 6-> System.out.println(jobService.getJobByEmployeeId());
        }
//        System.out.println("~~~~~~Employee~~~~~~~~" +
//                "1.Crate table Employees." +
//                "2. ");
//
//        EmployeeDao employeeDao = new EmployeeImpl();
//
//        switch (number2) {
//            case 1-> employeeDao.createEmployee();
//            case 2-> {employeeDao.addEmployee(new Employee(1L, "Asylbek", "Adilov", 15, "a@email.com", 2));
//                      employeeDao.addEmployee(new Employee(2L, "Nuriza", "Muratova", 16, "nurizz@email.com", 1));
//                      employeeDao.addEmployee(new Employee(3L, "Eliza", "AShirbaeva", 17, "eliz@email.com", 3));
//                      employeeDao.addEmployee(new Employee(4L, "Aygerim", "Bektenova", 18, "ay@email.com", 21);
//            }
//            case 3-> System.out.println(employeeDao.getEmployeeById(2L));
//            case 4-> System.out.println(employeeDao.getEmployeeByPosition("Mentor"));
//            case 5-> System.out.println(employeeDao.getAllEmployees());
//            case 6-> System.out.println(employeeDao.findByEmail("\"nurizz@email.com\" "));
//            case 7-> employeeDao.updateEmployee(new Employee(1L, "Kanykey","Askarbekove", 16, "kaku@email.com",1));
//            case 8-> employeeDao.cleanTable();
//            case 9-> employeeDao.dropTable();
//        }


    }
}
