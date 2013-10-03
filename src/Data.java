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
	
	
		
	
//	public static void main(String[] args) {
//		Data d = new Data();	
//		
//		private static Object[][] data = {
//			{1, "inter_freq_load_balance_SUITE", 60, 0, 0},
//			{2, "basic_intra_eNB_handover_SUITE", 15, 2, 0},
//			{3, "basic_x2_handover_SUITE", 1, 1, 0},
//			{4, "x2_handover_error_case_SUITE", 18, 2, 0},
//			{5, "packet_forwarding_SUITE", 40, 0, 0},
//			{6, "ue_release_SUITE", 10, 0, 0},
//			{7, "preemption_SUITE", 54, 0, 0},
//			{8, "interfreq_ho_SUITE", 21, 0, 0},
//			{9, "rrc_conn_reestablishment_SUITE", 26, 2, 0},
//			{10, "multi_target_rrc_conn_reestablishment_SUITE", 74, 6, 28},
//			{11, "mp_load_control_SUITE", 4,5,33},
//			{12, "basic_s1_handover_SUITE", 74, 4, 0},
//			{13, "s1_handover_error_case_SUITE", 22, 0, 0},
//			{14, "s1_handover_packet_forwarding_SUITE", 22, 0, 0}
//	    };
//		
//	}

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
}
