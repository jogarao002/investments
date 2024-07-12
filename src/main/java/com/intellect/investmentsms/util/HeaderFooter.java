package com.intellect.investmentsms.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author vinaykumar siribhadri The Class HeaderFooter. to define Header and
 *         footer of the pdf file
 */
@Component
public class HeaderFooter extends PdfPageEventHelper {

	private final Logger log = LoggerFactory.getLogger(HeaderFooter.class);

	public static final String IMGURL = "src/main/resources/IntellectLogo.png";

	String fileName = "images/intellectlogo.png";

	/** The t. */
	private com.itextpdf.text.pdf.PdfTemplate t;

	/** The total. */
	private Image total;

	@Autowired
	private Environment env;
	
	public static Long createdOn;
	
	public static String heading;

	/*
	 * (non-Javadoc)
	 * 
	 * @see@Value("${fhr.logo.url}")
	 private String logoUrl;
	 * com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(com.itextpdf.text.pdf
	 * .PdfWriter, com.itextpdf.text.Document)
	 */
	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 16);
		try {
			total = Image.getInstance(t);
			total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text.pdf.
	 * PdfWriter, com.itextpdf.text.Document)
	 */
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			addHeader(writer, document);
			addFooter(writer);
		} catch (Exception e) {
			log.error("Exception caught in HeaderFooter class while adding header and footer for PDF:: "
					+ e.getMessage());
		}

	}

	/**
	 * Adds the header.
	 *
	 * @param writer
	 *            the writer
	 * @param document
	 *            the document
	 */
	private void addHeader(PdfWriter writer, Document document) {
		PdfPTable header = new PdfPTable(2);
		try {
			// set defaults
			header.setWidths(new int[] { 10, 4 });
			header.setTotalWidth(527);
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(40);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);

			// add image

			// File file = ResourceUtils.getFile("intellectlogo.png");

			/*
			 * // adding new code File file = new File("intellectlogo.png");
			 * 
			 * BufferedImage image = null; image = ImageIO.read(file); //
			 * ImageIO.write(image, "png", new File("intellectlogo.png")); Image logo =
			 * Image.getInstance(image, null); // adding new code
			 * 
			 * 
			 * ClassLoader classLoader = getClass().getClassLoader(); File file = new
			 * File(classLoader.getResource(fileName).getFile());
			 */
			// File file = ResourceUtils.getFile("intellectlogo.png");
			// ClassPathResource imgFile = new
			// ClassPathResource("intellectlogo.png");
			// Image logo = Image.getInstance(file.getAbsolutePath());
			// ClassLoader classLoader = getClass().getClassLoader();
			// File file = new File(classLoader.getResource(fileName).getFile());

			// ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			// InputStream inputStream =
			// classloader.getResourceAsStream("intellectlogo.png");
			// image = ImageIO.read(inputStream);

			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(15);

			// text.setPaddingLeft(10);
			text.setHorizontalAlignment(Element.ALIGN_CENTER);
			text.setBorder(Rectangle.BOTTOM);
//			text.addElement(new Phrase("FHR(First Hand Report)"));
			if(null != header) {
				text.addElement(new Phrase(heading));
			}
			header.addCell(text);
			
//			URL	url = new URL(env.getProperty("fhr.logo.url"));
//		    BufferedImage image = ImageIO.read(url);
//			Image logo = Image.getInstance(image, null);
//			logo.scaleToFit(100, 100);
//			header.addCell(logo);
			
			String fileName = env.getProperty("fhr.logo");
			ClassPathResource resource = new ClassPathResource(fileName);
			Image logo = Image.getInstance(resource.getFile().getPath());
			logo.scaleToFit(100, 100);
			header.addCell(logo);


			// text.setBorderColor(BaseColor.LIGHT_GRAY);
			
			

			// write content
			header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (IOException e) {
			throw new ExceptionConverter(e);
		}
	}

	/**
	 * Adds the footer.
	 *
	 * @param writer
	 *            the writer
	 */
	private void addFooter(PdfWriter writer) {
		PdfPTable footer = new PdfPTable(3);
		try {
			// set defaults
			footer.setWidths(new int[] { 24, 24, 2 });
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(20);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
			Date now = new Date();
			if (null != createdOn && createdOn != 0) {
				Date created = new Date(createdOn);
				footer.addCell(new Phrase(
						String.format("Created on: %s", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(created))));
			}
			footer.addCell(new Phrase(
					String.format("Generated on: %s", new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a").format(now))));


			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
//			footer.addCell(new Phrase(String.format("Page %d of ", writer.getPageNumber())));
			footer.addCell(new Phrase(String.format(" %d of ", writer.getPageNumber())));

			// add placeholder for total page count
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			footer.addCell(totalPageCount);

			// write page
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 36, 30, canvas);
			canvas.endMarkedContentSequence();
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	public void onCloseDocument(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(t, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2,
				0);
	}
}
