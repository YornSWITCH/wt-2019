package controller.implementation;

import controller.Command;
import service.ValidationService;

public class ValidateXml implements Command {
    /**
     *
     * @param params method takes 2 argument.
     *      First argument is xsd path
     *      Second argument is xsm path
     * @return validation result:
     *      'true' if xml is valid
     *      and 'false' in other situation
     */
    @Override
    public String execute(String ... params) {
        boolean result = ValidationService.validateXMLSchema(params[0],params[1]);
        return Boolean.toString(result);
    }
}
