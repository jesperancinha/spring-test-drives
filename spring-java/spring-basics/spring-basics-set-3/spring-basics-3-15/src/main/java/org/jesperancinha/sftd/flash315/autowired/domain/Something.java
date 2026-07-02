package org.jesperancinha.sftd.flash315.autowired.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Component
public class Something {
    private String content = "Something I need";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Something{" +
                "content='" + content + '\'' +
                '}';
    }
}
