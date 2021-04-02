package com.example.finalproject.shop.clientcontroller;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import com.example.finalproject.shop.model.OrderDTO;
import com.example.finalproject.shop.model.OrderDetailDTO;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class OrderPDFExporter {
	private List<OrderDetailDTO> orderDetailDTOs;
	private List<OrderDTO> orderDTOs;
	
	public OrderPDFExporter(List<OrderDetailDTO> orderDetailDTOs ) {
		this.orderDetailDTOs = orderDetailDTOs;
		
	}
	 private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        font.setColor(Color.WHITE);
	         
	        cell.setPhrase(new Phrase("ID", font));
	         
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Mobile", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Address", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("City", font));
	        table.addCell(cell); 
	        
	        cell.setPhrase(new Phrase("Date", font));
	        table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Total_Price", font));
	        table.addCell(cell); 
	    }
	 
	 private void writeTableData(PdfPTable table) {
	        for (OrderDetailDTO orderDetailDTO : orderDetailDTOs) {
	            table.addCell(String.valueOf(orderDetailDTO.getId()));
	            table.addCell(orderDetailDTO.getUser().getName());
	            table.addCell(orderDetailDTO.getUser().getMobile());
	            table.addCell(orderDetailDTO.getAddress());
	            table.addCell(orderDetailDTO.getCity());
	            table.addCell(String.valueOf(orderDetailDTO.getAdded_on()));
	            table.addCell(String.valueOf(orderDetailDTO.getTotal_price()));
	        }
	    }
	 public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        font.setColor(Color.BLUE);
	         
	        Paragraph p = new Paragraph("Order", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	         
	        document.add(p);
	         
	        PdfPTable table = new PdfPTable(7);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {1.0f,2.5f,1.5f, 3.0f, 1.0f, 1.5f, 1.0f});
	        table.setSpacingBefore(12);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }
}
