package controller.implementation;

import controller.Command;
import service.MigrationService;
import service.ServiceException;

public class MigrateToBD implements Command {
    /**
     * Execute data migration from xml format to db
     * @param params no params
     * @return 'success' if migrate is success error otherwise
     */
    @Override
    public String execute(String ... params) {
        String result = "";
        try {
            MigrationService.MigrateAll();
            result = "success";
        }catch (ServiceException e)
        {
            result = "error";
        }
        return "";
    }
}
