package todoMain.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import todoMain.MainApp;
import todoMain.model.Task;

public class ToDoOverviewController {

	@FXML
	private TableView<Task> taskTable;

	@FXML
	private TableColumn<Task, String> taskColumn;

	@FXML
	private TableColumn<Task, String> dateColumn;

	@FXML
	private TextArea descriptionTextArea;

	@FXML
	private Button newButton;

	@FXML
	private Button editButton;

	@FXML
	private Button deleteButton;

	private MainApp mainApp;

	public ToDoOverviewController() {

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		taskTable.setItems(mainApp.getTaskList());
	}

	private void showTaskDetails(Task t) {
		if (t != null) {
			descriptionTextArea.setText(t.getDescription());
		}
		else {
			descriptionTextArea.setText("");
		}
	}

	@FXML
	private void initialize() {
		// Initialize the task table with the two columns
		taskColumn.setCellValueFactory(cellData -> cellData.getValue().taskTitleProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().taskDateProperty());
		descriptionTextArea.setText("");

		taskTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTaskDetails(newValue));
	}

	@FXML
	private void handleNewTask() throws IOException {
		Task temp = new Task();
		boolean okClicked = mainApp.showTaskEditDialog(temp);
		if (okClicked) {
			mainApp.getTaskList().add(temp);
		}
	}

	@FXML
	private void handleEditTask() throws IOException {
		Task selectedTask = taskTable.getSelectionModel().getSelectedItem();

		if (selectedTask != null) {
			boolean okClicked = mainApp.showTaskEditDialog(selectedTask);

			if (okClicked) {
				showTaskDetails(selectedTask);
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Task Selected");
			alert.setContentText("Please select a task in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteTask() {
		int selectedIndex = taskTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			taskTable.getItems().remove(selectedIndex);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Task Selected");
			alert.setContentText("Please select a task in the table");
			alert.showAndWait();
		}
	}
}