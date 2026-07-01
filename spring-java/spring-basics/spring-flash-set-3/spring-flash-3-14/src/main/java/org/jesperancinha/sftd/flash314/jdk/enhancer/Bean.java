package org.jesperancinha.sftd.flash314.jdk.enhancer;

import lombok.Data;

@Data
public class Bean implements IBean {
    private String message;

    public String beanState() {
        return "I'm just a bean seed. " + makeBeanSoup();
    }

    private String makeBeanSoup() {
        return "The soup is made";
    }

    public String makeProtectedBeanSoup() {
        return "The soup is made";
    }

    public String makeBeanSoupPirate() {
        return makeBeanSoup();
    }

    @Override
    public String toString() {
        return "Bean{" +
                "message='" + message + '\'' +
                '}';
    }
}
