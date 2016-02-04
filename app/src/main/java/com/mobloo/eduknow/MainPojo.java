package com.mobloo.eduknow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MainPojo {

    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("student_admno")
    @Expose
    private String studentAdmno;
    @SerializedName("school_id")
    @Expose
    private String schoolId;
    @SerializedName("student_cat")
    @Expose
    private String studentCat;
    @SerializedName("student_batch")
    @Expose
    private String studentBatch;
    @SerializedName("student_classno")
    @Expose
    private Integer studentClassno;
    @SerializedName("gurdian1_name")
    @Expose
    private String gurdian1Name;
    @SerializedName("guardian1_no")
    @Expose
    private String guardian1No;
    @SerializedName("guardian1_lang")
    @Expose
    private String guardian1Lang;
    @SerializedName("guardian2_name")
    @Expose
    private String guardian2Name;
    @SerializedName("guardian2_no")
    @Expose
    private String guardian2No;
    @SerializedName("guardian2_lang")
    @Expose
    private String guardian2Lang;
    @SerializedName("student_address")
    @Expose
    private String studentAddress;
    @SerializedName("is_sms_enabled")
    @Expose
    private Integer isSmsEnabled;
    @SerializedName("account_id")
    @Expose
    private Integer accountId;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("is_deleted")
    @Expose
    private Integer isDeleted;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("school_logo")
    @Expose
    private String schoolLogo;
    @SerializedName("sub_name")
    @Expose
    private String subName;

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
     * The studentAdmno
     */
    public String getStudentAdmno() {
        return studentAdmno;
    }

    /**
     *
     * @param studentAdmno
     * The student_admno
     */
    public void setStudentAdmno(String studentAdmno) {
        this.studentAdmno = studentAdmno;
    }

    /**
     *
     * @return
     * The schoolId
     */
    public String getSchoolId() {
        return schoolId;
    }

    /**
     *
     * @param schoolId
     * The school_id
     */
    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    /**
     *
     * @return
     * The studentCat
     */
    public String getStudentCat() {
        return studentCat;
    }

    /**
     *
     * @param studentCat
     * The student_cat
     */
    public void setStudentCat(String studentCat) {
        this.studentCat = studentCat;
    }

    /**
     *
     * @return
     * The studentBatch
     */
    public String getStudentBatch() {
        return studentBatch;
    }

    /**
     *
     * @param studentBatch
     * The student_batch
     */
    public void setStudentBatch(String studentBatch) {
        this.studentBatch = studentBatch;
    }

    /**
     *
     * @return
     * The studentClassno
     */
    public Integer getStudentClassno() {
        return studentClassno;
    }

    /**
     *
     * @param studentClassno
     * The student_classno
     */
    public void setStudentClassno(Integer studentClassno) {
        this.studentClassno = studentClassno;
    }

    /**
     *
     * @return
     * The gurdian1Name
     */
    public String getGurdian1Name() {
        return gurdian1Name;
    }

    /**
     *
     * @param gurdian1Name
     * The gurdian1_name
     */
    public void setGurdian1Name(String gurdian1Name) {
        this.gurdian1Name = gurdian1Name;
    }

    /**
     *
     * @return
     * The guardian1No
     */
    public String getGuardian1No() {
        return guardian1No;
    }

    /**
     *
     * @param guardian1No
     * The guardian1_no
     */
    public void setGuardian1No(String guardian1No) {
        this.guardian1No = guardian1No;
    }

    /**
     *
     * @return
     * The guardian1Lang
     */
    public String getGuardian1Lang() {
        return guardian1Lang;
    }

    /**
     *
     * @param guardian1Lang
     * The guardian1_lang
     */
    public void setGuardian1Lang(String guardian1Lang) {
        this.guardian1Lang = guardian1Lang;
    }

    /**
     *
     * @return
     * The guardian2Name
     */
    public String getGuardian2Name() {
        return guardian2Name;
    }

    /**
     *
     * @param guardian2Name
     * The guardian2_name
     */
    public void setGuardian2Name(String guardian2Name) {
        this.guardian2Name = guardian2Name;
    }

    /**
     *
     * @return
     * The guardian2No
     */
    public String getGuardian2No() {
        return guardian2No;
    }

    /**
     *
     * @param guardian2No
     * The guardian2_no
     */
    public void setGuardian2No(String guardian2No) {
        this.guardian2No = guardian2No;
    }

    /**
     *
     * @return
     * The guardian2Lang
     */
    public String getGuardian2Lang() {
        return guardian2Lang;
    }

    /**
     *
     * @param guardian2Lang
     * The guardian2_lang
     */
    public void setGuardian2Lang(String guardian2Lang) {
        this.guardian2Lang = guardian2Lang;
    }

    /**
     *
     * @return
     * The studentAddress
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     *
     * @param studentAddress
     * The student_address
     */
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    /**
     *
     * @return
     * The isSmsEnabled
     */
    public Integer getIsSmsEnabled() {
        return isSmsEnabled;
    }

    /**
     *
     * @param isSmsEnabled
     * The is_sms_enabled
     */
    public void setIsSmsEnabled(Integer isSmsEnabled) {
        this.isSmsEnabled = isSmsEnabled;
    }

    /**
     *
     * @return
     * The accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     *
     * @param accountId
     * The account_id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     *
     * @return
     * The isActive
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     *
     * @param isActive
     * The is_active
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     *
     * @return
     * The isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     *
     * @param isDeleted
     * The is_deleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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

}