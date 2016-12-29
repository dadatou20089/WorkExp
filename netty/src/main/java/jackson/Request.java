package jackson;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private String url;
    private String content;
    private List<Header> headers = new ArrayList<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    public void addHeader(Header header) {
        this.headers.add(header);
    }
}
