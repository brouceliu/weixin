package org.zr.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {
	private static Logger log=LoggerFactory.getLogger(TimeUtil.class);
//΢��ʱ�䴦����
	/**�Ƚ�ʱ��  s1 ΢��tokenʱ�� ϵͳ��ǰʱ��**/
	public static Boolean checksTime(String s1){
		Boolean finaresult=false;
		//String s1="2008-01-25 09:12:09";
		//String s2="2008-01-29 09:12:11";
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		java.util.Calendar c1=java.util.Calendar.getInstance();
		java.util.Calendar c2=java.util.Calendar.getInstance();
		String s2=df.format(date);
		try{
		c1.setTime(df.parse(s1));
		c2.setTime(df.parse(s2));
		}catch(java.text.ParseException e){
		log.warn("��ʽ����ȷ");
		}
		int result=c1.compareTo(c2);
		if(result==0){
			//c1=c2
		
		log.info("�����tokenʱ�����ϵͳʱ��");
		}
		else if(result<0){
			//c1<c2
			
			log.info("�����tokenʱ��С��ϵͳʱ��");
		}
		else{
		//c1>c2
		finaresult=true;//ֻ������ʱ�����ϵͳʱ�� ����true ��ʱtoken��Ч
		log.info("�����tokenʱ�����ϵͳʱ��");
		}
		return finaresult;
	}
	
	/**ʱ����� ��**/
	public static Date addOneSecond(Date date,int time) {  
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(date);  
	    calendar.add(Calendar.SECOND, time);  
	    return calendar.getTime();  
	}  
	public static String formatTime(String createTime) {
		// ��΢�Ŵ����CreateTimeת����long���ͣ��ٳ���1000
		long msgCreateTime = Long.parseLong(createTime) * 1000L;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(msgCreateTime));
	}
	
	
	/***����΢�ż��ܵ�ʱ���***/
	public static String createTimeStept(){
		Long tine=System.currentTimeMillis() / 1000L;
		String time=tine.toString();
		return time;
	}
	
	/***�� yyyy-MM-dd  ��ʽ��ʱ�䣬ת��Ϊ unix ʱ�� 1970.1.1 0.0.0 �����ڵ����� **/
	public static String toUnixTime(String local){
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String unix = "";
	    long msgCreateTime=111;
	    try {
	        unix = df.parse(local).getTime() + "";
	         msgCreateTime = Long.parseLong(unix) / 1000L;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return String.valueOf(msgCreateTime);
	}
	
	//��������ʱ��
	public static String getNowTime(){
		Date da=new Date();
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String a=df.format(da);
		return a;
	}
	
	
	public static void main(String[] args) {
		System.out.println(toUnixTime("2015-08-15 12:00:00"));
		System.out.println(formatTime("1438660476"));
		System.out.println(getNowTime());
		/*Long tine=System.currentTimeMillis();
		System.out.println(tine);
		Long tine2=new java.util.Date().getTime();
		System.out.println(tine2);*/
		/*SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		date=addOneSecond(date, 7200);  
		String gettime=from.format(date);+
		System.out.println(gettime);
		//System.out.println(formatTime("1429241806191"));
		System.out.println(checksTime(gettime));*/
	}
	
}
