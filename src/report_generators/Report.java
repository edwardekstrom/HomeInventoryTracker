/**
 * 
 */
package report_generators;

/**
 * @author edwardekstrom
 * The Report Interface is the layout for all report generators.
 * Report generators have basic functionality that they must
 * implement, in addition to their individual functoins.
 */
public interface Report {
	
	/**
	 * generateReport() will gather the data and create the 
	 * report.  Then it will turn it into a pdf.
	 */
	abstract void generateReport();
	
	/**
	 * makePDF() will generate the report then set the PDF
	 * version.
	 */
	abstract void makePDF();
	
	/**
	 * makeHTML() will generate the report then set the HTML
	 * version.
	 */
	abstract void makeHTML();
	
	/**
	 * printReport() generates then prints the report
	 * to either a PDF or an HTML file.
	 */
	public void printReport();
	

	
}
