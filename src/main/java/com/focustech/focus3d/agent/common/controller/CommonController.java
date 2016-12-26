package com.focustech.focus3d.agent.common.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.focustech.cief.filemanage.core.FileInfo;
import com.focustech.cief.filemanage.core.FileManageFactory;
import com.focustech.cief.filemanage.core.IFileManageClient;
import com.focustech.common.qrcodes.MatrixToImageWriter;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.agent.auth.service.AgentResourceService;
import com.focustech.focus3d.agent.auth.service.AgentRoleResourceService;
import com.focustech.focus3d.agent.auth.service.AgentUserRoleService;
import com.focustech.focus3d.agent.filter.RequestThreadLocal;
import com.focustech.focus3d.agent.model.AgentLogin;
import com.focustech.focus3d.agent.model.AgentResource;
import com.focustech.focus3d.agent.model.AgentRoleResource;
import com.focustech.focus3d.agent.model.AgentUser;
import com.focustech.focus3d.agent.model.AgentUserRole;
import com.focustech.focus3d.agent.user.service.AgentUserService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 *
 * *
 * @author lihaijun
 *
 */
public abstract class CommonController {

	@Autowired
	private AgentUserRoleService<AgentUserRole> agentUserRoleService;
	@Autowired
	private AgentRoleResourceService<AgentRoleResource> agentRoleResourceService;
	@Autowired
	private AgentResourceService<AgentResource> agentResourceService;
	@Autowired
	private AgentUserService<AgentUser> agentUserService;
	@ModelAttribute
	public void setAuthInfo(ModelMap modelMap){
		AgentLogin currentLogin = RequestThreadLocal.getLoginInfo();
		if(currentLogin != null){
			//Long userId = currentLogin.getUserId();
			//List<AgentResource> resourceList = getUserResourceList(userId);
			//modelMap.put("resourceList", resourceList);
			//AgentUser user = agentUserService.selectBySn(userId, AgentUser.class);
			//modelMap.put("user", user);
		
		}
	}
	/**
	 *
	 * *
	 * @return
	 */
	protected List<AgentResource> getUserResourceList(Long userId) {
		List<AgentResource> resourceList = new ArrayList<AgentResource>();
		List<AgentUserRole> roles = agentUserRoleService.getListByUserId(userId);
		if(ListUtils.isNotEmpty(roles)){
			List<AgentRoleResource> resources = agentRoleResourceService.getListByRoleId(roles.get(0).getRoleSn());
			for (AgentRoleResource agentRoleResource : resources) {
				AgentResource resource = agentResourceService.selectBySn(agentRoleResource.getResourceSn(), AgentResource.class);
				resourceList.add(resource);
			}
		}
	
		Collections.sort(resourceList, new Comparator<AgentResource>() {
			@Override
			public int compare(AgentResource o1, AgentResource o2) {
				if(TCUtil.iv(o1.getResourceOrder()) < TCUtil.iv(o2.getResourceOrder())){
					return -1;
				} else if(TCUtil.iv(o1.getResourceOrder()) > TCUtil.iv(o2.getResourceOrder())){
					return 1;
				}
				return 0;
			}
		});
		return resourceList;
	}
	/**
	 *
	 * *
	 * @return
	 */
	protected String getUserResourceOfFirst(){
		String rv = "";
		List<AgentResource> userResourceList = getUserResourceList(null);
		if(ListUtils.isNotEmpty(userResourceList)){
			rv = userResourceList.get(0).getResourceInterface();
		}
		return rv;
	}
	 /**
     * ajax输出
     *
     * @param response HttpServletResponse
     * @param outputString 输出字符
     * @throws IOException 输出流异常

     */
    protected void ajaxOutput(HttpServletResponse response, String outputString) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(outputString);
        response.getWriter().flush();
    }

    /**
     * 使用redirect跳转到指定的请求上
     *
     * @param URI 指定的请求链接
     * @return
     */
    protected String redirect(String URI, Integer statusCode, HttpServletRequest req) {
    	req.getSession().setAttribute("saveCode", TCUtil.sv(statusCode));
    	return redirect(URI);
    }

    /**
     * 使用redirect跳转到指定的请求上
     *
     * @param URI 指定的请求链接
     * @return
     */
    public static String redirect(String URI) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("redirect:");
        stringBuilder.append(URI);
        return stringBuilder.toString();
    }

    /**
	 *
	 * 生成二维码
	 * */
	public static byte[] generate(String content){
		try {
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 319, 319, hints);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", arrayOutputStream);
			return arrayOutputStream.toByteArray();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取二维码sn
	 * *
	 * @param productInfo
	 * @return
	 */
	protected long getQrFileSn(String content, String sequence) throws RuntimeException {
		long qrFileSn = 0;
		try {
			FileInfo fileUploadObject = new FileInfo();
			fileUploadObject.setName(sequence + ".jpg");
			fileUploadObject.setBytes(generate(content));
			IFileManageClient fileManageClient = FileManageFactory.getFileManageClient(fileUploadObject);
			fileManageClient.upload();
			qrFileSn = TCUtil.lv(fileManageClient.getFileId());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return qrFileSn;
	}
	/**
	 *
	 * *
	 * @param req
	 * @return
	 */
	public boolean isWeixinBrowser(HttpServletRequest req){
        String ua = TCUtil.sv(req.getHeader("User-Agent")).toLowerCase();
        return ua.contains("micromessenger");
    }
	/**
	 *
	 * *
	 * @param req
	 * @return
	 */
	public int getMobileSystemType(HttpServletRequest req){
        String ua = TCUtil.sv(req.getHeader("User-Agent")).toLowerCase();
        if(ua.contains("iphone") || ua.contains("ipad")){
        	return 2;
        }
        return !ua.contains("android") ? 0 : 1;
	}
}
