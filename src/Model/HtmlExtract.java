package Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import View.Main_GUI;

import java.io.*;
import java.text.Collator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;


public class HtmlExtract {
	
	static public final String link_title = "http://gtelogs-lte.lmera.ericsson.se/";
	static public final String link_all_runs = "/all_runs.html";
	
	
	
	private String userNames[];
	private ArrayList<OneTestReport> reportList;
	private String link_base;
	private Data data;
	private DataAnalysis da; 
	
	private String notes = "\n\nNote1: Result of this test suite in Legacy is empty\nNote2: Can't find this suite result in Legacy";
	
	private String reportFileName = "Report.txt";
	
	private Main_GUI g;
	public HtmlExtract() {
		
		link_base = link_title;
		data =  readConfig();
		
		
	}
	
	public Data readConfig(){
		data = new Data();
		
		this.userNames = data.getSignums();
		reportFileName = data.getSignumString() + " Report.txt";
		return data;
	}

	public void startExtract() {
		reportList = new ArrayList<OneTestReport>();
		if (data.isGui()) 
			readDataFromGUI();
		
		System.out.println("Start Extract info from website...");
		for(String userName: userNames){
			extract(userName);
		}
//		if (Data.isCleanUpRun){
		this.cleaUp();
		this.sort();
		
//	    System.out.println("There is " + reportList.size() + " Test suites");
	}
	
