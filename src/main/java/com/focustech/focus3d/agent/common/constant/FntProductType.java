package com.focustech.focus3d.agent.common.constant;
/**
 * *
 * @author lihaijun
 *
 */
public enum FntProductType {
	
	FURNITURE(1, "家具"),
	FLOOR(2, "地板"),
	WALL(3, "墙纸");
	
	int code;
	String nameCn;
	
	FntProductType(int code, String nameCn){
		this.code = code;
		this.nameCn = nameCn;
	}
	
	public int getCode() {
		return code;
	}

	public String getNameCn() {
		return nameCn;
	}
	/**
	 * *
	 * @param code
	 * @return
	 */
	public static String getNameEnByCode(int code){
		FntProductType[] values = FntProductType.values();
		for (FntProductType fntProductType : values) {
			int code2 = fntProductType.getCode();
			if(code == code2){
				return fntProductType.name().toLowerCase();
			}
		}
		return "";
	}
	
	public static int getCodeByNameEn(String nameEn){
		FntProductType[] values = FntProductType.values();
		for (FntProductType fntProductType : values) {
			String name = fntProductType.name().toLowerCase();
			if(name.equals(nameEn)){
				return fntProductType.getCode();
			}
		}
		return 0;
	}
}
