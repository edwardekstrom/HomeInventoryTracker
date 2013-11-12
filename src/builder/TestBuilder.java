package builder;

import java.util.ArrayList;

import reports.NoticeData;
import reports.ReportInterface;
import reports.ReportNotice;
import reports.ReportTable;
import reports.TableRow;

public class TestBuilder extends ReportBuilder{
	
	StringBuilder _sb = new StringBuilder();
	//String _document;
	
	public TestBuilder(){
		_sb.append("");
	}


	@Override
	public void printReportHeader(String header) {
		//System.out.println("<h1>" + header + "</h1><br><br>");
		_sb.append("<h1>" + header + "</h1><br><br>\n");
	}

	@Override
	public void printTableName(String tableName) {
		//System.out.println("<b>" + tableName + "</b>");
		_sb.append("<b>" + tableName + "</b>\n");
	}

	@Override
	public void printColName(String colName) {
		//System.out.println(" <th>" + colName + "</th>");
		_sb.append(" <th>" + colName + "</th>\n");
	}

	@Override
	public void printColData(String colData) {
		//System.out.println("  <td>" + colData + "</td>");
		_sb.append("  <td>" + colData + "</td>\n");
	}

	@Override
	public void printNoticeType(String noticeType) {
		//System.out.println(noticeType);
		_sb.append(noticeType + "\n");
	}

	@Override
	public void printNoticeDesc(String noticeDesc) {
		//System.out.println(noticeDesc);
		_sb.append(noticeDesc + "\n");
	}

	@Override
	public void printOffender(String offender) {
		//System.out.println(offender);
		_sb.append(offender + "\n");
	}

	@Override
	public void printBlankLine() {
		//System.out.println();
		_sb.append("\n");
	}

	@Override
	public void createDocument() {
		System.out.print(_sb.toString());		
	}


	@Override
	public void startTable(int col) {
		_sb.append("<table>");
	}

	@Override
	public void endTable() {
		_sb.append("</table>");
	}


	@Override
	public void openTableRow() {
		_sb.append("<tr>");
	}


	@Override
	public void closeTableRow() {
		_sb.append("</tr>");
	}

}
