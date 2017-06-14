package todoMain.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import todoMain.MainApp;

public class RootLayoutController {

	private MainApp mainApp;
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	// create new entries
	@FXML
	private void handleNew() {
		mainApp.getEntryList().clear();
		mainApp.setFilePath(null);
	}
	
	
	// use FileChooser to let the user select an address book to load
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();
		
		// set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		// show open file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		
		if (file != null) {
			mainApp.loadEntryDataFromFile(file);
		}
	}
	
	// saves the file that is currently open
	@FXML
	private void handleSave() {
		File entryFile = mainApp.getFilePath();
		if (entryFile != null) {
			mainApp.saveEntryDataToFile(entryFile);
		}
		else {
			handleSaveAs();
		}
	}
	
	// opens a FileChooser to let the user select a file to save to
	@FXML
	private void handleSaveAs() {
		FileChooser fileChooser = new FileChooser();
		
		// set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		
		// show save file dialog
		File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
		
		if (file != null) {
			if (!file.getPath().endsWith(".xml")) {
				file = new File(file.getPath() + ".xml");
			}
			mainApp.saveEntryDataToFile(file);
		}
	}
	
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Kanphnia2");
		alert.setHeaderText("About");
		alert.setContentText("This is a password manager");
		
		alert.showAndWait();
	}
	
	@FXML
	private void handleExit() {
		System.exit(0);
	}
	
	public RootLayoutController() {
		// TODO Auto-generated constructor stub
	}

}
