package com.mobloo.eduknow;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HashPojo {


    @SerializedName("hash")
    @Expose
    private String hash;

    /**
     *
     * @return
     * The hash
     */
    public String getHash() {
        return hash;
    }

    /**
     *
     * @param hash
     * The hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

}
