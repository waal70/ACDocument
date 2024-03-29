package org.waal70.utils.document.gui;

import java.awt.Component;
import java.awt.FocusTraversalPolicy;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.waal70.utils.document.Archive.DocumentType;
import org.waal70.utils.document.convenience.Helper;
import org.waal70.utils.document.convenience.Messages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author awaal
 */
@SuppressWarnings("serial")
public class ACDocumentViewImpl extends java.awt.Frame implements ACDocumentView {
	
	private static Logger log = LogManager.getLogger(ACDocumentViewImpl.class);
	/**
     * Creates new form NewFrame
     */
    public ACDocumentViewImpl(ACDocumentController controller) {
    	this.controller = controller;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelScan = new javax.swing.JPanel();
        lblScanFileName = new javax.swing.JLabel();
        lblScanDated = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        txtScanDated = new javax.swing.JTextField();
        lblPDFVersion = new javax.swing.JLabel();
        txtPDFVersion = new javax.swing.JTextField();
        lblNumPages = new javax.swing.JLabel();
        txtNumPages = new javax.swing.JTextField();
        lblFileSize = new javax.swing.JLabel();
        txtFileSize = new javax.swing.JTextField();
        panelArchive = new javax.swing.JPanel();
        lblTargetDated = new javax.swing.JLabel();
        txtTargetDated = new javax.swing.JSpinner(new SpinnerDateModel());
        //txtTargetDated = new javax.swing.JFormattedTextField(df);
        lblSender = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        lblRecipient = new javax.swing.JLabel();
        cmbRecipient = new javax.swing.JComboBox<String>();
        cmbSenderCompany = new javax.swing.JComboBox<String>();
        lblMainCategory = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<DocumentType>();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<DocumentType>();
        lblTargetFileName = new javax.swing.JLabel();
        txtTargetFileName = new javax.swing.JTextField();
        lblTargetFolder = new javax.swing.JLabel();
        txtTargetFolder = new javax.swing.JTextField();
        panelActions = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        panelPDF = new JScrollPane();
        lblPDFPreview = new javax.swing.JLabel();
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        panelScan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("ACDocumentViewImpl.0"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N //$NON-NLS-1$ //$NON-NLS-2$

        lblScanFileName.setText(Messages.getString("ACDocumentViewImpl.2")); //$NON-NLS-1$

        lblScanDated.setText(Messages.getString("ACDocumentViewImpl.3")); //$NON-NLS-1$

        txtFileName.setEditable(false);
        txtFileName.setText(Messages.getString("ACDocumentViewImpl.4")); //$NON-NLS-1$
        txtFileName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtFileName.setEnabled(false);

        txtScanDated.setEditable(false);
        txtScanDated.setText(Messages.getString("ACDocumentViewImpl.5")); //$NON-NLS-1$
        txtScanDated.setEnabled(false);

        lblPDFVersion.setText(Messages.getString("ACDocumentViewImpl.6")); //$NON-NLS-1$

        txtPDFVersion.setEditable(false);
        txtPDFVersion.setText(Messages.getString("ACDocumentViewImpl.7")); //$NON-NLS-1$
        txtPDFVersion.setEnabled(false);

        lblNumPages.setText(Messages.getString("ACDocumentViewImpl.8")); //$NON-NLS-1$

        txtNumPages.setEditable(false);
        txtNumPages.setText(Messages.getString("ACDocumentViewImpl.9")); //$NON-NLS-1$
        txtNumPages.setEnabled(false);

        lblFileSize.setText(Messages.getString("ACDocumentViewImpl.10")); //$NON-NLS-1$

        txtFileSize.setEditable(false);
        txtFileSize.setText(Messages.getString("ACDocumentViewImpl.11")); //$NON-NLS-1$
        txtFileSize.setEnabled(false);

        javax.swing.GroupLayout panelScanLayout = new javax.swing.GroupLayout(panelScan);
        panelScan.setLayout(panelScanLayout);
        panelScanLayout.setHorizontalGroup(
            panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblScanDated)
                    .addComponent(lblScanFileName)
                    .addComponent(lblPDFVersion)
                    .addComponent(lblNumPages)
                    .addComponent(lblFileSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFileSize, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNumPages, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                        .addComponent(txtPDFVersion)
                        .addComponent(txtFileName)
                        .addComponent(txtScanDated)))
                .addContainerGap())
        );
        panelScanLayout.setVerticalGroup(
            panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelScanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblScanFileName)
                    .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblScanDated)
                    .addComponent(txtScanDated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPDFVersion)
                    .addComponent(txtPDFVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumPages)
                    .addComponent(txtNumPages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelScanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFileSize)
                    .addComponent(txtFileSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelArchive.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("ACDocumentViewImpl.12"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N //$NON-NLS-1$ //$NON-NLS-2$

        //txtTargetDated.setNextFocusableComponent(txtSenderCompany);
 /////BLOCK FOR TARGET DATE
        
        SimpleDateFormat datePattern = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
        JSpinner txtTargetDated = new JSpinner(new SpinnerDateModel());
        txtTargetDated.setEditor(new JSpinner.DateEditor(txtTargetDated, datePattern.toPattern()));
        lblTargetDated.setText(Messages.getString("ACDocumentViewImpl.15")); //$NON-NLS-1$
       
        txtTargetDated.addChangeListener(new ChangeListener() {
    		@Override
			public void stateChanged(ChangeEvent e) {
    	        controller.dateChanged((Date)txtTargetDated.getValue());
			}
        });
        
        
        ///ANDRE 10-12-2021: select whole field on focus
        javax.swing.JFormattedTextField jftf = ((JSpinner.DefaultEditor) txtTargetDated.getEditor()).getTextField();
        jftf.addFocusListener(fcsListener);
        ////END ANDRE
        
        
        //txtTargetDated.setFocusCycleRoot(true);
/////BLOCK FOR TARGET DATE       
        

        lblSender.setText(Messages.getString("ACDocumentViewImpl.16")); //$NON-NLS-1$
        cmbSenderCompany.addActionListener((ActionListener) controller);

        lblSubject.setText(Messages.getString("ACDocumentViewImpl.17")); //$NON-NLS-1$
        txtSubject.setText(Messages.getString("ACDocumentViewImpl.18")); //$NON-NLS-1$
        txtSubject.addActionListener((ActionListener) controller);

        lblRecipient.setText(Messages.getString("ACDocumentViewImpl.19")); //$NON-NLS-1$
        cmbRecipient.addActionListener((ActionListener) controller);

        lblMainCategory.setText(Messages.getString("ACDocumentViewImpl.20")); //$NON-NLS-1$
        cmbCategory.addActionListener(new ActionListener () {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		controller.categoryChanged(e);
        	}
        });

        lblType.setText(Messages.getString("ACDocumentViewImpl.21")); //$NON-NLS-1$
        cmbType.addActionListener(new ActionListener () {
        	@Override
			public void actionPerformed(ActionEvent e) {
				controller.docTypeChanged(e);
				
			}
        });

        lblTargetFileName.setText(Messages.getString("ACDocumentViewImpl.22")); //$NON-NLS-1$

        txtTargetFileName.setText(Messages.getString("ACDocumentViewImpl.23")); //$NON-NLS-1$

        lblTargetFolder.setText(Messages.getString("ACDocumentViewImpl.24")); //$NON-NLS-1$

        txtTargetFolder.setText(Messages.getString("ACDocumentViewImpl.25")); //$NON-NLS-1$
        txtTargetFolder.setEnabled(false);
        txtTargetFolder.setEditable(false); //Informative only

        javax.swing.GroupLayout panelArchiveLayout = new javax.swing.GroupLayout(panelArchive);
        panelArchive.setLayout(panelArchiveLayout);
        panelArchiveLayout.setHorizontalGroup(
            panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArchiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTargetFileName)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelArchiveLayout.createSequentialGroup()
                        .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTargetDated)
                            .addComponent(lblSender)
                            .addComponent(lblSubject)
                            .addComponent(lblRecipient)
                            .addComponent(lblMainCategory)
                            .addComponent(lblType)
                            .addComponent(lblTargetFileName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbType, 0, 147, Short.MAX_VALUE)
                            .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTargetDated)
                            .addComponent(txtSubject)
                            .addComponent(cmbRecipient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSenderCompany,0, 147, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelArchiveLayout.createSequentialGroup()
                        .addComponent(lblTargetFolder)
                        .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(txtTargetFolder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelArchiveLayout.setVerticalGroup(
            panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelArchiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTargetDated)
                    .addComponent(txtTargetDated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSender)
                    .addComponent(cmbSenderCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubject)
                    .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecipient)
                    .addComponent(cmbRecipient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMainCategory)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblType)
                    .addComponent(cmbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTargetFileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTargetFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTargetFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTargetFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelActions.setBorder(javax.swing.BorderFactory.createTitledBorder(null, Messages.getString("ACDocumentViewImpl.26"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N //$NON-NLS-1$ //$NON-NLS-2$

        btnNext.setText(Messages.getString("ACDocumentViewImpl.28")); //$NON-NLS-1$
        btnNext.addActionListener((ActionListener) controller);

        btnPrevious.setText(Messages.getString("ACDocumentViewImpl.29")); //$NON-NLS-1$
        btnPrevious.addActionListener((ActionListener) controller);

        javax.swing.GroupLayout panelActionsLayout = new javax.swing.GroupLayout(panelActions);
        panelActions.setLayout(panelActionsLayout);
        panelActionsLayout.setHorizontalGroup(
            panelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelActionsLayout.createSequentialGroup()
                .addComponent(btnPrevious)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNext)
                .addContainerGap())
        );
        panelActionsLayout.setVerticalGroup(
            panelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(btnPrevious))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        lblPDFPreview.setText(""); //$NON-NLS-1$
       // panelPDF.add(lblPDFPreview);
        JScrollPane panelPDF = new JScrollPane(lblPDFPreview, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelScan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelArchive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelScan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelArchive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        //Setting the TAB-order:
        Vector<Component> order = new Vector<Component>(6);
        JSpinner.DateEditor spinEdit = (JSpinner.DateEditor)txtTargetDated.getEditor();
        order.add(spinEdit.getTextField());
        order.add(cmbSenderCompany);
        txtSubject.addFocusListener((FocusListener)controller);
        order.add(txtSubject);
        order.add(cmbRecipient);
        order.add(cmbCategory);
        order.add(cmbType);
        order.add(txtTargetFileName);
        
        FocusTraversalPolicy newPolicy = new MainTraversalPolicy(order);
        
        pack();
        this.getFrame().setFocusTraversalPolicy(newPolicy);
        
        log.debug("View after pack()"); //$NON-NLS-1$
    }// </editor-fold>//GEN-END:initComponents                      

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {
    	Helper.openExplorer();
        System.exit(0);
    }                         

 
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private JComboBox<DocumentType> cmbCategory;
    private javax.swing.JComboBox<String> cmbRecipient;
    private JComboBox<DocumentType> cmbType;
    private javax.swing.JLabel lblFileSize;
    private javax.swing.JLabel lblMainCategory;
    private javax.swing.JLabel lblNumPages;
    private javax.swing.JLabel lblPDFPreview;
    private javax.swing.JLabel lblPDFVersion;
    private javax.swing.JLabel lblRecipient;
    private javax.swing.JLabel lblScanDated;
    private javax.swing.JLabel lblScanFileName;
    private javax.swing.JLabel lblSender;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblTargetDated;
    private javax.swing.JLabel lblTargetFileName;
    private javax.swing.JLabel lblTargetFolder;
    private javax.swing.JLabel lblType;
    private javax.swing.JPanel panelActions;
    private javax.swing.JPanel panelArchive;
    @SuppressWarnings("unused")
	private javax.swing.JScrollPane panelPDF;
    private javax.swing.JPanel panelScan;
    private javax.swing.JTextField txtFileName;
    private javax.swing.JTextField txtFileSize;
    private javax.swing.JTextField txtNumPages;
    private javax.swing.JTextField txtPDFVersion;
    private javax.swing.JTextField txtScanDated;
    private javax.swing.JComboBox<String> cmbSenderCompany;
    private javax.swing.JTextField txtSubject;
    private javax.swing.JSpinner txtTargetDated;
    private javax.swing.JTextField txtTargetFileName;
    private javax.swing.JTextField txtTargetFolder;
    // End of variables declaration
    private ACDocumentController controller;
	@Override
	public void setPDFPreview(BufferedImage preview) {
		
		lblPDFPreview.setIcon(new ImageIcon(preview));
		
	}

	@Override
	public void setScanFileName(String text) {
		txtFileName.setText(text);
		
	}

	@Override
	public String getScanFileName() {
		return txtFileName.getText();
	}

	@Override
	public void setTargetFileName(String text) {
		txtTargetFileName.setText(text);
		
	}

	@Override
	public String getTargetFileName() {
		return txtTargetFileName.getText();
	}

	@Override
	public void setRecipient(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRecipient() {
		return cmbRecipient.getModel().getSelectedItem().toString();
	}

	@Override
	public void setSenderCompany(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSenderCompany() {
		return cmbSenderCompany.getModel().getSelectedItem().toString();
	}

	@Override
	public void setScanDated(String date) {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
		txtScanDated.setText(date);
		
	}

	@Override
	public String getScanDated() {
		return "0";
	}

	@Override
	public void setTargetDated(Date date) {
		txtTargetDated.setModel(new SpinnerDateModel());
		txtTargetDated.updateUI();
		//txtTargetDated.getModel().setValue(value);
		//txtTargetDated.setValue(date);
		
	}

	@Override
	public Date getTargetDated() {
		log.info("In view: " + txtTargetDated.getValue().toString()); //$NON-NLS-1$
		return (Date) txtTargetDated.getValue();
	}

	@Override
	public Frame getFrame() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void setPDFVersion(String text) {
		txtPDFVersion.setText(text);
		
	}

	@Override
	public void setNumPages(String text) {
		txtNumPages.setText(text);
		
	}

	@Override
	public void setFileSize(String text) {
		txtFileSize.setText(text);
		
	}

	@Override
	public void setCompanyCombo(String[] text) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(text);
		cmbSenderCompany.setModel(model);
		//cmbSenderCompany.setS
		//cmbSenderCompany.setEditable(true);
	
	}

	@Override
	public void setCategoryCombo(DocumentType[] text) {
		DefaultComboBoxModel<DocumentType> model = new DefaultComboBoxModel<DocumentType>(text);
		cmbCategory.setModel(model);

	}

	@Override
	public void setTypeCombo(DocumentType[] text) {
		//This one is depending on the Category combo:
		DefaultComboBoxModel<DocumentType> model = new DefaultComboBoxModel<DocumentType>(text);
		cmbType.setModel(model);
	}
	
	@Override
	public void setRecipientCombo(String[] text) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(text);
		cmbRecipient.setModel(model);
		
	}
	
	@Override
	public void disableTypeCombo() {
		cmbType.setEnabled(false);
	}
	
	@Override
	public void enableTypeCombo() {
		cmbType.setEnabled(true);
	}

	@Override
	public DocumentType getTypeCombo() {
		return (DocumentType) cmbType.getModel().getSelectedItem();
	}

	@Override
	public DocumentType getCategoryCombo() {
		return (DocumentType) cmbCategory.getModel().getSelectedItem();
	}

	@Override
	public String getSubject() {
		return txtSubject.getText();
	}

	@Override
	public void setTargetPath(String text) {
		txtTargetFolder.setText(text);
		
	}

	@Override
	public void disableButtons() {
		
		btnNext.setEnabled(false);
		btnPrevious.setEnabled(false);
		
	}

	@Override
	public void setSubject(String text) {
		txtSubject.setText(text);
		
	}
	
	//ANDRE 10-12-2021: Need focus listener for select all
	private FocusListener fcsListener = new FocusListener() {
        
		@Override
        public void focusGained(FocusEvent e) {
            setSelectAll(e);
        }

        @Override
        public void focusLost(FocusEvent e) {
            setSelectAll(e);
        }

        private void setSelectAll(FocusEvent e) {
            log.info("Setting select all on  : " + e.getComponent().getName());
            final Component c = e.getComponent();
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        ((javax.swing.JTextField) c).setText(((javax.swing.JTextField) c).getText());
                        ((javax.swing.JTextField) c).selectAll();
                    }
                });
           }
      };
      ///ANDRE END need focuslistener



}
