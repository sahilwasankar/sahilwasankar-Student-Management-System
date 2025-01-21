import java.util.*;
import java.io.*;


//Creating student class
// Implementing Serialization 
class Student implements Serializable{   
    private String name;// using the private access Modifiers can be accessed in these class only
    private int rollNumber;
    private String studentClass;
    private String section;
    private String contactDetails;
    private String address;
    private String dateOfBirth;
    // Map to store attendance records 
  private  HashMap <String, String> attendance= new HashMap<>();// Date -> Status

    // Map to store grade records 
  private HashMap<String,Integer>grades = new HashMap<>();// Subject -> Grade

  public Student(String name,int rollNumber,String studentClass,String section,String contactDetails,String address,String dateOfBirth){
    

    this.name = name;
    this.rollNumber = rollNumber;
    this.studentClass = studentClass;
    this.contactDetails = contactDetails;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
  }

    // creating the getters 
    public String getName(){
        return name;
    }
    public int getRollNumber() {
        return rollNumber;
    }

    public String getStudentClass(){
        return studentClass;
    }
    public String getSection(){
        return section;
    }

    public void updateDetails(String contactDetails, String address) {
        this.contactDetails = contactDetails;
        this.address = address;
    }
    // to insert the values of  Date,Status and Subject,Grade of Hashmap
    public void recordAttendance(String date,String status){
        attendance.put(date,status);
            
    }
    public void addGrade(String subject,int grade){
       grades.put(subject,grade);
            
    }
    //calculating grades
    public double calculateAverageGrade() {
        if(grades.isEmpty()){
            System.out.println("empty ");
            return 0;
        }
         int  total =0 ;
        for(int grade : grades.values()){// only vlues function from it
          
            total += grade;

        }
        return total / (double) grades.size();
    }
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Class: " + studentClass);
        System.out.println("Section: " + section);
        System.out.println("Contact: " + contactDetails);
        System.out.println("Address: " + address);
        System.out.println("Date of Birth: " + dateOfBirth);
    }

    public void displayAttendance() {
        System.out.println("Attendance:");
        for (Map.Entry<String, String> entry : attendance.entrySet()) {
            // when we have to print both key and value in for loop then we are using this function

            System.out.println(entry.getKey() + " - " + entry.getValue());
  }
}
public void displayGrades() {
    System.out.println("Grades:");
    for (Map.Entry<String, Integer> entry : grades.entrySet()) {
        // key Subject   values Grade    note key values cannot repeat  but vlaues can repeat

        System.out.println(entry.getKey() + ": " + entry.getValue());
    }
}
}


class Student_Management_System{
    private static LinkedList<Student> students = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner  scanner = new Scanner (System.in);


