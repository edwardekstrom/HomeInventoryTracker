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
		//TestBuilder tb = new TestBuilder();
		build.buildReport(report);
	}

	@Override
	public String getHeader() {
        String header = "This is a Test Report";
		return header;
	}

	@Override
	public ArrayList<ReportTable> getTableData() {
		
		ArrayList<ReportTable> tableData = new ArrayList<ReportTable>();
		
		//Populate Table 1
		int numCols1 = 5;

		ReportTable table1 = new ReportTable(numCols1, "fiveTable");

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
		
		//Populate Table 2
		int numCols2 = 7;
		
		ReportTable table2 = new ReportTable(numCols2, "sevenTable");

		String[] headerRow2 = new String[numCols2];
		String firstColName2 = "Barn";
		headerRow2[0] = firstColName2;
		String secondColName2 = "Junk";
		headerRow2[1] = secondColName2;
		String thirdColName2 = "Stuff";
		headerRow2[2] = thirdColName2;
		String fourthColName2 = "Thangs";
		headerRow2[3] = fourthColName2;
		String fifthColName2 = "Desc";
		headerRow2[4] = fifthColName2;
		String sixthColName2 = "Disk";
		headerRow2[5] = sixthColName2;
		String seventhColName2 = "LOTR";
		headerRow2[6] = seventhColName2;
		table2.setHeaderRow(headerRow2);
		
		for(int i = 0;i<17;i++){	
			TableRow tableRow = new TableRow(numCols2);
			String[] rowData = new String[numCols2];
			int j = 0;
			String firstCol = "Barn " + i;
			rowData[j] = firstCol;
			j++;
			String secondCol = "Junk " + i;
			rowData[j] = secondCol;
			j++;
			String thirdCol = "Stuff " + i;
			rowData[j] = thirdCol;
			j++;
			String fourthCol = "Thangs " + i;
			rowData[j] = fourthCol;
			j++;
			String fifthCol = "Desc " + i;
			rowData[j] = fifthCol;
			j++;
			String sixthCol = "Disk " + i;
			rowData[j] = sixthCol;
			j++;
			String seventhCol = "LOTR " + i;
			rowData[j] = seventhCol;
			j++;
			tableRow.setData(rowData);
			
			try {
				table2.addRow(tableRow);
			} catch (InvalidRowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		tableData.add(table2);
		//Finished Populating 2

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
		
		//Populate Notices
		ArrayList<ReportNotice> noticeDatas = new ArrayList<ReportNotice>();
		ReportNotice noticeType1 = new ReportNotice();
		int n = 3;
		noticeType1.setNoticeType(n + "-Month Supply Warnings");
		
		//How The Tree Looks
		/**
		 * StorageUnits
		 * -BagOfCookies (50 kg)
		 *  ~ChocolateChip 
		 *   *HomeMade (1 Count)
		 *    +Grandma'sCookies (5 grams)
		 *    +MyHomeMadeCookies (1 count)
		 *   *StoreBought (1 pints)
		 *    +ChipsAhoyChocolate (5 kg)
		 *    +OreoAndChips (1 gallons)
		 */
		
		
		NoticeData notice1 = new NoticeData();
		//Product group <storageunit/ProdGroup>::<prodgroup> has a <n>-month supply (<supply>) that is inconsistent with the following products:
		notice1.setDescription("Product group BagOfCookies::ChocolateChip has a "+n+"-month supply (50 kilograms) that is inconsistent with the following products:");
		
		//-<productgroup>::<itemname/productgroup> (size: <num> <unit>)
		notice1.addOffender("-HomeMade::MyHomeMadeCookies (size: 1 count)");
		notice1.addOffender("-StoreBought::OreoAndChips (size: 1 gallons)");
		noticeType1.addNotice(notice1);
		
		NoticeData notice2 = new NoticeData();
		//Product group <storageunit/ProdGroup>::<prodgroup> has a <n>-month supply (<supply>) that is inconsistent with the following products:
		notice2.setDescription("Product group BagOfCookies::StoreBought has a "+n+"-month supply (1 pints) that is inconsistent with the following products:");
		
		//-<productgroup>::<itemname/productgroup> (size: <num> <unit>)
		notice2.addOffender("-StoreBought::ChipsAhoyChocolate (size: 5 kilograms)");
		noticeType1.addNotice(notice2);
		
		noticeDatas.add(noticeType1);

		//End Populate Notices
		return noticeDatas;
	}

}
