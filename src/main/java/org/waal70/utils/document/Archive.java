package org.waal70.utils.document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author awaal
 *	The main purpose of this class is to hold the different enums for the application
 *	An enum should ALWAYS follow the following design (in order to properly work with
 *	ComboBoxes:
 *	toString() (instance level) should be overridden with the text to display in the combobox
 *	Therefore, any enum should at least have a "Set" for display purposes.
 */
public class Archive {
	
	
	private static Logger log = LogManager.getLogger(Archive.class);

	public interface DocumentTypeInterface {

		String getDisplayableType();

		String getPath();
	}
	
	public enum Recipient {
		NAME1("Naám", "Naam"),
		NAME2("Name2"),
		NAME3("Name3"),
		NAME4("Name4"),
		IRIDEOS("Irideos"),
		KATEWAY("Kateway"),
		ALL("Allemaal", ""),
		NOBODY("Niemand", "");

		
		private String fileAs;
		private String displayAs;
		
		@Override
		public String toString() {
			return getDisplayAs();
		}
		
		public String getDisplayAs() {
			return this.displayAs;
		}
		
		public String getFileAs() {
			return this.fileAs;
		}
		
		public static String[] getEnumForCombo() {
			
			Recipient[] recipients = values();
			String[] cmbRecps = new String[recipients.length];
			
			for (int i=0;i<recipients.length;i++)
				cmbRecps[i]=recipients[i].getFileAs();
			
			return cmbRecps;
		}
		
		//Overloaded in case both are the same
		private Recipient(String displayAs) {
			this(displayAs, displayAs);
		}
		
		private Recipient(String displayAs, String fileAs)
		{
			this.displayAs = displayAs;
			this.fileAs = fileAs;
		}
	}

	/**
	 * @author awaal
	 * Should be as follows:
	 * ENUM_NAME(ENUM_PARENT, ARCHIVECODE, DISPLAYNAME, PATH_SUFFIX);
	 * 
	 * If path_suffix is null that means it is equal to displayname
	 */
	public enum DocumentType {
        ROOT(null,"Digitaal_Archief"),
        
        ALGEMEEN(ROOT, 0, "Algemeen"),
            Archief(ALGEMEEN,1,"Archief"),
            Divers(ALGEMEEN,2,"Divers"),
            Geenidee(ALGEMEEN,99,"Geenidee"),
            
         FINANCIEEL(ROOT,1,"Financieel"),
         	Rekeningen(FINANCIEEL,1,"Rekeningen"),
         	Rekeningafschriften(FINANCIEEL,2,"Rekeningafschriften"),
         	Salarisspecificaties(FINANCIEEL,3,"Salarisspecificaties"),
         	Declaraties(FINANCIEEL,4,"Declaraties"),
         	Jaaropgaven(FINANCIEEL,5,"Jaaropgaven"),
         	Kateway(FINANCIEEL,8,"Kateway"),
         	Irideos(FINANCIEEL,9,"Irideos"),
         	
         BONNEN(ROOT,2,"Bonnen_Facturen_Garanties"),
     
         CONTRACTEN(ROOT,3,"Contracten, Overeenkomsten"),
         
         CORRESPONDENTIE(ROOT,4,"Correspondentie"),
         	Inkomend(CORRESPONDENTIE,1,"Inkomend"),
         	Uitgaand(CORRESPONDENTIE,2,"Uitgaand"),
         	Irideos_cor(CORRESPONDENTIE,3,"Irideos"),
         	Kateway_cor(CORRESPONDENTIE,4,"Kateway"),
         	
         BELASTINGEN(ROOT,5,"Belastingen"),
         	Belastingdienst(BELASTINGEN,1,"Belastingdienst"),
         		Irideos_bel(Belastingdienst,1,"Irideos"),
         		Kateway_bel(Belastingdienst,2,"Kateway"),
         	Gemeentebelastingen(BELASTINGEN,2,"Gemeentebelastingen"),
         	
         MEDISCH(ROOT,6,"Medisch"),
         
         VERZEKERINGEN(ROOT,7,"Verzekeringen"),
         	Ziektekosten(VERZEKERINGEN,1,"Ziektekosten"),
         	Overig(VERZEKERINGEN,2,"Overig"),
         	
         OFFICIELE(ROOT,8,"Officiële documenten"),
         	Persoonlijk(OFFICIELE,1,"Persoonlijk"),
         	Akte(OFFICIELE,2,"Akten, Uittreksels"),
         	Zakelijk(OFFICIELE,3,"Zakelijk"),
         		Iri_zak(Zakelijk,1,"Irideos"),
         		Kate_zak(Zakelijk,2,"Kateway"),
         DIPLOMA(ROOT,9,"Diploma's, Certificaten")

    ;

		private DocumentType parent = null;
		private String path = "";
		private String displayAs = "";
		private int archivecode = 0;
		
		private List<DocumentType> children = new ArrayList<DocumentType>();

		private DocumentType(DocumentType parent, String path) {
			// no archivecode given, so assume zero
			this(parent, 0, path);

		}
		private DocumentType(DocumentType parent, int archivecode) {
			this(parent, archivecode, null);
		}

		private DocumentType(DocumentType parent, int archivecode, String path) {
			this.parent = parent;
			this.path = path;
			this.archivecode = archivecode;
			if (this.parent != null)
				this.parent.addChild(this);

		}
		private DocumentType(DocumentType parent, int archivecode, String displayAs, String path) {
			this.parent = parent;
			this.archivecode = archivecode;
			this.displayAs = displayAs;
			this.path=path;
		}
		@Override
		public String toString() {
			return this.getOnlyPath();
		}
		
		//Try out giving enum type
		public static DocumentType[] getCategoryCombo() {
			DocumentType[] doctypes = values();
			List<DocumentType> categories = new ArrayList<DocumentType>();
			for (int i=0;i<doctypes.length;i++)
			{
				//only add if parent is root!
				if (doctypes[i].getParent() == ROOT)
					categories.add(doctypes[i]);
			}
			return categories.toArray(new DocumentType[categories.size()]);
			
			
		}
		public static String[] getCategoryForCombo() {
			
			DocumentType[] doctypes = values();
			
			//slightly different approach because I only want the
			//main categories
			List<String> categories = new ArrayList<String>();
						
			for (int i=0;i<doctypes.length;i++)
			{
				//only add if parent is root!
				if (doctypes[i].getParent() == ROOT)
					categories.add(doctypes[i].getOnlyPath().replaceAll("_", ", "));
			}
			
			return categories.toArray(new String[categories.size()]);
		}
		
		public static String[] getTypeForCombo(DocumentType parentCategory) {
			
			DocumentType[] children = parentCategory.getChildren();
			String[] result = new String[children.length];
			
			for (int i=0;i<children.length;i++)
			{
				result[i] = children[i].getOnlyPath();
			}
			
			return result;
		}

		/**
		 * @return the parent
		 */
		public DocumentType getParent() {
			return parent;
		}

		/**
		 * @return the children
		 */
		public DocumentType[] getChildren() {
			//Compile a list of all first-level child elements, starting
			// at the start element
			// by iterating through the enum and adding elements that have this
			// as their starting element
			return children.toArray(new DocumentType[children.size()]);
	
		}
		
		private void addChild(DocumentType child)
		{
			log.debug("Adding child to tree");
			this.children.add(child);
		}

		private String getArchivecode() {
			// the archive code is a result of a concat of
			// the parents archive code and the current archivecode
			final String SEPARATOR = "_";
			if (parent == null)
				return "";
			else if (parent == ROOT)
				return String.format("%02d", archivecode) + SEPARATOR;
			else
				return parent.getArchivecode() + Integer.toString(archivecode) + SEPARATOR;

		}
		
		public String getOnlyPath() {
			return path;
		}

		/**
		 * The path consists of a fully qualified directory pointer
		 * 
		 * @return Returns the full file path, using system-specific separators
		 */
		public String getPath() {

			String result = "";
			if (parent == null)
				result = path;
			else
				result = parent.getPath() + File.separator + getArchivecode() + path;

			return result;

		}
	}

	public Archive() {
		super();
	}

}