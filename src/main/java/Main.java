import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static Connection connection;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:postgresql://localhost:5432/A3";
        String user = "postgres";
        String password = "sdesde";

        try {
            // set up connection to the database
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

            // while loop to let user test out functions
            int numInput = 1;
            while (numInput != 0) {
                System.out.println("\nPlease enter an integer from 0 to 4 to choose a function or exit the program.");
                System.out.println("\t0: Exit the program");
                System.out.println("\t1: test getAllStudents()");
                System.out.println("\t2: test addStudent()");
                System.out.println("\t3: test updateStudentEmail()");
                System.out.println("\t4: test deleteStudent()");
                System.out.print("Enter here: ");
                numInput = sc.nextInt();
                sc.nextLine();

                switch (numInput) {
                    //  run getAllStudents()
                    case 1:
                        getAllStudents();
                        break;

                    // run addStudent()
                    case 2:
                        System.out.print("Please enter first_name: ");
                        String first_name = sc.nextLine();
                        System.out.print("Please enter last_name: ");
                        String last_name = sc.nextLine();
                        System.out.print("Please enter email: ");
                        String email = sc.nextLine();
                        System.out.print("Please enter enrollment_date: ");
                        String enrollment_date = sc.nextLine();
                        addStudent(first_name, last_name, email, enrollment_date);
                        break;

                    // run updateStudentEmail()
                    case 3:
                        System.out.print("Please enter student_id: ");
                        int student_id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Please enter new_email: ");
                        String new_email = sc.nextLine();
                        updateStudentEmail(student_id, new_email);
                        break;

                    // run deleteStudent()
                    case 4:
                        System.out.print("Please enter student_id: ");
                        int delete_id = sc.nextInt();
                        sc.nextLine();
                        deleteStudent(delete_id);
                        break;
                    default:
                        break;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    // Retrieves and displays all records from the students table.
    public static void getAllStudents() {
        try {
            Statement statement = connection.createStatement();

            // get all fields from the students table
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();

            // print out the column labels of the table
            System.out.println("student_id \t first_name \t last_name \t email \t enrollment_date");

            // output all data from the students table
            while (resultSet.next()) {
                System.out.print(resultSet.getString("student_id") + " \t");
                System.out.print(resultSet.getString("first_name") + " \t");
                System.out.print(resultSet.getString("last_name") + " \t");
                System.out.print(resultSet.getString("email") + " \t");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Inserts a new student record into the students table.
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        try {
            Statement statement = connection.createStatement();

            // inserts a new student using the parameters given
            statement.executeUpdate("INSERT INTO students(first_name, last_name, email, enrollment_date)" + "VALUES('" + first_name + "','" + last_name + "','" + email + "','" + enrollment_date + "')");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Updates the email address for a student with the specified student_id.
    public static void updateStudentEmail(int student_id, String new_email) {
        try {
            Statement statement = connection.createStatement();

            // updates a student's email if their student_id matches the student_id given
            statement.executeUpdate("UPDATE students SET email = '" + new_email + "'" + "WHERE student_id = " + student_id);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    // Deletes the record of the student with the specified student_id
    public static void deleteStudent(int student_id) {
        try {
            Statement statement = connection.createStatement();

            // deletes a student's record if their student_id matches the student_id given
            statement.executeUpdate("DELETE FROM students WHERE student_id = " + student_id);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
