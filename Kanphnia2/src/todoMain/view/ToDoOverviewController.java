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
import todoMain.model.Entry;

public class ToDoOverviewController {

	@FXML
	private TableView<Entry> entryTable;

	@FXML
	private TableColumn<Entry, String> appColumn;

	@FXML
	private TableColumn<Entry, String> usernameColumn;
	
	@FXML
	private TableColumn<Entry, String> passwordColumn;
	
	@FXML
	private TableColumn<Entry, String> dateColumn;

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

		entryTable.setItems(mainApp.getEntryList());
	}

	private void showEntryDetails(Entry e) {
		if (e != null) {
			descriptionTextArea.setText(e.getDescription());
		}
		else {
			descriptionTextArea.setText("");
		}
	}

	@FXML
	private void initialize() {
		// Initialize the entry table
		appColumn.setCellValueFactory(cellData -> cellData.getValue().entryTitleProperty());
		usernameColumn.setCellValueFactory(cellData -> cellData.getValue().entryUsernameProperty());
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().entryPasswordProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().entryDateProperty());
		descriptionTextArea.setText("");

		entryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEntryDetails(newValue));
	}

	@FXML
	private void handleNewEntry() throws Exception {
		Entry temp = new Entry();
		boolean okClicked = mainApp.showAppEditDialog(temp);
		if (okClicked) {
			mainApp.getEntryList().add(temp);
		}
	}

	@FXML
	private void handleEditEntry() throws Exception {
		Entry selectedEntry = entryTable.getSelectionModel().getSelectedItem();

		if (selectedEntry != null) {
			boolean okClicked = mainApp.showAppEditDialog(selectedEntry);

			if (okClicked) {
				showEntryDetails(selectedEntry);
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Entry Selected");
			alert.setContentText("Please select an entry in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleDeleteEntry() {
		int selectedIndex = entryTable.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			entryTable.getItems().remove(selectedIndex);
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainApp.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Entry Selected");
			alert.setContentText("Please select an entry in the table");
			alert.showAndWait();
		}
	}
}
