package com.focustech.focus3d.agent.model.ibator;

import com.focustech.cief.ibatis.annotation.Column;
import com.focustech.cief.ibatis.annotation.PrimaryKey;
import com.focustech.cief.ibatis.annotation.SqlMap;
import com.focustech.cief.ibatis.annotation.Xss;
import java.util.Date;

@Xss
@SqlMap(Name = "biz_prod_case", Class = BizProdCase.class)
public class BizProdCase<T, U> {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @PrimaryKey
    @Column
    private Long sn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String name;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.TYPE
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Integer type;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long imageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.INDEX_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long indexImageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.SERVICE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long serviceImageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.CIEF_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long ciefImageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.ABOUT_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long aboutImageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.CASE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long caseImageFileSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.COMPANY_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String companyName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.MAIN_PRODUCT
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String mainProduct;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.URL
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String url;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.PRIORITY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Integer priority;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.VALID_FLAG
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Integer validFlag;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.SUMMARY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String summary;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.ADDER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long adderSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.ADDER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String adderName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.ADD_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Date addTime;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.UPDATER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Long updaterSn;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.UPDATER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private String updaterName;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column biz_prod_case.UPDATE_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    @Column
    private Date updateTime;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.SN
     *
     * @return the value of biz_prod_case.SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getSn() {
        return sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.SN
     *
     * @param sn the value for biz_prod_case.SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setSn(Long sn) {
        this.sn = sn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.NAME
     *
     * @return the value of biz_prod_case.NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.NAME
     *
     * @param name the value for biz_prod_case.NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.TYPE
     *
     * @return the value of biz_prod_case.TYPE
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.TYPE
     *
     * @param type the value for biz_prod_case.TYPE
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getImageFileSn() {
        return imageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.IMAGE_FILE_SN
     *
     * @param imageFileSn the value for biz_prod_case.IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setImageFileSn(Long imageFileSn) {
        this.imageFileSn = imageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.INDEX_IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.INDEX_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getIndexImageFileSn() {
        return indexImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.INDEX_IMAGE_FILE_SN
     *
     * @param indexImageFileSn the value for biz_prod_case.INDEX_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setIndexImageFileSn(Long indexImageFileSn) {
        this.indexImageFileSn = indexImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.SERVICE_IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.SERVICE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getServiceImageFileSn() {
        return serviceImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.SERVICE_IMAGE_FILE_SN
     *
     * @param serviceImageFileSn the value for biz_prod_case.SERVICE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setServiceImageFileSn(Long serviceImageFileSn) {
        this.serviceImageFileSn = serviceImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.CIEF_IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.CIEF_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getCiefImageFileSn() {
        return ciefImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.CIEF_IMAGE_FILE_SN
     *
     * @param ciefImageFileSn the value for biz_prod_case.CIEF_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setCiefImageFileSn(Long ciefImageFileSn) {
        this.ciefImageFileSn = ciefImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.ABOUT_IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.ABOUT_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getAboutImageFileSn() {
        return aboutImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.ABOUT_IMAGE_FILE_SN
     *
     * @param aboutImageFileSn the value for biz_prod_case.ABOUT_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setAboutImageFileSn(Long aboutImageFileSn) {
        this.aboutImageFileSn = aboutImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.CASE_IMAGE_FILE_SN
     *
     * @return the value of biz_prod_case.CASE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getCaseImageFileSn() {
        return caseImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.CASE_IMAGE_FILE_SN
     *
     * @param caseImageFileSn the value for biz_prod_case.CASE_IMAGE_FILE_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setCaseImageFileSn(Long caseImageFileSn) {
        this.caseImageFileSn = caseImageFileSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.COMPANY_NAME
     *
     * @return the value of biz_prod_case.COMPANY_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.COMPANY_NAME
     *
     * @param companyName the value for biz_prod_case.COMPANY_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.MAIN_PRODUCT
     *
     * @return the value of biz_prod_case.MAIN_PRODUCT
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getMainProduct() {
        return mainProduct;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.MAIN_PRODUCT
     *
     * @param mainProduct the value for biz_prod_case.MAIN_PRODUCT
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.URL
     *
     * @return the value of biz_prod_case.URL
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.URL
     *
     * @param url the value for biz_prod_case.URL
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.PRIORITY
     *
     * @return the value of biz_prod_case.PRIORITY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.PRIORITY
     *
     * @param priority the value for biz_prod_case.PRIORITY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.VALID_FLAG
     *
     * @return the value of biz_prod_case.VALID_FLAG
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.VALID_FLAG
     *
     * @param validFlag the value for biz_prod_case.VALID_FLAG
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.SUMMARY
     *
     * @return the value of biz_prod_case.SUMMARY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.SUMMARY
     *
     * @param summary the value for biz_prod_case.SUMMARY
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.ADDER_SN
     *
     * @return the value of biz_prod_case.ADDER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getAdderSn() {
        return adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.ADDER_SN
     *
     * @param adderSn the value for biz_prod_case.ADDER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setAdderSn(Long adderSn) {
        this.adderSn = adderSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.ADDER_NAME
     *
     * @return the value of biz_prod_case.ADDER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getAdderName() {
        return adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.ADDER_NAME
     *
     * @param adderName the value for biz_prod_case.ADDER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setAdderName(String adderName) {
        this.adderName = adderName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.ADD_TIME
     *
     * @return the value of biz_prod_case.ADD_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.ADD_TIME
     *
     * @param addTime the value for biz_prod_case.ADD_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.UPDATER_SN
     *
     * @return the value of biz_prod_case.UPDATER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Long getUpdaterSn() {
        return updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.UPDATER_SN
     *
     * @param updaterSn the value for biz_prod_case.UPDATER_SN
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setUpdaterSn(Long updaterSn) {
        this.updaterSn = updaterSn;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.UPDATER_NAME
     *
     * @return the value of biz_prod_case.UPDATER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public String getUpdaterName() {
        return updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.UPDATER_NAME
     *
     * @param updaterName the value for biz_prod_case.UPDATER_NAME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column biz_prod_case.UPDATE_TIME
     *
     * @return the value of biz_prod_case.UPDATE_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column biz_prod_case.UPDATE_TIME
     *
     * @param updateTime the value for biz_prod_case.UPDATE_TIME
     *
     * @ibatorgenerated Mon May 16 17:58:01 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}