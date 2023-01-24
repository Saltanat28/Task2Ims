package peaksoft.Dao;

import peaksoft.config.Configuration;
import peaksoft.model.Employee;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeImpl implements EmployeeDao{
    Connection connection = Configuration.getConnection();

    @Override
    public void createEmployee() {
        String query = """
                create table employees(
                id serial primary key,
                first_name varchar,
                last_name varchar,
                age int,
                email varchar,
                job_id int references jod(id)
                """;
        try(Statement statement = connection.createStatement();){
            statement.execute(query);

        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    @Override
    public void addEmployee(Employee employee) {
        String query = """
                insert into employees(first_name, last_name, age, email, jodId)
                values(?, ?, ?, ?, ?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.getResultSet();
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropTable() {
        String query = """
                drop table employees;
                """;
        try(Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void cleanTable() {
        String query = """
                delete table employees;
                """;
        try(Statement statement = connection.createStatement();) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = """
                update employees
                set first_name = ?,
                last_name = ?, age = ?, email = ?, jobId = ?,
                where id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
    }catch (SQLException e){
            throw  new RuntimeException();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = """
                select * from employees;
                """;
        try (Statement statement = connection.createStatement();){
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString(1));
                employee.setLastName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setEmail(resultSet.getString(4));
                employee.setJobId(resultSet.getInt(5));
                employees.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public Employee findByEmail(String email) {
        Employee employee = new Employee();
        String query = """
                select * from employees where email = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employee.setFirstName(resultSet.getString(1));
                employee.setLastName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setEmail(resultSet.getString(4));
                employee.setJobId(resultSet.getInt(5));
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> map = new HashMap<>();
        String query = """
                select * from employees join job j on employees.id = j.employees.id where id = ?;
            
              """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
           // preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee=new Employee();
            while (resultSet.next()){
                employee.setFirstName(resultSet.getString("first name"));
                employee.setLastName(resultSet.getString("last name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("jodId"));

            }
            Job job=new Job();
            job.setPosition(resultSet.getString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("Experience"));

             map.put(employee,job);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();
        String query = """
                select * from employees join jobs j on employees.id = j.employees.id where position = ?;
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
            while (resultSet.next()){
                employee.setFirstName(resultSet.getString(1));
                employee.setLastName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setEmail(resultSet.getString(4));
                employee.setJobId(resultSet.getInt(5));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
