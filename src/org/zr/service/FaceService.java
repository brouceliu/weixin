package org.zr.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.Face;
import org.zr.main.MenuManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ����������
 * 
 * @author liufeng
 * @date 2013-12-18
 */
public class FaceService {
	private static Logger log = LoggerFactory.getLogger(FaceService.class);
	/**
	 * ����http����
	 * 
	 * @param requestUrl �����ַ
	 * @return String
	 */
	public static String httpRequest(String requestUrl) {
		log.info("���������url�ǣ�"+requestUrl);
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			//httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	

	
	
	

	/**
	 * ����Face++ APIʵ���������
	 * 
	 * @param picUrl �����ͼƬ�ķ��ʵ�ַ
	 * @return List<Face> �����б�
	 */
	private static List<Face> faceDetect(String picUrl) {
		List<Face> faceList = new ArrayList<Face>();
		try {
			// ƴ��Face++�������������ַ
			String queryUrl = "http://apicn.faceplusplus.com/v2/detection/detect?url=URL&api_secret=API_SECRET&api_key=API_KEY";
			// ��URL���б���
			queryUrl = queryUrl.replace("URL", java.net.URLEncoder.encode(picUrl, "UTF-8"));
			queryUrl = queryUrl.replace("API_KEY", "15d9704ca19cefcefe457ed3a6d4aee6");
			queryUrl = queryUrl.replace("API_SECRET", "goiPg3YQw1c8VEhMcX3bfH01k3vkrbti");
			// �����������ӿ�
			String json = httpRequest(queryUrl);
			// ��������json�е�Face�б�
			JSONArray jsonArray = JSONObject.fromObject(json).getJSONArray("face");
			// ������⵽������
			for (int i = 0; i < jsonArray.size(); i++) {
				// face
				JSONObject faceObject = (JSONObject) jsonArray.get(i);
				// attribute
				JSONObject attrObject = faceObject.getJSONObject("attribute");
				// position
				JSONObject posObject = faceObject.getJSONObject("position");
				Face face = new Face();
				face.setFaceId(faceObject.getString("face_id"));
				face.setAgeValue(attrObject.getJSONObject("age").getInt("value"));
				face.setAgeRange(attrObject.getJSONObject("age").getInt("range"));
				face.setGenderValue(genderConvert(attrObject.getJSONObject("gender").getString("value")));
				face.setGenderConfidence(attrObject.getJSONObject("gender").getDouble("confidence"));
				face.setRaceValue(raceConvert(attrObject.getJSONObject("race").getString("value")));
				face.setRaceConfidence(attrObject.getJSONObject("race").getDouble("confidence"));
				face.setSmilingValue(attrObject.getJSONObject("smiling").getDouble("value"));
				face.setCenterX(posObject.getJSONObject("center").getDouble("x"));
				face.setCenterY(posObject.getJSONObject("center").getDouble("y"));
				faceList.add(face);
			}
			// ��������Face���������ҵ�˳������
			Collections.sort(faceList);
		} catch (Exception e) {
			faceList = null;
			e.printStackTrace();
		}
		return faceList;
	}

	/**
	 * �Ա�ת����Ӣ��->���ģ�
	 * 
	 * @param gender
	 * @return
	 */
	private static String genderConvert(String gender) {
		String result = "����";
		if ("Male".equals(gender))
			result = "����";
		else if ("Female".equals(gender))
			result = "Ů��";

		return result;
	}

	/**
	 * ����ת����Ӣ��->���ģ�
	 * 
	 * @param race
	 * @return
	 */
	private static String raceConvert(String race) {
		String result = "��ɫ";
		if ("Asian".equals(race))
			result = "��ɫ";
		else if ("White".equals(race))
			result = "��ɫ";
		else if ("Black".equals(race))
			result = "��ɫ";
		return result;
	}

	/**
	 * ��������ʶ������װ��Ϣ
	 * 
	 * @param faceList �����б�
	 * @return
	 */
	private static String makeMessage(List<Face> faceList) {
		StringBuffer buffer = new StringBuffer();
		// ��⵽1����
		if (1 == faceList.size()) {
			buffer.append("����⵽ ").append(faceList.size()).append(" ������").append("\n");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("����,");
				buffer.append(face.getGenderValue()).append(",");
				buffer.append(face.getAgeValue()+6).append("������").append("\n");
			}
		}
		// ��⵽2-10����
		else if (faceList.size() > 1 && faceList.size() <= 10) {
			buffer.append("����⵽ ").append(faceList.size()).append(" ������������������λ�ô�����������Ϊ��").append("\n");
			for (Face face : faceList) {
				buffer.append(face.getRaceValue()).append("����,");
				buffer.append(face.getGenderValue()).append(",");
				buffer.append(face.getAgeValue()+6).append("������").append("\n");
			}
		}
		// ��⵽10��������
		else if (faceList.size() > 10) {
			buffer.append("����⵽ ").append(faceList.size()).append(" ������").append("\n");
			// ͳ�Ƹ����֡��Ա������
			int asiaMale = 0;
			int asiaFemale = 0;
			int whiteMale = 0;
			int whiteFemale = 0;
			int blackMale = 0;
			int blackFemale = 0;
			for (Face face : faceList) {
				if ("��ɫ".equals(face.getRaceValue()))
					if ("����".equals(face.getGenderValue()))
						asiaMale++;
					else
						asiaFemale++;
				else if ("��ɫ".equals(face.getRaceValue()))
					if ("����".equals(face.getGenderValue()))
						whiteMale++;
					else
						whiteFemale++;
				else if ("��ɫ".equals(face.getRaceValue()))
					if ("����".equals(face.getGenderValue()))
						blackMale++;
					else
						blackFemale++;
			}
			if (0 != asiaMale || 0 != asiaFemale)
				buffer.append("��ɫ���֣�").append(asiaMale).append("��").append(asiaFemale).append("Ů").append("\n");
			if (0 != whiteMale || 0 != whiteFemale)
				buffer.append("��ɫ���֣�").append(whiteMale).append("��").append(whiteFemale).append("Ů").append("\n");
			if (0 != blackMale || 0 != blackFemale)
				buffer.append("��ɫ���֣�").append(blackMale).append("��").append(blackFemale).append("Ů").append("\n");
		}
		// �Ƴ�ĩβ�ո�
		//buffer = new StringBuffer(buffer.substring(0, buffer.lastIndexOf("\n")));
		
		return buffer.toString();
	}

	/**
	 * �ṩ���ⲿ���õ�������ⷽ��
	 * 
	 * @param picUrl �����ͼƬ�ķ��ʵ�ַ
	 * @return String
	 */
	public static String detect(String picUrl) {
		// Ĭ�ϻظ���Ϣ
		String result ="δʶ������,���������������";
		List<Face> faceList = faceDetect(picUrl);
		if (null != faceList) {
			result = makeMessage(faceList);
		}
		log.info("���ؽ���ǣ�"+result);
		return result;
	}

	/*public static void main(String[] args) {
		String picUrl = "http://pic11.nipic.com/20101111/6153002_002722872554_2.jpg";
		System.out.println(detect(picUrl));
	}*/
}