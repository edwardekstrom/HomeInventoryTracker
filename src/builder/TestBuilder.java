package builder;

import java.util.ArrayList;

import reports.NoticeData;
import reports.ReportInterface;
import reports.ReportNotice;
import reports.ReportTable;
import reports.TableRow;

public class TestBuilder extends ReportBuilder{


	@Override
	public void printReportHeader(String header) {
		System.out.println("<h1>" + header + "</h1><br><br>");		
	}

	@Override
	public void printTableName(String tableName) {
		System.out.println("<b>" + tableName + "</b>");
	}

	@Override
	public void printColName(String colName) {
		System.out.println(" <th>" + colName + "</th>");
	}

	@Override
	public void printColData(String colData) {
		System.out.println("  <td>" + colData + "</td>");
	}

	@Override
	public void printNoticeType(String noticeType) {
		System.out.println(noticeType);
	}

	@Override
	public void printNoticeDesc(String noticeDesc) {
		System.out.println(noticeDesc);
	}

	@Override
	public void printOffender(String offender) {
		System.out.println(offender);
	}

	@Override
	public void printBlankLine() {
		System.out.println();
	}

}
