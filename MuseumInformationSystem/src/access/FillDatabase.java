package access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import access.MuseumItemDAO;
import model.Exhibit;
import model.MuseumItem;
import model.User;

public class FillDatabase {
	public static int[] location = { -1, -1 };

	public static void fillAll() {

		dropTables();
		createTables();
		fillUsers();
		fillMuseumItem();
		fillExhibitList();
		fillExhibits();
		fillFloorPlan();

	}

	public static void fillUsers() {
		ArrayList<User> userlist = new ArrayList<User>();
		User admin = new User("Admin","AdminPassword", 0);
		User guest = new User("Guest","GuestPassword", 1);
		userlist.add(admin);
		userlist.add(guest);
		
		UserDAO.update(userlist);
		
	}
	
	public static void fillExhibitList() {
		List<Exhibit> exhibitlist = new ArrayList<Exhibit>();

		Exhibit exhibit1 = new Exhibit("Generation1",
				"Exhibit filled with Generation1 museum items", location);
		Exhibit exhibit2 = new Exhibit("PreComputing",
				"Exhibit filled with Pre-Computing museum items", location);
		Exhibit exhibit3 = new Exhibit("Generation2",
				"Exhibit filled with Generation2 museum items", location);

		exhibitlist.add(exhibit1);
		exhibitlist.add(exhibit2);
		exhibitlist.add(exhibit3);

		ExhibitDAO.updateExhibitTable(exhibitlist);
	}

	public static void fillExhibits() {
		ArrayList itemlist = new ArrayList();

		itemlist.add("Z3");
		itemlist.add("Atanasoff-Berry Computer (ABC)");
		itemlist.add("Harvard Mark I");

		ExhibitDAO.updateItemsInExhibit("Generation1", itemlist);

		itemlist.clear();
		itemlist.add("The Williams Tube");
		ExhibitDAO.updateItemsInExhibit("PreComputing", itemlist);


		itemlist.clear();
		itemlist.add("TX-0");
		ExhibitDAO.updateItemsInExhibit("Generation2", itemlist);
	}


