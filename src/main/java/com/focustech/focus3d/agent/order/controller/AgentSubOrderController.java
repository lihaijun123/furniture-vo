package com.focustech.focus3d.agent.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;
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
@RequestMapping(value = "/ordersub")
public class AgentSubOrderController extends CommonController {
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
	//@Autowired
	//private AgentProductService<AgentProduct> productService;

	@ModelAttribute
	public void setPageData(ModelMap modelMap){
		//获取其他公有产品
		//List<String> uniqProdNameList = productService.getUniqProdNameList(1);
		//modelMap.put("productList", uniqProdNameList);
	}

	/**
	 * 订单页
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		return redirect("/ordersub/list");
	}

	/**
	 * 下订单
	 * *
	 * @param modelMap
	 * @return
	 */
	//@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreate(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			Long userId = currentLogin.getUserId();
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
			modelMap.put("receiveAddress", receiveAddress);
		}
		return "/order/sub/create";
	}
	/**
	 * 下订单
	 * *
	 * @param modelMap
	 * @return
	 */
	//@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postCreate(AgentOrder order, ModelMap modelMap){
		String view = "/order/sub/create";
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			Long userId = currentLogin.getUserId();
			if(userId != null){
				order.setUserId(userId);
				//收货信息
				ReceiveAddress receiveAddress = order.getReceiveAddress();
				receiveAddress.setUserId(userId);
				String userName = receiveAddress.getUserName();
				String mobilePhone = receiveAddress.getMobilePhone();
				String street = receiveAddress.getStreet();
				if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(mobilePhone) && StringUtils.isNotEmpty(street)){
					orderService.saveSub(order);
					view = redirect("/ordersub/confirm?id=" + EncryptUtil.encode(order.getSn()));
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
	public String viewConfirm(String id, ModelMap modelMap) throws Exception{
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
				order.setEncryptSn(EncryptUtil.encode(orderSn));
				modelMap.put("order", order);
				AgentUser agentUser = agentUserService.selectBySn(order.getUserId(), AgentUser.class);
				Long parentSn = agentUser.getParentSn();
				if(parentSn != null){
					AgentUser parentAgentUser = agentUserService.selectBySn(parentSn, AgentUser.class);
					modelMap.put("parentUser", parentAgentUser);
				}
			}
		}
		return "/order/sub/confirm";
	}

	/**
	 * 我的订单列表
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			Long userId = currentLogin.getUserId();
			if(userId != null){
				List<AgentOrder> orderList = orderService.getList(userId, null);
				modelMap.put("orderList", orderList);
			}
		}
		return "/order/sub/list";
	}
	/**
	 *
	 * *
	 * @param order
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/payed", method = RequestMethod.POST)
	public String payed(AgentOrder order, ModelMap modelMap){
		Long sn = order.getSn();
		if(sn != null){
			order = orderService.selectBySn(sn, AgentOrder.class);
			order.setStatus(5);//支付待确认
			order.setPayTime(new Date());
			orderService.updateByKeySelective(order);
		}
		return redirect("/ordersub/paysult?orderId=" + EncryptUtil.encode(sn));
	}

	/**
	 * 支付结果页面
	 * *
	 * @param orderId
	 * @param response
	 */
	@RequestMapping(value = "/paysult", method = RequestMethod.GET)
	public String paysult(String orderId, ModelMap modelMap){
		if(StringUtils.isNotEmpty(orderId)){
			try {
				Long orId = EncryptUtil.decode(orderId);
				AgentOrder order = orderService.selectBySn(orId, AgentOrder.class);
				if(order != null){
					modelMap.put("order", order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "/order/sub/paycomplate";
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
		}
		return "/order/sub/logtc";
	}
}
