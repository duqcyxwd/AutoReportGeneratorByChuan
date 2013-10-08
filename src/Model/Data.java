package Model;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class Data {
	

	//Specify Date of Report 
	// if set runAllReport to true, do not need to worry about date
	
	private Boolean isRunAllReport;
	private Date dateOfReportBegin;
	private Date dateOfReportEnd;
	
	private  Boolean isExclusiveUnnecessarySuite;
	private  Boolean isOutPutWithReadableFormat;
	
	private Boolean isIncludeReportSummary;
//	private static Object[][] data;
	
	private String[] signums;
	private char[] sortOrder;
	
	public Data(){
		Properties prop = new Properties();
		 
		String dateOfReportBeginString;
		String dateOfReportEndString;
		
    	try {
               //load a properties file
    		prop.load(new FileInputStream("config.properties"));
 
               //get the property value and print it out
			isRunAllReport = prop.getProperty("isRunAllReport").matches("true");
			isExclusiveUnnecessarySuite = prop.getProperty("isExclusiveUnnecessarySuite").matches("true");
			isOutPutWithReadableFormat = prop.getProperty("isOutPutWithReadableFormat").matches("true");

			isIncludeReportSummary = prop.getProperty("isIncludeReportSummary").matches("true");
			
			signums = prop.getProperty("signums").trim().split(",");

			
//			Get the date for Report
			dateOfReportBeginString = prop.getProperty("dateOfReportBegin");
			dateOfReportEndString = prop.getProperty("dateOfReportEnd");			
			DateFormat formatter = new SimpleDateFormat(prop.getProperty("SimpleDateFormat"));
			try {
            	dateOfReportBegin = (Date)formatter.parse(dateOfReportBeginString);
            	dateOfReportEnd = (Date)formatter.parse(dateOfReportEndString);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
//			Get the sort order for data
			String temp = prop.getProperty("sortOrder");
			this.sortOrder = temp.toCharArray();
			
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
		
		
	public Boolean getIsRunAllReport() {
		return isRunAllReport;
	}

	public void setIsRunAllReport(Boolean isRunAllReport) {
		this.isRunAllReport = isRunAllReport;
	}

	public Date getDateOfReportBegin() {
		return dateOfReportBegin;
	}


	public Date getDateOfReportEnd() {
		return dateOfReportEnd;
	}


	public Boolean getIsExclusiveUnnecessarySuite() {
		return isExclusiveUnnecessarySuite;
	}

	public void setIsExclusiveUnnecessarySuite(Boolean isExclusiveUnnecessarySuite) {
		this.isExclusiveUnnecessarySuite = isExclusiveUnnecessarySuite;
	}

	public Boolean getIsOutPutWithReadableFormat() {
		return isOutPutWithReadableFormat;
	}

	public void setIsOutPutWithReadableFormat(Boolean isOutPutWithReadableFormat) {
		this.isOutPutWithReadableFormat = isOutPutWithReadableFormat;
	}

	public String[] getSignums() {
		return signums;
	}

	public void setSignums(String[] signums) {
		this.signums = signums;
	}




	public char[] getSortOrder() {
		return sortOrder;
	}




	public Boolean getIsIncludeReportSummary() {
		return isIncludeReportSummary;
	}




	public void setIsIncludeReportSummary(Boolean isIncludeReportSummary) {
		this.isIncludeReportSummary = isIncludeReportSummary;
	}
}
