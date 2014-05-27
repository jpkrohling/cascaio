package com.cascaio.backend.v1.control.batch.stock;

import javax.inject.Named;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class NyseProcessor extends NasdaqProcessor {

    @Override
    protected boolean acceptLine(String line) {
        String[] parts = line.split("\\|");

        logger.trace("Determining if this line is to be accepted: {}", line);

        if (parts.length < 7) {
            return false;
        }

        if ("Y".equals(parts[6])) {
            // Y = Yes, it is a test issue, reject
            logger.trace("Rejecting, as this is a test symbol: {}", line);
            return false;
        }

        if (!"N".equals(parts[2])) {
            // N = New York Stock Exchange (NYSE)
            logger.trace("Rejecting, as this is not NYSE symbol: {}", line);
            return false;
        }

        // otherwise, just accept it
        return true;
    }

    @Override
    public String getSanitizedName(String name) {
        return name.replaceAll(" Common Stock", "");
    }

    @Override
    public String getMarketSymbol() {
        return "NYSE";
    }

}
