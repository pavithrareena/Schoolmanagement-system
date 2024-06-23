import java.util.TreeSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagementSystem {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InputMismatchException {
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println("!---Student Management System----!");
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println();

        ManagementSystem mnfsys = new ManagementSystem();
        InvalidOperation inv = new InvalidOperation();
        boolean exit = false;
        while (!exit) {
            System.out.println("+++++++++++++++++++++++++");
            System.out.println("1.Add Student\n2.Search Student\n3.Delete Student\n4.Get all Student Details\n5.Exit");
            System.out.println("+++++++++++++++++++++++++");
            System.out.print("Enter choice(1|2|3|4|5):- ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    try {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Enter Name of Student: ");
                        String name = sc.next();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Enter Rollnumber of Student:");
                        int roolNum = sc.nextInt();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Enter gender of Student: ");
                        String gender = sc.next();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Enter Grade of Student: ");
                        String grade = sc.next();
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        Student s = new Student(name, roolNum, grade, gender);
                        mnfsys.addStudent(s);
                        System.out.println("student added!");
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

                    } catch (InputMismatchException e) {
                        System.out.println(inv.getMessage());
                    } catch (Exception e) {
                        System.out.println(inv.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter the Roll Number of Student to Search: ");
                    int search = sc.nextInt();
                    Student s = mnfsys.searchStudent(search);
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    if (s != null) {
                        System.out.println(s.toString());
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter the Roll Number of Student to Delete: ");
                    int delete = sc.nextInt();
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    System.out.println(mnfsys.removeStudent(delete));
                    break;
                case 4:
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    mnfsys.allStudents();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    System.out.println(inv.getMessage());
            }
        }
    }
}

class ManagementSystem {
    private TreeSet<Student> student;

    public ManagementSystem() {
        student = new TreeSet<Student>();
    }

    public void addStudent(Student stu) throws InvalidOperation {
        if (stu.getName() != null || stu.getGender() != null || stu.getGrade() != null) {
            student.add(stu);
        } else {
            InvalidOperation inv = new InvalidOperation();
            System.out.println(inv.getMessage());
            throw inv;
        }

    }

    public String removeStudent(int id) {
        Student delStudent = null;
        for (Student stu : student) {
            if (stu.getRoolNum() == id) {
                delStudent = stu;
                break;
            }
        }
        if (delStudent != null) {
            student.remove(delStudent);
            return delStudent.getName() + " with " + delStudent.getRoolNum() + " deleted.";
        }
        return "Student not found!";
    }

    public Student searchStudent(int id) {
        for (Student stu : student) {
            if (stu.getRoolNum() == id) {
                return stu;
            }
        }
        return null;
    }

    public void allStudents() {
        for (Student stu : student) {
            System.out.println(stu.toString());
        }
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int roolNum;
    private String grade;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoolNum() {
        return roolNum;
    }

    public void setRoolNum(int roolNum) {
        this.roolNum = roolNum;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Student(String name, int roolNum, String grade, String gender) {
        this.name = name;
        this.roolNum = roolNum;
        this.grade = grade;
        this.gender = gender;
    }

    public Student() {
        super();
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.roolNum, o.roolNum);
    }

    @Override
    public String toString() {
        return "name-->" + name + " || Rool Number-->" + roolNum + " || Grade-->" + grade + " || Gender-->" + gender;
    }

}

class InvalidOperation extends Exception {
    @Override
    public String getMessage() {
        return "Invalid operation performed! Try Again.";
    }
}
