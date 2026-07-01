package org.jesperancinha.sftd.topic.container.converters;

public class Lime extends Citrus {

    private int size;

    public Lime(final int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Lime{" +
                "size=" + size +
                '}';
    }
}
