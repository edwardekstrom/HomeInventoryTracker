package reports;

import java.util.ArrayList;
import java.util.List;

import builder.ReportBuilder;
import visitor.ReportVisitor;

public interface ReportInterface {
	
	/**generates the needed report using the correct visitor and builder
	 * 
	 * @param visit
	 * @param build
	 * @precondition given the correct ReportVisitor and ReportBuilder
	 * @postcondition gives the correct report in the correct format
	 */
	public void generateReport(ReportInterface report, ReportBuilder build);
	
	/**
	 * 
	 * @return the header for the report
	 */
	public String getHeader();
	
	/**A list of the column names for the tables
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<String>> getColumnNames();
	
	public ArrayList<ArrayList<String>> getTableData();
	
	//currently doung for each
	public int getNumTables();
	
	//returns the number of columns in the table (wont need if use double lists
	public int getNumColumns();

}
