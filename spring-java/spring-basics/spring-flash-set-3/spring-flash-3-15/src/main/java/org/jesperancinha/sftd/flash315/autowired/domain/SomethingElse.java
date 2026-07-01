package org.jesperancinha.sftd.flash315.autowired.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"test && prod"})
@Component
public class SomethingElse {
    private String content = "In this world full of people";

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SomethingElse{" +
                "content='" + content + '\'' +
                '}';
    }
}
