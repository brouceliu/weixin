package org.zr.model.dao;

import org.zr.entity.UserLocation;

public interface UserLocationDao {

	/**�û�����λ�õ���Ϣ**/
	public  void saveLocation(UserLocation location);
	/**�����û��ϴεĵ���λ����Ϣ��ѯ**/
	public UserLocation getLastlocation(String openid);
}
