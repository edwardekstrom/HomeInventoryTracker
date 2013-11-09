package reports;

import hit_exceptions.InvalidRowException;

import java.util.ArrayList;
import java.util.List;

import visitor.ReportVisitor;
import builder.ReportBuilder;
import builder.TestBuilder;

public class ThisIsATestReport implements ReportInterface {

	@Override
	public void generateReport(ReportInterface report, ReportBuilder build) {
		TestBuilder tb = new TestBuilder();
		tb.buildReport(report);
	}

	@Override
	public String getHeader() {
        String header = "This is a Test Report";
		return header;
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		
		//Populate Table 1
		int numCols1 = 5;
		ArrayList<ReportTable> tableData = new ArrayList<ReportTable>();
		
		ReportTable table1 = new ReportTable(5);

		String[] headerRow1 = new String[numCols1];
		String firstColName = "Barcode";
		headerRow1[0] = firstColName;
		String secondColName = "Count";
		headerRow1[1] = secondColName;
		String thirdColName = "Unit";
		headerRow1[2] = thirdColName;
		String fourthColName = "Name";
		headerRow1[3] = fourthColName;
		String fifthColName = "Description";
		headerRow1[4] = fifthColName;
		table1.setHeaderRow(headerRow1);
		
		for(int i = 0;i<7;i++){	
			TableRow tableRow = new TableRow(numCols1);
			String[] rowData = new String[numCols1];
			int j = 0;
			String firstCol = "Barcode " + i;
			rowData[j] = firstCol;
			j++;
			String secondCol = "Count " + i;
			rowData[j] = secondCol;
			j++;
			String thirdCol = "Unit " + i;
			rowData[j] = thirdCol;
			j++;
			String fourthCol = "Name " + i;
			rowData[j] = fourthCol;
			j++;
			String fifthCol = "Description " + i;
			rowData[j] = fifthCol;
			j++;
			tableRow.setData(rowData);
			
			try {
				table1.addRow(tableRow);
			} catch (InvalidRowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		tableData.add(table1);
		//Finished Populating 1
		
		return tableData;
	}

	@Override
	public int getNumTables() {
		return 2;
	}

	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public ArrayList<ReportNotice> getNotices() {
		// TODO Auto-generated method stub
		return null;
	}

}
