package builder;

import java.util.ArrayList;

import reports.NoticeData;
import reports.ReportInterface;
import reports.ReportNotice;
import reports.ReportTable;
import reports.TableRow;

public abstract class ReportBuilder {

	/**uses the data that has been gathered to generate a report in the format that the concrete builder uses
	 * @precondition data from the model gathered by the visitors
	 * @postcondition the data given in the desired format
	 */ 
	public void buildReport(ReportInterface report){
		//String header = "<h1>" + report.getHeader() + "</h1><br><br>";
		//System.out.println(header);
		printReportHeader(report.getHeader());
		
		ArrayList<ReportTable> tableData = report.getTableData();
		ArrayList<ReportNotice> noticeData = report.getNotices();
		
		if(tableData != null){
			for(ReportTable table : tableData){
				//System.out.println("<b>" + table.getTableName() + "</b>");
				printTableName(table.getTableName());
				
				ArrayList<TableRow> tableRows = table.getRows();
				String[] colHeaders = table.getHeaderRow();
				
				startTable(colHeaders.length);
				openTableRow();
				for(String colName : colHeaders){
					//System.out.println(" <th>" + colName + "</th>");
					printColName(colName);
				}
				closeTableRow();
				for(TableRow row : tableRows){
					openTableRow();
					for(String column : row.getData()){
						//System.out.println("  <td>" + column + "</td>");
						printColData(column);
					}
					closeTableRow();
				}
				endTable();
			}
		}
		
		if(noticeData != null){
			for(ReportNotice noticeType : noticeData){
				//System.out.println(noticeType.getNoticeType());
				printNoticeType(noticeType.getNoticeType());
				ArrayList<NoticeData> datas = noticeType.getNotices();
				for(NoticeData data : datas){
					//System.out.println();
					printBlankLine();
					//System.out.println(data.getDescription());
					printNoticeDesc(data.getDescription());
					ArrayList<String> offenders = data.getOffenders();
					for(String offender : offenders){
						//System.out.println(offender);
						printOffender(offender);
					}
					//printBlankLine();
				}
			}
		}
		
		createDocument();
	}
	
	public abstract void printReportHeader(String header);
	
	public abstract void printTableName(String tableName);
	
	public abstract void printColName(String colName);
	
	public abstract void printColData(String colData);
	
	public abstract void printNoticeType(String noticeType);
	
	public abstract void printNoticeDesc(String noticeDesc);
	
	public abstract void printOffender(String offender);
	
	public abstract void printBlankLine();
	
	public abstract void startTable(int numCols);
	
	public abstract void endTable();
	
	public abstract void openTableRow();
	
	public abstract void closeTableRow();
	
	public abstract void createDocument();
	
}
