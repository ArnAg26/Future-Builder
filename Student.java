import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Student {
    private ArrayList<Company> companiesregisteredfor;
    private final String name;
    private final int rollno;
    protected double cgpa;
    private final String branch;
    private String status;
    private boolean placed;
    private ArrayList<Company> Offers;
    private int currentctc;
    private LocalDateTime registration_date;

    private Company currentCompany;

    public ArrayList<Company> getOffers(){
        return this.Offers;
    }
    public boolean getPlaced(){
        return this.placed;
    }
    public String getStatus(){
        return this.status;
    }
    public void addOffer(Company c){
        this.Offers.add(c);
    }
    public void setStatus(String d){
        this.status=d;
    }
    public ArrayList<Company> getCompaniesregisteredfor(){
        return this.companiesregisteredfor;
    }
    public Student(String name,int rollno,double cgpa,String branch){
        this.name=name;
        this.rollno=rollno;
        this.branch=branch;
        this.cgpa=cgpa;
        this.placed=false;
        this.status="not applied";
        this.currentctc=0;
        Offers=new ArrayList<Company>();
        companiesregisteredfor=new ArrayList<>();
    }
    public String getName(){
        return this.name;
    }
    public int getCurrentctc(){
        return currentctc;
    }
    public int getRollno(){
        return this.rollno;
    }

    private Company getHighestOffer(){
        int max=0;
        Company curr= null;
        for(int i=0;i<Offers.size();i++){
            if(Offers.get(i).getCtc()>max){
                max=Offers.get(i).getCtc();
                curr=Offers.get(i);
            }

        }
        return curr;
    }


    private void RegisterForCompany(Company A){

        if((placed==false && this.cgpa>=A.getCgpa())|| (placed==true && this.cgpa>=A.getCgpa() && 3*this.currentctc<=A.getCtc())){
            this.status="applied";
            A.addStudent(this);
            companiesregisteredfor.add(A);
            A.selectStudents(this);
            System.out.println(this.name +" has registered for "+A.getName());
        }
        else{
            System.out.println("You don't match the eligibility criteria");
        }
    }

    private void RegisterforDrive(PlacementCell p){
        System.out.println("Enter registration date and time in format dd MM yyyy HH:mm");
        Scanner st=new Scanner(System.in);
        String d=st.nextLine();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        LocalDateTime date=LocalDateTime.parse(d,formatter);
        if(date.compareTo(p.openingtimeforStudents)<0 || date.compareTo(p.closingtimeforStudents)>0){
            System.out.println("Date you entered is invalid. Requesty can't be procesed");
        }
        registration_date=date;
        p.AddStudent(this);
        System.out.println("Registered for drive");
        System.out.println("Ypur details are:");
        System.out.println(this);
    }

    private void getAvailableCompanies(PlacementCell p){
        ArrayList<Company> l=p.getCompanyList();
        for(int i=0;i<l.size();i++){
            Company c=l.get(i);
            if((placed==false && this.cgpa>=c.getCgpa())|| (placed==true && this.cgpa>=c.getCgpa() && 3*this.currentctc<=c.getCtc())){
                System.out.println(c);
                System.out.println();
            }
            else{
                System.out.println(c.getName()+" unavailable");
            }
        }

    }
    private void currentStatus() {
        String st = this.status;
        System.out.println("current status is " +st);
        if (status.equals("offered")) {
            for(int i=0;i<this.Offers.size();i++){
                System.out.println(Offers.get(i));
            }
        }
    }
    private void UpdateCGPA(PlacementCell p,double new_cgpa){
        p.UpdateStudentCGPA(this,new_cgpa);
        System.out.println("Request to update cgpa approved");
    }
    private int AcceptOffer(Company c){

            System.out.println(c.getName()+" offer accepted");
            System.out.println("Cogratulations your current ctc is "+c.getCtc());
            status="offered";
            this.currentCompany=c;
            this.currentctc=c.getCtc();
            placed=true;
        return 0;
    }
    private void RejectOffer(Company c){

        System.out.println(c.getName()+" offer rejected");
        Offers.removeIf(t->t.getName().equals(c.getName()));
        if(Offers.size()==0){
            status="blocked";
        }
    }

    public String toString(){
        return "name: "+name+"\nroll number: "+rollno+"\ncgpa: "+cgpa+"\nbranch: "+branch+"\nstatus: "+status;
    }

    public static void main(Student s,PlacementCell p){
        System.out.println("Welcome Student "+s.getName());
        System.out.println("    1)Register for placement Drive");
        System.out.println("    2)Register for Company");
        System.out.println("    3)Get all available companies");
        System.out.println("    4)Get Current Status");
        System.out.println("    5)Update CGPA");
        System.out.println("    6)Accept offer");
        System.out.println("    7)Reject offer");
        System.out.println("    8)Back");
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        if(x==1){
            s.RegisterforDrive(p);
        }
        if(x==2){
            sc=new Scanner(System.in);
            String name=sc.nextLine();
            Company comp=null;
            for(int i=0;i<p.getCompanyList().size();i++){
                comp=p.getCompanyList().get(i);
                if(comp.getName().equals(name)){
                    break;
                }
            }

            s.RegisterForCompany(comp);
        }
        if(x==3){
            s.getAvailableCompanies(p);
        }
        if(x==4){
            s.currentStatus();
        }
        if(x==5){
            sc=new Scanner(System.in);
            double cgpa=sc.nextDouble();
            s.UpdateCGPA(p,cgpa);
        }
        if(x==6){
            System.out.println("You have the following offers with highest ctc");
            Company c=s.getHighestOffer();
            System.out.println(c);
            s.AcceptOffer(c);
        }
        if(x==7){
            System.out.println("You have following offers with highest package");
            Company c=s.getHighestOffer();
            System.out.println(c);
            s.RejectOffer(c);
        }
        if(x==8){
            System.out.println("thank you "+s.getName());
            FutureBuilder.StudentMode(null);
        }
        if(x!=8){
            Student.main(s,p);
        }



    }




}
