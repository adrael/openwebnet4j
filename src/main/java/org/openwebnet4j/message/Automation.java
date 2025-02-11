/**
 * Copyright (c) 2020-2023 Contributors to the openwebnet4j project
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
 * OpenWebNet Automation messages (WHO=2)
 *
 * @author M. Valla - Initial contribution
 */
public class Automation extends BaseOpenMessage {

    public enum WhatAutomation implements What {
        STOP(0),
        UP(1),
        DOWN(2);

        private static Map<Integer, WhatAutomation> mapping;

        private final int value;

        private WhatAutomation(int value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, WhatAutomation>();
            for (WhatAutomation w : values()) {
                mapping.put(w.value, w);
            }
        }

        public static WhatAutomation fromValue(int i) {
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
        return WhatAutomation.fromValue(i);
    }

    public enum DimAutomation implements Dim {
        SHUTTER_STATUS(10),
        GOTO_LEVEL(11);

        private static Map<Integer, DimAutomation> mapping;

        private final int value;

        private DimAutomation(Integer value) {
            this.value = value;
        }

        private static void initMapping() {
            mapping = new HashMap<Integer, DimAutomation>();
            for (DimAutomation d : values()) {
                mapping.put(d.value, d);
            }
        }

        public static DimAutomation fromValue(int i) {
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
        return DimAutomation.fromValue(i);
    }

    private static final int WHO = Who.AUTOMATION.value();

    protected Automation(String value) {
        super(value);
        this.who = Who.AUTOMATION;
    }

    /**
     * OpenWebNet message request to send <code>STOP</code> <code>*2*0*WHERE##</code>.
     *
     * @param w WHERE string
     * @return message
     */
    public static Automation requestStop(String w) {
        return new Automation(format(FORMAT_REQUEST, WHO, WhatAutomation.STOP.value, w));
    }

    /**
     * OpenWebNet message request to send <code>UP</code> <code>*2*1*WHERE##</code>.
     *
     * @param w WHERE string
     * @return message
     */
    public static Automation requestMoveUp(String w) {
        return new Automation(format(FORMAT_REQUEST, WHO, WhatAutomation.UP.value, w));
    }

    /**
     * OpenWebNet message request to send <code>DOWN</code> <code>*2*2*WHERE##</code>.
     *
     * @param w WHERE string
     * @return message
     */
    public static Automation requestMoveDown(String w) {
        return new Automation(format(FORMAT_REQUEST, WHO, WhatAutomation.DOWN.value, w));
    }

    /**
     * OpenWebNet message request automation status <code>*#2*WHERE##</code>.
     *
     * @param w WHERE string
     * @return message
     */
    public static Automation requestStatus(String w) {
        return new Automation(format(FORMAT_STATUS, WHO, w));
    }

    /**
     * Verify OpenWebNet message is <code>STOP</code> (WHAT=0).
     *
     * @return true if message is "STOP"
     */
    public boolean isStop() {
        if (getWhat() == null) {
            return false;
        } else {
            return getWhat().equals(WhatAutomation.STOP);
        }
    }

    /**
     * Verify OpenWebNet message is <code>UP</code> (WHAT=1).
     *
     * @return true if message is "UP"
     */
    public boolean isUp() {
        if (getWhat() == null) {
            return false;
        } else {
            return getWhat().equals(WhatAutomation.UP);
        }
    }

    /**
     * Verify OpenWebNet message is <code>DOWN</code> (WHAT=2).
     *
     * @return true if message is "DOWN"
     */
    public boolean isDown() {
        if (getWhat() == null) {
            return false;
        } else {
            return getWhat().equals(WhatAutomation.DOWN);
        }
    }

    /**
     * Convert an Automation message UP&lt;&gt;DOWN
     *
     * @param autMsg message to convert
     * @return converted Automation message
     * @throws FrameException in case of error in frame
     */
    public static Automation convertUpDown(Automation autMsg) throws FrameException {
        if (autMsg.isUp()) {
            return (Automation) BaseOpenMessage.parse(autMsg.getFrameValue().replaceFirst("\\*2\\*1", "\\*2\\*2"));
        } else if (autMsg.isDown()) {
            return (Automation) BaseOpenMessage.parse(autMsg.getFrameValue().replaceFirst("\\*2\\*2", "\\*2\\*1"));
        } else {
            return autMsg;
        }
    }

    @Override
    protected void parseWhere() throws FrameException {
        if (whereStr == null) {
            throw new FrameException("Frame has no WHERE part: " + whereStr);
        } else {
            if (whereStr.endsWith(WhereZigBee.ZB_NETWORK)) {
                where = new WhereZigBee(whereStr);
            } else {
                where = new WhereLightAutom(whereStr);
            }
        }
    }

    @Override
    public OpenDeviceType detectDeviceType() {
        if (isCommand()) { // ignore status/dimension frames for detecting device type
            return OpenDeviceType.SCS_SHUTTER_CONTROL;
        } else {
            return null;
        }
    }
}
