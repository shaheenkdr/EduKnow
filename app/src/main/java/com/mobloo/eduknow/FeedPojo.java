package com.mobloo.eduknow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class FeedPojo
{
    @SerializedName("feed_id")
    @Expose
    private Integer feedId;
    @SerializedName("re_engage")
    @Expose
    private Object reEngage;
    @SerializedName("feed_title")
    @Expose
    private String feedTitle;
    @SerializedName("feed_content")
    @Expose
    private String feedContent;
    @SerializedName("feed_content_url")
    @Expose
    private String feedContentUrl;
    @SerializedName("feed_content_type")
    @Expose
    private String feedContentType;
    @SerializedName("feed_poster")
    @Expose
    private Object feedPoster;
    @SerializedName("is_general")
    @Expose
    private Integer isGeneral;
    @SerializedName("student_id")
    @Expose
    private Integer studentId;
    @SerializedName("is_seen")
    @Expose
    private Integer isSeen;
    @SerializedName("school_id")
    @Expose
    private Integer schoolId;
    @SerializedName("feed_time")
    @Expose
    private String feedTime;
    @SerializedName("feed_created")
    @Expose
    private String feedCreated;
    @SerializedName("is_pushed")
    @Expose
    private Integer isPushed;
    @SerializedName("batch_id")
    @Expose
    private Integer batchId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("student_code")
    @Expose
    private List<Integer> studentCode = new ArrayList<Integer>();
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
     * The feedId
     */
    public Integer getFeedId() {
        return feedId;
    }

    /**
     *
     * @param feedId
     * The feed_id
     */
    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    /**
     *
     * @return
     * The reEngage
     */
    public Object getReEngage() {
        return reEngage;
    }

    /**
     *
     * @param reEngage
     * The re_engage
     */
    public void setReEngage(Object reEngage) {
        this.reEngage = reEngage;
    }

    /**
     *
     * @return
     * The feedTitle
     */
    public String getFeedTitle() {
        return feedTitle;
    }

    /**
     *
     * @param feedTitle
     * The feed_title
     */
    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    /**
     *
     * @return
     * The feedContent
     */
    public String getFeedContent() {
        return feedContent;
    }

    /**
     *
     * @param feedContent
     * The feed_content
     */
    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    /**
     *
     * @return
     * The feedContentUrl
     */
    public String getFeedContentUrl() {
        return feedContentUrl;
    }

    /**
     *
     * @param feedContentUrl
     * The feed_content_url
     */
    public void setFeedContentUrl(String feedContentUrl) {
        this.feedContentUrl = feedContentUrl;
    }

    /**
     *
     * @return
     * The feedContentType
     */
    public String getFeedContentType() {
        return feedContentType;
    }

    /**
     *
     * @param feedContentType
     * The feed_content_type
     */
    public void setFeedContentType(String feedContentType) {
        this.feedContentType = feedContentType;
    }

    /**
     *
     * @return
     * The feedPoster
     */
    public Object getFeedPoster() {
        return feedPoster;
    }

    /**
     *
     * @param feedPoster
     * The feed_poster
     */
    public void setFeedPoster(Object feedPoster) {
        this.feedPoster = feedPoster;
    }

    /**
     *
     * @return
     * The isGeneral
     */
    public Integer getIsGeneral() {
        return isGeneral;
    }

    /**
     *
     * @param isGeneral
     * The is_general
     */
    public void setIsGeneral(Integer isGeneral) {
        this.isGeneral = isGeneral;
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
     * The isSeen
     */
    public Integer getIsSeen() {
        return isSeen;
    }

    /**
     *
     * @param isSeen
     * The is_seen
     */
    public void setIsSeen(Integer isSeen) {
        this.isSeen = isSeen;
    }

    /**
     *
     * @return
     * The schoolId
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     *
     * @param schoolId
     * The school_id
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     *
     * @return
     * The feedTime
     */
    public String getFeedTime() {
        return feedTime;
    }

    /**
     *
     * @param feedTime
     * The feed_time
     */
    public void setFeedTime(String feedTime) {
        this.feedTime = feedTime;
    }

    /**
     *
     * @return
     * The feedCreated
     */
    public String getFeedCreated() {
        return feedCreated;
    }

    /**
     *
     * @param feedCreated
     * The feed_created
     */
    public void setFeedCreated(String feedCreated) {
        this.feedCreated = feedCreated;
    }

    /**
     *
     * @return
     * The isPushed
     */
    public Integer getIsPushed() {
        return isPushed;
    }

    /**
     *
     * @param isPushed
     * The is_pushed
     */
    public void setIsPushed(Integer isPushed) {
        this.isPushed = isPushed;
    }

    /**
     *
     * @return
     * The batchId
     */
    public Integer getBatchId() {
        return batchId;
    }

    /**
     *
     * @param batchId
     * The batch_id
     */
    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
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
     * The studentCode
     */
    public List<Integer> getStudentCode() {
        return studentCode;
    }

    /**
     *
     * @param studentCode
     * The student_code
     */
    public void setStudentCode(List<Integer> studentCode) {
        this.studentCode = studentCode;
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
