package todoMain;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import todoMain.model.Entry;
import todoMain.model.EntryListWrapper;
import todoMain.view.AppEditDialogController;
import todoMain.view.RootLayoutController;
import todoMain.view.ToDoOverviewController;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<Entry> entryList = FXCollections.observableArrayList();
	
	public MainApp() {

	}
	
	public ObservableList<Entry> getEntryList() {
		return entryList;
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
		
		// give the controller access to the main app
		RootLayoutController controller = loader.getController();
		controller.setMainApp(this);
		
		// try to load the last opened entry file
		File file = getFilePath();
		if (file != null) {
			loadEntryDataFromFile(file);
		}
		
		// load ToDoOverview.fxml
		FXMLLoader loader2 = new FXMLLoader();
		loader2.setLocation(MainApp.class.getResource("view/ToDoOverview.fxml"));
		AnchorPane toDoOverview = (AnchorPane) loader2.load();
		
		// set ToDoOverview into the center of RootLayout
		rootLayout.setCenter(toDoOverview);
		
		// give the controller access to MainApp
		ToDoOverviewController controller2 = loader2.getController();
		controller2.setMainApp(this);
		
		this.primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public boolean showAppEditDialog(Entry e) throws IOException {
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
        AppEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEntry(e);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
        
        return controller.isOkClicked();
	}
	
	// return the file that was last opened
	public File getFilePath() {
		Preferences p = Preferences.userNodeForPackage(MainApp.class);
		String filePath = p.get("filePath", null);
		
		if (filePath != null) {
			return new File(filePath);
		}
		else {
			return null;
		}
	}
	
	// sets the file path of the currently loaded file
	public void setFilePath(File f) {
		Preferences p = Preferences.userNodeForPackage(MainApp.class);
		
		if (f != null) {
			p.put("filePath",  f.getPath());
			primaryStage.setTitle("Kanphnia2 - " + f.getName());
		}
		else {
			p.remove("filePath");
			primaryStage.setTitle("Kanphnia2");
		}
	}
	
	// loads the entry data from the specified file.
	// the current entry data will be replaced
	public void loadEntryDataFromFile(File f) {
		try {
			JAXBContext context = JAXBContext.newInstance(EntryListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			// read XML from the file and unmarshalling
			EntryListWrapper wrapper = (EntryListWrapper) um.unmarshal(f);
			
			entryList.clear();
			entryList.addAll(wrapper.getEntries());
			
			// save the file path
			setFilePath(f);
		}
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + f.getPath());
			
			alert.showAndWait();
		}
	}
	
	// saves the current entry data to the specified file
	public void saveEntryDataToFile(File f) {
		try {
			JAXBContext context = JAXBContext.newInstance(EntryListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// wrapping the entry data
			EntryListWrapper wrapper = new EntryListWrapper();
			wrapper.setEntries(entryList);
			
			// marshalling and saving xml to file
			m.marshal(wrapper, f);
			
			// save file path
			setFilePath(f);
		}
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + f.getPath());
			
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
