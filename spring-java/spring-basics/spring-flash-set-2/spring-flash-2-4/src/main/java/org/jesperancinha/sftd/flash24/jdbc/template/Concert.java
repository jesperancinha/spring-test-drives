package org.jesperancinha.sftd.flash24.jdbc.template;

/**
 * Class representing concerts
 */
public class Concert {
    private long id;
    private String name;
    private String venue;
    private String localDateTime;

    public Concert(long id, String name, String venue, String localDateTime) {
        this.id = id;
        this.name = name;
        this.venue = venue;
        this.localDateTime = localDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", venue='" + venue + '\'' +
                ", localDateTime='" + localDateTime + '\'' +
                '}';
    }
}
