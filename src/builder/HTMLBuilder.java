/**
 * 
 */
package builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import reports.ReportInterface;

/**
 * @author Capchu
 *
 */
public class HTMLBuilder extends ReportBuilder {
	
	File _html = new File("htmlReport.html");
	StringBuilder _sb = new StringBuilder();
	
	public HTMLBuilder(){
		if(_html.exists()){
			_html.delete();
		}
		try {
			_html.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void printReportHeader(String header) {
		//System.out.println("<h1>" + header + "</h1><br><br>");
		_sb.append("<h1>" + header + "</h1><br>");
	}

	@Override
	public void printTableName(String tableName) {
		//System.out.println("<b>" + tableName + "</b>");
		_sb.append("<b>" + tableName + "</b><br>");
	}

	@Override
	public void printColName(String colName) {
		//System.out.println(" <th>" + colName + "</th>");
		_sb.append(" <th>" + colName + "</th>");
	}

	@Override
	public void printColData(String colData) {
		//System.out.println("  <td>" + colData + "</td>");
		_sb.append("  <td>" + colData + "</td>");
	}

	@Override
	public void printNoticeType(String noticeType) {
		//System.out.println(noticeType);
		_sb.append(noticeType + "<br>");
	}

	@Override
	public void printNoticeDesc(String noticeDesc) {
		//System.out.println(noticeDesc);
		_sb.append(noticeDesc + "<br>");
	}

	@Override
	public void printOffender(String offender) {
		//System.out.println(offender);
		_sb.append(offender + "<br>");
	}

	@Override
	public void printBlankLine() {
		//System.out.println();
		_sb.append("<br>");
	}

	@Override
	public void startTable() {
		_sb.append("<table border=\"1\">");
	}

	@Override
	public void endTable() {
		_sb.append("</table><br>");
	}
	
	@Override
	public void openTableRow() {
		_sb.append("<tr>");
	}


	@Override
	public void closeTableRow() {
		_sb.append("</tr>");
	}
	
	@Override
	public void createDocument() {
		//System.out.print(_sb.toString());		
		_html.setWritable(true);
		try {
			Files.write(_html.toPath(), _sb.toString().getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	


}
