package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Model.*;


public class Main_GUI {
	JFrame f = new JFrame("Auto Report Generator by Chuan");;
	
	JTextArea ta = new JTextArea(40, 130);
	// JTextField tf = new JTextField(20);
	// JTextField tfSign = new JTextField(20);
	// JTextField tfBeginTime = new JTextField(20);
	// JTextField tfEndTime = new JTextField(20);

	String[] labels = {"SigNums: ", "Report Begin from:", "Report End at: ", "Date Format"};
	ArrayList<JTextField> tfs;
	JButton run = new JButton("Run");;
	
	
	Dimension a = new Dimension(1200, 900);
	public Main_GUI(){
		f.setSize(a);
		f.setContentPane(this.createContentPane());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.pack();
		f.setVisible(true);
		
		tfs.get(0).setText("eyonduu");
		tfs.get(1).setText("Oct 07 2013 17 00 00");
		tfs.get(2).setText("Oct 08 2013 05 00 00");
		tfs.get(3).setText("MMM dd yyyy HH mm ss");

	}

	private JPanel createContentPane() {
		JPanel gui = new JPanel();
//		gui.setBackground(Color.black);
		
		gui.add(creatAddingPane());
	
		// ta.setBackground(Color.white);
		JScrollPane sp = new JScrollPane(ta);
		MessageConsole mc = new MessageConsole(ta);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);

		ta.setFont(new Font("Courier New", Font.PLAIN, 13));  
		// mc.setMessageLines(100);

		// sp.setOpaque(false);
		// sp.getViewport().setOpaque(false);

		gui.add(sp);
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
            JTextField textField = new JTextField(20);
            l.setLabelFor(textField);
            tfs.add(textField);
            p.add(textField);
        }
        
        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p,
                                        numPairs, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad
        JPanel pa = new JPanel();
        pa.add(p);
        pa.add(run);
        
        run.addActionListener(new RunListener());
        
		return pa;

	}
	
	private class RunListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getSource().equals(run)){

				HtmlExtract newExtract = new HtmlExtract();
				newExtract.startExtract();
				String summary = newExtract.summaryReport();
				// ta.append(summary);
				System.out.println(summary);
//				newExtract.write();
			}
		}
		
	}
	


}
