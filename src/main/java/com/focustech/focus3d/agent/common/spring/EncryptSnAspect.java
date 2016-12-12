package com.focustech.focus3d.agent.common.spring;

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.focus3d.agent.model.CommonModel;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Component
@Aspect
public class EncryptSnAspect {

	public static final String EXECUTION_PREFIX_SERVICE_LIST = "com.focustech.focus3d.agent..*.service.impl..*.list*(..)";
	public static final String EXECUTION_PREFIX_DAO_LIST = "com.focustech.focus3d.agent..*.dao..*.list*(..)";

	@Pointcut(value = "execution(* " + EXECUTION_PREFIX_SERVICE_LIST + ")")
	public void serviceList(){

	}
	@Pointcut(value = "execution(* " + EXECUTION_PREFIX_DAO_LIST + ")")
	public void daoList(){

	}

	@AfterReturning(value = "serviceList() || daoList()", returning = "retList")
	public void encryptSn(Object retList){
	/*	String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		jp.getTarget();
		Object obj = args[0];
		if(obj instanceof CommonModel){

		}*/
		if(retList instanceof List){
			List<Object> l = (List<Object>)retList;
			for(int i = 0; i < l.size(); i ++){
				Object obj = l.get(i);
				if(obj instanceof CommonModel){
					CommonModel commonModel = (CommonModel)obj;
					commonModel.setEncryptSn(EncryptUtil.encode(commonModel.getSn()));
				}
			}
		}
	}

}
