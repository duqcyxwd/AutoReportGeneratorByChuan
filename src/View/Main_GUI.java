package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Model.*;


public class Main_GUI {
	
	int tfLength = 20;
	JFrame f = new JFrame("Auto Report Generator by Chuan");;
	JLabel l = new JLabel("More option can be find in config.properties");
	JTextArea ta = new JTextArea(40, 130);
	String[] labels = {"SigNums: ", "Report Begin from:", "Report End at: ", "Date Format", "UP"};
	ArrayList<JTextField> tfs;
	JButton run = new JButton("Run");;
	JButton save = new JButton("Save Result");
	JButton disPlayAllResult = new JButton("Display Test Suite Result Table ");
	JTextField outputTf = new JTextField(tfLength);
	Dimension a = new Dimension(1200, 900);
	
	HtmlExtract e;
	public Main_GUI(){
		f.setSize(a);
		f.setContentPane(this.createContentPane());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.pack();
		f.setVisible(true);
		
	}
	
	public void setModel(HtmlExtract e) {
		this.e = e;
		Data d = e.getData();
		tfs.get(0).setText(d.getSignumString());
		tfs.get(1).setText(d.getDateOfReportBeginString());
		tfs.get(2).setText(d.getDateOfReportEndString());
		tfs.get(3).setText(d.getTimeformatterString());
		tfs.get(3).setEditable(false);
		tfs.get(4).setText(d.getUP());
		
		outputTf.setText(e.getReportFileName());
	}

	private JPanel createContentPane() {
		JPanel gui = new JPanel();
//		gui.setBackground(Color.black);
		
		gui.add(creatAddingPane());
		gui.add(creatButtonPane());
		gui.add(creatOutputPane());
	
		// ta.setBackground(Color.white);
		JScrollPane sp = new JScrollPane(ta);
		
		
//		Redirect message from console to GUI
//		MessageConsole mc = new MessageConsole(ta);
//		mc.redirectOut();
//		mc.redirectErr(Color.RED, null);

		
		
		ta.setFont(new Font("Courier New", Font.PLAIN, 13));  
		// mc.setMessageLines(100);

		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);

		gui.add(sp);
		gui.add(l);
//		gui.add(tf);
		
		return gui;
	}

	private JPanel creatAddingPane(){
      
        int numPairs = labels.length;
        
        //Create and populate the panel.
        JPanel p = new JPanel(new SpringLayout());
        tfs = new ArrayList<JTextField>();
        
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            p.add(l);
            JTextField textField = new JTextField(tfLength);
            l.setLabelFor(textField);
            tfs.add(textField);
            p.add(textField);
        }
        
        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p,
                                        numPairs, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad

        
		return p;

	}
	
	private JPanel creatButtonPane(){
        JPanel pa = new JPanel(new SpringLayout());
        

        pa.add(run);
        
        disPlayAllResult.addActionListener(new RunListener());
        pa.add(disPlayAllResult);
        
        pa.add(creatOutputPane());
        
        run.addActionListener(new RunListener());
        
        
        SpringUtilities.makeCompactGrid(pa,
                2, 1, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
        
        return pa;
	}
	
	private JPanel creatOutputPane(){
		JPanel p = new JPanel();
		JLabel l = new JLabel("Output File Name:");
		
		p.add(l);
		p.add(outputTf);
		
		p.add(save);
		
		save.addActionListener(new RunListener());
		return p;
		
	}
	
	private class RunListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getSource().equals(run)){

//				HtmlExtract newExtract = new HtmlExtract();		
				e.startExtract();
//				try {
//					
//				} catch (Exception e2) {
//					
//				}

				String summary = e.summaryReport();
				// ta.append(summary);
				System.out.println(summary);
//				newExtract.write();
			} else if (arg0.getSource().equals(save)){
				e.write();
			} else if (arg0.getSource().equals(disPlayAllResult)){
				ta.append("\n\n\n" + e.toString() + "\n\n");
			}
		}
	}

	public String getSignums() {
		return tfs.get(0).getText();
	}

	public String getBeginTime() {
		return tfs.get(1).getText();
	}

	public String getEndTime() {
		return tfs.get(2).getText();
	}

	public String getTimeFormat() {
		return tfs.get(3).getText();
	}

	public String getUP() {
		return tfs.get(4).getText();
	}
	
	public String getOutputFileName() {
		return outputTf.getText();
	}
}
