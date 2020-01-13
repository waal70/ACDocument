package org.waal70.utils.document.gui;

import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        txtTargetDated = new javax.swing.JSpinner();
        //txtTargetDated = new javax.swing.JFormattedTextField(df);
        lblSender = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        lblRecipient = new javax.swing.JLabel();
        cmbRecipient = new javax.swing.JComboBox<>();
        cmbSenderCompany = new javax.swing.JComboBox();
        lblMainCategory = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        lblType = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<>();
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

        panelScan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Scan properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

        lblScanFileName.setText("Filename:");

        lblScanDated.setText("Scan date:");

        txtFileName.setEditable(false);
        txtFileName.setText("jTextField1");
        txtFileName.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtFileName.setEnabled(false);

        txtScanDated.setEditable(false);
        txtScanDated.setText("jTextField2");
        txtScanDated.setEnabled(false);

        lblPDFVersion.setText("PDF Version:");

        txtPDFVersion.setEditable(false);
        txtPDFVersion.setText("jTextField1");
        txtPDFVersion.setEnabled(false);

        lblNumPages.setText("Pages:");

        txtNumPages.setEditable(false);
        txtNumPages.setText("jTextField1");
        txtNumPages.setEnabled(false);

        lblFileSize.setText("Size:");

        txtFileSize.setEditable(false);
        txtFileSize.setText("jTextField1");
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

        panelArchive.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Archive properties", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

 /////BLOCK FOR TARGET DATE
        
        SimpleDateFormat datePattern = new SimpleDateFormat("dd-MM-yyyy");
        JSpinner txtTargetDated = new JSpinner(new SpinnerDateModel());
        txtTargetDated.setEditor(new JSpinner.DateEditor(txtTargetDated, datePattern.toPattern()));
        
        
        lblTargetDated.setText("Dated:");
       // txtTargetDated.setColumns(20);
       // try {
       //    MaskFormatter dateMask = new MaskFormatter("##-##-####");
       //     dateMask.setPlaceholderCharacter('_');
       //     dateMask.install(txtTargetDated);
       // } catch (ParseException ex) {
       //    log.error("Unable to parse date: " + ex.getLocalizedMessage());
       // }

       
        txtTargetDated.addChangeListener(new ChangeListener() {
    		@Override
			public void stateChanged(ChangeEvent e) {
				controller.dateChanged(e);
			}
        });
/////BLOCK FOR TARGET DATE       
        

        lblSender.setText("Sender:");

        lblSubject.setText("Subject:");

        txtSubject.setText("jTextField1");

        lblRecipient.setText("Recipient:");

        cmbRecipient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        //cmbSenderCompany.setText("jTextField1");

        lblMainCategory.setText("Category:");

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCategory.addActionListener((ActionListener) controller);

        lblType.setText("Type:");

        cmbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblTargetFileName.setText("Target name:");

        txtTargetFileName.setText("jTextField1");

        lblTargetFolder.setText("Will be filed under:");

        txtTargetFolder.setText("jTextField1");

        javax.swing.GroupLayout panelArchiveLayout = new javax.swing.GroupLayout(panelArchive);
        panelArchive.setLayout(panelArchiveLayout);
        panelArchiveLayout.setHorizontalGroup(
            panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelArchiveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTargetFolder)
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
                            .addComponent(txtTargetFileName)
                            .addComponent(cmbType, 0, 147, Short.MAX_VALUE)
                            .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTargetDated)
                            .addComponent(txtSubject)
                            .addComponent(cmbRecipient, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSenderCompany,0, 147, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelArchiveLayout.createSequentialGroup()
                        .addComponent(lblTargetFolder)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addGroup(panelArchiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTargetFileName)
                    .addComponent(txtTargetFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTargetFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTargetFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelActions.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 12))); // NOI18N

        btnNext.setText("Next");
        btnNext.addActionListener((ActionListener) controller);
        //btnNext.addActionListener(new java.awt.event.ActionListener() {
         //   public void actionPerformed(java.awt.event.ActionEvent evt) {
         //       btnNextActionPerformed(evt);
        //    }
        //});

        btnPrevious.setText("Back");

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

        lblPDFPreview.setText("");
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
                .addComponent(panelPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
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

        pack();
        log.info("View after pack()");
    }// </editor-fold>                        

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {                          
        System.exit(0);
    }                         

 
    // Variables declaration - do not modify                     
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbRecipient;
    private javax.swing.JComboBox<String> cmbType;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTargetFileName(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTargetFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRecipient(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRecipient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSenderCompany(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSenderCompany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScanDated(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		txtScanDated.setText(sdf.format(new Date(date)));
		
	}

	@Override
	public long getScanDated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTargetDated(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calendar getTargetDated() {
		// TODO Auto-generated method stub
		return null;
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
	public void setCategoryCombo(String[] text) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(text);
		cmbCategory.setModel(model);
		
	}

	@Override
	public String getCurrentCategory() {
		return cmbCategory.getSelectedItem().toString();
	}

	@Override
	public void setTypeCombo(String[] text) {
		//This one is depending on the Category combo:
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(text);
		cmbType.setModel(model);
		
		
	}
}
