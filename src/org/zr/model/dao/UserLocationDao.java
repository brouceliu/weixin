package org.zr.model.dao;

import org.zr.entity.UserLocation;

public interface UserLocationDao {

	/**用户地理位置的信息**/
	public  void saveLocation(UserLocation location);
	/**根据用户上次的地理位置信息查询**/
	public UserLocation getLastlocation(String openid);
}
