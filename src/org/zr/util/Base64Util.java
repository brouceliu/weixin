package org.zr.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	/**
     * 
     * ��������2011-4-25����10:12:38
     * �޸�����
     * ���ߣ�dh *TODO ʹ��Base64�����㷨�����ַ���
     *return
     */
    public static String encodeStr(String plainText){
        byte[] b=plainText.getBytes();
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }
    
    /**
     * 
     * ��������2011-4-25����10:15:11
     * �޸�����
     * ���ߣ�dh     *TODO ʹ��Base64����
     *return
     */
    public static String decodeStr(String encodeStr){
        byte[] b=encodeStr.getBytes();
        Base64 base64=new Base64();
        b=base64.decode(b);
        String s=new String(b);
        return s;
    }
    
    public static void main(String[] args) {
		System.out.println(encodeStr("11lzr"));
		System.out.println(decodeStr(encodeStr("11lzr")));
	}
}