	public String getReportFileName() {
		return reportFileName;
	}

	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
	}

	private void readDataFromGUI() {
		data.setSignums(g.getSignums());	


		DateFormat Timeformatter = new SimpleDateFormat(g.getTimeFormat());
		try {
			data.setDateOfReportBegin((Date)Timeformatter.parse(g.getBeginTime()));
			data.setDateOfReportEnd((Date)Timeformatter.parse(g.getEndTime()));	
			data.setUp(g.getUP());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		this.userNames = data.getSignums();
		
	}
	

	public void extract(String userName) {
		
		LegacyDataCompare ld = new LegacyDataCompare(data);
		
		String reportLink, testName, nodeName, suscess, fails, skips;
		System.out.println("Extracting Data for " + userName);		
		Document doc = null;
		try {
			doc = Jsoup.connect(link_title + userName + link_all_runs).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
//		File input = new File("All_test_runs_in_ eyonduu.htm");
//		link_base = link_title;
//		try {
//			doc = Jsoup.parse(input, "UTF-8", "http://gtelogs-lte.lmera.ericsson.se/");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		

		
	    for (Element table2 : doc.select("table[id=SortableTable]")) {
	        for (Element row : table2.select("tr")) {        	
	            Elements tds = row.select("td");
	            if (tds.size() > 8) {
		            reportLink = link_base + userName + "/" + tds.get(0).select("a").first().attr("href"); 
		           
		            DateFormat formatter = new SimpleDateFormat("EEE MMM dd y HH:mm:ss");
		            Date date = null;
		            try {
						date = (Date)formatter.parse(tds.get(0).text());
					} catch (ParseException e) {
						e.printStackTrace();
					}
		            
		            nodeName = tds.get(1).text();
		            testName = tds.get(4).attr("title");
		            suscess = tds.get(6).text();
		            fails = tds.get(7).text();
		            skips = tds.get(8).text();
		            reportList.add(new OneTestReport(reportLink, nodeName, testName, suscess, fails, skips, date, userName, ld));
	            }
	        }
	    }
		
	}
	
	public void cleaUp(){
		Iterator<OneTestReport> ite = reportList.iterator();
		ArrayList<OneTestReport> badList = new ArrayList<OneTestReport>();
		while(ite.hasNext()){
			//test suite check
			OneTestReport o = ite.next();
			if (isUnwantedSuite(o) || isDateLimit(o)) {
				badList.add(o);
			}
		}
		
		for (OneTestReport oo :badList) {
			reportList.remove(oo);
		}
	}
	
	public boolean isDateLimit(OneTestReport d){
		if (data.getIsRunAllReport()){
			return false;
		} else
		
		{
//			DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
//            Date dateBegin = null;
//            Date dateEnd = null;
//           
//            Date currentDate = d.getDate();
//            try {
//            	dateBegin = (Date)formatter.parse(data.getDateOfReportBegin());
//            	dateEnd = (Date)formatter.parse(data.getDateOfReportEnd());
//			} catch (ParseException e) {
//				e.printStackTrace();
//			} 

//			Boolean a = currentDate.after(dateBegin) ;
//			Boolean a2 = (currentDate.getDate() == dateBegin.getDate() && currentDate.getMonth() == dateBegin.getMonth()) ;
//			Boolean a3 = currentDate.before(dateEnd);
//			Boolean a4 = currentDate.getDate() == dateEnd.getDate() && currentDate.getMonth() == dateEnd.getMonth();

//            if (
//            	(currentDate.after(dateBegin) || (currentDate.getDate() == dateBegin.getDate() && currentDate.getMonth() == dateBegin.getMonth())) 
//            		
//            		&& (currentDate.before(dateEnd)	|| currentDate.getDate() == dateEnd.getDate() && currentDate.getMonth() == dateEnd.getMonth())
//            	){
//    			return false;
//    		}      
            
			
            Date currentDate = d.getDate();
            Date dateBegin = data.getDateOfReportBegin();
            Date dateEnd = data.getDateOfReportEnd();
            if (currentDate.after(dateBegin)&& currentDate.before(dateEnd))
            	return false;
        	    
		}
		return true;
	}
	
	public boolean isUnwantedSuite(OneTestReport t){
		if (data.getIsExclusiveUnnecessarySuite()){
			String testName = t.getTestName();
			if (testName.equals("install_rn_site_script_SUITE") || testName.equals("install_cpp_SUITE") || t.getTestNumber() == 0)
				return true;
			return false;
		} else {
			return false;
		}
	}
	
	public String summaryReport(){
		da = new DataAnalysis(reportList, data);
		return da.reportSummary();
	}
	
	public void write(){
		if (data.isGui()) 
			this.reportFileName = g.getOutputFileName();
		String content = this.toString();
		
		try {
			File file = new File(reportFileName);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.write(notes);
			if (data.getIsIncludeReportSummary()){
//				bw.write("\n\n" + da.reportSummary());
				bw.write("\n\n" + this.summaryReport());
			}
			bw.close();
 
			System.out.println("\n\nResult Can be find at " + file.getPath());
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString(){
		String returnString = "";
		int listSize = reportList.size();
		int i;
		if (data.getIsOutPutWithReadableFormat()){
			if (listSize > 0){

				for (i = 0; i < listSize-1; i++)
				{
					returnString += (reportList.get(i).toStringWithReadableFormat() + "\n");
				}
				returnString += reportList.get(i).toStringWithReadableFormat();	
			}
		} else {
			if (listSize > 0){

				for (i = 0; i < listSize-1; i++)
				{
					returnString += (reportList.get(i) + "\n");
				}
				returnString += reportList.get(i);	
			}
		}

		return returnString;
	}

	public void sort(){
		char[] list = data.getSortOrder();
		for (int i = 0; i < list.length; i++)
		{
			switch (list[i]) {
				case 'o': sortBySuccess();
					break;
				case 'n': sortByNode();
					break;
				case 's': sortByTestSuite();
					break;
				case 'r': sortByTester();
					break;
				case 't': sortByTime();
					break;
				default: 
					break;
			}
		}
	}

	public void sortBySuccess(){
		Comparator<OneTestReport> comparatorA=new Comparator<OneTestReport>() {
            @Override
            public int compare(OneTestReport o1, OneTestReport o2) {
                if (o1.getSuscess() >o2.getSuscess()) return -1;
                if (o1.getSuscess()<o2.getSuscess()) return 1;
                return 0;
            }
        };
		Collections.sort(reportList, comparatorA);
	}
	
	public void sortByNode(){
		Comparator<OneTestReport> comparatorA=new Comparator<OneTestReport>() {
            @Override
            public int compare(OneTestReport o1, OneTestReport o2) {
            	 return Collator.getInstance().compare(o1.getNodeName(), o2.getNodeName());
            }
        };
		Collections.sort(reportList, comparatorA);
	}
	
	public void sortByTestSuite(){
		Comparator<OneTestReport> comparatorA=new Comparator<OneTestReport>() {
            @Override
            public int compare(OneTestReport o1, OneTestReport o2) {
            	return Integer.compare(o1.getTestNumber(), o2.getTestNumber());
            }
        };
		Collections.sort(reportList, comparatorA);
	}
	
	public void sortByTester(){
		Comparator<OneTestReport> comparatorA=new Comparator<OneTestReport>() {
            @Override
            public int compare(OneTestReport o1, OneTestReport o2) {
            	return Collator.getInstance().compare(o1.getTesterName(), o2.getTesterName());
            }
        };
		Collections.sort(reportList, comparatorA);
	}
	
	public void sortByTime(){
		Comparator<OneTestReport> comparatorA=new Comparator<OneTestReport>() {
            @Override
            public int compare(OneTestReport o1, OneTestReport o2) {
                if (o1.getDate().after(o2.getDate())) return -1;
                if (o2.getDate().after(o1.getDate())) return 1;
                return 0;
            }
        };
		Collections.sort(reportList, comparatorA);
	}

	public void setGui(Main_GUI g) {
		this.g = g;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}


