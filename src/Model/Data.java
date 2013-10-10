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
	
	private DateFormat Timeformatter;
	
	private  Boolean isExclusiveUnnecessarySuite;
	private  Boolean isOutPutWithReadableFormat;
	
	private Boolean isIncludeReportSummary;
	
	private Boolean isGui;
//	private static Object[][] data;
	
	private String[] signums;
	private String up;
	private char[] sortOrder;
	
	private Properties prop;
	
	public Data(){
		prop = new Properties();
		 		
		
		
    	try {
               //load a properties file
    		prop.load(new FileInputStream("config.properties"));
    		    		
               //get the property value and print it out
			isRunAllReport = prop.getProperty("isRunAllReport").matches("true");
			isExclusiveUnnecessarySuite = prop.getProperty("isExclusiveUnnecessarySuite").matches("true");
			isOutPutWithReadableFormat = prop.getProperty("isOutPutWithReadableFormat").matches("true");

			isIncludeReportSummary = prop.getProperty("isIncludeReportSummary").matches("true");
			
			signums = getSignumStringFromProperty().trim().split(",");

			
//			Get the date for Report
		
			Timeformatter = new SimpleDateFormat(getTimeformatterStringFromProperty());
			try {
            	dateOfReportBegin = (Date)Timeformatter.parse(getDateOfReportBeginStringFromProperty());
            	dateOfReportEnd = (Date)Timeformatter.parse(getDateOfReportEndStringFromProperty());
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
//			Get the sort order for data
			String temp = prop.getProperty("sortOrder");
			this.sortOrder = temp.toCharArray();
			
			
			this.isGui = prop.getProperty("isGui").matches("true");
			this.up = getUP();
			
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }
	}
		
	private String getUP(){
		return prop.getProperty("UP");
	}
	
	public String getSignumStringFromProperty(){
		return prop.getProperty("signums");
	}
	
	public String getDateOfReportBeginStringFromProperty(){
		return prop.getProperty("dateOfReportBegin");
	}
	
	public String getDateOfReportEndStringFromProperty(){
		return prop.getProperty("dateOfReportEnd");
	}
	
	public String getTimeformatterStringFromProperty(){
		return prop.getProperty("SimpleDateFormat");
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

	public void setSignums(String signums) {
		this.signums = signums.trim().split(",");;
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


	public boolean isGui() {
		// TODO Auto-generated method stub
		return isGui;
	}


	public Boolean getIsGui() {
		return isGui;
	}


	public void setIsGui(Boolean isGui) {
		this.isGui = isGui;
	}


	public void setDateOfReportBegin(Date dateOfReportBegin) {
		this.dateOfReportBegin = dateOfReportBegin;
	}


	public void setDateOfReportEnd(Date dateOfReportEnd) {
		this.dateOfReportEnd = dateOfReportEnd;
	}


	public void setSortOrder(char[] sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

}