        while (true) {
            System.out.println("\nStudent Management System");

            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Details");
            System.out.println("4. Delete Student");
            System.out.println("5. Record Attendance");
            System.out.println("6. Generate Attendance Report");
            System.out.println("7. Add Grades");
            System.out.println("8. View Grades");
            System.out.println("9. Save Data to File");
            System.out.println("10. Load Data from File");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
         // By using switch case we can select the function by just entering the numbers
            switch(choice){
                case 1 : 
                addStudent();
                break;
                case 2 :
                viewAllStudents();
                break;
                case 3:
                updateStudentDetails();
                break;

                case 4 :
                 deleteStudent();
                 break;

                case 5 :
                recordAttendance();
                break;

                case 6 :
                generateAttendanceReport();
                break;

                case 7 :
                addGrades();
                break;

                case 8 :
                viewGrades();
                break;

                case 9 :
                saveDataToFile();
                break;

                case 10 :
                loadDataFromFile();
                break;

              case 11 : {
                System.out.println("Exiting... Goodbye!");
                return;
              }

              default : System.out.println("Invalid choice. Please try again.");   

            }
                
        }}
    
        private static void addStudent(){
        
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter roll number: ");
            int rollNumber = scanner.nextInt();
            scanner.nextLine();         
            // to avoid the skiping function
            System.out.print("Enter class: ");
            String studentClass = scanner.nextLine();
            System.out.print("Enter section: ");
            String section = scanner.nextLine();
            System.out.print("Enter contact details: ");
            String contactDetails = scanner.nextLine();
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            System.out.print("Enter date of birth: ");
            String dateOfBirth = scanner.nextLine();
            
            students.add(new Student(name, rollNumber, studentClass, section, contactDetails, address, dateOfBirth));
            System.out.println("Student added successfully.");
                    
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        students.sort(Comparator.comparingInt(Student::getRollNumber));

        for (Student student : students) {
            student.displayDetails();
            System.out.println();
        }
    }
   
    private static void  updateStudentDetails(){
        System.out.println("Enter the rollNumber: ");
        int Rollno= scanner.nextInt();
        scanner.nextLine();

        for(Student student : students){

     if (student.getRollNumber()== Rollno){
        System.out.println("Enter the ContactDetails :");
       String contactdetails =scanner.nextLine();
       System.out.println("Enter the Address :");
       String address =scanner.nextLine();
      student.updateDetails(contactdetails, address);
        System.out.println("Student details updated successfully.");
        return;

     }

    }
}

    private static void deleteStudent(){
        System.out.println("Enter the rollNumber: ");
        int Rollno= scanner.nextInt();
        scanner.nextLine();

       
        Iterator<Student> iterator = students.iterator();
      //  it does full iteration at one time of students then by comparing the role number it removes it at one time
       while( iterator.hasNext()){
         Student student =  iterator.next();
              if (student.getRollNumber()== Rollno){
                iterator.remove();
                System.out.println("Student deleted successfully");
                return;
       }
       System.out.println("Student not found");
    }

    }
    private static void recordAttendance(){
        System.out.print("Enter date (dd-mm-yyyy): ");
        String date = scanner.nextLine();

        for(Student student : students){
            System.out.println("Is"+student.getName()+"(P/A)");
            String status = scanner.nextLine();
            if(status.equalsIgnoreCase("P")){
                  student.recordAttendance(date,"present");

            }
            else{
                student.recordAttendance(date,"absent");        
                }

                System.out.println("Attendance recorded successfully.");

        }
    }
    
    private static void generateAttendanceReport(){
        System.out.println("Enter the start dat(dd-mm-yyyy)");
             String startDate = scanner.nextLine();
        System.out.println("Enter the end dat(dd-mm-yyyy)");
           String endDate = scanner.nextLine();

           for (Student student : students) {
            System.out.println("Attendance for " + student.getName() + ":");
            student.displayAttendance();
            System.out.println();
        }
    }
    
    private static void addGrades() {
        System.out.print("Enter roll number of the student: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();

        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.print("Enter subject: ");
                String subject = scanner.nextLine();
                System.out.print("Enter grade: ");
                int grade = scanner.nextInt();
                scanner.nextLine();
                student.addGrade(subject, grade);
                System.out.println("Grade added successfully.");
                return;
            }
        }

        System.out.println("Student not found.");
    }
    
        private static void viewGrades() {
            System.out.print("Enter roll number of the student: ");
            int rollNumber = scanner.nextInt();
            scanner.nextLine();
    
            for (Student student : students) {
                if (student.getRollNumber() == rollNumber) {
                    student.displayGrades();
                    System.out.println("Average Grade: " + student.calculateAverageGrade());
                    return;
                }
            }
    
            System.out.println("Student not found.");
        }
    

        private static void saveDataToFile() {
            try {
                FileOutputStream fos = new FileOutputStream("students.dat");//serialisation is the mechanism of converting the state of an object into byte stream
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                // FileOutputStream here the object gets
                // oos by these obj the data can written and saved
                oos.writeObject(students);// to save the data of array list is comulsary to create a object function like writeobject
                System.out.println("Data saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }
    
        private static void loadDataFromFile() {
          try{
            FileInputStream  fio = new FileInputStream("students.dat");
            ObjectInputStream ois = new ObjectInputStream(fio);
    //Deserialization is the reverse process where the byte streams is used to recreate the actual Java object in memory
    List<Student> loadedStudents = (List<Student>) ois.readObject();
                System.out.println("Data loaded successfully.");

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading data: " + e.getMessage());
            }
        }
    }
   









