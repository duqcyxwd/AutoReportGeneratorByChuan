package Model;
import java.util.Date;


public class OneTestReport {
	private String reportLink, isMatchwithLegacy, testName, nodeName, testerName;
	private Integer testNumber, suscess, fails, skips;
	private Date date;
	
	public OneTestReport(){
		reportLink = "NoContent";
		isMatchwithLegacy = "NoContent";
		testName = "NoContent";
		nodeName = "NoContent";
		testNumber = -1;
		suscess = -1;
		fails = -1;
		skips = -1;
		date = null;
	}


	public OneTestReport(String reportLink, String nodeName, String testName0, String successes, String fails, String skips, Date date, String testerName) {
		this.reportLink = reportLink;
		this.nodeName = nodeName.split("@")[0];
		
		String testNameTemp[];
		testNameTemp = testName0.split("\\.");
		
		int l = testNameTemp.length;
		this.testName = testNameTemp[l-1];
		
		
	
		try {
			this.suscess = Integer.parseInt(successes);
		} catch (Exception e) {
			this.suscess = -1;
		}
		
		try {
			this.fails = Integer.parseInt(fails);
		} catch (Exception e) {
			this.fails = -1;
		}
		
		try {
			this.skips = Integer.parseInt(skips.split(" ")[0]);
		} catch (Exception e) {
			this.skips = -1;
		}
		
		
		this.date = date;
		testNumber = LegacyDataCompare.getTestNumber(testName);
		isMatchwithLegacy = LegacyDataCompare.compareWithLegacy(this.testName, this.suscess, this.fails, this.skips);
		
		this.testerName = testerName;

	}




	public String getreportLink() {
		return reportLink;
	}
	public void setreportLink(String reportLink) {
		this.reportLink = reportLink;
	}
	public String getisMatchwithLegacy() {
		return isMatchwithLegacy;
	}
	public void setisMatchwithLegacy(String isMatchwithLegacy) {
		this.isMatchwithLegacy = isMatchwithLegacy;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getTestNumber() {
		return testNumber;
	}
	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
	}
	public Integer getSuscess() {
		return suscess;
	}
	public void setSuscess(Integer suscess) {
		this.suscess = suscess;
	}
	public Integer getFails() {
		return fails;
	}
	public void setFails(Integer fails) {
		this.fails = fails;
	}
	public Integer getSkips() {
		return skips;
	}
	public void setSkips(Integer skips) {
		this.skips = skips;
	}


	public String getReportLink() {
		return reportLink;
	}


	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}


	public String getIsMatchwithLegacy() {
		return isMatchwithLegacy;
	}


	public void setIsMatchwithLegacy(String isMatchwithLegacy) {
		this.isMatchwithLegacy = isMatchwithLegacy;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getTesterName() {
		return testerName;
	}


	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}


	@Override
	public String toString() {
		

		return testNumber + "	" + testName + "	" + nodeName + "	" + suscess + "	" + fails + "	" + skips + "	" + isMatchwithLegacy + "	" + reportLink;
	}
	
	public String toStringWithReadableFormat() {
		return String.format("%-30s %4d %-45s %-12s %3d %3d %3d %9s  %7s  %3s", date, testNumber, testName, nodeName, suscess, fails, skips, isMatchwithLegacy, testerName,reportLink);
		
	}

}
