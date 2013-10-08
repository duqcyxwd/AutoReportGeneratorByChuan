package Control;
import Model.Data;
import Model.HtmlExtract;
import View.Main_GUI;

public class Main {

	public static void main(String[] args) {
		Data d = new Data();
		HtmlExtract newExtract = new HtmlExtract(d);
		newExtract.startExtract();
//		newExtract.sortBySuccess();
//
//
//		newExtract.sortByTime();
//		newExtract.sortByTestSuite();
//		newExtract.sortByNode();	
//		newExtract.sort();
//		System.out.print("\n" + newExtract);
		
		String summary = newExtract.summaryReport();
		System.out.println(summary);
		newExtract.write();
				
		Main_GUI g = new Main_GUI();
	}
}