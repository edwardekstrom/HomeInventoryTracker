package builder;

import java.util.ArrayList;

import reports.ReportInterface;

public class TestBuilder implements ReportBuilder {

	
	/**uses the data that has been gathered to generate a report in the format that the concrete builder uses
	 * @precondition data from the model gathered by the visitors
	 * @postcondition the data given in the desired format
	 */ 
	@Override
	public void buildReport(ReportInterface report) {
		
		String header = "<h1>" + report.getHeader() + "</h1><br><br>";
		System.out.println(header);
		
		
//		ArrayList<ArrayList<String>> colNames = report.getColumnNames();
//		int numTables = report.getNumTables();
//		int numColumns = report.getNumColumns();
//		
//		int i = 0;
//		for(ArrayList<String> tableHead : colNames){
//			System.out.println("<table>");
//			for(String col : tableHead){
//				System.out.println("  <tr>" + col + "</tr>");
//			}
//			ArrayList<String> tableData = report.getTableData().get(i);
//			
//			for(String colData : tableData){
//				System.out.println("    <td>" + colData + "</td>");
//			}
//			
//			System.out.println("</table>");
//			i++;
//		}
		
		

	}

}
