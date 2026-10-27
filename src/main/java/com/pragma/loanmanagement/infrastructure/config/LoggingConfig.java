package com.pragma.loanmanagement.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

@Configuration
public class LoggingConfig implements WebMvcConfigurer {

    private static final String REQUEST_ID_HEADER = "X-Request-ID";
    private static final String REQUEST_ID_ATTRIBUTE = "requestId";

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/actuator/**");
    }

    public static class LoggingInterceptor implements org.springframework.web.servlet.HandlerInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            String requestId = request.getHeader(REQUEST_ID_HEADER);
            if (requestId == null || requestId.isBlank()) {
                requestId = UUID.randomUUID().toString();
            }

            request.setAttribute(REQUEST_ID_ATTRIBUTE, requestId);

            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);

            logger.info("[{}] {} {} - Inicio de solicitud - IP: {}",
                    requestId,
                    request.getMethod(),
                    request.getRequestURI(),
                    getClientIpAddress(request));

            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                   Object handler, Exception ex) {
            String requestId = (String) request.getAttribute(REQUEST_ID_ATTRIBUTE);
            Long startTime = (Long) request.getAttribute("startTime");
            long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;

            int httpStatus = response.getStatus();
            String logLevel = determineLogLevel(httpStatus);

            if (logger.isInfoEnabled()) {
                logger.info("[{}] {} {} - Completado - Estado: {} - Duración: {}ms",
                        requestId,
                        request.getMethod(),
                        request.getRequestURI(),
                        httpStatus,
                        duration);
            }

            if (ex != null && logger.isErrorEnabled()) {
                logger.error("[{}] {} {} - Error: {}",
                        requestId,
                        request.getMethod(),
                        request.getRequestURI(),
                        ex.getMessage(),
                        ex);
            }
        }

        private String determineLogLevel(int httpStatus) {
            if (httpStatus >= 500) {
                return "ERROR";
            } else if (httpStatus >= 400) {
                return "WARN";
            } else {
                return "INFO";
            }
        }

        private String getClientIpAddress(HttpServletRequest request) {
            String xForwardedFor = request.getHeader("X-Forwarded-For");
            if (xForwardedFor != null && !xForwardedFor.isBlank()) {
                return xForwardedFor.split(",")[0].trim();
            }

            String xRealIp = request.getHeader("X-Real-IP");
            if (xRealIp != null && !xRealIp.isBlank()) {
                return xRealIp;
            }

            return request.getRemoteAddr();
        }
    }

    public static void logInfo(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.info(message, args);
    }

    public static void logWarn(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.warn(message, args);
    }

    public static void logError(String message, Throwable throwable) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.error(message, throwable);
    }

    public static void logDebug(String message, Object... args) {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.debug(message, args);
    }

    public static void configureLogLevels(String packageName, String level) {
        Logger logger = LoggerFactory.getLogger(packageName);
        if ("DEBUG".equalsIgnoreCase(level)) {
            logger.debug("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("INFO".equalsIgnoreCase(level)) {
            logger.info("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("WARN".equalsIgnoreCase(level)) {
            logger.warn("Nivel de logging configurado para {}: {}", packageName, level);
        } else if ("ERROR".equalsIgnoreCase(level)) {
            logger.error("Nivel de logging configurado para {}: {}", packageName, level);
        }
    }
}