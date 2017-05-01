package org.z1key.projects.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception error, Model model) {
        logger.error("Error was: " + error.getMessage(), error);
        model.addAttribute("message", error.getMessage());
        model.addAttribute("stackTrace", error.getStackTrace());
        model.addAttribute("exception", error);
        return "error";
    }

    @RestControllerAdvice
    public static class RestBaseController {

        private static final Logger logger = LoggerFactory.getLogger(RestBaseController.class);

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//        @ResponseBody
        public String handleException(Exception error) {
            logger.error("Error was: " + error.getMessage(), error);

            return "error";
        }
    }
}

