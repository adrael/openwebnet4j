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

/**
 * Class to wrap WHERE part of frame
 *
 * @author M. Valla - Initial contribution
 */
public abstract class Where {
    protected final String whereStr;

    public Where(String w) throws NullPointerException, IllegalArgumentException {
        if (w != null) {
            char[] a = w.toCharArray();
            for (char c : a) {
                if (!(((c >= '0') && (c <= '9')) || (c == '#'))) {
                    throw new IllegalArgumentException("WHERE can only contain '#' or digits [0-9]");
                }
            }
            this.whereStr = w;
        } else {
            throw new NullPointerException("WHERE value is null");
        }
    }

    @Override
    public String toString() {
        return "w:" + whereStr;
    }

    /**
     * Returns a String with the value of this WHERE
     *
     * @return a String with the value of this WHERE
     */
    public String value() {
        return whereStr;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Where) {
            return whereStr.equals(((Where) o).whereStr);
        } else {
            return false;
        }
    }
}

/*
 *
 * Add to a WHERE address a prefix or postfix according to type.
 *
 * @param String where the where address
 *
 * @param Type the type of address (AREA, GENERAL, POINT_TO_POINT, etc.)
 *
 * @return String WHERE
 *
 * protected static String buildWhereValue(String where, Type type) {
 * switch (type) {
 * case GENERAL:
 * case AREA:
 * case POINT_TO_POINT:
 * return where;
 * case ZIGBEE:
 * return where.concat(WHERE_ZIGBEE_SUFFIX);
 * case GROUP:
 * return WHERE_GROUP_PREFIX.concat(where);
 * }
 * throw new IllegalArgumentException("invalid type");
 * }
 */
