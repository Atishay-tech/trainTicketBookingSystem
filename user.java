import java.util.*;
import java.sql.*;
import java.text.*;


public class user
{
    admin ad=null;
    railway r=null;
    String tname,bp,dp;
    int tnum,seat,catnum,ch;
    String[] pname=new String[1000];
    int[] page=new int[1000];
    String[] pgen=new String[1000];
    public ClientSock socket = null;

    
    Scanner a=new Scanner(System.in);
    
    user(ClientSock clientSock)
    {
        this.socket = clientSock;
        this.ad = new admin(socket);
        this.r = new railway(socket);
    }

    int check1(int tnum) throws Exception
    {
        String message = "select tnum from train where tnum='"+String.valueOf(tnum)+"'";
        String response = socket.get_response(message);

        if (!response.isEmpty())
            return 1;
        else
            return 0;
    }
            
    void inputreserve() throws Exception
    {
        System.out.print("Enter train number : ");
        tnum=a.nextInt();


        int var;
        var=check1(tnum);

        if(var==0)
        {
            System.out.println("Train number doesn't exist");
        }

        System.out.print("Enter boarding point : ");
        bp=a.next();
        
        System.out.print("Enter destination point : ");
        dp=a.next();
        
        System.out.print("Number of seats required : ");
        seat=a.nextInt();
        java.sql.Date dt2=null;
        System.out.print("Enter date of train's journey in (yyyy-mm-dd) format : ");
        String dt=a.next();
        java.util.Date dt1=new SimpleDateFormat("yyyy-MM-dd").parse(dt);
        dt2=new java.sql.Date(dt1.getTime());
            
        int j=0;
        int k=0;
        for(int i=0;i<seat;i++)
        {			
            System.out.print("Enter "+(i+1)+" passenger's name : ");
            pname[i]=a.next();
                        
            System.out.print("Enter "+(i+1)+" passenger's age : ");
            page[i]=a.nextInt();
            if((page[i]>0 &&page[i]<=12)||(page[i]>=60&&page[i]<120))
            k++;
            if(page[i]<0||page[i]>120)
            {
                j=1;
                System.out.println("Please enter a valid age");
            }
            System.out.print("Enter "+(i+1)+" passenger's gender : ");
            pgen[i]=a.next();
        }
        if(j==1)
        return;
        
        System.out.println("Enter the class : ");
        System.out.println("Press 1 - First AC");
        System.out.println("Press 2 - Second AC");
        System.out.println("Press 3 - Third AC");
        System.out.println("Press 4 - Sleeper coach");
        ch=a.nextInt();
        if((ch!=1)&&(ch!=2)&&(ch!=3)&&(ch!=4))
        System.out.println("Choose from seat class only");
        
        else		
        {
            String coach;
            if(ch==1)  coach="First AC";
            else if(ch==2)  coach="Second AC";
            else if(ch==3)  coach="Third AC";
            else  coach="Sleeper Coach";
            
            System.out.println("Enter the concession category : ");
            System.out.println("Press 1. Military Personnel , Senior Citizen , Government Employee (35% concession)");
            System.out.println("Press 2. None");
            catnum=a.nextInt();
            if(catnum!=2&&catnum!=1)
            {
                System.out.println("Choose from above options only");
                r.user_mode();
            }				
            System.out.print("Confirm changes (y/n) ");
            String conf=a.next();
            
            if(conf=="n")
            {
                System.out.println("Your ticket is not booked");
                r.user_mode();
            }
            int fare=reserve(tnum,tname,bp,dp,seat,ch);
            if(fare==0)
            {
                System.out.println("Train number doesn't exist");
                r.user_mode();
            }
            if(catnum==1)
            {				
                System.out.println("Enter how many concession availing passengers are travelling and make sure to carry your ID ");
                int mil=a.nextInt();
                System.out.println("Amount to be paid is "+(fare-((mil*(fare/seat))*0.35)));					
            }
            else if(catnum==2)
            System.out.println("Amount to be paid is " + fare);
            chart(pname,page,coach,tnum,dt2);
        }
    }

