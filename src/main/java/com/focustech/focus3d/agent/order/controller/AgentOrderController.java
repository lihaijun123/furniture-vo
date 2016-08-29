package com.focustech.focus3d.agent.order.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.focustech.cief.filemanage.core.SuffixContentTypeConfig;
import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.HttpUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.MathUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.common.controller.CommonController;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentOrder;
import com.focustech.focus3d.agent.model.AgentOrderItem;
import com.focustech.focus3d.agent.model.AgentOrderLogtc;
import com.focustech.focus3d.agent.model.AgentProduct;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.ReceiveAddress;
import com.focustech.focus3d.agent.order.service.AgentOrderItemService;
import com.focustech.focus3d.agent.order.service.AgentOrderLogtcService;
import com.focustech.focus3d.agent.order.service.AgentOrderService;
import com.focustech.focus3d.agent.order.service.ReceiveAddressService;
import com.focustech.focus3d.agent.product.service.AgentProductService;
import com.focustech.focus3d.agent.user.service.AgentUserService;
import com.focustech.focus3d.pay.PayPlatformType;
import com.focustech.focus3d.pay.ali.config.AlipayConfig;
import com.focustech.focus3d.pay.ali.protocol.AlipayReqData;
import com.focustech.focus3d.pay.service.PayService;
import com.focustech.focus3d.pay.utils.PayUtils;
import com.focustech.focus3d.pay.wx.protocol.QrCodeData;
import com.focustech.focus3d.pay.yl.protocol.ClientPayParams;
import com.focustech.focus3d.pay.yl.protocol.NetPayBankCode;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Controller
@RequestMapping(value = "/order")
public class AgentOrderController extends CommonController{
	@Autowired
	private AgentProductService<AgentProduct> productService;
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@Autowired
	private AgentOrderService<AgentOrder> orderService;
	@Autowired
	private ReceiveAddressService<ReceiveAddress> receiveAddressService;
	@Autowired
	private AgentOrderItemService<AgentOrderItem> orderItemService;
	@Autowired
	private AgentOrderLogtcService<AgentOrderLogtc> orderLogtcService;
	@Autowired
	private PayService payService;
	@Autowired
	private AlipayConfig alipayConfig;

