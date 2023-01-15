import java.awt.*;
import java.util.*;

public class FutureBuilder {

    public static PlacementCell p=new PlacementCell();
    public static ArrayList<Student> stu=new ArrayList<Student>();
    public static ArrayList<Company> com=new ArrayList<Company>();
    public static void EnterMode(){
        System.out.println("Chose the mode you want to enter in:");
        System.out.println("1 ) Enter as student mode");
        System.out.println("2 ) Enter as company mode");
        System.out.println("3 ) Enter as Placement cell");
        System.out.println("4 ) Return to main application");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        if(x==4){
            main(null);
        }
        else if(x==3){
            PlacementCell.main(FutureBuilder.p);
        }
        else if(x==2){
            CompanyMode(null);
        }
        else if(x==1){
            StudentMode(null);
        }
        if(x!=4){
            EnterMode();
        }

    }
    public static void CompanyMode(String[] args){
        System.out.println("Choose the company query to perform");
        System.out.println("    1) Add Company details");
        System.out.println("    2) Choose Company");
        System.out.println("    3)Get all available companies");
        System.out.println("    4)Back");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        sc.nextLine();
        if(x==1){
            System.out.println("Enter details in order of: name,role,ctc(in lpa), cgpa)");
            String name=sc.next();
            String role=sc.next();
            sc.nextLine();
            int pkg=sc.nextInt();
            double cgpa=sc.nextDouble();
            Company c=new Company(name,role,pkg,cgpa);
            FutureBuilder.com.add(c);
        }
        if(x==2){
            System.out.println("Enter name of company you want to enter as");
            sc=new Scanner(System.in);
            String f=sc.next();
            Company c=com.get(0);
            for(int i=0;i<com.size();i++){
                c=com.get(i);
                if(c.getName().equals(f)){
                    break;
                }
            }
            Company.main(c,p);
        }
        if(x==3){
            System.out.println("These are the names of all available companies");
            for(int i=0;i<p.getCompanyList().size();i++){
                Company c=p.getCompanyList().get(i);
                System.out.println(c.getName());
            }
        }
        if(x==4){
            EnterMode();
        }
        if(x!=4){
            CompanyMode(null);
        }

    }

    public static void StudentMode(String[] args){
        System.out.println("Choose the student query to perform");
        System.out.println("    1) Enter as a student(Give student name,and Roll no)");
        System.out.println("    2) Add students");
        System.out.println("    3) Back");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        if (x == 1) {
            Scanner s=new Scanner(System.in);
            String name=s.nextLine();
            s=new Scanner(System.in);
            Student S=null;
            int d=s.nextInt();
            for(int i=0;i<stu.size();i++){
                S=stu.get(i);
                if(S.getName().equals(name) && S.getRollno()==d){
                    break;
                }
            }
            Student.main(S,p);
        }
        else if(x==2){
            System.out.println("Number of students");
            sc=new Scanner(System.in);
            int d=sc.nextInt();
            System.out.println("Enter in order of name,roll number,cgpa,branch");
            for(int i=0;i<d;i++){
                sc=new Scanner(System.in);
                String name=sc.nextLine();
                sc=new Scanner(System.in);
                int rollno=sc.nextInt();
                sc=new Scanner(System.in);
                double xx=sc.nextDouble();
                sc=new Scanner(System.in);
                String b=sc.next();
                Student ss=new Student(name,rollno,xx,b);
                stu.add(ss);
                System.out.println();
            }
        }
        else if(x==3){
            EnterMode();
        }
        if(x!=3)
            StudentMode(null);
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.println("Welcome to Future Builder");
        System.out.println("1 ) Enter the application ");
        System.out.println("2 ) Exit the application");
        int x=s.nextInt();
        if(x==2){
            System.out.println("Thank you for using Future Builder");
            System.exit(0);
        }
        else if(x==1){
            EnterMode();
        }



    }
}
