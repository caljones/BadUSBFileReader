package DefaultPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class UI extends JFrame implements ActionListener {

	private JPanel panel;
	private JButton btnLoadFile;
	private JButton btnSaveFile;
	private FontUIResource customFont = new FontUIResource("custom font", 3, 10);
	private ArrayList<String> list;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UI() {

		list = new ArrayList<String>();

		setSize(300, 75);
		setLocation(50, 50);
		setTitle("Bad USB File Reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		btnLoadFile = new JButton("Load from File");
		btnSaveFile = new JButton("Save to File");
		btnLoadFile.setFont(customFont);
		btnSaveFile.setFont(customFont);
		btnLoadFile.addActionListener(this);
		btnSaveFile.addActionListener(this);
		btnLoadFile.setForeground(Color.white);
		btnSaveFile.setForeground(Color.white);
		btnLoadFile.setBackground(Color.red);
		btnSaveFile.setBackground(Color.blue);

		panel.add(btnLoadFile);
		panel.add(btnSaveFile);
		
		Container c = this.getContentPane();
		c.add(panel, BorderLayout.CENTER);
		
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			if (e.getSource() == btnLoadFile) {

				list = IO.readFile(getFileName(true));

			} else if (e.getSource() == btnSaveFile) {

				if (list == null) {
					throw new IllegalArgumentException("Load a file before saving to a file");
				}

				IO.writeFile(getFileName(false), list);

			}

		} catch (IllegalArgumentException | IllegalStateException iae) {
			JOptionPane.showMessageDialog(this, iae.getMessage());
		}

	}

	private String getFileName(boolean chooserType) {
		JFileChooser fc = new JFileChooser("./");
		fc.setApproveButtonText("Select");
		int returnVal = Integer.MIN_VALUE;
		if (chooserType) {
			fc.setDialogTitle("Load File for Formatting");
			returnVal = fc.showOpenDialog(this);
		} else {
			fc.setDialogTitle("Save Output to File");
			returnVal = fc.showSaveDialog(this);
		}
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			throw new IllegalStateException("Invalid selection.");
		}
		return fc.getSelectedFile().getAbsolutePath();
	}

	public static void main(String[] args) {
		new UI();
	}

}
