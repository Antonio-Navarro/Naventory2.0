package com.antoniojnavarro.naventory.app.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperReportUtil {

	public static ByteArrayOutputStream getOutputStreamFromReport(List list, Map<String, Object> map, String pathJasper)
			throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

		JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, dataSource);

		JasperExportManager.exportReportToPdfStream(jp, os);
		os.flush();
		os.close();
		return os;
	}

	public static StreamedContent getStreamContentFromOutputStream(ByteArrayOutputStream os, String contentType,
			String nameFile) throws Exception {
		StreamedContent file = null;
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		file = new DefaultStreamedContent(is, contentType, nameFile);
		return file;
	}

	public static StreamedContent getStreamContentReport(List<Object> list, Map<String, Object> map, String pathJasper,
			String nameFilePdf) throws Exception {
		StreamedContent pdf = null;
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

		JasperPrint jp = JasperFillManager.fillReport(pathJasper, map, dataSource);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jp, os);
		os.flush();
		os.close();

		InputStream is = new ByteArrayInputStream(os.toByteArray());
		pdf = new DefaultStreamedContent(is, "application/pdf", nameFilePdf);
		return pdf;
	}
}