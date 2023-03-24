/**
 * Copyright (c) 2020-2023 Contributors to the openwebnet4j project
 * <p>
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 * <p>
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * <p>
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openwebnet4j.message;

import org.openwebnet4j.OpenDeviceType;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.openwebnet4j.message.Who.SOUND_SYSTEM_2;

/**
 * OpenWebNet Sound System messages (WHO=22)
 *
 * @author R. Marques - Initial contribution
 */
public class SoundSystem extends BaseOpenMessage {

    public enum WhatSoundSystem implements What {
        TURN_OFF(0),
        TURN_ON(1),
        SOURCE_TURNED_ON(2),
        INCREASE_VOLUME(3),
        DECREASE_VOLUME(4),
        AUTOMATIC_TUNER_SEARCH_TOWARDS_HIGHER_FREQUENCIES(5),
        MANUAL_TUNER_SEARCH_TOWARDS_LOWER_FREQUENCIES(6),
        GO_TO_A_FOLLOWING_STATION(9),
        GO_TO_A_PREVIOUS_STATION(10),
        GO_TO_A_FOLLOWING_TRACK(11),
        GO_TO_A_PREVIOUS_TRACK(12),
        SLIDING_REQUEST(22),
        ASK_A_SOURCE_TO_START_TELLING_RDS_MESSAGE(31),
        ASK_A_SOURCE_TO_STOP_TELLING_RDS_MESSAGE(32),
        STORE_THE_PRESENTLY_TUNED_FREQUENCY_ON_A_CERTAIN_STATION(33),
        TURN_ON_AMPLIFIER_WITH_FOLLOW_ME_METHOD(34),
        TURN_ON_AMPLIFIER_TO_A_CERTAIN_SOURCE(35),
        INCREMENT_LOW_TONES(36),
        DECREMENT_LOW_TONES(37),
        INCREMENT_MID_TONES(38),
        DECREMENT_MID_TONES(39),
        INCREMENT_HIGH_TONES(40),
        DECREMENT_HIGH_TONES(41),
        INCREMENT_BALANCE_LEFT_TO_RIGHT(42),
        DECREMENT_BALANCE_RIGHT_TO_LEFT(43),
        NEXT_PRESET(55),
        PREVIOUS_PRESET(56);

        private static Map<Integer, WhatSoundSystem> mapping;

        private final int value;

