package org.openwebnet4j.message;

import static java.lang.String.format;
import static org.openwebnet4j.message.WhereSoundSystem.*;

public class SoundSystemDevice {
    private final WhereSoundSystem where;
    private final String area;
    private final String sourceId;

    public SoundSystemDevice(WhereSoundSystem where, String area, String sourceId) {
        this.area = area;
        this.where = where;
        this.sourceId = sourceId;
    }

    public boolean isSpeaker() {
        return where == SPEAKER;
    }

    public boolean isSource() {
        return where == SOURCE;
    }

    public WhereSoundSystem getWhere() {
        return where;
    }

    public String getArea() {
        return area;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String toString() {
        switch (where.whereStr) {
            // See WhereSoundSystem
            case "2":
                return format(FORMAT_SOURCE, sourceId);
            case "3":
                return format(FORMAT_SPEAKER, area, sourceId);
            case "4":
                return format(FORMAT_SPEAKER_AREA, area);
            case "5":
                return format(FORMAT_GENERAL, format("%s#%s", where.value(), sourceId));
            case "6":
                return ALL_SOURCE.value();
            default:
                throw new IllegalStateException("Unexpected value: " + where);
        }
    }

    public static SoundSystemDevice fromWhere(Where where) {
        String[] parts = where.value().split("#");

        if (parts.length == 2 && parts[0].equals(SOURCE.value())) {
            return new SoundSystemDevice(SOURCE, null, parts[1]);
        }

        if (parts.length == 3) {
            if (parts[0].equals(SPEAKER.value())) {
                return new SoundSystemDevice(SPEAKER, parts[1], parts[2]);
            }

            if (parts[0].equals(GENERAL.value())) {
                return new SoundSystemDevice(GENERAL, parts[1], parts[2]);
            }

            return null;
        }

        throw new IllegalArgumentException("Invalid where for SoundSystemDevice: " + where);
    }
}
