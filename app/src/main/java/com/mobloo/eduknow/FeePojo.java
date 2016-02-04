package com.mobloo.eduknow;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeePojo
{
    @SerializedName("fee_id")
    @Expose
    private Integer feeId;
    @SerializedName("fee_name")
    @Expose
    private String feeName;
    @SerializedName("fee_amount")
    @Expose
    private Integer feeAmount;
    @SerializedName("payment_enabled")
    @Expose
    private Integer paymentEnabled;
    @SerializedName("is_paid")
    @Expose
    private Integer isPaid;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("fee_currency")
    @Expose
    private String feeCurrency;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("recipt_id")
    @Expose
    private Integer reciptId;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("sub_name")
    @Expose
    private String subName;
    @SerializedName("school_logo")
    @Expose
    private String schoolLogo;

    /**
     *
     * @return
     * The feeId
     */
    public Integer getFeeId() {
        return feeId;
    }

    /**
     *
     * @param feeId
     * The fee_id
     */
    public void setFeeId(Integer feeId) {
        this.feeId = feeId;
    }

    /**
     *
     * @return
     * The feeName
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     *
     * @param feeName
     * The fee_name
     */
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }

    /**
     *
     * @return
     * The feeAmount
     */
    public Integer getFeeAmount() {
        return feeAmount;
    }

    /**
     *
     * @param feeAmount
     * The fee_amount
     */
    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    /**
     *
     * @return
     * The paymentEnabled
     */
    public Integer getPaymentEnabled() {
        return paymentEnabled;
    }

    /**
     *
     * @param paymentEnabled
     * The payment_enabled
     */
    public void setPaymentEnabled(Integer paymentEnabled) {
        this.paymentEnabled = paymentEnabled;
    }

    /**
     *
     * @return
     * The isPaid
     */
    public Integer getIsPaid() {
        return isPaid;
    }

    /**
     *
     * @param isPaid
     * The is_paid
     */
    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    /**
     *
     * @return
     * The studentId
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     *
     * @param studentId
     * The student_id
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     *
     * @return
     * The feeCurrency
     */
    public String getFeeCurrency() {
        return feeCurrency;
    }

    /**
     *
     * @param feeCurrency
     * The fee_currency
     */
    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    /**
     *
     * @return
     * The studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     *
     * @param studentName
     * The student_name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     *
     * @return
     * The reciptId
     */
    public Integer getReciptId() {
        return reciptId;
    }

    /**
     *
     * @param reciptId
     * The recipt_id
     */
    public void setReciptId(Integer reciptId) {
        this.reciptId = reciptId;
    }

    /**
     *
     * @return
     * The schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     *
     * @param schoolName
     * The school_name
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     *
     * @return
     * The subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     *
     * @param subName
     * The sub_name
     */
    public void setSubName(String subName) {
        this.subName = subName;
    }

    /**
     *
     * @return
     * The schoolLogo
     */
    public String getSchoolLogo() {
        return schoolLogo;
    }

    /**
     *
     * @param schoolLogo
     * The school_logo
     */
    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }


}
