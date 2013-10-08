package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;


public class DataAnalysis {

	private ArrayList<OneTestReport> reportList;
	private HashSet<String> nodeNameSet;
	private HashMap<Integer, String> suitesNameSet;
	private Integer totalPassed, totalFailed;
	private String reportSummary;
	private Data data;
	
	public DataAnalysis(ArrayList<OneTestReport> reportList, Data data) {
		this.reportList = reportList;
		nodeNameSet = new HashSet<String>();
		suitesNameSet = new HashMap<Integer, String>();
		totalPassed = 0;
		totalFailed = 0;
		reportSummary = new String();
		this.data = data;

	}
	
	public String reportSummary(){
		
		getNodesName();
		getSuiteName();
		getTotalPassed();
		
		reportSummary = "\n\nThis is auto generate Report.\n";
		reportSummary += "Report time:" + data.getDateOfReportBegin() +" to " + data.getDateOfReportEnd() + "\n\n";
		reportSummary += "Tester: ";
		
		for (int i =0; i < data.getSignums().length; i ++) {
			reportSummary += data.getSignums()[i] + " ";
		}
		

		int totalTest = reportList.size();
		
		reportSummary += "\n\nThere is total " + totalTest + " Test suites running on nodes: " + nodeNameSet.toString() + "\n\n";
		
		double passedRate = 100.0*totalPassed/(totalPassed + totalFailed);
		reportSummary += "The Total passed rate is " + String.format("%.1f", passedRate) + "% \n";
		
		Iterator<String> i = nodeNameSet.iterator();
		while (i.hasNext()) {
			getSusRatePerNode(i.next());
		}
		
		reportSummary += "\n\n";
		
		for(int j = 0; j < 100; j ++){
			if (suitesNameSet.containsKey(j)){
				getSusRatePerSuite(suitesNameSet.get(j));
			}
		}
//		Iterator<Entry<Integer, String>> i1 = suitesNameSet.newEntryIterator();
//		while(i1.hasNext()) {
//			getSusRatePerSuite(i1.next());
//		}
		
//		String.format("%-30s %4d %-45s %-12s %3d %3d %3d %9s  %7s  %3s", date, testNumber, testName, nodeName, suscess, fails, skips, isMatchwithLegacy, testerName,reportLink);
		
		return reportSummary;
	}
	
	private void getNodesName(){
		for (int i = 0; i < reportList.size(); i ++){
			nodeNameSet.add(reportList.get(i).getNodeName());
		}
	}
	
	private void getSuiteName(){
		for (OneTestReport o: reportList){
			suitesNameSet.put(o.getTestNumber(), o.getTestName());
		}

	}
	
	private void getTotalPassed(){
		totalPassed = 0;
		for (OneTestReport tr: reportList) {
			if (tr.getIsMatchwithLegacy().equals("Yes")){
				totalPassed ++;
			} else if(tr.getIsMatchwithLegacy().equals("No")) {
				totalFailed++;
			}
				
		}	
	}
	
	
	private void getSusRatePerSuite(String suiteName){
		int testNumber = 0;
		Integer totalTestNumerForNode = 0, numMatchWithLegacy = 0, numNotMatchWithLegacy = 0;
		for (OneTestReport tr: reportList) {
			if (tr.getTestName().equals(suiteName)){
				testNumber = tr.getTestNumber();
				totalTestNumerForNode++;
				if (tr.getIsMatchwithLegacy().equals("Yes")){
					numMatchWithLegacy ++;
				}
				if (tr.getIsMatchwithLegacy().equals("No")){
					numNotMatchWithLegacy++;
				}
			}
		}	
		
		// reportSummary += (nodeName + " : " + numMatchWithLegacy + " Passed over total " + totalTestNumerForNode + " Test Case." + String.format("%.1f", 100.0*numMatchWithLegacy/totalTestNumerForNode) + "%\n");
		reportSummary += String.format("%2d", testNumber);
		reportSummary += ". ";
		reportSummary += String.format("%-44s", suiteName);
		reportSummary += String.format("%2s%4s%2s%4s",numMatchWithLegacy, " Passed ", numMatchWithLegacy, " Failed ");
		reportSummary += String.format("%4s%2s%s",  " Passed over total ", totalTestNumerForNode, " Test Case.");
		if ((numNotMatchWithLegacy + numMatchWithLegacy) == 0){
			reportSummary += "  No Result\n";
		} else {
			reportSummary += String.format("%7.1f%s",  100.0*numMatchWithLegacy/(numNotMatchWithLegacy + numMatchWithLegacy), "%\n");
		}
	}
	
	private void getSusRatePerNode(String nodeName){
		ArrayList<OneTestReport> list = new ArrayList<OneTestReport>();
		Integer totalTestNumerForNode = 0, numMatchWithLegacy = 0, numNotMatchWithLegacy = 0;
		for (OneTestReport tr: reportList) {
			if (tr.getNodeName().equals(nodeName)){
				list.add(tr);			
				totalTestNumerForNode++;
				if (tr.getIsMatchwithLegacy().equals("Yes")){
					numMatchWithLegacy ++;
				}
				if (tr.getIsMatchwithLegacy().equals("No")){
					numNotMatchWithLegacy++;
				}
					
			}
			
			
		}	
		
		// reportSummary += (nodeName + " : " + numMatchWithLegacy + " Passed over total " + totalTestNumerForNode + " Test Case." + String.format("%.1f", 100.0*numMatchWithLegacy/totalTestNumerForNode) + "%\n");
		reportSummary += String.format("%-9s%2s%2s%4s%2s%4s", nodeName, " : ", numMatchWithLegacy, " Passed ", numMatchWithLegacy, " Failed ");
		reportSummary += String.format("%4s%2s%s", "over total ", totalTestNumerForNode, " Test Cases.");
		if ((numNotMatchWithLegacy + numMatchWithLegacy) == 0){
			reportSummary += "No Result\n";
		} else {
			reportSummary += String.format("%7.1f%s", 100.0*numMatchWithLegacy/(numNotMatchWithLegacy + numMatchWithLegacy), "%\n");
		}
	}

}
