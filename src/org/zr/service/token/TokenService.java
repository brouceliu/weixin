package org.zr.service.token;

import org.zr.entity.AccessToken;
import org.zr.token.TokenThread;
import org.zr.util.TokenUtil;

public class TokenService {

	public static AccessToken getAccesstoken(){
		AccessToken tokens= TokenThread.accessToken;
		return tokens;
	}
	
}
