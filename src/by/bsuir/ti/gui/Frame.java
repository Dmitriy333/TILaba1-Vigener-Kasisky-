package by.bsuir.ti.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import by.bsuir.logic.KasiskyTest;
import by.bsuir.logic.Translator;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;


public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmOpenFile;
	private JMenuBar menuBar;
	private JPanel panelMain;
	private JLabel lblFileName;
	private JTextPane textPaneInput;
	private JTextPane textPaneTranslation;
	private JButton btnCipher;
	private JTextField textField;
	private JScrollPane scrollPaneInitText;
	private JMenuItem mntmSaveFile;
	private JPanel panelTextFields;
	private JLabel lblKeyWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("Laba1 MIAPR\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 448);
		frameDisplayCenter(this.getWidth() ,this.getHeight(),this);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmOpenFile = new JMenuItem("Open file");
		mntmOpenFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openFile();
            }
        });
		mnNewMenu.add(mntmOpenFile);
		
		mntmSaveFile = new JMenuItem("Save result");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveResult();
			}
		});
		mnNewMenu.add(mntmSaveFile);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panelMain = new JPanel();
		contentPane.add(panelMain, BorderLayout.CENTER);
		
		lblFileName = new JLabel("");
		
		panelTextFields = new JPanel();
		panelTextFields.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "TextField", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		panelTextFields.setToolTipText("");
		
		JPanel paneMenu = new JPanel();
		paneMenu.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Menu", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		
		lblKeyWord = new JLabel("");
		GroupLayout gl_panelMain = new GroupLayout(panelMain);
		gl_panelMain.setHorizontalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelMain.createSequentialGroup()
							.addComponent(lblKeyWord)
							.addContainerGap())
						.addGroup(gl_panelMain.createSequentialGroup()
							.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelMain.createSequentialGroup()
									.addComponent(panelTextFields, GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
									.addGap(29)
									.addComponent(paneMenu, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblFileName))
							.addGap(20))))
		);
		gl_panelMain.setVerticalGroup(
			gl_panelMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFileName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelMain.createParallelGroup(Alignment.LEADING)
						.addComponent(paneMenu, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelTextFields, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
					.addGap(51)
					.addComponent(lblKeyWord, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
		);
		
		btnCipher = new JButton("Cipher");
		btnCipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPaneInput.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Check inputing file",
							"Check input file", 1);
				}
				else if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Check the inputing of a key word.",
							"Check key word", 1);
				} 
				else{
					Translator translator = new Translator();
					StringBuilder sb = new StringBuilder();
					for (char character : translator.getRussianAlphabet()) {
						sb.append(character);
					}
					textPaneTranslation.setText(translator.
							encryptText(textPaneInput.getText(), textField.getText()));
				}
			}
		});
		
		JButton btnDecipher = new JButton("Decipher");
		
		textField = new JTextField();
		textField.setText("\u043B\u0435\u043D\u0438\u043D");
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key word:");
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaneTranslation.setText("");
				textPaneInput.setText("");
				lblFileName.setText("");
				lblKeyWord.setText("");
			}
		});
		
		JButton btnGetKeyWord = new JButton("Key Word");
		btnGetKeyWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPaneTranslation.setText("");
				KasiskyTest kasisky = new KasiskyTest();
				Integer n = kasisky.getLengthOfKeyWord(textPaneInput.getText().toLowerCase());
				lblKeyWord.setText("Размер ключа зашифрованного текста: " + n.toString());
			}
		});
		GroupLayout gl_paneMenu = new GroupLayout(paneMenu);
		gl_paneMenu.setHorizontalGroup(
			gl_paneMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_paneMenu.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_paneMenu.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGetKeyWord, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(btnClear, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 86, Short.MAX_VALUE)
						.addComponent(btnDecipher, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addComponent(btnCipher, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING))
					.addGap(15))
		);
		gl_paneMenu.setVerticalGroup(
			gl_paneMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneMenu.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(2)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCipher)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDecipher)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnGetKeyWord)
					.addGap(14))
		);
		paneMenu.setLayout(gl_paneMenu);
		btnDecipher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Translator translator = new Translator();
				if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Check the inputing of a key word.",
							"Check key word", 1);
				}else if(textPaneInput.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Check inputing file",
							"Check input file", 1);
				}else{
					textPaneTranslation.setText(translator.decryptText(textPaneInput.getText().toLowerCase(), textField.getText().toLowerCase()));	
				}
				
			}
		});
		
		JScrollPane scrollPaneResultText = new JScrollPane();
		scrollPaneResultText.setViewportBorder(new TitledBorder(null, "Result text", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		
		scrollPaneInitText = new JScrollPane();
		scrollPaneInitText.setViewportBorder(new TitledBorder(null, "Initial text", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, null));
		GroupLayout gl_panelTextFields = new GroupLayout(panelTextFields);
		gl_panelTextFields.setHorizontalGroup(
			gl_panelTextFields.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTextFields.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneInitText, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
					.addGap(40)
					.addComponent(scrollPaneResultText, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelTextFields.setVerticalGroup(
			gl_panelTextFields.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTextFields.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTextFields.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneResultText, Alignment.LEADING)
						.addComponent(scrollPaneInitText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
					.addGap(23))
		);
		textPaneInput = new JTextPane();
		scrollPaneInitText.setViewportView(textPaneInput);
		textPaneTranslation = new JTextPane();
		textPaneTranslation.setEditable(false);
		scrollPaneResultText.setViewportView(textPaneTranslation);
		panelTextFields.setLayout(gl_panelTextFields);
		panelMain.setLayout(gl_panelMain);
	}
	
	private void frameDisplayCenter (int sizeWidth, int sizeHeight, JFrame frame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int locationX = (screenSize.width - sizeWidth) / 2;
	    int locationY = (screenSize.height - sizeHeight) / 2;
	    frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
	}
	
	private void saveResult(){
		if(!textPaneTranslation.getText().equals("")){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Specify a file to save"); 
			int userSelection = fileChooser.showSaveDialog(null);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToSave = fileChooser.getSelectedFile();
			    FileWriter wrt = null;
			    try {
			    	wrt = new FileWriter(fileToSave);
					wrt.append(textPaneTranslation.getText());
					wrt.flush();
					wrt.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Translate text pane is empty. Nothing to save.",
					"Save result file", 1);
		}
	}
	private void openFile(){
        JFileChooser fileopen = new JFileChooser();             
        int ret = fileopen.showDialog(null, "Open file");                
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            lblFileName.setText(file.getAbsolutePath());
            StringBuilder sb = new StringBuilder();
            try {
    			BufferedReader br = new BufferedReader(new FileReader(file));
    			String line = br.readLine();
    			while (line != null) {
    				sb.append(line);
    				sb.append(System.lineSeparator());
    				line = br.readLine();
    			}
    			br.close();
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
            textPaneInput.setText(sb.toString());
        }
	}
}

