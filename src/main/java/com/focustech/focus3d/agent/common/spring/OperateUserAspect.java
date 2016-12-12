package com.focustech.focus3d.agent.common.spring;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.focustech.cief.ibatis.domain.BaseEntity;

/**
 *
 * *
 * @author lihaijun
 *
 */
@Component
@Aspect
public class OperateUserAspect {
	private Logger log = org.slf4j.LoggerFactory.getLogger(OperateUserAspect.class);
	public static final String EXECUTION_PREFIX_SERVICE_INSERT = "com.focustech.focus3d.agent.service.impl..*.insert*(..)";

	@Pointcut(value = "execution(* " + EXECUTION_PREFIX_SERVICE_INSERT + ")")
	public void insert(){

	}

	@Before(value = "insert()")
	public void addOperateInfo(JoinPoint jp){
		String methodName = jp.getSignature().getName();
		log.info("methodName:" + methodName);
		Object[] args = jp.getArgs();
		Object obj = args[0];
		if(obj instanceof BaseEntity){
			BaseEntity baseEntity = (BaseEntity)obj;
			baseEntity.setAdderSn(-1L);
			baseEntity.setAdderName("furniture_restservice");
			baseEntity.setAddTime(new Date());
			baseEntity.setUpdaterSn(-1L);
			baseEntity.setUpdaterName("furniture_restservice");
			baseEntity.setUpdateTime(new Date());
		}
	}

}
