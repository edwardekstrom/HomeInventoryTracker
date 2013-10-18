package ui_interaction;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font;


import java.io.*;

import java.awt.*;
import java.util.*;

import java.util.Date;

import data_structures.Barcode;
import data_structures.*;


public class BarcodeLabelPrinter {

	private static ArrayList<Item> items;

	public static void main(String[] args){

		Product c1 = new Product(new Date(), new Barcode("product"),
			 "product 1", 3,3);
		items = new ArrayList<Item>();
		for(int i =0;i<10;i++){
			Product p = new Product();
			Barcode b = new Barcode(i+"2345678999");
			Date entry = new Date();
			ProductContainer c = new StorageUnit();
			items.add(new Item(p,b,entry,c));
		}

		try{
			Document document = new Document(new Rectangle(610, 840));
			String filename = "barcode_test";
	        // step 2
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
	        writer.setPdfVersion(PdfWriter.VERSION_1_5);
	        // step 3
	        document.open();
	        // step 4
        	PdfContentByte cb = writer.getDirectContent();
			printLabel(document, cb, "123456789999","Description","10/10/10","12/12/12");

			document.close();

			java.awt.Desktop.getDesktop().open(new File(filename)); 
		}catch(Exception e){System.out.println(e.getMessage());}

	}

	public static void printLabel(Document document, PdfContentByte cb, String barcode,
		String description, String entry, String exit) throws IOException, DocumentException{
		
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 7);
 		
        PdfPCell cell;
        PdfPTable theTable = new PdfPTable(5);


        theTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);

        // BC1
        PdfPTable local = new PdfPTable(1);



        BarcodeEAN codeEAN = new BarcodeEAN();


        Phrase desc = new Phrase(description,font);
        Phrase dates = new Phrase(entry + " exp "+ exit,font);
        
        cell = new PdfPCell(desc);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(0);
        local.addCell(cell);
        cell = new PdfPCell(dates);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPadding(1);
        local.addCell(cell);


        codeEAN.setCodeType(com.itextpdf.text.pdf.Barcode.UPCA);
        codeEAN.setCode(barcode);
        Image img = codeEAN.createImageWithBarcode(cb, null, null);
    
        cell = new PdfPCell(img);
        cell.setBorder(PdfPCell.NO_BORDER);
        local.addCell(cell);

        cell = new PdfPCell(local);
        cell.setBorder(PdfPCell.NO_BORDER);
      


        theTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		theTable.setWidthPercentage(100f);

        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);
        theTable.addCell(cell);

        theTable.completeRow();
   
	        
        
        document.add(theTable);
        
	}



}