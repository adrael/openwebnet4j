package org.openwebnet4j.message;

import org.openwebnet4j.message.SoundSystem.SpeakerOrSourceSoundSystem;

public class SoundSystemDevice {
    private final SpeakerOrSourceSoundSystem type;
    private final String area;
    private final String sourceId;

    public SoundSystemDevice(SpeakerOrSourceSoundSystem type, String area, String sourceId) {
        this.area = area;
        this.sourceId = sourceId;
        this.type = type;
    }

    public SpeakerOrSourceSoundSystem getType() {
        return type;
    }

    public String getArea() {
        return area;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String toString() {
        return type.value() + "#" + area + "#" + sourceId;
    }
}
