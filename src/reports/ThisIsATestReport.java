package reports;

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
	public ArrayList<ArrayList<String>> getColumnNames() {
		
		ArrayList<ArrayList<String>> tableHeaders = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> colNames = new ArrayList<String>();
		//table 1
		String firstCol = "Barcode";
		colNames.add(firstCol);
		String secondCol = "Count";
		colNames.add(secondCol);
		String thirdCol = "Unit";
		colNames.add(thirdCol);
		String fourthCol = "Name";
		colNames.add(fourthCol);
		String fifthCol = "Description";
		colNames.add(fifthCol);
		tableHeaders.add(colNames);
		
		ArrayList<String> colNames2 = new ArrayList<String>();
		//table 2
		String firstCol2 = "ID";
		colNames2.add(firstCol2);
		String secondCol2 = "Num";
		colNames2.add(secondCol2);
		String thirdCol2 = "Type";
		colNames2.add(thirdCol2);
		String fourthCol2 = "Name";
		colNames2.add(fourthCol2);
		String fifthCol2 = "Desc";
		colNames2.add(fifthCol2);
		tableHeaders.add(colNames2);
		
		return tableHeaders;
	}

	@Override
	public ArrayList<ArrayList<String>> getTableData() {
		
		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> rowData = new ArrayList<String>();
		for(int i = 0;i<7;i++){		
			String firstCol = "Barcode " + i;
			rowData.add(firstCol);
			String secondCol = "Count " + i;
			rowData.add(secondCol);
			String thirdCol = "Unit " + i;
			rowData.add(thirdCol);
			String fourthCol = "Name " + i;
			rowData.add(fourthCol);
			String fifthCol = "Description " + i;
			rowData.add(fifthCol);
			
		}
		tableData.add(rowData);
		
		ArrayList<String> rowData2 = new ArrayList<String>();
		for(int i = 0;i<17;i++){		
			String firstCol = "ID " + i;
			rowData2.add(firstCol);
			String secondCol = "Num " + i;
			rowData2.add(secondCol);
			String thirdCol = "Type " + i;
			rowData2.add(thirdCol);
			String fourthCol = "Name " + i;
			rowData2.add(fourthCol);
			String fifthCol = "Desc " + i;
			rowData2.add(fifthCol);

		}			
		tableData.add(rowData2);
		
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

}
