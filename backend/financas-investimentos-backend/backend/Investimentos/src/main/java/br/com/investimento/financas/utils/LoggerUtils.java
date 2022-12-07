package br.com.investimento.financas.utils;

import br.com.investimento.financas.config.RotinaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.investimento.financas.service.LancamentoService;

public class LoggerUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(RotinaConfig.class);

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message) {
        logger.error(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logWarn(String message) {
        logger.warn(message);
    }

    public static void logTrace(String message) {
        logger.trace(message);
    }

    public static boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }
}