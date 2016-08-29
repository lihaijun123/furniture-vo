package com.focustech.focus3d.agent.model.ibator;

import com.focustech.cief.ibatis.annotation.Column;
import com.focustech.cief.ibatis.annotation.PrimaryKey;
import com.focustech.cief.ibatis.annotation.SqlMap;
import com.focustech.cief.ibatis.annotation.Xss;
import java.util.Date;

@Xss
@SqlMap(Name = "biz_agent_message_validate", Class = BizAgentMessageValidate.class)
public class BizAgentMessageValidate<T, U> {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @PrimaryKey
    @Column
    private Long sn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.MOBILE_PHONE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String mobilePhone;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.VERIFY_CODE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String verifyCode;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.VALIDITY_PERIOD
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Integer validityPeriod;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.STATUS
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Integer status;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.IP_ADDR
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String ipAddr;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.IP_POSITION
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String ipPosition;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.CREATE_DATE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Date createDate;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.ADDER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Long adderSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.ADDER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String adderName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.ADD_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Date addTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.UPDATER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Long updaterSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.UPDATER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private String updaterName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_agent_message_validate.UPDATE_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    @Column
    private Date updateTime;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.SN
     *
     * @return the value of biz_agent_message_validate.SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Long getSn() {
        return sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.SN
     *
     * @param sn the value for biz_agent_message_validate.SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setSn(Long sn) {
        this.sn = sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.MOBILE_PHONE
     *
     * @return the value of biz_agent_message_validate.MOBILE_PHONE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.MOBILE_PHONE
     *
     * @param mobilePhone the value for biz_agent_message_validate.MOBILE_PHONE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.VERIFY_CODE
     *
     * @return the value of biz_agent_message_validate.VERIFY_CODE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.VERIFY_CODE
     *
     * @param verifyCode the value for biz_agent_message_validate.VERIFY_CODE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.VALIDITY_PERIOD
     *
     * @return the value of biz_agent_message_validate.VALIDITY_PERIOD
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.VALIDITY_PERIOD
     *
     * @param validityPeriod the value for biz_agent_message_validate.VALIDITY_PERIOD
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.STATUS
     *
     * @return the value of biz_agent_message_validate.STATUS
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.STATUS
     *
     * @param status the value for biz_agent_message_validate.STATUS
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.IP_ADDR
     *
     * @return the value of biz_agent_message_validate.IP_ADDR
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.IP_ADDR
     *
     * @param ipAddr the value for biz_agent_message_validate.IP_ADDR
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.IP_POSITION
     *
     * @return the value of biz_agent_message_validate.IP_POSITION
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getIpPosition() {
        return ipPosition;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.IP_POSITION
     *
     * @param ipPosition the value for biz_agent_message_validate.IP_POSITION
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setIpPosition(String ipPosition) {
        this.ipPosition = ipPosition;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.CREATE_DATE
     *
     * @return the value of biz_agent_message_validate.CREATE_DATE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.CREATE_DATE
     *
     * @param createDate the value for biz_agent_message_validate.CREATE_DATE
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.ADDER_SN
     *
     * @return the value of biz_agent_message_validate.ADDER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Long getAdderSn() {
        return adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.ADDER_SN
     *
     * @param adderSn the value for biz_agent_message_validate.ADDER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setAdderSn(Long adderSn) {
        this.adderSn = adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.ADDER_NAME
     *
     * @return the value of biz_agent_message_validate.ADDER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getAdderName() {
        return adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.ADDER_NAME
     *
     * @param adderName the value for biz_agent_message_validate.ADDER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setAdderName(String adderName) {
        this.adderName = adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.ADD_TIME
     *
     * @return the value of biz_agent_message_validate.ADD_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.ADD_TIME
     *
     * @param addTime the value for biz_agent_message_validate.ADD_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.UPDATER_SN
     *
     * @return the value of biz_agent_message_validate.UPDATER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Long getUpdaterSn() {
        return updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.UPDATER_SN
     *
     * @param updaterSn the value for biz_agent_message_validate.UPDATER_SN
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setUpdaterSn(Long updaterSn) {
        this.updaterSn = updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.UPDATER_NAME
     *
     * @return the value of biz_agent_message_validate.UPDATER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public String getUpdaterName() {
        return updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.UPDATER_NAME
     *
     * @param updaterName the value for biz_agent_message_validate.UPDATER_NAME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_agent_message_validate.UPDATE_TIME
     *
     * @return the value of biz_agent_message_validate.UPDATE_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_agent_message_validate.UPDATE_TIME
     *
     * @param updateTime the value for biz_agent_message_validate.UPDATE_TIME
     *
     * @ibatorgenerated Thu May 19 16:15:28 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}