package org.zr.model.dao;

public interface FileNameDao {
/***存储上传的文件的 文件名  
 *  取出最近上传的文件名
 *  
 * ***/
	void addFileName(String filename);
	
	String getFileName();
	
}
