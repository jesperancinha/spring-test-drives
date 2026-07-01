package org.jesperancinha.sftd.topic.container.converters;

import java.beans.PropertyEditorSupport;

public class CitrusEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        String[] splits = text.split("-");
        switch (splits[0]) {
            case "lemon":
                super.setValue(new Lemon(Integer.parseInt(splits[1])));
                break;
            case "lime":
                super.setValue(new Lime(Integer.parseInt(splits[1])));
                break;
            default:
                super.setValue(null);
        }

    }
}