	/**
	 * 订单页
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap modelMap){
		return redirect("/order/create");
	}
	/**
	 *
	 * *
	 * @param modelMap
	 */
	@ModelAttribute
	public void setPageData(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			Long userId = currentLogin.getUserId();
			//代理商指定的优惠价格
			List<AgentProduct> privateProdList = productService.getListByUserId(userId);
			//获取其他公有产品
			List<String> uniqProdNameList = productService.getUniqProdNameList(1);
			for (String prodName : uniqProdNameList) {
				boolean isExist = false;
				for(AgentProduct privateProd : privateProdList){
					if(prodName.equals(privateProd.getName())){
						isExist = true;
						break;
					}
				}
				if(!isExist){
					AgentProduct agentProduct = new AgentProduct();
					agentProduct.setName(prodName);
					privateProdList.add(agentProduct);
				}
			}
			modelMap.put("productList", privateProdList);
			if(userId != null){
				AgentUser agentUser = agentUserService.selectBySn(userId, AgentUser.class);
				modelMap.put("agentUser", agentUser);
			}
		}
	}
	/**
	 * 下订单
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreate(ModelMap modelMap){
		return "/order/create";
	}
	/**
	 * 下订单
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String postCreate(AgentOrder order, ModelMap modelMap){
		String view = "/order/create";
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
				List<AgentOrderItem> itemFilter = new ArrayList<AgentOrderItem>();
				if(StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(mobilePhone) && StringUtils.isNotEmpty(street)){
					List<AgentOrderItem> items = order.getItems();
					if(ListUtils.isNotEmpty(items)){
						for (AgentOrderItem itm : items) {
							//订单项
							int itemAmount = TCUtil.iv(itm.getItemAmount());
							BigDecimal itemPrice = itm.getItemPrice();
							String encryptProductId = itm.getEncryptProductId();
							if(itemAmount > 0 &&  itemPrice != null && StringUtils.isNotEmpty(itemPrice.toString()) && StringUtils.isNotEmpty(encryptProductId)){
								//数量对应的价格验证，防止客户端恶意纂改
								try {
									Long prodSn = EncryptUtil.decode(encryptProductId);
									AgentProduct product = productService.selectBySn(prodSn, AgentProduct.class);
									boolean isValid = true;
									if(product != null){
										String tagName = product.getTagName();
										if(StringUtils.isNotEmpty(tagName)){
											if(tagName.indexOf("-") != -1){
												String[] amountRangeAry = tagName.split("-");
												if(amountRangeAry.length == 2){
													int begin = TCUtil.iv(amountRangeAry[0]);
													int end = TCUtil.iv(amountRangeAry[1]);
													isValid = (itemAmount >= begin && itemAmount <= end);
												}
											}
										}
									}
									if(isValid){
										itemFilter.add(itm);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					if(ListUtils.isNotEmpty(itemFilter)){
						order.setItems(itemFilter);
						orderService.save(order);
						view = redirect("/order/confirm?id=" + EncryptUtil.encode(order.getSn()));
					}
				}
			}
		}
		return view;
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
		return "/order/list";
	}
	/**
	 * 订单确认
	 * *
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
			}
		}
		return "/order/confirm";
	}
	/**
	 * 订单确认
	 * *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public String postConfirm(String encryptOrderId, String payType, ModelMap modelMap){
		String view = "";
		if(StringUtils.isNotEmpty(encryptOrderId)){
			try {
				Long orderId = EncryptUtil.decode(encryptOrderId);
				AgentOrder order = orderService.selectBySn(orderId, AgentOrder.class);
				if(order != null){
					String orderNum = order.getOrderNum();
					if(StringUtils.isEmpty(payType)){
						payType = PayPlatformType.WX.getCode();
					}
					if(PayPlatformType.WX.getCode().equals(payType)){
						byte[] qrPic = order.getQrPic();
						if(qrPic == null || qrPic.length <= 0){
							QrCodeData codeData = new QrCodeData(TCUtil.sv(order.getOrderNum()));
							qrPic = generate(codeData.createLink());
							order.setQrPic(qrPic);
							orderService.updateByKeySelective(order);
						}
						order.setQrFileUrl("/order/qrcode/" + EncryptUtil.encode(order.getSn()) + ".jpg");
						view = "/order/payway_wx";
					} else {
						if(PayPlatformType.ZGYL.getCode().equals(payType)){
							String payUrl = payService.getPayUrl();
							ClientPayParams payParams = new ClientPayParams();
							payParams.setGateId(TCUtil.sv(NetPayBankCode.getBankCodeByName("")));
							payParams.setOrdId(orderNum);
							payParams.setTransAmt(TCUtil.sv(MathUtils.round(order.getTotalPrice().doubleValue(), 2)));
							payParams.setPriv1(PayUtils.createPriv1("1"));
							String data = payService.getFormHiddenFields(payParams);
							modelMap.put("payUrl", payUrl);
							modelMap.put("data", data);
							view = "/order/payway_yl";
						} else if(PayPlatformType.ZFB.getCode().equals(payType)){
							AlipayReqData alipayReqData = new AlipayReqData(orderNum, "小小梦想家", TCUtil.sv(MathUtils.round(order.getTotalPrice().doubleValue(), 2)), "http://www.3d-focus.com/service/xxmxj", "小小梦想家");
							modelMap.put("payUrl", alipayConfig.getAlipayGetWay());
							modelMap.put("data", alipayReqData.buildFormHiddenFields(alipayConfig));
							view = "/order/payway_ali";
						}
					}
					order.setEncryptSn(EncryptUtil.encode(order.getSn()));
					modelMap.put("order", order);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return view;
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
			}
		}
		return "/order/logtc";
	}
	/**
	 * 微信支付完成监听
	 * *
	 * @param orderId
	 * @param response
	 */
	@RequestMapping(value = "/monitor/wx", method = RequestMethod.POST)
	public void monitorStatus(String orderId, HttpServletResponse response){
		if(StringUtils.isNotEmpty(orderId)){
			try {
				Long orId = EncryptUtil.decode(orderId);
				AgentOrder order = orderService.selectBySn(orId, AgentOrder.class);
				if(order != null){
					Integer status = order.getStatus();
					JSONObject jo = new JSONObject();
					jo.put("orderId", orderId);
					jo.put("isSuccess", status == 3);
					ajaxOutput(response, jo.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
		return "/order/paycomplate";
	}
	/**
	 * 订单价格设置，不同数量对应不同价格
	 * *
	 * @param prodSn
	 * @param prodName
	 * @param itemAmount
	 * @param modelMap
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getItemPrice", method = RequestMethod.GET)
	public void getItemPrice(String prodSn, String prodName, String itemAmount, ModelMap modelMap, HttpServletResponse response) throws IOException{
		String itemPrice = "0";
		String itemEncrptSn = prodSn;
		if(StringUtils.isNotEmpty(prodName) && StringUtils.isNotEmpty(itemAmount)){
			if(StringUtils.isNotEmpty(prodSn)){
				try {
					Long sn = EncryptUtil.decode(prodSn);
					AgentProduct prod = productService.selectBySn(sn, AgentProduct.class);
					itemPrice = TCUtil.sv(prod.getPrice());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				List<AgentProduct> prodList = productService.getList(1, prodName);
				for (AgentProduct agentProduct : prodList) {
					String tagName = agentProduct.getTagName().trim();
					if(tagName.indexOf("-") != -1){
						String[] amountRangeAry = tagName.split("-");
						if(amountRangeAry.length == 2){
							int begin = TCUtil.iv(amountRangeAry[0]);
							int end = TCUtil.iv(amountRangeAry[1]);
							int amount = TCUtil.iv(itemAmount);
							if(amount >= begin && amount <= end){
								itemPrice = TCUtil.sv(agentProduct.getPrice());
								itemEncrptSn = EncryptUtil.encode(agentProduct.getSn());
								break;
							}
						}
					}
				}
			}
		}
		JSONObject jo = new JSONObject();
		jo.put("prodSn", itemEncrptSn);
		jo.put("prodName", prodName);
		jo.put("itemAmount", itemAmount);
		jo.put("itemPrice", itemPrice);
		ajaxOutput(response, jo.toString());
	}

	/**
	 * 获取二维码图片
	 * *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/qrcode/{cfsFileName}", method = RequestMethod.GET)
	public void fetchFile(@PathVariable String cfsFileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//GhoeUKyAKeBg
			String servletPath = request.getRequestURI();
			String cfsFileNames = servletPath.substring(servletPath.lastIndexOf("/") + 1);
			if(!StringUtils.isEmpty(cfsFileNames)){
				cfsFileNames = HttpUtil.decodeUrl(cfsFileNames);
				if(cfsFileNames.contains(".")){
					String[] cfsFileNameInfo = cfsFileNames.split("\\.");
					cfsFileName = cfsFileNameInfo[0];
					if(!StringUtils.isEmpty(cfsFileName)){
						String suffix = "." + cfsFileNameInfo[1];
						Long decode = EncryptUtil.decode(cfsFileName);
						byte[] btyAry = null;
						if(decode != null){
							AgentOrder agentOrder = orderService.selectBySn(decode, AgentOrder.class);
							if(agentOrder != null){
								btyAry = agentOrder.getQrPic();
							}
						}
						if(btyAry != null && btyAry.length > 0){
							response.setHeader("Content-Length", TCUtil.sv(btyAry.length));
							response.setContentType(SuffixContentTypeConfig.suffixContentTypeMap.get(suffix));
							response.getOutputStream().write(btyAry);
						} else {
							response.getWriter().write("404");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
