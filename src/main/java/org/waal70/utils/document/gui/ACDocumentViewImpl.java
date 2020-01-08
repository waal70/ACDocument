package org.waal70.utils.document.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ACDocumentViewImpl implements ACDocumentView{

	private JFrame frmAcdocument;
	private ACDocumentController myController;
	private List<ButtonObserver> observers;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the application.
	 */
	public ACDocumentViewImpl(ACDocumentController controller) {
		myController = controller;
		observers = new ArrayList<>(25);
		initialize();
	}
	
	public JFrame getFrame() {
		return frmAcdocument;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAcdocument = new JFrame();
		frmAcdocument.setTitle("ACDocument");
		frmAcdocument.setBounds(100, 100, 676, 486);
		frmAcdocument.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAcdocument.getContentPane().setLayout(new BoxLayout(frmAcdocument.getContentPane(), BoxLayout.X_AXIS));
		
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		
		JDesktopPane desktopPane = new JDesktopPane();
		frmAcdocument.getContentPane().add(desktopPane);
		
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(6, 389, 237, 69);
		desktopPane.add(actionPanel);
		
		JButton btnPrevious = new JButton("Previous");
		actionPanel.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		actionPanel.add(btnNext);
		btnNext.addActionListener((ActionListener) myController);
		
		JButton btnNewButton = new JButton("New button");
		actionPanel.add(btnNewButton);
		
		JPanel PDFPanel = new JPanel();
		PDFPanel.setBounds(248, 6, 422, 452);
		desktopPane.add(PDFPanel);
		
		Label lblPDF = new Label("New label");
		PDFPanel.add(lblPDF);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 237, 371);
		desktopPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Input1");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Input2");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Input3");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 3;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addButtonObserver(ButtonObserver observer) {
		observers.add(observer);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeButtonObserver(ButtonObserver observer) {
		observers.remove(observer);
		
	}
	protected void buttonChanged() {
		for (ButtonObserver observer : observers) {
			observer.buttonWasChanged(this);
		}
	}

}
