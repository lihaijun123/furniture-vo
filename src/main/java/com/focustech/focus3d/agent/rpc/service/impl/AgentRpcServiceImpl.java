package com.focustech.focus3d.agent.rpc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.rpc.service.AgentRpcService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class AgentRpcServiceImpl implements AgentRpcService {

	private Map<String, Object> cache = new HashMap<String, Object>();

	private ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
	@Autowired
	private AgentUserService<AgentUser> agentUserService;

	@PostConstruct
	public void init(){
		pool.scheduleAtFixedRate(new Runnable() {
			Map<String, Object> cacheTmp = new HashMap<String, Object>();
			@Override
			public void run() {
				List<AgentUser> agentList = agentUserService.getAvailableList();
				for (AgentUser agentUser : agentList) {
					String userName = agentUser.getUserName();
					String wxId = agentUser.getWxId();
					String partnerId = agentUser.getPartnerId();
					cacheTmp.put(userName, agentUser.getSn());
					cacheTmp.put(wxId, agentUser.getSn());
					cacheTmp.put(partnerId, agentUser.getSn());
				}
				if(!cacheTmp.isEmpty()){
					cache.clear();
					cache.putAll(cacheTmp);
					cacheTmp.clear();
				}
			}
		}, 15, 1200, TimeUnit.SECONDS);
	}

	@Override
	public boolean searchAgent(String key) {
		return cache.containsKey(key);
	}
}
