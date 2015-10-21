package org.zr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.card;
import org.zr.model.dao.CardNumDao;
import org.zr.model.dao.CardNumDaoImol;
import org.zr.model.dao.FileNameDao;
import org.zr.model.dao.FileNameDaoImpl;
import org.zr.upload.ImportExecl;

public class UploadHandleServlet extends HttpServlet {
	private static Logger log=LoggerFactory.getLogger(UploadHandleServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
                String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
                File file = new File(savePath);
               
              String cardid = "aaa";
             //   String tempPath= this.getServletContext().getRealPath("/WEB-INF/temp");
                
                //判断上传文件的保存目录是否存在
                if (!file.exists() && !file.isDirectory()) {
                    System.out.println(savePath+"目录不存在，需要创建");
                    //创建目录
                    file.mkdir();
                }
                //消息提示
                String message = "";
                try{
                    //使用Apache文件上传组件处理文件上传步骤：
                    //1、创建一个DiskFileItemFactory工厂
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    //2、创建一个文件上传解析器
                    ServletFileUpload upload = new ServletFileUpload(factory);
                     //解决上传文件名的中文乱码
                    upload.setHeaderEncoding("UTF-8"); 
                    //限制文件最大3M
                    upload.setFileSizeMax(1024*1024*3);
                    //3、判断提交上来的数据是否是上传表单的数据
                    if(!ServletFileUpload.isMultipartContent(request)){
                        //按照传统方式获取数据
                        return;
                    }
                    //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
                    List<FileItem> list = upload.parseRequest(request);
                    for(FileItem item : list){
                        //如果fileitem中封装的是普通输入项的数据
                        if(item.isFormField()){
                            String name = item.getFieldName();
                            //解决普通输入项的数据的中文乱码问题
                            String value = item.getString("UTF-8");
                            //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                        //    System.out.println(name + "=" + value);
                            cardid=value;
                            log.info(value);
                        }else{//如果fileitem中封装的是上传文件
                            //得到上传的文件名称，
                            String filename = item.getName();
                            log.info(filename);
                            if(filename==null || filename.trim().equals("")){
                                continue;
                            }
                            //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                            //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                            filename = filename.substring(filename.lastIndexOf("\\")+1);
                            String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                            if(fileExtName.equals("xls") || fileExtName.equals("xlsx")){
                            	//存储文件名
                            	FileNameDao fdao=new FileNameDaoImpl();
                            	fdao.addFileName(filename);
                            	   //获取item中的上传文件的输入流
                                InputStream in = item.getInputStream();
                                //创建一个文件输出流
                                FileOutputStream out = new FileOutputStream(savePath + "/" + filename);//"\\"windows "/" linux
                                //创建一个缓冲区
                                byte buffer[] = new byte[1024];
                                //判断输入流中的数据是否已经读完的标识
                                int len = 0;
                                //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                                while((len=in.read(buffer))>0){
                                    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                                    out.write(buffer, 0, len);
                                }
                                //关闭输入流
                                in.close();
                                //关闭输出流
                                out.close();
                                //删除处理文件上传时生成的临时文件
                                item.delete();
                                message = "卡卷的文件上传成功！";
                              log.info("excel upload success");
                              CardNumDao cdao=new CardNumDaoImol();
                              log.info(savePath+ "/" +filename);
                              List<String> arr=  ImportExecl.getAllInfo(savePath+ "/" +filename);
                              Iterator< String > ite=arr.iterator();
                              while(ite.hasNext()){
                            	  log.info("ardid is "+cardid);
                            	  String a=ite.next();
                            	  log.info("a is"+a);
                            	  if ("".equals(a.trim())) {
									break;
								}
                            	  String[] b=a.split("P");
                            	 card ca=new card();
                            	 ca.setCardcode(b[0]+"-p-");//导入的时候就加上-p-
                            	 ca.setCardstatus("no");
                            	 log.info(b[0]);
                            	 log.info(b[1]);
                            	 ca.setCardpasswd(b[1]);
                            	 ca.setCardtype("CASH");
                           	     ca.setCardid(cardid);
                            	 cdao.addCard(ca);
                              }
                                
                            }else{
                            	   message = "文件格式不对！";
                            	//   break;
                            	   
                            }
                            
                         
                        }
                    }
                }catch (Exception e) {
                    message= "文件上传失败！";
                    StringWriter sw = new StringWriter();   
                    PrintWriter pw = new PrintWriter(sw, true);   
                    e.printStackTrace(pw);   
                    pw.flush();   
                    sw.flush();   
        		
        			log.error("上传错误"+sw.toString());
        			e.printStackTrace();
                    
                }
                request.setAttribute("message",message);
                request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}