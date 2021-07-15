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
 * OpenWebNet class for CEN
 *
 * @author M. Valla - Initial contribution
 */
public class CENScenario extends CEN {
    // private static final Logger logger = LoggerFactory.getLogger(CENScenario.class);

    public enum WhatCEN implements What {
        BUTTON_00(0),
        BUTTON_01(1),
        BUTTON_02(2),
        BUTTON_03(3),
        BUTTON_04(4),
        BUTTON_05(5),
        BUTTON_06(6),
        BUTTON_07(7),
        BUTTON_08(8),
        BUTTON_09(9),
        BUTTON_10(10),
        BUTTON_11(11),
        BUTTON_12(12),
        BUTTON_13(13),
        BUTTON_14(14),
        BUTTON_15(15),
        BUTTON_16(16),
        BUTTON_17(17),
        BUTTON_18(18),
        BUTTON_19(19),
        BUTTON_20(20),
        BUTTON_21(21),
        BUTTON_22(22),
        BUTTON_23(23),
        BUTTON_24(24),
        BUTTON_25(25),
        BUTTON_26(26),
        BUTTON_27(27),
        BUTTON_28(28),
        BUTTON_29(29),
        BUTTON_30(30),
        BUTTON_31(31);

        private static Map<Integer, WhatCEN> mapping;

        private final int value;

        private WhatCEN(Integer value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, WhatCEN>();
            for (WhatCEN w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static WhatCEN fromValue(int i) {
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

    public enum CENPressure {
        PRESSURE(0),
        RELEASE_SHORT_PRESSURE(1),
        RELEASE_EXT_PRESSURE(2),
        EXT_PRESSURE(3);

        private static Map<Integer, CENPressure> mapping;

        private final int value;

        private CENPressure(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, CENPressure>();
            for (CENPressure w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static CENPressure fromValue(int i) {
            if (mapping == null) {
                initMapping();
            }
            return mapping.get(i);
        }

        // public Integer value() {
        // return value;
        // }
    }

    @Override
    protected Dim dimFromValue(int i) {
        // no Dims for this WHO
        return null;
    }

    private static final int WHO = Who.CEN_SCENARIO_SCHEDULER.value();

    protected CENScenario(String value) {
        super(value);
    }

    @Override
    protected What whatFromValue(int i) {
        return WhatCEN.fromValue(i);
    }

    /**
     * OpenWebNet message request for Virtual Start Pressure <b>*15*BUTTON*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENScenario virtualStartPressure(String where, int buttonNumber) {
        return new CENScenario(format(FORMAT_REQUEST_WHAT_STR, WHO, whoFromButton(buttonNumber), where));
    }

    /**
     * OpenWebNet message request for Virtual Release after Short Pressure <b>*15*BUTTON#1*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENScenario virtualReleaseShortPressure(String where, int buttonNumber) {
        return new CENScenario(format(FORMAT_REQUEST_PARAM_STR, WHO, whoFromButton(buttonNumber),
                CENPressure.RELEASE_SHORT_PRESSURE.value, where));
    }

    /**
     * OpenWebNet message request for Virtual Extended Pressure <b>*15*BUTTON#3*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENScenario virtualExtendedPressure(String where, int buttonNumber) {
        return new CENScenario(format(FORMAT_REQUEST_PARAM_STR, WHO, whoFromButton(buttonNumber),
                CENPressure.EXT_PRESSURE.value, where));
    }

    /**
     * OpenWebNet message request for Virtual Release after Extended Pressure <b>*15*BUTTON#2*WHERE##</b>.
     *
     * @param where WHERE
     * @param buttonNumber button number
     * @return message
     */
    public static CENScenario virtualReleaseExtendedPressure(String where, int buttonNumber) {
        return new CENScenario(format(FORMAT_REQUEST_PARAM_STR, WHO, whoFromButton(buttonNumber),
                CENPressure.RELEASE_EXT_PRESSURE.value, where));
    }

    @Override
    public Integer getButtonNumber() throws FrameException {
        return getWhat().value();
    }

    /**
     * Get button {@link CENPressure}
     *
     * @return button {@link CENPressure}, or null if a {@link CENPressure} cannot be recognised
     * @throws FrameException in case of frame error
     */
    public CENPressure getButtonPressure() throws FrameException {
        if (getCommandParams() == null) {
            return CENPressure.PRESSURE;
        } else {
            return CENPressure.fromValue(getCommandParams()[0]);
        }
    }

    @Override
    protected void parseWhere() throws FrameException {
        if (whereStr == null) {
            throw new FrameException("Frame has no WHERE part: " + whereStr);
        } else {
            where = new WhereLightAutom(whereStr);
        }
    }

    @Override
    public OpenDeviceType detectDeviceType() {
        if (!isCommand()) { // ignore status/dimension frames for discovery
            return null;
        }
        return OpenDeviceType.SCENARIO_CONTROL;
    }

    private static String whoFromButton(int button) {
        return (button < 10 ? "0" : "") + button;
    }

}
