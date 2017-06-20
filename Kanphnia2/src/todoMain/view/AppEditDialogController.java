package todoMain.view;

import helpers.Crypt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import todoMain.model.Entry;

public class AppEditDialogController {
	@FXML
	private TextField appTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private Button okButton;

	@FXML
	private Button cancelButton;

	private Stage dialogStage;
	private Entry entry;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
	}

	// sets the stage of this dialog
	public void setDialogStage(Stage stage) {
		this.dialogStage = stage;
	}

	// set the entry to be edited in this dialog
	public void setEntry(Entry e) throws Exception {
		this.entry = e;
		appTextField.setText(e.getTitle());
		usernameTextField.setText(e.getUsername());
		/*if (e.getEncryptStatus()) {
			e.setPassword(Crypt.decrypt(e.getPassword()));
			e.setEncryptStatus(false);
		}*/
		passwordTextField.setText(e.getPassword());
		descriptionTextField.setText(e.getDescription());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if ((appTextField.getText() == null) || appTextField.getText().length() == 0) {
			errorMessage += "No valid application!\n";
		}
		if ((usernameTextField.getText() == null) || usernameTextField.getText().length() == 0) {
			errorMessage += "No valid username!\n";
		}
		if ((passwordTextField.getText() == null) || passwordTextField.getText().length() == 0) {
			errorMessage += "No valid password!\n";
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
	private void handleOk() throws Exception {
		if (isInputValid()) {
			entry.setTitle(appTextField.getText());
			entry.setUsername(usernameTextField.getText());
			entry.setPassword(Crypt.encrypt(passwordTextField.getText()));
			entry.setEncryptStatus(true);
			entry.setDescription(descriptionTextField.getText());
			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

}
