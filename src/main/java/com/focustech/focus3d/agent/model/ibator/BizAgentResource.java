package com.focustech.focus3d.agent.model.ibator;

import com.focustech.cief.ibatis.annotation.Column;
import com.focustech.cief.ibatis.annotation.PrimaryKey;
import com.focustech.cief.ibatis.annotation.SqlMap;
import com.focustech.cief.ibatis.annotation.Xss;
import java.util.Date;

@Xss
@SqlMap(Name = "biz_agent_resource", Class = BizAgentResource.class)
public class BizAgentResource<T, U> {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @PrimaryKey
    @Column
    private Long sn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String resourceName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_PARENT_ID
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Long resourceParentId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_TYPE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Integer resourceType;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_INTERFACE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String resourceInterface;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_DISPLAY
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String resourceDisplay;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.ACTIVE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Integer active;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.RESOURCE_ORDER
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Integer resourceOrder;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.ATTACHMENT
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String attachment;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.DESCRIPTION
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String description;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.ADDER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Long adderSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.ADDER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String adderName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.ADD_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Date addTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.UPDATER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Long updaterSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.UPDATER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private String updaterName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_resource.UPDATE_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    @Column
    private Date updateTime;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.SN
     *
     * @return the value of biz_agent_resource.SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Long getSn() {
        return sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.SN
     *
     * @param sn the value for biz_agent_resource.SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setSn(Long sn) {
        this.sn = sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_NAME
     *
     * @return the value of biz_agent_resource.RESOURCE_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_NAME
     *
     * @param resourceName the value for biz_agent_resource.RESOURCE_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_PARENT_ID
     *
     * @return the value of biz_agent_resource.RESOURCE_PARENT_ID
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Long getResourceParentId() {
        return resourceParentId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_PARENT_ID
     *
     * @param resourceParentId the value for biz_agent_resource.RESOURCE_PARENT_ID
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceParentId(Long resourceParentId) {
        this.resourceParentId = resourceParentId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_TYPE
     *
     * @return the value of biz_agent_resource.RESOURCE_TYPE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Integer getResourceType() {
        return resourceType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_TYPE
     *
     * @param resourceType the value for biz_agent_resource.RESOURCE_TYPE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_INTERFACE
     *
     * @return the value of biz_agent_resource.RESOURCE_INTERFACE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getResourceInterface() {
        return resourceInterface;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_INTERFACE
     *
     * @param resourceInterface the value for biz_agent_resource.RESOURCE_INTERFACE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceInterface(String resourceInterface) {
        this.resourceInterface = resourceInterface;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_DISPLAY
     *
     * @return the value of biz_agent_resource.RESOURCE_DISPLAY
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getResourceDisplay() {
        return resourceDisplay;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_DISPLAY
     *
     * @param resourceDisplay the value for biz_agent_resource.RESOURCE_DISPLAY
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceDisplay(String resourceDisplay) {
        this.resourceDisplay = resourceDisplay;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.ACTIVE
     *
     * @return the value of biz_agent_resource.ACTIVE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Integer getActive() {
        return active;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.ACTIVE
     *
     * @param active the value for biz_agent_resource.ACTIVE
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setActive(Integer active) {
        this.active = active;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.RESOURCE_ORDER
     *
     * @return the value of biz_agent_resource.RESOURCE_ORDER
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Integer getResourceOrder() {
        return resourceOrder;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.RESOURCE_ORDER
     *
     * @param resourceOrder the value for biz_agent_resource.RESOURCE_ORDER
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setResourceOrder(Integer resourceOrder) {
        this.resourceOrder = resourceOrder;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.ATTACHMENT
     *
     * @return the value of biz_agent_resource.ATTACHMENT
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.ATTACHMENT
     *
     * @param attachment the value for biz_agent_resource.ATTACHMENT
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.DESCRIPTION
     *
     * @return the value of biz_agent_resource.DESCRIPTION
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.DESCRIPTION
     *
     * @param description the value for biz_agent_resource.DESCRIPTION
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.ADDER_SN
     *
     * @return the value of biz_agent_resource.ADDER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Long getAdderSn() {
        return adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.ADDER_SN
     *
     * @param adderSn the value for biz_agent_resource.ADDER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setAdderSn(Long adderSn) {
        this.adderSn = adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.ADDER_NAME
     *
     * @return the value of biz_agent_resource.ADDER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getAdderName() {
        return adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.ADDER_NAME
     *
     * @param adderName the value for biz_agent_resource.ADDER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setAdderName(String adderName) {
        this.adderName = adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.ADD_TIME
     *
     * @return the value of biz_agent_resource.ADD_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.ADD_TIME
     *
     * @param addTime the value for biz_agent_resource.ADD_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.UPDATER_SN
     *
     * @return the value of biz_agent_resource.UPDATER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Long getUpdaterSn() {
        return updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.UPDATER_SN
     *
     * @param updaterSn the value for biz_agent_resource.UPDATER_SN
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setUpdaterSn(Long updaterSn) {
        this.updaterSn = updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.UPDATER_NAME
     *
     * @return the value of biz_agent_resource.UPDATER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public String getUpdaterName() {
        return updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.UPDATER_NAME
     *
     * @param updaterName the value for biz_agent_resource.UPDATER_NAME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_resource.UPDATE_TIME
     *
     * @return the value of biz_agent_resource.UPDATE_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_resource.UPDATE_TIME
     *
     * @param updateTime the value for biz_agent_resource.UPDATE_TIME
     *
     * @ibatorgenerated Mon Aug 08 17:32:24 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}