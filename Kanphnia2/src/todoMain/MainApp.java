package todoMain;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoMain.model.Entry;
import todoMain.view.TaskEditDialogController;
import todoMain.view.ToDoOverviewController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Entry> taskList = FXCollections.observableArrayList();
	
	public MainApp() {

	}
	
	public ObservableList<Entry> getTaskList() {
		return taskList;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kanphnia2");
		this.primaryStage.getIcons().add(new Image("file:images/clipboard_icon.png"));

		// load RootLayout.fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
		rootLayout = (BorderPane) loader.load();

		// show the scene containing the rootLayout
		Scene scene = new Scene(rootLayout);
		this.primaryStage.setScene(scene);
		
		// load ToDoOverview.fxml
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(MainApp.class.getResource("view/ToDoOverview.fxml"));
		AnchorPane toDoOverview = (AnchorPane) loader2.load();
		
		// set ToDoOverview into the center of RootLayout
		rootLayout.setCenter(toDoOverview);
		
		// give the controller access to MainApp
		ToDoOverviewController controller = loader2.getController();
		controller.setMainApp(this);
		
		this.primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public boolean showTaskEditDialog(Entry t) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/AppEditDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		// Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Entry");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        TaskEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setTask(t);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
        
        return controller.isOkClicked();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
