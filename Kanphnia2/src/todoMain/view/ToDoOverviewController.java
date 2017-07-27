package todoMain.view;

import helpers.Crypt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import todoMain.MainApp;
import todoMain.model.Entry;

public class ToDoOverviewController {
    @FXML
    private SplitPane overviewSplitPane;
    
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
	private Button copyButton;
	
	@FXML
	private Button newButton;

	@FXML
	private Button editButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button showhideButton;
	private static boolean show = true;
	
	private MainApp mainApp;

	public ToDoOverviewController() {
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		entryTable.setItems(mainApp.getEntryList());
	}

	@FXML
	private void initialize() {
		// Initialize the entry table
		appColumn.setCellValueFactory(cellData -> cellData.getValue().entryTitleProperty());
		usernameColumn.setCellValueFactory(cellData -> cellData.getValue().entryUsernameProperty());
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().entryPasswordProperty());
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().entryDateProperty());
	}

	@FXML
	private void copyToClipboard() throws Exception {
		int selectedIndex = entryTable.getSelectionModel().getSelectedIndex();
        final ClipboardContent content = new ClipboardContent();
        
		if (selectedIndex >= 0) {
			content.putString(Crypt.decrypt(entryTable.getItems().get(selectedIndex).getPassword(false)));
			Clipboard.getSystemClipboard().setContent(content);
		}
		else {
			content.putString("");
			Clipboard.getSystemClipboard().setContent(content);
		}
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
				// removed description field
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
	
	@FXML
	private void handleShowHide() throws Exception {
		if (show) {
			showhideButton.setText("Hide");
			for (Entry e : entryTable.getItems()) {
				System.out.println("the password is " + e.getPassword(false));
				e.setPassword(e.getPassword(false));
			}
		}
		else {
			showhideButton.setText("Show");
			for (Entry e : entryTable.getItems()) {
				System.out.println("the password is " + e.getPassword(true));
				e.setPassword(e.getPassword(true));
			}
		}
		
		show = !show;
	}
}