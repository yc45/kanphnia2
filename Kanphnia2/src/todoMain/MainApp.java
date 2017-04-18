package todoMain;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoMain.model.Task;
import todoMain.view.TaskEditDialogController;
import todoMain.view.ToDoOverviewController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Task> taskList = FXCollections.observableArrayList();
	
	public MainApp() {
		taskList.add(new Task("item1", "do this"));
		taskList.add(new Task("item2", "do that"));
	}
	
	public ObservableList<Task> getTaskList() {
		return taskList;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("To-Do List");

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
	
	public boolean showTaskEditDialog(Task t) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/TaskEditDialog.fxml"));
		AnchorPane page = (AnchorPane) loader.load();
		
		// Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Task");
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
