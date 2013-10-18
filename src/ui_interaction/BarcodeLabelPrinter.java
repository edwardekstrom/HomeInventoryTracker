package ui_interaction;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font;


import java.io.*;

import java.awt.*;
import java.util.*;


import data_structures.Barcode;
import data_structures.Date;
import data_structures.*;
import hit_exceptions.InvalidShelfLifeException;

import java.text.SimpleDateFormat;


public class BarcodeLabelPrinter {

	private static ArrayList<Item> items;

	public static void main(String[] args){

		Product p1 = new Product(
            new Date(),
            new Barcode("123456789999"),
			"product 1", 
            new Integer(3),
            new Integer(3));
            ProductContainer c = new StorageUnit();


    	items = new ArrayList<Item>();


		for(int i =0;i<32;i++){
			Barcode b = new Barcode();
			Date entry = new Date();
			items.add(new Item(p1,b,entry,c));
		}
        printLabels(items);

	}


        public static void printLabels(ArrayList<Item> items){

                try{
                        Document document = new Document(new Rectangle(610, 840));

                        String filename = "itemLabels.pdf";

                        PdfWriter writer = PdfWriter.getInstance(document, 
                                new FileOutputStream(filename));
                        writer.setPdfVersion(PdfWriter.VERSION_1_5);

                        document.open();

                        PdfContentByte cb = writer.getDirectContent();

                        PdfPTable table = new PdfPTable(5);
                        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                        table.getDefaultCell().setPadding(30);
                         
                        

                        for (Item i : items){
                                printLabel(document, cb,table,i);
                        }

                        table.completeRow();
                        document.add(table);
                        document.close();

                        java.awt.Desktop.getDesktop().open(new File(filename)); 
                }catch(Exception e){System.out.println(e.toString());}
        }


	public static void printLabel(Document document, PdfContentByte cb, 
                PdfPTable table, Item item) throws IOException, DocumentException{
		
        // unpack Item

        String description = item.getProduct().getDescription();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String entry = item.getEntryDate().getDateAsString(sdf);
        String exit  = item.getExpirationDate().getDateAsString(sdf);
        String barcode = item.getBarcode().getBarcode();
 		
        PdfPCell cell;
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 7);
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
        System.out.println(barcode.length());
        Image img = codeEAN.createImageWithBarcode(cb, null, null);
    
        cell = new PdfPCell(img);
        cell.setBorder(PdfPCell.NO_BORDER);
        local.addCell(cell);

        cell = new PdfPCell(local);
        cell.setBorder(PdfPCell.NO_BORDER);
      


        table.setHorizontalAlignment(Element.ALIGN_LEFT);

	table.setWidthPercentage(100f);

        table.addCell(cell);
        

        
   
	        
        
        
        
	}



}