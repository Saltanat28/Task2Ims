package peaksoft.Dao;

import peaksoft.config.Configuration;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao{

private Connection connection =Configuration.getConnection();

    @Override
    public void createJobTable() {
        String query = """
                create table jobs(
                id serial primary key ,
                position varchar,
                profession varchar,
                description varchar,
                experience int 
                );
                """;

            try(Statement statement = connection.createStatement()){
                statement.execute(query);

            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void addJob(Job job) {
        String query = """
                insert into  jobs(position, profession, description, experience)
                values(?, ?, ?, ?);
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println("Job successfully added! ");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String query = """
                select * from jobs where  id  = ?;
                """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setPosition(resultSet.getString(1));
                job.setProfession(resultSet.getString(2));
                job.setDescription(resultSet.getString(3));
                job.setExperience(resultSet.getInt(4));



            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String query = " ";
        String query1 = """
                select * from jobs order by experience;
                """;
        String query2 = """
                Select * from jobs order by experience desc;
                """;

        if(ascOrDesc.equals("asc"))  query=query1;
        else if (ascOrDesc.equals("desc")) {
            query=query2;
        }
        try (Statement statement = connection.createStatement();){
           ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jobs.add(new Job(resultSet.getLong("id"),resultSet.getString("Position"),
                        resultSet.getString("Profession"),resultSet.getString("Description"),resultSet.getInt("Experience")));


            }
            return jobs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();

        String query = """
                select * from jobs join employees  on jobs.id=employees.jobId where employee.id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                job.getId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = """
                alter table jobs drop column description;""";


        try(Statement statement = connection.createStatement();) {
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
