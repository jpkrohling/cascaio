package com.cascaio.backend.v1.control.batch.stock;

import javax.inject.Named;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class NyseReader extends NasdaqReader {

    @Override
    public String getRemotePath() {
        return "SymbolDirectory/otherlisted.txt";
    }

}
