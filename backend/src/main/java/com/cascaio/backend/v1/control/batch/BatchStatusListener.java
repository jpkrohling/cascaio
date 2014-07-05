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
package com.cascaio.backend.v1.control.batch;

import org.slf4j.Logger;

import javax.batch.api.chunk.listener.*;
import javax.batch.api.listener.JobListener;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * @author <a href="mailto:juraci.javadoc@kroehling.de">Juraci Paixão Kröhling</a>
 */
@Named
public class BatchStatusListener implements
        SkipProcessListener, SkipReadListener, SkipWriteListener,
        RetryProcessListener, RetryReadListener, RetryWriteListener, ItemProcessListener,
        JobListener, ChunkListener {

    @Inject
    Logger logger;

    @Override
    public void onSkipProcessItem(Object item, Exception ex) throws Exception {
        logger.error("onSkipProcessItem - Exception caught on Listener", ex);
    }

    @Override
    public void onSkipReadItem(Exception ex) throws Exception {
        logger.error("onSkipReadItem - Exception caught on Listener", ex);
    }

    @Override
    public void onSkipWriteItem(List<Object> items, Exception ex) throws Exception {
        logger.error("onSkipWriteItem - Exception caught on Listener", ex);
    }

    @Override
    public void onRetryProcessException(Object item, Exception ex) throws Exception {
        logger.error("onRetryProcessException - Exception caught on Listener", ex);
    }

    @Override
    public void onRetryReadException(Exception ex) throws Exception {
        logger.error("onRetryReadException - Exception caught on Listener", ex);
    }

    @Override
    public void onRetryWriteException(List<Object> items, Exception ex) throws Exception {
        logger.error("onRetryWriteException - Exception caught on Listener", ex);
    }

    @Override
    public void beforeProcess(Object item) throws Exception {
        logger.trace("beforeProcess for {}", item);
    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {
        logger.trace("afterProcess for {}", item);
    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
        logger.error("onProcessError - Exception caught on Listener", ex);
    }

    @Override
    public void beforeJob() throws Exception {
        logger.info("Starting Batch job");
    }

    @Override
    public void afterJob() throws Exception {
        logger.info("Finished Batch job");
    }

    @Override
    public void beforeChunk() throws Exception {
        logger.trace("afterJob");
    }

    @Override
    public void onError(Exception ex) throws Exception {
        logger.error("onError - Exception caught on Listener", ex);
    }

    @Override
    public void afterChunk() throws Exception {
        logger.trace("afterChunk");
    }
}
