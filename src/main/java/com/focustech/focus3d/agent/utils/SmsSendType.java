package com.focustech.focus3d.agent.utils;
/**
 *
 * *
 * @author lihaijun
 *
 */
public enum SmsSendType {
	ORDER_SUBMIT(1, "订单提交内部通知"),
	ORDER_PAY_OK(2, "订单支付成功内部通知"),
	COMMON(3, "通用"),
	AGENT_APPLY_INSIDE_NOTIFY(4, "代理商申请内部通知"),
	AGENT_LOGIN(5, "代理商登录"),
	AGENT_APPLY(6, "代理商申请"),
	AGENT_MODIFY_PASSWORD(7, "代理商修改密码");

	private int type;
	private String name;

	SmsSendType(int type, String name){
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * *
	 * @param type
	 * @return
	 */
	public static SmsSendType valueOf(int type){
		for(SmsSendType t : SmsSendType.values()){
			if(t.getType() == type){
				return t;
			}
		}
		return null;
	}

	public boolean equals(SmsSendType sendType){
		boolean flag = false;
		if(sendType != null){
			flag = sendType.getType() == this.getType();
		}
		return flag;
	}
}
