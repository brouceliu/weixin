package org.zr.download;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.message.zr.request.TextMessage;
import org.zr.util.TimeUtil;

public class CheckOutMessage {

	// 导出志愿者信息到excel poi
	public static Boolean doexport(String path, List<TextMessage> list) {
		Logger log = Logger.getLogger(CheckOutMessage.class);
		Boolean one = false;
		HSSFWorkbook hssw = new HSSFWorkbook();
		HSSFSheet sheet = hssw.createSheet("WeiXinMessage");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = hssw.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("微信名");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("微信id");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("消息");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("时间");
		cell.setCellStyle(style);

		/*
		 * Vdao vd=new Vempl(); List<volunteer> vol=vd.findall();
		 */

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			TextMessage txm = list.get(i);
			row.createCell(0).setCellValue(txm.getUsername());
			row.createCell(1).setCellValue(txm.getToUserName());
			row.createCell(2).setCellValue(txm.getContent());
			row.createCell(3).setCellValue(
					TimeUtil.formatTime(String.valueOf(txm.getCreateTime())));

		}

		try {
			Date myDate = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
			String time = sdf.format(myDate);
			FileOutputStream out = new FileOutputStream(
					"../webapps/Txwx/message/" + time + "message.xls");
			hssw.write(out);
			out.flush();
			out.close();
			one = true;
			log.info("用户导出了文件^*^");
			return one;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return one;
	}

}
