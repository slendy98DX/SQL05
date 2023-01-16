import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "Inazumaeleven1#");

            Statement statement = connection.createStatement();

            statement.executeUpdate("create view italian_students as select first_name,last_name from students where country = 'Italy'");

            System.out.println("View italian_students creata correttamente");

            statement.executeUpdate("create view german_students as select first_name,last_name from students where country = 'Germany'");

            System.out.println("View german_students creata correttamente");

            List<Student> italianStudents = new ArrayList<>();

            List<Student> germanStudents = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("select first_name,last_name from italian_students");

            while (resultSet.next()) {
                Student italianStudent = new Student(resultSet.getString("first_name"), resultSet.getString("last_name"));
                italianStudents.add(italianStudent);
            }

            ResultSet resultSet2 = statement.executeQuery("select first_name,last_name from german_students");

            while (resultSet2.next()) {
                Student germanStudent = new Student(resultSet2.getString("first_name"), resultSet2.getString("last_name"));
                germanStudents.add(germanStudent);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
