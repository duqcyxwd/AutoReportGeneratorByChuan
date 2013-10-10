package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class LegacyDataCompare {

	private ArrayList<LegacyData> legacyList;
	private Integer length;
	
	public LegacyDataCompare(Data data) {	

		legacyList = new ArrayList<LegacyData>();
		Properties prop = new Properties();

		try {
		   //load a properties file
			prop.load(new FileInputStream("LegacyResultData.txt"));
		//			System.out.println(prop.getProperty(data.getUP()));

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String s = prop.getProperty(data.getUP());
			
//		if (s == null){
//			System.out.println(" =================================================================================================");
//			System.out.println("       No Legacy result for UP:" + data.getUp() + "\n     Please Add legacy result to LegacyResultData.txt");
//			System.out.println(" =================================================================================================");
//		}
			
		String ss[] = s.replace(" ", "").replace("}", "").replace("{", "").replace("\"", "").split(",");
		
		for (int i = 0; i < ss.length/5; i++){
			Integer suiteNum = Integer.parseInt(ss[5*i]);
		    String suiteName = ss[5*i + 1];
		    Integer susNum = Integer.parseInt(ss[5*i + 2]);
		    Integer failNum = Integer.parseInt(ss[5*i + 3]);
		    Integer skipNum = Integer.parseInt(ss[5*i + 4]);
			legacyList.add(new LegacyData(suiteNum, suiteName, susNum, failNum, skipNum));
		}
			
		 length = legacyList.size();
//		 System.out.println(length);
//			for (LegacyData ld: legacyList){
//				System.out.println(ld);
//			}

	}


	public Integer getTestNumber(String testName){
        
        for (int i = 0; i < length; i++)
        {
        	if (legacyList.get(i).getSuiteName().equals(testName))
        	return legacyList.get(i).getSuiteNumber();
        }		
		
		return 0;
	}
	
	public String compareWithLegacy(String testName, Integer successes, Integer fails, Integer skipped){
		for (int i = 0; i < length; i++)
		{
	       if  (legacyList.get(i).getSuiteName().equals(testName)){
	    	    if (legacyList.get(i).getSusNum() == -1)
//	    	    	return "No result from Legacy";
	    	    	return "Note1";
	    	    if (legacyList.get(i).getSusNum() == -2)
//	    	    	return "Can't find in Legacy";
	    	    	return "Note2";
				if (successes >= (Integer) legacyList.get(i).getSusNum()
//				 && fails <= (Integer) data[i][3]
//				 && skipped <= (Integer) data[i][4]
						)	return "Yes";	
				
				return "No";
	       }
	 	}		
			
		return "Test Suite not found";
	}


	public ArrayList<LegacyData> getLegacyList() {
		return legacyList;
	}


	public void setLegacyList(ArrayList<LegacyData> legacyList) {
		this.legacyList = legacyList;
	}
	
//	public getTestNumber

}
