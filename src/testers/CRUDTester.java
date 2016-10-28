package testers;

import util.*;
import model.*;


/// Tester for updating / creating DB
public class CRUDTester {

	public static void main(String[] args) {
		
		DBManager.buildSessionFactory();
		
		PageDao dao = new PageDao();
		
		dao.addPage("www.google.nl", "/dikke");

	}

}
