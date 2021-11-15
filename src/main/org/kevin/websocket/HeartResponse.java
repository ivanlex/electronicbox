package org.kevin.websocket;

public class HeartResponse {
    String mContent;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public HeartResponse(String content) {
        mContent = content;
    }
}
