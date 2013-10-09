package Control;
import Model.Data;
import Model.HtmlExtract;
import View.Main_GUI;

public class Control {
	private Main_GUI g;
	private HtmlExtract newExtract;
	public Control(){
		newExtract = new HtmlExtract();
		Data d = newExtract.readConfig();
		
		if (d.isGui()){
			g = new Main_GUI();
			newExtract.setGui(g);
			g.setModel(newExtract);
			
		} else {
			System.out.println("No GUI:");
			newExtract.startExtract();
			String summary = newExtract.summaryReport();
			System.out.println(summary);
			newExtract.write();
		}
	}

	public static void main(String[] args) {

//		newExtract.sortBySuccess();
//
//
//		newExtract.sortByTime();
//		newExtract.sortByTestSuite();
//		newExtract.sortByNode();	
//		newExtract.sort();
//		System.out.print("\n" + newExtract);
			
//		Data d = new Data();
//		HtmlExtract newExtract = new HtmlExtract(d);
//		newExtract.startExtract();
//		String summary = newExtract.summaryReport();
//		System.out.println(summary);
//		newExtract.write();
		Control c = new Control();
	}
}