	public static void fillMuseumItem() {
		ArrayList mylist = new ArrayList();

		MuseumItem item1 = new MuseumItem(
				"Z3",
				"First functional program-controlled Turing-complete computer, the Z3, which "
						+ "became operational in May 1941. Thanks to this machine and its predecessors, Konrad Zuse is often regarded"
						+ " as the inventor of the computer. Using 2,300 relays, the Z3 used floating point binary arithmetic and had "
						+ "a 22-bit word length. The original Z3 was destroyed in a bombing raid of Berlin in late 1943. However, Zuse "
						+ "later supervised a reconstruction of the Z3 in the 1960s.",
						"style\\\\Z3.jpg", null, null, location);

		MuseumItem item2 = new MuseumItem(
				"Atanasoff�Berry Computer (ABC)",
				"Built at Iowa State College (now University), "
						+ "the ABC was designed and built by Professor John Vincent Atanasoff and graduate student Cliff Berry "
						+ "between 1939 and 1942. The ABC was at the center of a patent dispute relating to the invention of the "
						+ "computer, which was resolved in 1973 when it was shown that ENIAC co-designer John Mauchly had come to "
						+ "examine the ABC shortly after it became functional. Atanasoff was declared the originator of several "
						+ "basic computer ideas, but the computer as a concept was declared un-patentable and thus was freely open "
						+ "to all. This result has been referred to as the dis-invention of the computer. A full-scale "
						+ "reconstruction of the ABC was completed in 1997 and proved that the ABC machine functioned as Atanasoff "
						+ "had claimed.",
						"style\\\\ABC.jpg", null, null, location);

		MuseumItem item3 = new MuseumItem(
				"Harvard Mark I",
				"H. Aiken and G. Hopper designed the MARK series of computers at "
						+ "Harvard University. The MARK series of computers began with the Mark I in 1944: 55 feet long and 8 feet high. "
						+ "The 5-ton device contained almost 760,000 separate pieces. Used by the US Navy for gunnery and ballistic "
						+ "calculations, the Mark I was in operation until 1959. The computer, controlled by pre-punched paper tape, could "
						+ "carry out addition, subtraction, multiplication, division and reference to previous results. It had special "
						+ "subroutines for logarithms and trigonometric functions and used 23 decimal place numbers. Data was stored and "
						+ "counted mechanically using 3000 decimal storage wheels, 1400 rotary dial switches, and 500 miles of wire. Its "
						+ "electromagnetic relays classified the machine as a relay computer. All output was displayed on an electric "
						+ "typewriter.",
						"style\\\\markI.jpg", null, null, location);

		MuseumItem item4 = new MuseumItem(
				"The Williams Tube",
				"Better called the Williams�Kilburn tube (after inventors Freddie "
						+ "Williams and Tom Kilburn) it was a cathode ray tube used as a computer memory to electronically store binary "
						+ "data. It was the first random-access digital storage device, and was used successfully in several early computers. "
						+ "Vacuum tube machines, such as the IBM 701, used the Williams tube as primary memory.",
						"style\\\\williamsTube.jpg", null, null, location);

		MuseumItem item5 = new MuseumItem(
				"TX-0",
				"Engineers at Lincoln Laboratory led by Kenneth Olsen built the first general-purpose, programmable computer "
						+ "built with transistors. The TX-0 was an experimental high-speed digital computer used for testing "
						+ "transistor circuitry and very large magnetic core memory. Transistors were expensive, and TX-0 used 3,600 "
						+ "total. For easy replacement, designers placed each transistor circuit inside a \"bottle,\" similar to a "
						+ "vacuum tube. The very large memory (64K RAM), speed, and reliability of the TX-0 made it one of the "
						+ "most advanced computers in the world when fully operational in 1957.", 
						"style\\\\TX0.jpg", null, null, location);
                
                MuseumItem item6 = new MuseumItem(
                        "FORTRAN",
                        "A new language, FORTRAN (short for FORmula TRANslator), enabled a computer to perform a repetitive task from a single set "
                                                + "of instructions by using loops. The first commercial FORTRAN program ran at Westinghouse, producing "
                                                + "a missing comma diagnostic. A successful attempt followed. The picture is a Punch card from a typical Fortran program.",
                                                "style\\\\fortranCard.jpg", null, null, location);
                
                MuseumItem item7 = new MuseumItem(
                        "MATH-MATIC",
                        "Sperry Rand released a commercial compiler for its UNIVAC. Developed by Grace Hopper as a refinement of her earlier innovation, "
                                                + "the A-0 compiler, the new version was called MATH-MATIC.",
                                                "style\\\\mathMatic.jpg", null, null, location);
                
                MuseumItem item8 = new MuseumItem(
                        "First Integrated Circuit",
                        "The first working integrated circuits (IC) were created independently by Jack Kilby (Texas Instruments) and Robert Noyce "
                                                + "(Fairchild Semiconductor) in 1958. It contains a single transistor and supporting components on a slice "
                                                + "of germanium and measures 1/16 by 7/16 inches (1.6 x 11.1 mm). In 1961 the first commercially available ICs "
                                                + "came from the Fairchild Semiconductor Corporation. All computers then started to be made using chips instead of "
                                                + "the individual transistors and their accompanying parts. Texas Instruments first used the chips in Air Force computers "
                                                + "and the Minuteman Missile in 1962. They later used the chips to produce the first electronic portable calculators. The "
                                                + "original IC had only one transistor, three resistors and one capacitor and was the size of an adult's pinkie finger.",
                                                "style\\\\kilbysIC.jpg", null, null, location);
                
                MuseumItem item9 = new MuseumItem(
                        "ERMA",
                        "ERMA, the Electronic Recording Method of Accounting, digitized checking for the Bank of America by creating a computer-readable font. A special "
                                                + "scanner read account numbers preprinted on checks in magnetic ink.",
                                                "style\\\\ERMA.jpg", null, null, location);

		mylist.add(item1);
		mylist.add(item2);
		mylist.add(item3);
		mylist.add(item4);
		mylist.add(item5);
                mylist.add(item6);
                mylist.add(item7);
                mylist.add(item8);
                mylist.add(item9);

		MuseumItemDAO.update(mylist);
	}

