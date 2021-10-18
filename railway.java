import java.util.*;
class railway
{
	Scanner r=new Scanner(System.in);
	public ClientSock socket = null;

	
	railway(ClientSock clientSock)
	{
		this.socket = clientSock;
	}

	void main_menu() throws Exception
	{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~WELCOME TO MAIN MENU~~~~~~~~~~~~~~~~~~~~~~~~~\nPress 1 for Admin Mode\nPress 2 for User Mode\nPress 3 to Exit");
		int ch=r.nextInt();
		switch(ch)
		{
			case 1: admin_log();
				break;
			case 2: u_user_login();
				break;
			default:
				System.out.println("**************************************************************************************************************");
				System.out.println("Thanks for visiting , Made By : ");
				System.out.println("  /$$$$$$$$                                        /$$");
				System.out.println(" |__  $$__/                                      /$$$$");
				System.out.println("\t| $$  /$$$$$$   /$$$$$$  /$$$$$$/$$$$       |_  $$");
				System.out.println("\t| $$ /$$__  $$ |____  $$| $$_  $$_  $$        | $$");
				System.out.println("\t| $$| $$$$$$$$  /$$$$$$$| $$ \\ $$ \\ $$        | $$");
				System.out.println("\t| $$| $$_____/ /$$__  $$| $$ | $$ | $$        | $$");
				System.out.println("\t| $$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$       /$$$$$$");
				System.out.println("\t|__/ \\_______/ \\_______/|__/ |__/ |__/      |______/");
				System.out.println("**************************************************************************************************************");
				break;
		
		}
			
	}

	
	void admin_log() throws Exception 
	{
		admin ad=new admin(socket);
		if(ad.admin_login()==1)
		{
			admin_mode();
		}
		else
		{
			System.out.println("Your password is incorrect , please enter correct password");
			main_menu();
		}
	}
	

	void admin_mode() throws Exception
	{
		admin ad=new admin(socket);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~ WELCOME TO ADMIN MENU~~~~~~~~~~~~~~~~~~~~~~~~~\nPress 1 to Add new train to database\nPress 2 to Change details of existing train\nPress 3 to Display all the train in database\nPress 4 to Display Chart of a train\nPress 5 to Display all registered users\nPress 6 to Update train date\nPress 7 to Return to main menu \nPress 8 to Exit");
		int ch=r.nextInt();
		switch(ch)
		{
			case 1:ad.cr_train_info();
				break;
			case 2: ad.train_info();
				break;
			case 3: ad.dis_train_db();
				break;
			case 4: ad.disp_chart();
				break;
			case 5: ad.disp_user();
				break;
			case 6:ad.train_update_date();
				break;		
			case 7:main_menu();
				break;	
			default:
				System.out.println("**************************************************************************************************************");
				System.out.println("Thanks for visiting , Made By : ");
				System.out.println("  /$$$$$$$$                                        /$$");
				System.out.println(" |__  $$__/                                      /$$$$");
				System.out.println("\t| $$  /$$$$$$   /$$$$$$  /$$$$$$/$$$$       |_  $$");
				System.out.println("\t| $$ /$$__  $$ |____  $$| $$_  $$_  $$        | $$");
				System.out.println("\t| $$| $$$$$$$$  /$$$$$$$| $$ \\ $$ \\ $$        | $$");
				System.out.println("\t| $$| $$_____/ /$$__  $$| $$ | $$ | $$        | $$");
				System.out.println("\t| $$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$       /$$$$$$");
				System.out.println("\t|__/ \\_______/ \\_______/|__/ |__/ |__/      |______/");
				System.out.println("**************************************************************************************************************");
				break;
		}
		
	}


	void user_log() throws Exception 
	{
		admin ad=new admin(socket);
		if(ad.user_login()==1)
		{
			user_mode();
		}
		else
		{
			System.out.println("Incorrect Credentials !");
			u_user_login();
		}
	}


	void u_user_login() throws Exception
	{
		admin ad=new admin(socket);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~WELCOME TO USER MENU~~~~~~~~~~~~~~~~~~~~~~~~~\nPress 1 to Login\nPress 2 to Sign Up\nPress 3 to Return to main menu\nPress 4 to Exit");
		int ch=r.nextInt();
		switch(ch)
		{
			case 1:
				user_log();
				break;
			case 2:
				ad.user_signup();
				break;
			case 3:
				main_menu();
				break;
			default:
				System.out.println("**************************************************************************************************************");
				System.out.println("Thanks for visiting , Made By : ");
				System.out.println("  /$$$$$$$$                                        /$$");
				System.out.println(" |__  $$__/                                      /$$$$");
				System.out.println("\t| $$  /$$$$$$   /$$$$$$  /$$$$$$/$$$$       |_  $$");
				System.out.println("\t| $$ /$$__  $$ |____  $$| $$_  $$_  $$        | $$");
				System.out.println("\t| $$| $$$$$$$$  /$$$$$$$| $$ \\ $$ \\ $$        | $$");
				System.out.println("\t| $$| $$_____/ /$$__  $$| $$ | $$ | $$        | $$");
				System.out.println("\t| $$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$       /$$$$$$");
				System.out.println("\t|__/ \\_______/ \\_______/|__/ |__/ |__/      |______/");
				System.out.println("**************************************************************************************************************");
				break;
		}
	}


	void user_mode() throws Exception
	{
		user us=new user(this.socket);
		
		System.out.println("Press 1 to Book a ticket\nPress 2 to Cancel a ticket\nPress 3 to Enquire about available train\nPress 4 to Return to main menu\nPress 5 to Exit");
		int ch=r.nextInt();
		switch(ch)
		{
			case 1: us.inputreserve();
				break;
			case 2: us.cancel1();
				break;
			case 3: us.enquiry1();
				break;
			case 4: main_menu();
				break;
			default:
				System.out.println("**************************************************************************************************************");
				System.out.println("Thanks for visiting , Made By : ");
				System.out.println("  /$$$$$$$$                                        /$$");
				System.out.println(" |__  $$__/                                      /$$$$");
				System.out.println("\t| $$  /$$$$$$   /$$$$$$  /$$$$$$/$$$$       |_  $$");
				System.out.println("\t| $$ /$$__  $$ |____  $$| $$_  $$_  $$        | $$");
				System.out.println("\t| $$| $$$$$$$$  /$$$$$$$| $$ \\ $$ \\ $$        | $$");
				System.out.println("\t| $$| $$_____/ /$$__  $$| $$ | $$ | $$        | $$");
				System.out.println("\t| $$|  $$$$$$$|  $$$$$$$| $$ | $$ | $$       /$$$$$$");
				System.out.println("\t|__/ \\_______/ \\_______/|__/ |__/ |__/      |______/");
				System.out.println("**************************************************************************************************************");
				break;
		}
	}

		
	public void run() throws Exception
	{
		System.out.println("**************************************************************************************************************");
		System.out.println("                          WELCOME TO TEAM-1's TRAIN TICKET BOOKING SYSTEM                                     ");
		System.out.println();
		System.out.println("	    oooOOOOOOOOOOO");
		System.out.println("	  o   ____         :::::::::::::::::: :::::::::::::::::: __|----- |__");
		System.out.println("	  Y_,_|[]|--++++++ |[][][][][][][][]| |[][][][][][][][]| |  [] []  |");
		System.out.println("   {(|_|_|__|;|______|;|________________|;|________________|;|_________|;");
		System.out.println("     /oo--OO   oo  oo   oo oo      oo oo   oo oo      oo oo   oo     oo");
		System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
		System.out.println();
		System.out.println("**************************************************************************************************************");
		this.main_menu();
	}
}
