package com.focustech.focus3d.agent.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;

public class RequestMessageCookie {

	public static final String COOKIE_KEY = "_f3d_session";

	public static final String MESSAGE = "message";

	public Map<String, String> messageMap = new HashMap<String, String>();

	public void addMessage(String value) {
		if(!StringUtils.isEmpty(value)){
			messageMap.put(MESSAGE, value);
		}
	}

	public String getMessage(){
		String message = messageMap.get(MESSAGE);
		messageMap.remove(MESSAGE);
		return TCUtil.sv(message);
	}

	public void build(String cookieValue){
		if(!TCUtil.isEmpty(cookieValue)){
			byte[] decodeBase64 = Base64.decodeBase64(cookieValue.getBytes());
			String str = new String(decodeBase64);
			if(!StringUtils.isEmpty(str)){
				JSONObject fromObject = JSONObject.fromObject(str);
				Iterator keys = fromObject.keys();
				while(keys.hasNext()){
					String value = TCUtil.sv(keys.next());
					messageMap.put(value, fromObject.getString(value));
				}
			}
		}
	}

	public String getCookieValue(){
		JSONObject jo = new JSONObject();
		for(Map.Entry<String, String> map :  messageMap.entrySet()){
			jo.put(map.getKey(), map.getValue());
		}
		return new String(Base64.encodeBase64(jo.toString().getBytes()));
	}
}
