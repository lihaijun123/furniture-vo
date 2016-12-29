package com.focustech.focus3d.agent.common.spring;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.furniture.restful.common.RestMethodDesc;
import com.focustech.focus3d.furniture.restful.common.RestServiceBean;
import com.focustech.focus3d.furniture.restful.common.RestServiceMethod;
/**
 * 
 * *
 * @author lihaijun
 *
 */
@Component
public class RestServiceBeanPostProcessor implements BeanPostProcessor {
	private static final Logger log = LoggerFactory.getLogger(RestServiceBeanPostProcessor.class);
	
	private static final Map<String, RestServiceBean> cache = new HashMap<String, RestServiceBean>();
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Annotation[] annotations = bean.getClass().getAnnotations();
		if(annotations.length > 0){
			Path pathAnno = AnnotationUtils.findAnnotation(bean.getClass(), Path.class);
			if(pathAnno != null){
				RestServiceBean restServiceBean = new RestServiceBean();
				log.info("reset-service:" + bean.getClass().getName());
				for (Annotation serviceAnno : annotations) {
					Object value = AnnotationUtils.getValue(serviceAnno);
					if(value != null){
						String serviceAnnoName = serviceAnno.annotationType().getName();
						log.info("serviceAnno[" + serviceAnnoName + "]-value:" + TCUtil.sv(value));
						String pathValue = value.toString();
						if("/rest/export".equals(pathValue)){
							return bean;
						}
						if(Path.class.getName().equals(serviceAnnoName)){
							restServiceBean.setPath(pathValue);
						}
						if(Produces.class.getName().equals(serviceAnnoName)){
							restServiceBean.setProduces((String[])value);
						}
						if(RestMethodDesc.class.getName().equals(serviceAnnoName)){
							restServiceBean.setDesc(pathValue);
						}
					}
				
				}
				Method[] methods = bean.getClass().getDeclaredMethods();
				for (Method method : methods) {
					log.info("method:" + method.getName());
					RestServiceMethod restServiceMethod = new RestServiceMethod();
					Path methodPathAnno = AnnotationUtils.findAnnotation(method, Path.class);
					if(methodPathAnno != null){
						Annotation[] methodAnnos = method.getAnnotations();
						for (Annotation methodAnno : methodAnnos) {
							String methodAnnoName = methodAnno.annotationType().getName();
							log.info("methodAnnoName:" + methodAnnoName);
							Object methodAnnoValue = AnnotationUtils.getValue(methodAnno);
							log.info("methodAnno[" + methodAnno + "]-value:" + TCUtil.sv(methodAnnoValue));
							if(Path.class.getName().equals(methodAnnoName)){
								restServiceMethod.setPath(methodAnnoValue.toString());
							}
							if(POST.class.getName().equals(methodAnnoName)){
								restServiceMethod.setHttpMethod("post");
							} else if (GET.class.getName().equals(methodAnnoName)){
								restServiceMethod.setHttpMethod("get");
							}
							if(RestMethodDesc.class.getName().equals(methodAnnoName)){
								restServiceMethod.setDesc(methodAnnoValue.toString());
							}
						
						}
						Annotation[][] parameterAnnotations = method.getParameterAnnotations();
						if(parameterAnnotations.length > 0){
							for (Annotation[] parameterAnno : parameterAnnotations) {
								for (Annotation annotation : parameterAnno) {
									restServiceMethod.getRequestParameters().add(AnnotationUtils.getValue(annotation).toString());
								}
							}
						}
						restServiceBean.getServices().add(restServiceMethod);
					}
				}
				cache.put(beanName, restServiceBean);
			}
		}
		return bean;
	}

	public static Map<String, RestServiceBean> getCache() {
		return cache;
	}

	
}
