package com.bsuir.workshops.laba_1.validation;

import com.bsuir.workshops.laba_1.entity.ValidationError;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Component
public class Validator {
    private static Logger logger = LoggerFactory.getLogger(Validator.class);

    public ValidationError validateInputNum(String inputNum){
        ValidationError response = new ValidationError();

        if (!StringUtils.isNumeric(inputNum)){
            logger.error("Передано не число!");
            response.addErrorMessage("Передано не число!");
        }
        else{
            int translate = Integer.parseInt(inputNum);
            if (translate<0){
                logger.error("Введено число меньше 0");
                response.addErrorMessage("Введено число меньше 0");
            }
            if (translate>10000){
                logger.error("Введено число больше 10000");
                response.addErrorMessage("Введено число меньше 10000");
            }
        }
        return response;
    }
}
