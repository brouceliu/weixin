package org.zr.entity;

/**
 * ΢��ͨ�ýӿ�ƾ֤
 * 
 * @author liufeng
 * @date 2013-08-08
 */
public class AccessToken {
	// ��ȡ����ƾ֤
	private String token;
	// ƾ֤��Чʱ�䣬��λ����
	private int expiresIn;
	private String gettime;

	public String getGettime() {
		return gettime;
	}

	public void setGettime(String gettime) {
		this.gettime = gettime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}