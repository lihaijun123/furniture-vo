package com.focustech.focus3d.agent.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentOrderItem;
import com.focustech.focus3d.agent.model.AgentOrderLogtc;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.order.service.AgentOrderItemService;
import com.focustech.focus3d.agent.order.service.AgentOrderLogtcService;
import com.focustech.focus3d.agent.order.service.AgentOrderService;
import com.focustech.focus3d.agent.order.service.ReceiveAddressService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/accountsuborder")
public class AgentSubAccountOrderController extends CommonController {
	@Autowired
	private ReceiveAddressService<ReceiveAddress> receiveAddressService;
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private AgentOrderService<AgentOrder> orderService;
	@Autowired
	private AgentOrderLogtcService<AgentOrderLogtc> orderLogtcService;
	@Autowired
	private AgentOrderItemService<AgentOrderItem> orderItemService;
	/**
	 *
	 * *
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list/{userId}/{status}", method = RequestMethod.GET)
	public String list(@PathVariable String userId, @PathVariable String status, ModelMap modelMap) throws Exception{
		if(StringUtils.isNotEmpty(userId)){
			List<AgentOrder> orderList = orderService.getList(EncryptUtil.decode(userId), null);
			AgentUser agentUser = new AgentUser();
			agentUser.setEncryptSn(userId);
			for (AgentOrder agentOrder : orderList) {
				Integer s = agentOrder.getStatus();
				if(s == 2){
					agentUser.getWaitPayList().add(agentOrder);
				}
				if(s == 3){
					agentUser.getConfirmedList().add(agentOrder);
				}
				if(s == 5){
					agentUser.getWaitConfirmList().add(agentOrder);
				}
			}
			agentUser.setOrderList(orderList);
			modelMap.put("status", TCUtil.iv(status));
			modelMap.put("agentUser", agentUser);
		}
		return "/user/sub/order/list";
	}
	/**
	 * 下订单给子账户
	 * *
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/create/{subUserId}", method = RequestMethod.GET)
	public String getCreate(@PathVariable String subUserId, ModelMap modelMap) throws Exception{
		if(StringUtils.isNotEmpty(subUserId)){
			Long userId = EncryptUtil.decode(subUserId);
			if(userId != null){
				ReceiveAddress receiveAddress = receiveAddressService.getByUserId(userId);
				if(receiveAddress == null){
					AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
					receiveAddress = new ReceiveAddress();
					receiveAddress.setUserName(agentUser.getUserName());
					receiveAddress.setMobilePhone(agentUser.getMobilePhone());
					receiveAddress.setProvince(agentUser.getProvince());
					receiveAddress.setCity(agentUser.getCity());
					receiveAddress.setStreet(agentUser.getStreet());
				}
				AgentUser agentUser = new AgentUser();
				agentUser.setEncryptSn(subUserId);
				modelMap.put("agentUser", agentUser);
				modelMap.put("receiveAddress", receiveAddress);
			}
		}
		return "/user/sub/order/create";
	}
	/**
	 * 下订单给子账户
	 * *
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/create/{subUserId}", method = RequestMethod.POST)
	public String postCreate(@PathVariable String subUserId, AgentOrder order, ModelMap modelMap) throws Exception{
		String view = "/user/sub/order/create";
		if(StringUtils.isNotEmpty(subUserId)){
			Long userId = EncryptUtil.decode(subUserId);
			if(userId != null){
				order.setUserId(userId);
				order.setSubUserId(subUserId);
				//收货信息
				ReceiveAddress receiveAddress = order.getReceiveAddress();
				receiveAddress.setUserId(userId);
				String userName = receiveAddress.getUserName();
				String mobilePhone = receiveAddress.getMobilePhone();
				String street = receiveAddress.getStreet();
				if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(mobilePhone) && StringUtils.isNotEmpty(street)){
					orderService.saveSub(order);
					view = redirect("/accountsuborder/confirm?id=" + EncryptUtil.encode(order.getSn()));
				}
			}
		}
		return view;
	}
	/**
	 *
	 * *
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String viewConfirm(String id,  ModelMap modelMap) throws Exception{
		if(StringUtils.isNotEmpty(id)){
			Long orderId = EncryptUtil.decode(id);
			AgentOrder order = orderService.selectBySn(orderId, AgentOrder.class);
			Long orderSn = order.getSn();
			if(orderSn != null && orderSn > 0){
				List<AgentOrderItem> orderItem = orderItemService.getByOrderId(orderSn);
				if(ListUtils.isNotEmpty(orderItem)){
					order.setItems(orderItem);
				}
				ReceiveAddress receiveAddress = receiveAddressService.selectBySn(order.getReceiveAddressId(), ReceiveAddress.class);
				if(receiveAddress != null){
					order.setReceiveAddress(receiveAddress);
				}
				AgentOrderLogtc agentOrderLogtc = orderLogtcService.getByOrderId(orderSn);
				if(agentOrderLogtc != null){
					order.setOrderLogtc(agentOrderLogtc);
				}
				Long userId = order.getUserId();
				order.setEncryptSn(EncryptUtil.encode(orderSn));
				order.setSubUserId(EncryptUtil.encode(userId));
				modelMap.put("order", order);
				//获取管理员信息
				AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
				Long parentSn = agentUser.getParentSn();
				if(parentSn != null){
					AgentUser parentAgentUser = agentUserService.selectBySn(parentSn, AgentUser.class);
					modelMap.put("parentUser", parentAgentUser);
				}
			}
		}
		return "/user/sub/order/confirm";
	}

	/**
	 * 订单物流信息
	 * *
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logtc", method = RequestMethod.GET)
	public String logtc(String id, ModelMap modelMap) throws Exception{
		if(StringUtils.isNotEmpty(id)){
			Long orderId = EncryptUtil.decode(id);
			AgentOrder order = orderService.selectBySn(orderId, AgentOrder.class);
			Long orderSn = order.getSn();
			if(orderSn != null && orderSn > 0){
				ReceiveAddress receiveAddress = receiveAddressService.selectBySn(order.getReceiveAddressId(), ReceiveAddress.class);
				if(receiveAddress != null){
					order.setReceiveAddress(receiveAddress);
				}
				AgentOrderLogtc agentOrderLogtc = orderLogtcService.getByOrderId(orderSn);
				if(agentOrderLogtc != null){
					order.setOrderLogtc(agentOrderLogtc);
				}
				order.setEncryptSn(EncryptUtil.encode(orderSn));
				modelMap.put("order", order);
			}
			modelMap.put("subUserId", EncryptUtil.encode(order.getUserId()));
		}
		return "/user/sub/order/logtc";
	}
	/**
	 *
	 * *
	 * @param id
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logtc", method = RequestMethod.POST)
	public String postLogtc(AgentOrderLogtc agentOrderLogtc, ModelMap modelMap) throws Exception{
		Long sn = agentOrderLogtc.getSn();
		String subUserId = agentOrderLogtc.getSubUserId();
		if(sn != null){
			String logtcName = agentOrderLogtc.getLogtcName();
			String logtcId = agentOrderLogtc.getLogtcId();
			agentOrderLogtc = orderLogtcService.selectBySn(sn, AgentOrderLogtc.class);
			if(agentOrderLogtc != null){
				agentOrderLogtc.setLogtcName(logtcName);
				agentOrderLogtc.setLogtcId(logtcId);
				agentOrderLogtc.setIsSend(1);
				orderLogtcService.updateByKeySelective(agentOrderLogtc);
			}
		}
		if(StringUtils.isNotEmpty(subUserId)){
			return redirect("/accountsuborder/list/" + subUserId + "/3");
		} else {
			return redirect("/accountsub");
		}
	}
	/**
	 *
	 * *
	 * @param order
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/confirmpay", method = RequestMethod.POST)
	public String confirmpay(AgentOrder order, ModelMap modelMap){
		Long sn = order.getSn();
		String subUserId = order.getSubUserId();
		if(sn != null){
			order = orderService.selectBySn(sn, AgentOrder.class);
			order.setStatus(3);//确认支付
			orderService.updateByKeySelective(order);
		}
		if(StringUtils.isNotEmpty(subUserId)){
			return redirect("/accountsuborder/list/" + subUserId + "/3");
		} else {
			return redirect("/accountsub");
		}
	}

}
