/*
 * Copyright (C) 2014 Juraci Paixão Kröhling <juraci at kroehling.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.cascaio.backend.v1.control;

/**
 *
 * @author Juraci Paixão Kröhling <juraci at kroehling.de>
 */
public class DataAccessForbiddenException extends RuntimeException {

    public DataAccessForbiddenException() {
    }

    public DataAccessForbiddenException(String string) {
        super(string);
    }

    public DataAccessForbiddenException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public DataAccessForbiddenException(Throwable thrwbl) {
        super(thrwbl);
    }

    public DataAccessForbiddenException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }

}
