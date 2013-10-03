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
		getNodesName();
		int totalTest = reportList.size();
		
		reportSummary += "\nThere is total " + totalTest + " Test suites running on nodes: " + nodeNameSet.toString() + "\n\n";
		
		reportSummary += "The Total passed rate is " + 100.0*totalPassed/totalTest + "% \n";
		
		Iterator<String> i = nodeNameSet.iterator();
		while (i.hasNext()) {
			getSusRate(i.next());
		}
		
		return reportSummary;
	}
	
	public void getNodesName(){
		for (int i = 0; i < reportList.size(); i ++){
			nodeNameSet.add(reportList.get(i).getNodeName());
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
			
			if (tr.getIsMatchwithLegacy().equals("Yes")){
				totalPassed ++;
			}
		}	
		
		reportSummary += (nodeName + " : " + numMatchWithLegacy + " Passed over total " + totalTestNumerForNode + " Test Case." + 100.0*numMatchWithLegacy/totalTestNumerForNode + "%\n");

	}

}
