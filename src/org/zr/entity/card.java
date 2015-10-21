package org.zr.entity;

public class card {
	private String cardtype;
	 private String cardcode;
	 private String cardpasswd;
	 private String cardstatus;
	 private String cardid;//这个卡对应的id
 public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getCardcode() {
		return cardcode;
	}
	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}
	public String getCardpasswd() {
		return cardpasswd;
	}
	public void setCardpasswd(String cardpasswd) {
		this.cardpasswd = cardpasswd;
	}
	public String getCardstatus() {
		return cardstatus;
	}
	public void setCardstatus(String cardstatus) {
		this.cardstatus = cardstatus;
	}

 
}
