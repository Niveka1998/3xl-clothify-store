import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

public class Starter extends Application {
    public static void main(String[] args) {
       launch();
//        List<String > list =Arrays.asList("saman","kamal","nimal");
//        list.forEach(name -> System.out.println(name));//step 01
//        list.forEach(System.out::println); //step 02
    }


    @Override
    public void start(Stage stage) throws Exception {
//        Injector injector = Guice.createInjector(new AppModule());
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-dashboard.fxml"));
//        loader.setControllerFactory(injector::getInstance);
//        stage.setScene(new Scene(loader.load()));
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/main-dashboard.fxml"))));
        stage.show();
    }
}
