import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Company {
    private String name;
    private String role;
    private int ctc;
    private double cgpa;
    private LocalDateTime registrationdate;
    private ArrayList<Student> registered_Students;

    private ArrayList<Student> selected_Students;

    public String getName(){
        return this.name;
    }

    public double getCgpa(){
        return this.cgpa;
    }
    public int getCtc(){
        return this.ctc;
    }

    public Company(String name,String role,int pack,double cgpa_criteria){
        this.name=name;
        this.role=role;
        this.ctc=pack;
        this.cgpa=cgpa_criteria;
        registered_Students=new ArrayList<Student>();
        selected_Students=new ArrayList<Student>();


    }

    public void addStudent(Student A){
        registered_Students.add(A);

    }

    private void registerCompany(PlacementCell p){
        System.out.println("Enter registration date and time in format dd MM yyyy HH:mm");
        Scanner st=new Scanner(System.in);
        String d=st.nextLine();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        LocalDateTime date=LocalDateTime.parse(d,formatter);
        if(date.compareTo(p.openingtimeforComapnis) <0|| date.compareTo(p.closingtimeforComapnis)>0){
                System.out.println("The date you entered is invalid. Request can't be processed");
                return;
        }
        registrationdate=date;
        p.AddCompany(this);
    }
    public String toString(){
        return "name: "+name +"\nrole: "+role+"\nctc being offered: "+ctc+"\ncgpa criteria: "+cgpa+" ";
    }

    private void UpdateCGPA(double new_cgpa){
        cgpa=new_cgpa;
    }

    private void UpdatePackage(int pkg){
        ctc=pkg;
    }
    private void UpdateRole(String new_role){
        role=new_role;
    }

    public void selectStudents(Student S){
        Random random =new Random();

        //for(int i=0;i<registered_Students.size();i++){
            int d=random.nextInt(2);
            //System.out.println(d);
            if(selected_Students.size()==0){
                d=0;
            }
            if(d==0) {
                S.setStatus("offered");
                S.addOffer(this);
                this.selected_Students.add(S);
            }
            else if(d==1 && S.getOffers().equals("not applied")){
                S.setStatus("not offered");
            }

        }
    //}

    private void selectStudent(Student s){
        this.selected_Students.add(s);
    }
    public ArrayList<Student> getSelectedStudents(){
        return selected_Students;
    }

    public static void main(Company c,PlacementCell p){
        System.out.println("Welcome " + c.getName());
        System.out.println("    1) Update role");
        System.out.println("    2) Update Package");
        System.out.println("    3) Update CGPA criteria");
        System.out.println("    4) Register to Interview Drive");
        System.out.println("    5) Back");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        sc.nextLine();
        if(x==1){
            System.out.println("Enter new role");
            String s=sc.next();
            c.UpdateRole(s);
        }
        if(x==2){
            System.out.println("Enter new ctc");
            int f=sc.nextInt();
            c.UpdatePackage(f);
        }
        if(x==3){
            System.out.println("Enter new cgpa criteria");
            double xx=sc.nextDouble();
            c.UpdateCGPA(xx);
        }
        if(x==4){
            c.registerCompany(p);
            System.out.println("Registered");
        }
        if(x==5){
            FutureBuilder.CompanyMode(null);
        }
        if(x!=5){
            Company.main(c,p);
        }

    }









}
