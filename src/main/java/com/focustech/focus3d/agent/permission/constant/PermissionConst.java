package com.focustech.focus3d.agent.permission.constant;

import java.util.HashMap;
import java.util.Map;

import com.focustech.common.utils.MessageUtils;

/**
 * 权限常量
 * *
 * @author lihaijun
 *
 */
public class PermissionConst {
	public static final String ROLE_SN = "ADMIN_ROLE_SN";
	public static final String ROLE_ID = "ROLE_ID";
	public static final String ROLE_NAME = "ROLE_NAME";
	public static final String RESOURCE_SN = "ADMIN_RESOURCE_SN";
	public static final String RESOURCE_LINK_ID = "RESOURCE_LINK_ID";
	public static final String RESOURCE_PERM_FLAG = "PERM_FLAG";
	public static final String PARENT_NODE_SN = "RESOURCE_PARENT_SN";
	public static final String NODE_DISPLAY_NAME = "RESOURCE_DISPLAY_NAME";
	public static final String NODE_TYPE = "RESOURCE_TYPE";
	public static final String NODE_NAME = "RESOURCE_NAME";
	public static final String NODE_PERM_FLAG = "PERM_FLAG";
	public static final String NODE_CODE = "RESOURCE_CODE";
	public static final String NODE_URL = "RESOURCE_URL";
	public static final String NODE_ACTIVE = "ACTIVE";
	public static final String WHERE_USE_MAIN_SITE = "from=mainSite";
	/** 会员中心域名 */
	public static final String VO_DOMAIN = "VO_DOMAIN";
	/**排除的菜单节点*/
	public static final String EXCEPT_RESOURCE_CODE_ARY = "EXCEPT_RESOURCE_CODE_ARY";

	public static Map<String, Map<String, String>> avatarPageHeadLinkMap = new HashMap<String, Map<String,String>>();
	/**根节点*/
	public static final String PARENT_NODE = "-1";
	/**固有*/
	public static final String NODE_TYPE_INHERENT = "1";
	/**可授权*/
	public static final String NODE_TYPE_AUTH = "2";
	/**无*/
	public static final String NODE_TYPE_NO = "3";
	/**固有可授权*/
	public static final String NODE_TYPE_INHERENT_AUTH = "4";
	/**菜单*/
	public static final String NODE_PERM_FLAG_MENU = "1";
	/**功能点*/
	public static final String NODE_PERM_FLAG_FUNC = "2";
	/**vo外频道功能节点*/
	public static final String NODE_PERM_AVATAR_CHANNEL = "3";
	/**节点可用状态-0*/
	public static final String NODE_ACTIVE_0 = "0";

	/**产品菜单节点的SN*/
	public static final String PRODUCT_RESOURCE_CODE = "20001";
	/**产品菜单-产品管理SN*/
	public static final String PRODUCT_MANAGE_RESOURCE_CODE = "20001001";
	/**产品菜单-分组管理SN*/
	public static final String PRODUCT_GROUP_MANAGE_RESOURCE_CODE = "20001002";
	/**产品菜单-产品分配SN*/
	public static final String PRODUCT_ASSIGN_RESOURCE_CODE = "20001003";

    /** 我的订单 */
    public static final String ORDERS_RESOURCE_CODE = "30003";

	/**媒体库菜单节点SN*/
	public static final String MEDIA_INFO_RESOURCE_CODE = "20005";

	public static final String MENU_TREE_NODE_CODE = "nodeCode";
	/**图册节点*/
	public static final String ATLAS_RESOURCE_CODE = "20002";
    /** 展位布置节点 */
    public static final String BOOTH_RESOURCE_CODE = "20004";
    /**数据统计*/
    public static final String DATA_STATISTICS_RESOURCE_CODE = "70000";
    /**产品目录*/
    public static final String NODE_AVATAR_CHANNEL_1_CODE = "100000000";
    /**会员中心*/
    public static final String NODE_AVATAR_CHANNEL_2_CODE = "100000001";
    /**历届展会*/
    public static final String NODE_AVATAR_CHANNEL_3_CODE = "100000002";
    /**展馆商城*/
    public static final String NODE_AVATAR_CHANNEL_4_CODE = "100000003";
    /**收藏夹*/
    public static final String NODE_AVATAR_CHANNEL_5_CODE = "100000004";
    /**帮助*/
    public static final String NODE_AVATAR_CHANNEL_6_CODE = "100000005";
    public static final String DOMAIN = "domain";
    public static final String DISPLAY_NAME = "displayName";
    public static final String FUNC_NAME = "funcName";

    static {
    	String avatarDomain = "";
    	String voDomain = "";
    	Map<String, String> linkMap_1 = new HashMap<String, String>();
    	linkMap_1.put(DOMAIN, avatarDomain);
    	linkMap_1.put(DISPLAY_NAME, "产品目录");
    	linkMap_1.put(FUNC_NAME, "gotoProdCategory");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_1_CODE, linkMap_1);
    	Map<String, String> linkMap_2 = new HashMap<String, String>();
    	linkMap_2.put(DOMAIN, voDomain);
    	linkMap_2.put(DISPLAY_NAME, "会员中心");
    	linkMap_2.put(FUNC_NAME, "gotoVoCenter");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_2_CODE, linkMap_2);
    	Map<String, String> linkMap_3 = new HashMap<String, String>();
    	linkMap_3.put(DOMAIN, avatarDomain);
    	linkMap_3.put(DISPLAY_NAME, "历届展会");
    	linkMap_3.put(FUNC_NAME, "linkHref");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_3_CODE, linkMap_3);
    	Map<String, String> linkMap_4 = new HashMap<String, String>();
    	linkMap_4.put(DOMAIN, voDomain);
    	linkMap_4.put(DISPLAY_NAME, "展馆商城");
    	linkMap_4.put(FUNC_NAME, "gotoOnlineBuy");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_4_CODE, linkMap_4);
    	Map<String, String> linkMap_5 = new HashMap<String, String>();
    	linkMap_5.put(DOMAIN, voDomain);
    	linkMap_5.put(DISPLAY_NAME, "收藏夹");
    	linkMap_5.put(FUNC_NAME, "gotoFavorites");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_5_CODE, linkMap_5);
    	Map<String, String> linkMap_6 = new HashMap<String, String>();
    	linkMap_6.put(DOMAIN, avatarDomain);
    	linkMap_6.put(DISPLAY_NAME, "帮助");
    	linkMap_6.put(FUNC_NAME, "gotoHelp");
    	avatarPageHeadLinkMap.put(NODE_AVATAR_CHANNEL_6_CODE, linkMap_6);
    }

}
