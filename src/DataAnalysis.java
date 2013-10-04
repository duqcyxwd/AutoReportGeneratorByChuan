import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class DataAnalysis {

	private ArrayList<OneTestReport> reportList;
	private HashSet<String> nodeNameSet;
	private Integer totalPassed;
	private String reportSummary;
	
	public DataAnalysis(ArrayList<OneTestReport> reportList) {
		this.reportList = reportList;
		nodeNameSet = new HashSet<String>();
		totalPassed = 0;
		reportSummary = new String();

	}
	
	public String reportSummary(){	
		reportSummary = "";
		getNodesName();
		getTotalPassed();
		int totalTest = reportList.size();
		
		reportSummary += "\nThere is total " + totalTest + " Test suites running on nodes: " + nodeNameSet.toString() + "\n\n";
		
		double passedRate = 100.0*totalPassed/totalTest;
		reportSummary += "The Total passed rate is " + String.format("%.1f", passedRate) + "% \n";
		
		Iterator<String> i = nodeNameSet.iterator();
		while (i.hasNext()) {
			getSusRate(i.next());
		}
		
//		String.format("%-30s %4d %-45s %-12s %3d %3d %3d %9s  %7s  %3s", date, testNumber, testName, nodeName, suscess, fails, skips, isMatchwithLegacy, testerName,reportLink);
		
		return reportSummary;
	}
	
	public void getNodesName(){
		for (int i = 0; i < reportList.size(); i ++){
			nodeNameSet.add(reportList.get(i).getNodeName());
		}
	}
	
	public void getTotalPassed(){
		totalPassed = 0;
		for (OneTestReport tr: reportList) {
			if (tr.getIsMatchwithLegacy().equals("Yes")){
				totalPassed ++;
			}
		}	
	}
	
	
	public void getSusRate(String nodeName){
		ArrayList<OneTestReport> list = new ArrayList<OneTestReport>();
		Integer totalTestNumerForNode = 0, numMatchWithLegacy = 0;
		for (OneTestReport tr: reportList) {
			if (tr.getNodeName().equals(nodeName)){
				list.add(tr);			
				totalTestNumerForNode++;
				if (tr.getIsMatchwithLegacy().equals("Yes")){
					numMatchWithLegacy ++;
				}
					
			}
			
			
		}	
		
		// reportSummary += (nodeName + " : " + numMatchWithLegacy + " Passed over total " + totalTestNumerForNode + " Test Case." + String.format("%.1f", 100.0*numMatchWithLegacy/totalTestNumerForNode) + "%\n");
		reportSummary += String.format("%-8s%s%s%4s%2s%s%5.1f%s", nodeName, " : ", numMatchWithLegacy, " Passed over total ", totalTestNumerForNode, " Test Case.", 100.0*numMatchWithLegacy/totalTestNumerForNode, "%\n");
	}

}
