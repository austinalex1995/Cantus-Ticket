import Controller.CantusController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;

public class Application {

    public static void main(String[] args) {
//        this line is just for testing that your tables get set up correctly
//        if not, you'll get a stack trace

        ConnectionSingleton.getConnection();
        CantusController controller = new CantusController();
        Javalin app = controller.startAPI();
        app.start(8000);

    }

}
