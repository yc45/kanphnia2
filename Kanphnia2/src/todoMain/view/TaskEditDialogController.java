package todoMain.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import todoMain.model.Task;

public class TaskEditDialogController {

	@FXML
	private TextField taskTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	private Stage dialogStage;
	private Task task;
	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	// sets the stage of this dialog
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}

	// set the task to be edited in this dialog
	public void setTask(Task t) {
		this.task = t;
		taskTextField.setText(t.getTitle());
		descriptionTextField.setText(t.getDescription());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if ((taskTextField.getText() == null) || taskTextField.getText().length() == 0) {
			errorMessage += "No valid task!\n";
		}
		if ((descriptionTextField.getText() == null) || descriptionTextField.getText().length() == 0) {
			errorMessage += "No valid description!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			task.setTitle(taskTextField.getText());
			task.setDescription(descriptionTextField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
