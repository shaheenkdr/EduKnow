package com.mobloo.eduknow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class StreamPojo {

    @SerializedName("stream_id")
    @Expose
    private Integer streamId;
    @SerializedName("stream_name")
    @Expose
    private String streamName;
    @SerializedName("stream_type")
    @Expose
    private String streamType;
    @SerializedName("stream_content")
    @Expose
    private String streamContent;
    @SerializedName("stream_poster")
    @Expose
    private String streamPoster;
    @SerializedName("stream_url")
    @Expose
    private String streamUrl;

    /**
     *
     * @return
     * The streamId
     */
    public Integer getStreamId() {
        return streamId;
    }

    /**
     *
     * @param streamId
     * The stream_id
     */
    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    /**
     *
     * @return
     * The streamName
     */
    public String getStreamName() {
        return streamName;
    }

    /**
     *
     * @param streamName
     * The stream_name
     */
    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    /**
     *
     * @return
     * The streamType
     */
    public String getStreamType() {
        return streamType;
    }

    /**
     *
     * @param streamType
     * The stream_type
     */
    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    /**
     *
     * @return
     * The streamContent
     */
    public String getStreamContent() {
        return streamContent;
    }

    /**
     *
     * @param streamContent
     * The stream_content
     */
    public void setStreamContent(String streamContent) {
        this.streamContent = streamContent;
    }

    /**
     *
     * @return
     * The streamPoster
     */
    public String getStreamPoster() {
        return streamPoster;
    }

    /**
     *
     * @param streamPoster
     * The stream_poster
     */
    public void setStreamPoster(String streamPoster) {
        this.streamPoster = streamPoster;
    }

    /**
     *
     * @return
     * The streamUrl
     */
    public String getStreamUrl() {
        return streamUrl;
    }

    /**
     *
     * @param streamUrl
     * The stream_url
     */
    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

}