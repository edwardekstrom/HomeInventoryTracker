package facade;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font;



import java.io.*;
import java.awt.*;
import java.util.*;

import hit_exceptions.InvalidShelfLifeException;

import java.text.SimpleDateFormat;

import model.*;


public class BarcodeLabelPrinter {

	private static ArrayList<Item> items;


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
        }catch(Exception e){}
    }


	public static void printLabel(Document document, PdfContentByte cb, 
                PdfPTable table, Item item) throws IOException, DocumentException{
		
        // unpack Item

        String description = item.getProduct().getDescription();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        String entry = item.getEntryDate().getDateAsString(sdf);
        
        String exit = "";
        if(item.getExpirationDate().getDate().getTimeInMillis() == 0){
        	exit  = "";
        }else{
        	exit  = item.getExpirationDate().getDateAsString(sdf);
        }
        
        
        String barcode = item.getBarcode().getBarcode();
 		
        PdfPCell cell;
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 7);
        PdfPTable local = new PdfPTable(1);
        BarcodeEAN codeEAN = new BarcodeEAN();


        Phrase desc = new Phrase(description,font);
        Phrase dates = new Phrase();
        if(exit.equals("")){
        	dates = new Phrase(entry,font);
        }else{
        	dates = new Phrase(entry + " exp "+ exit,font);
        }
        
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
      


        table.setHorizontalAlignment(Element.ALIGN_LEFT);

	    table.setWidthPercentage(100f);

        table.addCell(cell);
        

        
   
	        
        
        
        
	}



}