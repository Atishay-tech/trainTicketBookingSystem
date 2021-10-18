import java.util.*;  
import java.sql.*;
import java.text.*;


class admin
{
	railway r=null;
	Scanner i=new Scanner(System.in);
	ClientSock socket = null;

	admin(ClientSock clientSock)
	{
		this.socket = clientSock;
		r = new railway(clientSock);
	}

	void train_info()
	{
		int num,seats;
		String name,bp,dp;
		int[] f=new int[4];
		
		
		System.out.print("Enter 4-digit train number : ");
		num=i.nextInt();
		if(num<1000 || num>9999)
		{
			System.out.println("Enter correct train number of 4 digits only!!!");
		}
		else
		{	
			System.out.print("Enter Train name for Train number "+num+" : ");
			name=i.next();
			java.sql.Date date=	null;
			System.out.print("Enter date of train's journey in (yyyy-mm-dd) format : ");
			String dt=i.next();
			try
			{
				java.util.Date dt1=new SimpleDateFormat("yyyy-MM-dd").parse(dt);
				date=new java.sql.Date(dt1.getTime());
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			
			System.out.print("Enter Boarding Point : "); 
			bp=i.next();
			System.out.print("Enter Destination Point : ");
			dp=i.next();
			
			System.out.print("Enter Departure time of train's journey in (hh:mm:ss) format : "); 
			String dtime=i.next();
			
			System.out.print("Enter arrival time of train's journey in (hh:mm:ss) format : "); 
			String atime=i.next();
			
			
			System.out.print("Enter the total number of seats in the train : ");
			seats=i.nextInt();
		
		
			System.out.println("Enter price for each ticket type : ");
			System.out.print("First AC ticket fare: ");
			f[0]=i.nextInt();
			System.out.print("Second AC ticket fare: ");
			f[1]=i.nextInt();
			System.out.print("Third AC ticket fare: ");
			f[2]=i.nextInt();
			System.out.print("Sleeper Class ticket fare: ");
			f[3]=i.nextInt();
			train_db(num,name,seats,bp,dp,f[0],f[1],f[2],f[3],date,dtime,atime);
		}
	}
	
	void user_signup() throws Exception
	{

		String uname;
		String password;
		String gen;
		int age;
		System.out.print("Enter Username : ");
		uname=i.next();

		String results = socket.get_response("select uname from user where uname='"+uname+"' ");

		if(results.isEmpty())
		{
			System.out.print("Please Enter Your Password : ");
			password=i.next();
			System.out.print("Please Enter Your Age : ");
			age=i.nextInt();
			System.out.print("Enter Gender(M/F) : ");
			gen=i.next();
			user_db(uname,password,age,gen);
		}
		else {
			System.out.println("Username already exist");
			return;
		}

		System.out.print("Dear user to continue(y) or return to main menu (n) respectively  : ");
		String ch=i.next();

		if(ch.equals("y"))
		{
			r.user_log();
		}
		else
		{
			r.main_menu();
		}
	}
	
	int user_login() throws Exception
	{
		
		String uname;
		String password;

		System.out.print("Enter Username : ");
		uname=i.next();
		System.out.print("Please Enter Your Password : ");
		password=i.next();

		String results = socket.get_response("select uname,pass from user where uname='"+uname+"' and pass='"+password+"'");

		if (!results.isEmpty()) {
			return 1;
		}

		return 0;
	}


	int admin_login() throws Exception
	{
		String uname;
		String password;

		System.out.print("Enter Username : ");
		uname=i.next();
		System.out.print("Please Enter Your Password : ");
		password=i.next();

		String results = socket.get_response("select uname,pass from admin where uname='"+uname+"' and pass='"+password+"'");

		if (!results.isEmpty()) {
			return 1;
		}

		return 0;
	}
		
	void train_db(int tnum,String tname,int seats,String bp,String dp,int i,int j,int k,int l,java.sql.Date date,String dtime,String atime)
	{	
		try
		{
			Connection c=Server.connect_db();
			PreparedStatement st=c.prepareStatement("insert into train (tnum,tname,seats,bp,dp,fAC,sAC,tAC,sc,doj,dtime,atime) values(?,?,?,?,?,?,?,?,?,?,?,?)");

			st.setInt(1,tnum);
			st.setString(2,tname);
			st.setInt(3,seats);
			st.setString(4,bp);
			st.setString(5,dp);	
			st.setInt(6,i);
			st.setInt(7,j);
			st.setInt(8,k);
			st.setInt(9,l);
			st.setDate(10,date);
			st.setString(11,dtime);
			st.setString(12,atime);			
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	void user_db(String uname,String pass,int age,String g)
	{
		try
		{
			Connection c=Server.connect_db();
			java.util.Date date=new java.util.Date();
			java.sql.Timestamp sqt=new java.sql.Timestamp(date.getTime());
			PreparedStatement st=c.prepareStatement("insert into user(uname,pass,age,g,timestamp) values(?,?,?,?,?)");

			st.setString(1,uname);
			st.setString(2,pass);
			st.setInt(3,age);
			st.setString(4,g);
			st.setTimestamp(5,sqt);				
			st.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void cr_train_info() throws Exception
	{	
		String ch="y";
		while(ch=="y")
		{
			train_info();
			System.out.print("Do you want to continue (y/n) : ");
			ch=i.next();
		}
		r.admin_mode();
	}
	
	
	void train_update_date() throws Exception
	{
		try
		{
			int tnum,d;

			System.out.print("Enter train Number whose date you want to update : ");
			tnum=i.nextInt();
			System.out.print("Enter number of days to be incremented : ");
			d=i.nextInt();	
		    socket.get_response("update train set doj = DATE_ADD(doj, INTERVAL '"+d+"' DAY) where tnum='"+tnum+"'");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		System.out.print("Dear user to continue(y) or return to main menu (n) respectively  : ");
		String ch=i.next();
		
		if(ch.equals("y")) {
			r.admin_mode();
		}
		else {
			r.main_menu();
		}
		
	}			


	void setwR(int tnum, String str1, int seats,String str10,String str11, int fAc,int sAc,int tAc,int sc,java.util.Date doj, String str7,String str9, int width)
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


	void dis_train_db()
	{
		disp_train_db();
		try
		{
			System.out.print("Dear user to continue(y) or return to main menu (n) respectively  : ");
			String ch=i.next();
			if(ch.equals("y"))
			{
				r.admin_mode();
			}
			else
			{
				r.main_menu();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	void disp_train_db()
	{
		try
		{
			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
			System.out.println("Train Number   Train Name     Seats          Boarding       Destination    First AC       Second AC      Third AC       Sleeper Coach  Journey date   Departure      Arrival");
			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");


			String results = socket.get_response("select * from train");
        	String[] trains = results.split("\n");

			for (int i=0; i<trains.length; i++)
			{
				String[] cols = trains[i].split(";");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				// Setting Window
				setwR(Integer.parseInt(cols[0]), cols[1], Integer.parseInt(cols[2]), cols[3], cols[4], 
						Integer.parseInt(cols[5]), Integer.parseInt(cols[6]), Integer.parseInt(cols[7]),
						Integer.parseInt(cols[0]), formatter.parse(cols[9]), cols[10], cols[11], 15);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}


	void setwR(long pnr, String str1,int age,String str3, int seats,String str5,String str6,Timestamp tm,java.util.Date dot, int width)
	{
		String str=Long.toString(pnr);
		System.out.print(str);
		for (int x = str.length(); x < width; ++x) 
		System.out.print(' ');	
		System.out.print(str1);		
		for (int x = str1.length(); x < width; ++x) 
		System.out.print(' ');	
		String str2=Integer.toString(age);
		System.out.print(str2);
		for (int x = str2.length(); x < width; ++x) 
		System.out.print(' ');	
		System.out.print(str3);
		for (int x = str3.length(); x < width; ++x) 
		System.out.print(' ');	
		String str4=Integer.toString(seats);
		System.out.print(str4);		
		for (int x = str4.length(); x < width; ++x) 
		System.out.print(' ');		
		System.out.print(str5);
		for (int x = str5.length(); x < width; ++x) 
		System.out.print(' ');		
		System.out.print(str6);
		for (int x = str6.length(); x < width; ++x) 
		System.out.print(' ');
		
		java.util.Date d=new java.util.Date();
		d.setTime(tm.getTime());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String str7 = df.format(d);
		
		
		System.out.print(str7);
		for (int x = str7.length(); x < width; ++x) 
		System.out.print(' ');		
		
		DateFormat dF = new SimpleDateFormat("dd/MM/yyyy");
		String str8 = dF.format(dot);
		System.out.println(str8);
	}


	void disp_chart() throws Exception
	{
		try
		{
			System.out.print("Enter the train number ");
			int tn=i.nextInt();	

			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
			System.out.println("PNR Number          Name                Age                 Gender              Seat Number         Coach               Status              Booking time        Date of travelling");
			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");


			String results = socket.get_response("select * from chart where pnr>=21215 and tnum='"+tn+"' ");
			String[] tickets = results.split("\n");

			for (int i=0; i<tickets.length; i++)
			{
				String[] cols = tickets[i].split(";");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    			java.util.Date parsedDate = dateFormat.parse(cols[7]);
    			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

				// Setting Window
				setwR(Integer.parseInt(cols[0]), cols[1], Integer.parseInt(cols[2]), cols[3], Integer.parseInt(cols[4]),
						cols[5], cols[6], timestamp, formatter.parse(cols[8]), 20);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		System.out.print("Dear user to continue(y) or return to main menu (n) respectively  : ");
		String ch=i.next();

		if(ch.equals("y")) {
			r.admin_mode();
		}
		else {
				r.main_menu();
		}
	}


	void setwR(String str,int age,String str3,Timestamp tm,int width)
	{
		System.out.print(str);
		for (int x = str.length(); x < width; ++x) 
		System.out.print(' ');		
		String str2=Integer.toString(age);
		System.out.print(str2);
		for (int x = str2.length(); x < width; ++x) 
		System.out.print(' ');	
		System.out.print(str3);
		for (int x = str3.length(); x < width; ++x) 
		System.out.print(' ');	
		java.util.Date d=new java.util.Date();
		d.setTime(tm.getTime());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String str7 = df.format(d);
		System.out.println(str7);

	}

	void disp_user() throws Exception
	{
		try
		{
			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
			System.out.println("Username                 Age                      Gender                   Booking time");
			System.out.println("```````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````");
			String response = socket.get_response("select * from user");
			String[] rows = response.split("\n");

			for (int i=0; i < rows.length; i++) {
				String[] cols = rows[i].split(";");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				java.util.Date parsedDate = dateFormat.parse(cols[4]);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

				setwR(cols[0], Integer.parseInt(cols[2]), cols[3], timestamp, 25);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		System.out.print("Dear user to continue(y) or return to main menu (n) respectively  : ");
		String ch=i.next();

		if(ch.equals("y")) {
			r.admin_mode();
		}
		else {
			r.main_menu();
		}
		
	}	
	
}