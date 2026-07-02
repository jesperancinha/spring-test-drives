package org.jesperancinha.sftd.flash39.cglib.enhancer;

public class Bean {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String beanState() {
        return "I'm just a bean seed. " + makeBeanSoup();
    }

    private String makeBeanSoup() {
        return "The soup is made";
    }

    protected String makeProtectedBeanSoup() {
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
