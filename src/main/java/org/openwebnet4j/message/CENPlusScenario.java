/**
 * Copyright (c) 2021 Contributors to the openwebnet4j project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 */
package org.openwebnet4j.message;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import org.openwebnet4j.OpenDeviceType;

/**
 * OpenWebNet class for CEN+ and DRY CONTACT IR
 *
 * @author M. Valla - Initial contribution
 */
public class CENPlusScenario extends CEN {
    // private static final Logger logger = LoggerFactory.getLogger(CENPlusScenario.class);

    public enum WhatCENPlus implements What {
        SHORT_PRESSURE(21),
        START_EXT_PRESSURE(22),
        EXT_PRESSURE(23),
        RELEASE_EXT_PRESSURE(24),
        // dry contact & IR sensors
        ON_IR_DETECTION(31),
        OFF_IR_NO_DETECTION(32);

        private static Map<Integer, WhatCENPlus> mapping;

        private final int value;

        private WhatCENPlus(Integer value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, WhatCENPlus>();
            for (WhatCENPlus w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static WhatCENPlus fromValue(int i) {
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

    public enum CENPlusPressure {
        SHORT_PRESSURE(21),
        START_EXT_PRESSURE(22),
        EXT_PRESSURE(23),
        RELEASE_EXT_PRESSURE(24);

        private static Map<Integer, CENPlusPressure> mapping;

        private final int value;

        private CENPlusPressure(Integer value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, CENPlusPressure>();
            for (CENPlusPressure w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static CENPlusPressure fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }
    }

    @Override
    protected Dim dimFromValue(int i) {
        // no Dims for this WHO
        return null;
    }

    private static final int WHO = Who.CEN_PLUS_SCENARIO_SCHEDULER.value();

    protected CENPlusScenario(String value) {
        super(value);
    }

    @Override
    protected What whatFromValue(int i) {
        return WhatCENPlus.fromValue(i);
    }

    /**
     * OpenWebNet message to request status.
     *
     * @param where Address
     * @return message
     */
    public static CENPlusScenario requestStatus(String where) {
        return new CENPlusScenario(format(FORMAT_STATUS, WHO, where));
    }

    /**
     * Verify OpenWebNet message if Dry Contact/IR is ON (WHAT=31).
     *
     * @return true if Dry Contact/IR is ON
     */
    public boolean isOn() throws FrameException {
        if (getWhat() == null) {
            throw new FrameException("invalid WHAT in frame");
        } else {
            return getWhat().equals(WhatCENPlus.ON_IR_DETECTION);
        }
    }

    /**
     * Verify OpenWebNet message if Dry Contact/IR is OFF (WHAT=32).
     *
     * @return true if Dry Contact/IR is OFF
     */
    public boolean isOff() throws FrameException {
        if (getWhat() == null) {
            throw new FrameException("invalid WHAT in frame");
        } else {
            return getWhat().equals(WhatCENPlus.OFF_IR_NO_DETECTION);
        }
    }

    /**
     * OpenWebNet message request for Virtual Short Pressure <b>*25*21#BUTTON*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENPlusScenario virtualShortPressure(String where, int buttonNumber) {
        return new CENPlusScenario(
                format(FORMAT_REQUEST_PARAM_STR, WHO, WhatCENPlus.SHORT_PRESSURE.value, buttonNumber, where));
    }

    /**
     * OpenWebNet message request for Virtual Start Extended Pressure <b>*25*22#BUTTON*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENPlusScenario virtualStartExtendedPressure(String where, int buttonNumber) {
        return new CENPlusScenario(
                format(FORMAT_REQUEST_PARAM_STR, WHO, WhatCENPlus.START_EXT_PRESSURE.value, buttonNumber, where));
    }

    /**
     * OpenWebNet message request for Virtual Extended Pressure <b>*25*23#BUTTON*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENPlusScenario virtualExtendedPressure(String where, int buttonNumber) {
        return new CENPlusScenario(
                format(FORMAT_REQUEST_PARAM_STR, WHO, WhatCENPlus.EXT_PRESSURE.value, buttonNumber, where));
    }

    /**
     * OpenWebNet message request for Virtual Release after Extended Pressure <b>*25*24#BUTTON*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENPlusScenario virtualReleaseExtendedPressure(String where, int buttonNumber) {
        return new CENPlusScenario(
                format(FORMAT_REQUEST_PARAM_STR, WHO, WhatCENPlus.RELEASE_EXT_PRESSURE.value, buttonNumber, where));
    }

    @Override
    public Integer getButtonNumber() throws FrameException {
        if (getWhat() == WhatCENPlus.OFF_IR_NO_DETECTION || getWhat() == WhatCENPlus.ON_IR_DETECTION) {
            return null;
        }
        if (getCommandParams() != null) {
            return getCommandParams()[0];
        } else {
            return null;
        }
    }

    /**
     * Get button pressure type.
     *
     * @return button pressure type or null
     */
    public CENPlusPressure getButtonPressure() {
        if (getWhat() == WhatCENPlus.OFF_IR_NO_DETECTION || getWhat() == WhatCENPlus.ON_IR_DETECTION) {
            return null;
        }
        return CENPlusPressure.fromValue(getWhat().value());
    }

    /**
     * Returns true in case message has WHAT=ON_IR_DETECTION(31) or WHAT=OFF_IR_NO_DETECTION(32)
     *
     * @return boolean
     */
    public boolean isDryContactIR() {
        if (getWhat() != WhatCENPlus.OFF_IR_NO_DETECTION && getWhat() != WhatCENPlus.ON_IR_DETECTION) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void parseWhere() throws FrameException {
        if (whereStr == null) {
            throw new FrameException("Frame has no WHERE part: " + whereStr);
        } else {
            // TODO define e specific WhereCENPlus class to be returned here, according to specs WHO 15/25 page 15
            where = new WhereLightAutom(whereStr);
        }
    }

    @Override
    public OpenDeviceType detectDeviceType() {
        if (!isCommand()) { // ignore status/dimension frames for discovery
            return null;
        }
        if (isDryContactIR()) {
            return OpenDeviceType.SCS_DRY_CONTACT_IR;
        } else {
            return OpenDeviceType.MULTIFUNCTION_SCENARIO_CONTROL;
        }

    }
}
