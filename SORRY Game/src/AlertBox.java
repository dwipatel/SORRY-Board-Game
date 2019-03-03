import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void display(String title, String x) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        Label text = new Label(x);
        Button button = new Button("Exit");
        //closes window
        button.setOnAction(e -> window.close());

        //layout positioned to centre
        VBox layout = new VBox(10);
        layout.getChildren().addAll(text, button);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

}