	public static void fillFloorPlan(){
		String[][] mylist1 = new String[10][10];
		String[][] mylist2 = new String[10][10];

		for(int i = 0; i < 10; i++){
			mylist1[0][i] = "Wall";
			mylist1[i][0] = "Wall";
			mylist1[9-i][9] = "Wall";
			mylist1[9][9-i] = "Wall";
		}

		mylist1[3][1] = "Wall";
		mylist1[3][3] = "Wall";
		mylist1[3][4] = "Wall";
		mylist1[3][5] = "Wall";
		mylist1[3][6] = "Wall";
		mylist1[3][8] = "Wall";

		mylist1[6][2] = "Wall";
		mylist1[6][3] = "Wall";
		mylist1[6][6] = "Wall";
		mylist1[6][7] = "Wall";

		mylist1[7][3] = "Wall";
		mylist1[7][4] = "Wall";
		mylist1[7][6] = "Wall";

		mylist1[8][4] = "Wall";
		mylist1[8][6] = "Wall";

		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				mylist2[i][j] = "";
				if (mylist1[i][j] == null || !mylist1[i][j].equals("Wall"))		
					mylist1[i][j] = "";

				//System.out.println(mylist1[i][j]);
			}
		}	

		FloorPlanDAO.updateFloorPlan(mylist1, mylist2, 0);

	}


	public static void createTables() {
		String sqlStatement;
		try {
			sqlStatement = "CREATE TABLE User(Login varchar(50), Password varchar(50), AccessLevel int) ";
			MySqlConnection.sqlUpdate(sqlStatement);

			sqlStatement = "CREATE TABLE MuseumItem(ItemName varchar(100), ItemDescription varchar(1000),"
					+ " ImagePath varchar(250), AudioPath varchar(250), VideoPath varchar(250),"
					+ " LocationX int, LocationY int)";
			MySqlConnection.sqlUpdate(sqlStatement);

			sqlStatement =  "CREATE TABLE ExhibitList(ExhibitName varchar(100), ExhibitDescription varchar(1000),"
					+ " LocationX int, LocationY int)";
			MySqlConnection.sqlUpdate(sqlStatement);

			sqlStatement = "CREATE TABLE FloorPlan0(LocationX int, LocationY int, StructureType varchar(50)"
					+ ", ItemName varchar(50))";
			MySqlConnection.sqlUpdate(sqlStatement);
			
			sqlStatement = "CREATE TABLE FloorPlan1(LocationX int, LocationY int, StructureType varchar(50)"
					+ ", ItemName varchar(50))";
			MySqlConnection.sqlUpdate(sqlStatement);
			
			sqlStatement = "CREATE TABLE FloorPlan2(LocationX int, LocationY int, StructureType varchar(50)"
					+ ", ItemName varchar(50))";
			MySqlConnection.sqlUpdate(sqlStatement);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void dropTables() {
		ResultSet result;

		try {
			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
							+ " WHERE table_name = 'User' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE User ");
			}

			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
							+ " WHERE table_name = 'MuseumItem' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE MuseumItem");
			}

			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
							+ " WHERE table_name = 'ExhibitList' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE ExhibitList");
			}

			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
							+ " WHERE table_name = 'FloorPlan0' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE FloorPlan0");
			}
			
			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
					+ " WHERE table_name = 'FloorPlan1' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE FloorPlan1");
			}		
			
			result = MySqlConnection.sqlQuery("SELECT COUNT(*) FROM information_schema.TABLES"
					+ " WHERE table_name = 'FloorPlan2' and table_schema = 'MuseumInfoSystem'");
			result.next();
			if (result.getInt(1) > 0) {
				MySqlConnection.sqlUpdate("DROP TABLE FloorPlan2");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
