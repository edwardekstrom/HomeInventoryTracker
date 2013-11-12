/**
 * 
 */
package builder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import reports.ReportInterface;

/**
 * @author Capchu
 *
 */
public class PDFBuilder extends ReportBuilder {
	
	private PdfWriter _writer;
	private Document _document;
	private final String _filePath = "PDFReport.pdf";
	private PdfPTable _table; 
	private Font _fontCellHeaders = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font _fontCells = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private Font _fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private Font _fontTableName = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
	private Font _fontNoticeData = new Font(Font.FontFamily.TIMES_ROMAN, 9);
	private FileOutputStream _fileOutputStream;
	
	public PDFBuilder(){
		
		_document = new Document();
		
		 try {
			 _fileOutputStream = new FileOutputStream(_filePath);
			_writer = PdfWriter.getInstance(_document, _fileOutputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_writer.setPdfVersion(PdfWriter.VERSION_1_5);
		_document.open();    
	}

	@Override
	public void printReportHeader(String header) {
		try {
			_document.add(new Paragraph(header + "\n\n", _fontHeader));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printTableName(String tableName) {
		try {
			_document.add(new Paragraph(tableName + "\n\n", _fontTableName));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printColName(String colName) {
		
		Phrase cellData = new Phrase(colName, _fontCellHeaders);
		//PdfPCell cell = new PdfPCell(cellData);
		_table.addCell(cellData);
		
	}

	@Override
	public void printColData(String colData) {
		Phrase cellData = new Phrase(colData, _fontCells);
		//PdfPCell cell = new PdfPCell(cellData);
		_table.addCell(cellData);
	}

	@Override
	public void printNoticeType(String noticeType) {
		try {
			_document.add(new Paragraph(noticeType, _fontTableName));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printNoticeDesc(String noticeDesc) {
		try {
			_document.add(new Paragraph(noticeDesc, _fontNoticeData));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printOffender(String offender) {
		try {
			_document.add(new Paragraph(offender, _fontNoticeData));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printBlankLine() {
		try {
			_document.add(new Paragraph("", _fontCells));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createDocument() {
		// TODO Auto-generated method stub
		
		
		
		try {
			_writer.close();
			_fileOutputStream.close();
			_document.close();
			java.awt.Desktop.getDesktop().open(new File(_filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startTable(int numCols) {
		_table = new PdfPTable(numCols);
		_table.setHorizontalAlignment(Element.ALIGN_LEFT);
	}

	@Override
	public void endTable() {
		
		_table.completeRow();
		try {
			_document.add(_table);
			_document.add(new Paragraph("\n", _fontCells));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void openTableRow() {
		//No Row Management in iText
	}

	@Override
	public void closeTableRow() {
		//No Row Management in iText
	}


}
