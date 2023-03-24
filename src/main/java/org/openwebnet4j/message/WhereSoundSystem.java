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
 * WHERE for Sound System frames
 *
 * @author R. Marques - Initial contribution
 */
public class WhereSoundSystem extends Where {
    public static final WhereSoundSystem SOURCE = new WhereSoundSystem("2");
    public static final WhereSoundSystem SPEAKER = new WhereSoundSystem("3");
    public static final WhereSoundSystem SPEAKER_AREA = new WhereSoundSystem("4");
    public static final WhereSoundSystem GENERAL = new WhereSoundSystem("5");
    public static final WhereSoundSystem ALL_SOURCE = new WhereSoundSystem("6");

    public static final String FORMAT_SOURCE = "2#%s";
    public static final String FORMAT_SPEAKER = "3#%s#%s";
    public static final String FORMAT_SPEAKER_AREA = "4#%s";
    public static final String FORMAT_GENERAL = "5#%s"; // s is sender address e.g. 2#1

    public WhereSoundSystem(String w) throws NullPointerException {
        // TODO check range for WHERE
        super(w);
    }
}