        private WhatSoundSystem(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, WhatSoundSystem>();
            for (WhatSoundSystem w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static WhatSoundSystem fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        @Override
        public Integer value() {
            return value;
        }
    }

    @Override
    protected What whatFromValue(int i) {
        return WhatSoundSystem.fromValue(i);
    }

    public enum FrequencyStepSoundSystem {
        FIFTY_HZ(1),
        ONE_HUNDRED_HZ(2),
        ONE_HUNDRED_FIFTY_HZ(3),
        TWO_HUNDRED_HZ(4),
        TWO_HUNDRED_FIFTY_HZ(5),
        THREE_HUNDRED_HZ(6),
        THREE_HUNDRED_FIFTY_HZ(7),
        FOUR_HUNDRED_HZ(8),
        FOUR_HUNDRED_FIFTY_HZ(9),
        FIVE_HUNDRED_HZ(10),
        FIVE_HUNDRED_FIFTY_HZ(11),
        SIX_HUNDRED_HZ(12),
        SIX_HUNDRED_FIFTY_HZ(13),
        SEVEN_HUNDRED_HZ(14),
        SEVEN_HUNDRED_FIFTY_HZ(15);

        private static Map<Integer, FrequencyStepSoundSystem> mapping;

        private final int value;

        private FrequencyStepSoundSystem(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, FrequencyStepSoundSystem>();
            for (FrequencyStepSoundSystem w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static FrequencyStepSoundSystem fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        public Integer value() {
            return value;
        }
    }

    protected FrequencyStepSoundSystem frequencyStepSoundSystemFromValue(int i) {
        return FrequencyStepSoundSystem.fromValue(i);
    }

    public enum MultimediaTypeSoundSystem {
        VOICE(1),
        CANAL_RIGHT(2),
        CANAL_LEFT(3),
        CANAL_STEREO(4),
        UNKNOWN(10),
        ALL_SOURCE(11);

        private static Map<Integer, MultimediaTypeSoundSystem> mapping;

        private final int value;

        private MultimediaTypeSoundSystem(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, MultimediaTypeSoundSystem>();
            for (MultimediaTypeSoundSystem w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static MultimediaTypeSoundSystem fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        public Integer value() {
            return value;
        }
    }

    protected MultimediaTypeSoundSystem multimediaTypeFromValue(int i) {
        return MultimediaTypeSoundSystem.fromValue(i);
    }
    public enum ModulationSoundSystem {
        FM(1),
        AM_LW(2),
        AM_MW(3),
        AM_SW(4);

        private static Map<Integer, ModulationSoundSystem> mapping;

        private final int value;

        private ModulationSoundSystem(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, ModulationSoundSystem>();
            for (ModulationSoundSystem w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static ModulationSoundSystem fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        public Integer value() {
            return value;
        }
    }

    protected ModulationSoundSystem modulationFromValue(int i) {
        return ModulationSoundSystem.fromValue(i);
    }

    public enum DimSoundSystem implements Dim {
        VOLUME(1),
        HIGH_TONES(2),
        MEDIUM_TONES(3),
        LOW_TONES(4),
        FREQUENCY(5),
        TRACK_STATION(6),
        PLAY_STATUS(7),
        FREQUENCY_AND_STATION(11),
        DEVICE_STATE(12),
        BALANCE(17),
        THREE_D(18),
        PRESET(19),
        LOUDNESS(20);

        private static Map<Integer, DimSoundSystem> mapping;

        private final int value;

        private DimSoundSystem(Integer value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, DimSoundSystem>();
            for (DimSoundSystem d : values()) {
                mapping.put(d.value, d);
            }
        }

        public static DimSoundSystem fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        @Override
        public Integer value() {
            return value;
        }
    }

    @Override
    protected Dim dimFromValue(int i) {
        return DimSoundSystem.fromValue(i);
    }

    private static final int WHO = SOUND_SYSTEM_2.value();

    protected SoundSystem(String value) {
        super(value);
        this.who = SOUND_SYSTEM_2;
    }

    // TODO: remove - for debug only
    public static SoundSystem rawRequest(String request) {
        return new SoundSystem(request);
    }

    public static SoundSystem requestTurnOn(SoundSystemDevice device, MultimediaTypeSoundSystem mmType) {
        return new SoundSystem(format("*%d*%d#%d#%s*%s##", WHO, WhatSoundSystem.TURN_ON.value, mmType.value, device.getArea(), device));
    }

    public static SoundSystem requestTurnOff(SoundSystemDevice device, MultimediaTypeSoundSystem mmType) {
        return new SoundSystem(format("*%d*%d#%d#%s*%s##", WHO, WhatSoundSystem.TURN_OFF.value, mmType.value, device.getArea(), device));
    }

    public static SoundSystem requestFrequencyUpAutomaticSearch(SoundSystemDevice device) {
        return requestFrequencyUpAutomaticSearch(device.getSourceId());
    }

    public static SoundSystem requestFrequencyUpAutomaticSearch(String sourceId) {
        return new SoundSystem(format("*%d*5*2#%s##", WHO, sourceId));
    }

    public static SoundSystem requestFrequencyDownAutomaticSearch(SoundSystemDevice device) {
        return requestFrequencyDownAutomaticSearch(device.getSourceId());
    }

    public static SoundSystem requestFrequencyDownAutomaticSearch(String sourceId) {
        return new SoundSystem(format("*%d*6*2#%s##", WHO, sourceId));
    }

    public static SoundSystem requestFrequencyUp(SoundSystemDevice device, FrequencyStepSoundSystem freqStep) {
        return requestFrequencyUp(device.getSourceId(), freqStep);
    }

    public static SoundSystem requestFrequencyUp(String sourceId, FrequencyStepSoundSystem freqStep) {
        return new SoundSystem(format("*%d*5#%d*2#%s##", WHO, freqStep.value(), sourceId));
    }

    public static SoundSystem requestFrequencyDown(SoundSystemDevice device, FrequencyStepSoundSystem freqStep) {
        return requestFrequencyDown(device.getSourceId(), freqStep);
    }

    public static SoundSystem requestFrequencyDown(String sourceId, FrequencyStepSoundSystem freqStep) {
        return new SoundSystem(format("*%d*6#%d*2#%s##", WHO, freqStep.value(), sourceId));
    }

    public static SoundSystem requestNextStation(SoundSystemDevice device) {
        return requestNextStation(device.getSourceId());
    }

    public static SoundSystem requestNextStation(String sourceId) {
        return new SoundSystem(format("*%d*%d*2#%s##", WHO, WhatSoundSystem.GO_TO_A_FOLLOWING_STATION.value(), sourceId));
    }

    public static SoundSystem requestPreviousStation(SoundSystemDevice device) {
        return requestPreviousStation(device.getSourceId());
    }

    public static SoundSystem requestPreviousStation(String sourceId) {
        return new SoundSystem(format("*%d*%d*2#%s##", WHO, WhatSoundSystem.GO_TO_A_PREVIOUS_STATION.value(), sourceId));
    }

    public static SoundSystem requestVolumeIncrease(SoundSystemDevice device, int step) {
        return new SoundSystem(format(FORMAT_REQUEST_PARAM_STR, WHO, WhatSoundSystem.INCREASE_VOLUME.value(), step, device));
    }

    public static SoundSystem requestVolumeDecrease(SoundSystemDevice device, int step) {
        return new SoundSystem(format(FORMAT_REQUEST_PARAM_STR, WHO, WhatSoundSystem.DECREASE_VOLUME.value(), step, device));
    }

    public static SoundSystem requestVolume(SoundSystemDevice device) {
        return new SoundSystem(format(FORMAT_DIMENSION_REQUEST, WHO, device, DimSoundSystem.VOLUME.value()));
    }

    public static SoundSystem requestFrequency(SoundSystemDevice device) {
        return new SoundSystem(format("*#%d*%s#%s#%s*%s##", WHO, WhereSoundSystem.GENERAL.value(), WhereSoundSystem.SOURCE.value(), device.getSourceId(), DimSoundSystem.FREQUENCY.value()));
    }

    public static SoundSystem requestStationOrTrack(SoundSystemDevice device) {
        return new SoundSystem(format("*#%d*%s#%s#%s*%s##", WHO, WhereSoundSystem.GENERAL.value(), WhereSoundSystem.SOURCE.value(), device.getSourceId(), DimSoundSystem.TRACK_STATION.value()));
    }

    public static SoundSystem requestStatus(String where) {
        return new SoundSystem(format(FORMAT_STATUS, WHO, where));
    }

    public MultimediaTypeSoundSystem getMultimediaType() throws FrameException {
        String[] valuesOrParams;

        if (getWhat() == null) {
            valuesOrParams = getDimValues();
        } else {
            valuesOrParams = getWhatParams();
        }

        if (valuesOrParams.length > 1) {
            return multimediaTypeFromValue(Integer.parseInt(valuesOrParams[1]));
        }

        return null;
    }

    public boolean isSpeaker() {
        return whereStr.startsWith(WhereSoundSystem.SPEAKER.value());
    }

    public boolean isSource() {
        return whereStr.startsWith(WhereSoundSystem.SOURCE.value());
    }

    public boolean isOn() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 0) {
            return Integer.parseInt(values[0]) == WhatSoundSystem.TURN_ON.value;
        }

        return false;
    }

    public boolean isOff() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 0) {
            return Integer.parseInt(values[0]) == WhatSoundSystem.TURN_OFF.value;
        }

        return false;

    }

    public String getVolume() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 0) {
            return values[0];
        }

        return null;
    }

    public String getVolumeStep() throws FrameException {
        String[] params = getWhatParams();

        if (params.length > 0) {
            return params[0];
        }

        return null;
    }

    public String getArea() throws FrameException {
        String[] values = getWhatParams();

        if (values.length > 1) {
            return values[1];
        }

        return null;
    }

    public ModulationSoundSystem getModulation() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 0) {
            return modulationFromValue(Integer.parseInt(values[0]));
        }

        return null;
    }

    public float getFrequency() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 1) {
            return Float.parseFloat(values[1]) / 100;
        }

        return -1;
    }

    public String getStationOrTrack() throws FrameException {
        String[] values = getDimValues();

        if (values.length > 2) {
            return values[2];
        }

        return null;
    }

    public SoundSystemDevice getDevice() {
        return SoundSystemDevice.fromWhere(getWhere());
    }

    @Override
    protected void parseWhere() throws FrameException {
        if (whereStr == null) {
            throw new FrameException("Sound System frame has no WHERE part: " + whereStr);
        } else {
            if (whereStr.endsWith(WhereZigBee.ZB_NETWORK)) {
                where = new WhereZigBee(whereStr);
            } else {
                where = new WhereSoundSystem(whereStr);
            }
        }
    }

    @Override
    public OpenDeviceType detectDeviceType() {
        if (isCommand()) { // ignore status/dimension frames for detecting device type
            return OpenDeviceType.UNKNOWN;
        } else {
            return null;
        }
    }
}
