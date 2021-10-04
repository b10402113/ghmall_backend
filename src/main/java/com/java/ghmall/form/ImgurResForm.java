package com.java.ghmall.form;




public class ImgurResForm {
    private Data data;

    private String success;

    private String status;
    public ImgurResForm()
    {

    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Data {

        private String link;

        public Data() {}
        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

    }
}