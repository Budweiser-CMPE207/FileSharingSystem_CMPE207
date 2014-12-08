/*
 * Emy Itegbe	
 * Group BudWeiser
 * CMPE 207
 * 008740953
 * This file creates the text editor with several functionality
 * such as able to write and save the file. Also open the file
 * as well upload the file to the server. 
 */
package cmpe_207;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class JavaGUIEditor extends JPanel implements ActionListener {

	private JMenu fileM, editM, styleM, fontM;
	private JButton button1, button2, button3;
	private JTextArea textArea;
	private JTextArea textArea2;
	private String pad;
	private JLabel label;
	private JTextField text;
	private JMenuBar menuBar;
	private JScrollPane scpane;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private JPanel panel1, panel3;
	private JPanel panel2;
	
	private JMenuItem aboutI, openI, saveI, cutI, copyI, pasteI, exitI;
	CloseableHttpClient httpClient = null;  
	static String URL = "http://emysdomain.com/";
	ArrayList<DBInfo> file_info = new ArrayList<DBInfo>();

	public JavaGUIEditor(CloseableHttpClient httpClient, String phpURL) {
		setLayout(new GridBagLayout());
		this.httpClient = httpClient;
		this.URL = phpURL;
		initialize();
	}

	private void initialize() {
			
		panel1 = new JPanel(new BorderLayout());
		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createTitledBorder("File to upload"));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		pad ="";
		textArea2 = new JTextArea(40, 50);
		Font font = new Font("Times", Font.PLAIN, 12);
		textArea2.setFont(font);
		textArea2.setLineWrap(true);
		textArea2.setWrapStyleWord(true);
		textArea2.addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						if(SwingUtilities.isRightMouseButton(e))
						{
							System.out.println("Right Button Pressed");
						}
					}
				});
		scpane = new JScrollPane(textArea2);
		scpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		label = new JLabel("Filename: ");
		text = new JTextField(25);
		
		button1 = new JButton("Upload");
		button1.setToolTipText("Click to upload a file");
		button1.addActionListener(this);
		button2 = new JButton("Submit");
		button2.setToolTipText("Submit file to server..");
		button2.addActionListener(this);
		button3 = new JButton("View Database");
		button3.addActionListener(this);

		openI = new JMenuItem("Open");
		
		openI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				JFileChooser openFile = new JFileChooser();
				int result = openFile.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = openFile.getSelectedFile();
					String filename = selectedFile.getAbsolutePath();
					System.out.println("Selected file: "
							+ selectedFile.getAbsolutePath());
					try {
						reader = new BufferedReader(new FileReader(filename));
					    String temp = "";
						while ((temp = reader.readLine()) != null) {
							textArea2.append(temp);
						}
						reader.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		});
		openI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		
		
		saveI = new JMenuItem("Save");
		saveI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser saveFile = new JFileChooser();
				saveFile.addChoosableFileFilter(null);
				int result = saveFile.showSaveDialog(null);
				if (result != JFileChooser.APPROVE_OPTION) {
					return;
				} else {
					File selectedFile = new File(saveFile.getSelectedFile()
							+ ".txt");
					String filename = selectedFile.getAbsolutePath();
					try {
						writer = new BufferedWriter(new FileWriter(filename));
						textArea2.write(writer);

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (writer != null) {
							try {
								writer.close();
								JOptionPane.showMessageDialog(null,
										"The File was Saved Successfully!",
										"Success!",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null,
										"The File Could not be Saved!",
										"ERROR!", JOptionPane.ERROR_MESSAGE);
							}
						}

					}
				}
			}
		});
		saveI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exitI = new JMenuItem("Exit");
		exitI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		copyI = new JMenuItem("Copy");
		copyI.addActionListener(this);
		copyI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		pasteI = new JMenuItem("Paste");
		pasteI.addActionListener(this);
		pasteI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		aboutI = new JMenuItem("About");
		aboutI.addActionListener(this);
	    cutI = new JMenuItem("Cut");
	    cutI.addActionListener(this);
	    cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		fileM = new JMenu("File");
		editM = new JMenu("Edit");
		styleM = new JMenu("Style");

		fileM.add(openI);
		fileM.add(saveI);
		fileM.add(aboutI);
		fileM.add(exitI);

		editM.add(copyI);
		editM.add(pasteI);
		editM.add(cutI);

		menuBar = new JMenuBar();
		menuBar.add(fileM);
		menuBar.add(editM);
		menuBar.add(styleM);
		
		panel1.add(menuBar,BorderLayout.NORTH);
	    panel2.add(scpane, BorderLayout.CENTER);
	    panel3.add(button1);
	    panel3.add(label);
	    panel3.add(text);
	    panel3.add(button2);
	    panel3.add(button3);
	
	    addComp(panel1,0,0,0.5,0.0,1,1,GridBagConstraints.NORTHEAST,GridBagConstraints.HORIZONTAL);
		addComp(panel2,0,1,0.0,0,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE);
		addComp(panel3,0,2,0.5,0.0,2,2,GridBagConstraints.LAST_LINE_START, GridBagConstraints.NONE);	
		
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == button1) {
			JFileChooser choose = new JFileChooser();
			int result = choose.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = choose.getSelectedFile();
				String filename = file.getAbsolutePath();
				text.setText(filename);
			}
		} else if (event.getSource() == button2) {
			if (text.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Please select a file to be uploaded!", "Error!",
						JOptionPane.ERROR_MESSAGE);
			} else {
					try {
						CloseableHttpClient httpClient = HttpClients
								.createDefault();
						
						File file = new File(text.getText());
						JavaHttpClient.uploadFile(httpClient, file, URL);
						JOptionPane.showMessageDialog(null,
								"The File was ulpoaded Successfully!",
								"Success!",
							JOptionPane.INFORMATION_MESSAGE);
					}
					
					catch (Exception e) {
					e.printStackTrace();
					}	
			}
		}
			
		else if(event.getSource()==button3)
		{
			new DBWindow();
		}
	}
	
	//Layout components..
	private void addComp(JPanel panel, int Xpos, int Ypos, double wx,double wy,
			            int compWidth, int compHeight, int place, int stretch)
	{
		GridBagConstraints g = new GridBagConstraints();
	    g.gridx = Xpos;
	    g.gridy = Ypos;
	    g.weightx = wx;
	    g.weighty= wy;
	    g.gridwidth = compWidth;
	    g.gridheight = compHeight;
	    g.insets = new Insets(5,5,5,5);
	    g.anchor = place;
	    g.fill = stretch;

	    add(panel, g);
	}
}


