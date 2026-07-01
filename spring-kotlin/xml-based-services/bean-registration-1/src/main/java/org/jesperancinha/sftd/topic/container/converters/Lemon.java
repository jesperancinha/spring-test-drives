package org.jesperancinha.sftd.topic.container.converters;

public class Lemon extends Citrus {

    private int size;

    public Lemon(final int size) {
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
        return "Lemon{" +
                "size=" + size +
                '}';
    }
}
