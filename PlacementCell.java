import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlacementCell {
    private ArrayList<Student> registered_students;
    private ArrayList<Company> registered_companies;
    public LocalDateTime openingtimeforComapnis;
    public LocalDateTime closingtimeforComapnis;

    public LocalDateTime openingtimeforStudents;
    public LocalDateTime closingtimeforStudents;

    public void AddCompany(Company A){
        registered_companies.add(A);
    }

    public void AddStudent(Student S){
        registered_students.add(S);
    }

    public PlacementCell(){
        registered_companies=new ArrayList<Company>();
        registered_students=new ArrayList<Student>();

    }

    private void OpenCompanyRegistrations(LocalDateTime a, LocalDateTime b){
                openingtimeforComapnis=a;
                closingtimeforComapnis=b;
                System.out.println("Institute is open for company registrations");
    }

    private void OpenStudentRegistrations(LocalDateTime a, LocalDateTime b){
        openingtimeforStudents=a;
        closingtimeforStudents=b;
        System.out.println("Institute is open for student registrations");
    }

    private void getStudentDetails(int rollno,String name){
        for(int i=0;i<registered_students.size();i++){
            Student s=registered_students.get(i);
            System.out.println("Companies registered for:");
            if(s.getName().equals(name) && s.getRollno()==rollno){
                for(int j=0;j<s.getCompaniesregisteredfor().size();j++){
                    System.out.println(s.getCompaniesregisteredfor().get(j));
                }
                System.out.println("Offers made to him:");
                for(int j=0;j<s.getOffers().size();j++){
                    System.out.println(s.getOffers().get(j));
                }
                return;
            }
        }
        System.out.println("Invalid roll no and name");
        return;
    }

    private void getCompanyDetails(String name){
        for(int i=0;i<registered_companies.size();i++){
            Company s=registered_companies.get(i);
            if(s.getName().equals(name)){
                System.out.println(s);
                System.out.println("Students selected during placement process are");
                for(int j=0;j<s.getSelectedStudents().size();j++){
                    System.out.println(s.getSelectedStudents().get(j));
                }
                return;
            }
        }
        System.out.println("Invalid name of company");
        return;
    }

    public ArrayList<Company> getCompanyList(){


        return registered_companies;
    }
    public void UpdateStudentCGPA(Student s,double cgpa){
        System.out.println("Placement Cell processin to request to update gpa");
        s.cgpa=cgpa;
    }
    private void NumberofPlacedStudents(){
        int numberPlaced=0;
        int numberUnplaced=0;
        int numberBlocked=0;
        for(int i=0;i<registered_students.size();i++){
            Student s=registered_students.get(i);
            if(s.getPlaced()==true){
                numberPlaced++;
            }
            else{
                numberUnplaced++;
            }
            if(s.getStatus()=="blocked"){
                numberBlocked++;
            }
        }
        System.out.println("Number of placed candidates "+numberPlaced);
        System.out.println("Number of unplaced candidates "+numberUnplaced);
        System.out.println("Number of blocked candidates "+numberBlocked);
    }

    private int getAveragePackage(){
        int sum=0;int count=0;
        for(int i=0;i<registered_students.size();i++){
            Student s=registered_students.get(i);
            if(s.getPlaced()==true) {
                sum += s.getCurrentctc();
                count++;
            }
        }
        System.out.println("Average package is :" +sum/count);
        return sum/count;
    }
    private void getCompanyProcessResults(String name){
        ArrayList<Student> s = null;
        for(int i=0;i<this.registered_companies.size();i++){
            Company c=registered_companies.get(i);
            if(name.equals(c.getName())){
                System.out.println("Students selected are:");
               for(int j=0;j<c.getSelectedStudents().size();j++){
                   System.out.println(c.getSelectedStudents().get(j));
               }
                break;
            }
        }
    }

    public static void main(PlacementCell p){
        System.out.println("Welcome to IIITD Placement Cell");
        System.out.println("1) Open student registrations");
        System.out.println("2) Open Company Registrations");
        System.out.println("3) Get Number of Student Registrations");
        System.out.println("4) Get Number of Company Registrations");
        System.out.println("5) Get  Number of Offered/Unoffered and blocked students");
        System.out.println("6) Get student Details");
        System.out.println("7) Get Company Details");
        System.out.println("8) Get Average Package");
        System.out.println("9) Get company Results");
        System.out.println("10) Back");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();

        if(x==10){
            FutureBuilder.EnterMode();
        }
        else if(x==9){
            sc=new Scanner(System.in);
            String g= sc.nextLine();
            p.getCompanyProcessResults(g);
        }
        else if(x==8){
            p.getAveragePackage();
        }
        else if(x==7){
            sc=new Scanner(System.in);
            String g=sc.nextLine();
            p.getCompanyDetails(g);
        }
        else if(x==6){
            System.out.print("Enter roll no. ");
            sc=new Scanner(System.in);
            int k=sc.nextInt();
            System.out.print("Enter name ");
            sc=new Scanner(System.in);
            String name=sc.nextLine();
            p.getStudentDetails(k,name);
        }
        else if(x==5){
            p.NumberofPlacedStudents();
        }
        else if(x==4){
            System.out.println("Number of registered companies is");
            System.out.println(p.registered_companies.size());
        }
        else if(x==3){
            System.out.println("Number of registered students is");
            System.out.println(p.registered_students.size());
        }
        else if(x==2){
            System.out.println("Fill the following details");
            System.out.println("Set the opening time for the registrations in format dd MM yyyy HH:mm");
            System.out.println("Set the closing time for the registrations in format dd MM yyyy HH:mm");
            sc=new Scanner(System.in);
            String d=sc.nextLine();
            sc=new Scanner(System.in);
            String D=sc.nextLine();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
            LocalDateTime open=LocalDateTime.parse(d,formatter);
            LocalDateTime close=LocalDateTime.parse(D,formatter);
            p.OpenCompanyRegistrations(open,close);
        }

        else if(x==1){
            System.out.println("Fill the following details");
            System.out.println("Set the opening date and time for the registrations in format dd MM yyyy HH:mm");
            System.out.println("Set the closing time for the registrations in format dd MM yyyy HH:mm");
            sc=new Scanner(System.in);
            String d=sc.nextLine();
            sc=new Scanner(System.in);
            String D=sc.nextLine();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
            LocalDateTime open=LocalDateTime.parse(d,formatter);
            LocalDateTime close=LocalDateTime.parse(D,formatter);
            p.OpenStudentRegistrations(open,close);
        }
        if(x!=10){
            main(p);
        }



    }










}
