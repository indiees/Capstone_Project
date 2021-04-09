import controller.*;
import controller.paths.Web;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(getHerokuAssignedPort());
        app.routes(() -> {
            //Basic resources
            app.post(Web.checkLogin, UserController.checkLogin);
            app.post(Web.createUser, UserController.createUser);
            app.get(Web.getBays, BayController.getBays);
            app.get(Web.getCars, CarController.getCars);
        });
    }
    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }
}