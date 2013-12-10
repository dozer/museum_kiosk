import java.util.ArrayList;

import access.MuseumItemDAO;
import model.MuseumItem;


public class FillDatabase {
	
	public static void fillAll(){
		fillMuseumItem();
		
	}
	

	
	public static void fillMuseumItem(){
		ArrayList mylist = new ArrayList();
		int[] location = {-1,-1};
		MuseumItem item1 = new MuseumItem("Z3","First functional program-controlled Turing-complete computer, the Z3, which "
				+ "became operational in May 1941. Thanks to this machine and its predecessors, Konrad Zuse is often regarded"
				+ " as the inventor of the computer. Using 2,300 relays, the Z3 used floating point binary arithmetic and had "
				+ "a 22-bit word length. The original Z3 was destroyed in a bombing raid of Berlin in late 1943. However, Zuse "
				+ "later supervised a reconstruction of the Z3 in the 1960s.", null,null,null, location);
		
		MuseumItem item2 = new MuseumItem("Atanasoff–Berry Computer (ABC)","Built at Iowa State College (now University), "
				+ "the ABC was designed and built by Professor John Vincent Atanasoff and graduate student Cliff Berry "
				+ "between 1939 and 1942. The ABC was at the center of a patent dispute relating to the invention of the "
				+ "computer, which was resolved in 1973 when it was shown that ENIAC co-designer John Mauchly had come to "
				+ "examine the ABC shortly after it became functional. Atanasoff was declared the originator of several "
				+ "basic computer ideas, but the computer as a concept was declared un-patentable and thus was freely open "
				+ "to all. This result has been referred to as the dis-invention of the computer. A full-scale "
				+ "reconstruction of the ABC was completed in 1997 and proved that the ABC machine functioned as Atanasoff "
				+ "had claimed.", "C:\\\\MISDatabase\\\\Generation1\\\\ABC.jpg", null, null, location);
		mylist.add(item1);
		mylist.add(item2);
		MuseumItemDAO.update(mylist);
	}
	
}