    // Reserve will book the tickets and show calculated fare
    int reserve(int tnum,String tname,String bp,String dp,int seat,int ch) 
    { 
        int flag=0;
        int fare=0;

        String results = socket.get_response("select * from train");
        String[] trains = results.split("\n");

        for (int i=0; i<trains.length; i++)
        {
            String[] cols = trains[i].split(";");
            if(tnum == Integer.valueOf(cols[0]))
            {
                flag = 1;	
                if(ch == 1)
                fare = seat*Integer.valueOf(cols[5]);
                else if(ch == 2)
                fare = seat*Integer.valueOf(cols[6]);
                else if(ch == 3)	
                fare = seat*Integer.valueOf(cols[7]);
                else			
                fare = seat*Integer.valueOf(cols[8]);
                break;
            }
        }					
        if(flag!=0)
        {
            String temp = socket.get_response("update train set seats=seats-'"+seat+"' where tnum='"+tnum+"' ");
        }

        if(flag==0)
            return 0;
        else
            return fare;
    }

    void tckt1() throws Exception
    {
        System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
        String ch=a.next();
        if(ch.equals("y"))
        {
            r.user_mode();
        }
        else
        {
            r.main_menu();
        }
    }
    
    // The ticket booked recently will be printed from chart
    void tckt() 
    {
        String r1 = socket.get_response("select * from chart order by sno desc limit 1");
        String[] entries = r1.split(";");
        String r2 = socket.get_response("select * from chart where pnr='"+entries[0]+"'");
        String[] rows = r2.split("\n");


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("PNR Number : "+entries[0]+"                        "+"Coach : "+entries[5]);

        for (int i=0; i<rows.length; i++) {
            entries = rows[i].split(";");
            System.out.println("Name : "+entries[1]+"             "+"Age : "+entries[2]+"     "+"Gender : "+entries[3]);
            System.out.println("Status : "+entries[6]+"                           "+"Seat Number : "+entries[4]);
        }

        System.out.println("Date of Travelling : "+entries[8]+"                    "+"Booked on : "+entries[7]);	
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    void chart(String pname[],int page[],String coach,int tnum,java.sql.Date dt2) throws Exception
    {
        java.util.Date date=new java.util.Date();
        java.sql.Timestamp sqt=new java.sql.Timestamp(date.getTime());
        
        Connection c=Server.connect_db();
        Statement s2=c.createStatement();
        Statement s1=c.createStatement();
        Statement s3=c.createStatement();

        ResultSet r3=s3.executeQuery("select * from chart order by sno desc limit 1");

        r3.next();
        try {
            for(int i=0;i<seat;i++)
            {	
                ResultSet r2=s2.executeQuery("select * from chart order by sno desc limit 1");
                r2.next();
                ResultSet r1=s1.executeQuery("select * from train where tnum='"+tnum+"' and doj='"+dt2+"' ");
                r1.next();
                PreparedStatement st=c.prepareStatement("insert into chart (pnr,name,age,gender,seatno,coach,status,timestamp,dot,tnum) values(?,?,?,?,?,?,?,?,?,?)");
                st.setLong(1,r3.getLong(1)+1);
                st.setString(2,pname[i]);
                st.setInt(3,page[i]);
                st.setString(4,pgen[i]);				
                if(r1.getInt(3)>0)			st.setInt(5,r2.getInt(5)+1);
                else						st.setInt(5,0);
                st.setString(6,coach);
                if(r1.getInt(3)>0)			st.setString(7,"confirmed");
                else						st.setString(7,"waiting");
                st.setTimestamp(8,sqt);
                st.setDate(9,r1.getDate(10));
                st.setInt(10,tnum);				
                st.executeUpdate();
                System.out.println("Congrats!!!! Your ticket is booked. Have a nice day!!");
            }

            tckt();
            tckt1();
        }
        catch (Exception e) {
            System.out.println("Ticket Not Booked!!");
            tckt1();   
        }
    }
    void cancel1() throws Exception
    {
        cancel();

        System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
        String ch=a.next();
        if(ch.equals("y"))
        {
            r.user_mode();
        }
        else
        {
            r.main_menu();
        }
    }
        
    
    void cancel() throws Exception
    {
        long pnr;
        String j="cancel";

        System.out.print("Enter PNR Number  ");
        pnr=a.nextLong();

        String results = socket.get_response("select * from chart where pnr='"+pnr+"' ");

        if(!results.isEmpty())
        {

            socket.get_response("update chart set status='"+j+"' where pnr='"+pnr+"'");
        }
        else
        {	
            System.out.println("PNR number does not exist ");
        }
        
    }

    void setw(int tnum, String str1, int seats,String str10,String str11, int fAc,int sAc,int tAc,int sc,java.util.Date doj, String str7,String str9, int width)
    {
        String str=Integer.toString(tnum);
        System.out.print(str);
        for (int x = str.length(); x < width; ++x) 
        System.out.print(' ');	
        System.out.print(str1);		
        for (int x = str1.length(); x < width; ++x) 
        System.out.print(' ');		
        String str8=Integer.toString(seats);
        System.out.print(str8);		
        for (int x = str8.length(); x < width; ++x) 
        System.out.print(' ');		
        System.out.print(str10);		
        for (int x = str10.length(); x < width; ++x) 
        System.out.print(' ');
        System.out.print(str11);		
        for (int x = str11.length(); x < width; ++x) 
        System.out.print(' ');		
        String str2=Integer.toString(fAc);
        System.out.print(str2);
        for (int x = str2.length(); x < width; ++x) 
        System.out.print(' ');	
        String str3=Integer.toString(sAc);
        System.out.print(str3);
        for (int x = str3.length(); x < width; ++x) 
        System.out.print(' ');	
        String str4=Integer.toString(tAc);
        System.out.print(str4);
        for (int x = str4.length(); x < width; ++x) 
        System.out.print(' ');	
        String str5=Integer.toString(sc);
        System.out.print(str5);
        for (int x = str5.length(); x < width; ++x) 
        System.out.print(' ');

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String str6 = df.format(doj);
        System.out.print(str6);
        for (int x = str6.length(); x < width; ++x) 
        System.out.print(' ');
        System.out.print(str7);
        for (int x = str7.length(); x < width; ++x) 
        System.out.print(' ');
        System.out.println(str9);
    }
    void enquiry1() throws Exception
    {

        enquiry();
        System.out.print("Do you want to continue or return to main menu (y/n) respectively  : ");
        String ch=a.next();
        if(ch.equals("y"))
        {
            r.user_mode();
        }
        else
        {
            r.main_menu();
        }
    }
    
    void enquiry() throws Exception
    {
        System.out.print("From ");
        String from=a.next();	
        System.out.print("To ");
        String to=a.next();	

        System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
        System.out.println("Train Number   Train Name     Seats          Boarding       Destination    First AC       Second AC      Third AC       Sleeper Coach  Journey date   Departure      Arrival");
        System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");

        String results = socket.get_response("select * from train where bp='"+from+"' and dp='"+to+"' ");
        String[] trains = results.split("\n");

        for (int i=0; i<trains.length; i++)
        {
            String[] cols = trains[i].split(";");
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            // Setting window to display train details during enquiry
            setw(Integer.parseInt(cols[0]), cols[1], Integer.parseInt(cols[2]), cols[3], cols[4], 
                    Integer.parseInt(cols[5]), Integer.parseInt(cols[6]), Integer.parseInt(cols[7]),
                    Integer.parseInt(cols[0]), formatter.parse(cols[9]), cols[10], cols[11], 15);
        }
    }
}
