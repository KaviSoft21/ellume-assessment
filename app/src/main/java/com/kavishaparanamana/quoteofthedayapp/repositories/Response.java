package com.kavishaparanamana.quoteofthedayapp.repositories;

public class Response {

    private Success SuccessObject;
    private Contents ContentsObject;
    private String baseurl;
    private Copyright CopyrightObject;


    public Success getSuccess() {
        return SuccessObject;
    }

    public Contents getContents() {
        return ContentsObject;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public Copyright getCopyright() {
        return CopyrightObject;
    }

    public void setSuccess(Success successObject) {
        this.SuccessObject = successObject;
    }

    public void setContents(Contents contentsObject) {
        this.ContentsObject = contentsObject;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public void setCopyright(Copyright copyrightObject) {
        this.CopyrightObject = copyrightObject;
    }
}



