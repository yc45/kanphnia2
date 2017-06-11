package todoMain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {
	private StringProperty entryTitle;
	private StringProperty entryUsername;
	private StringProperty entryPassword;
	private StringProperty entryDate;
	private StringProperty entryDescription;
	
	public Entry() {
		this.entryTitle = new SimpleStringProperty("");
		this.entryUsername = new SimpleStringProperty("");
		this.entryPassword = new SimpleStringProperty("");
		this.entryDescription = new SimpleStringProperty("");
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
		String formattedDate = date.format(formatter);
		
		this.entryDate = new SimpleStringProperty(formattedDate);
	}

	public Entry(String entry, String username, String password, String description) {
		this.entryTitle = new SimpleStringProperty(entry);
		this.entryUsername = new SimpleStringProperty(username);
		this.entryPassword = new SimpleStringProperty(password);
		this.entryDescription = new SimpleStringProperty(description);
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
		String formattedDate = date.format(formatter);
		
		this.entryDate = new SimpleStringProperty(formattedDate);
	}
	
	public String getTitle() {
		return entryTitle.get();
	}
	
	public void setTitle(String title) {
		entryTitle.set(title);
	}
	
	public StringProperty entryTitleProperty() {
		return entryTitle;
	}
	
	public String getUsername() {
		return entryUsername.get();
	}
	
	public void setUsername(String title) {
		entryUsername.set(title);
	}
	
	public StringProperty entryUsernameProperty() {
		return entryUsername;
	}
	
	public String getPassword() {
		return entryPassword.get();
	}
	
	public void setPassword(String title) {
		entryPassword.set(title);
	}
	
	public StringProperty entryPasswordProperty() {
		return entryPassword;
	}
	
	public String getDate() {
		return entryDate.get();
	}
	
	public void setDate(String date) {
		entryDate.set(date);
	}
	
	public StringProperty entryDateProperty() {
		return entryDate;
	}
	
	public String getDescription() {
		return entryDescription.get();
	}
	
	public void setDescription(String description) {
		entryDescription.set(description);
	}
	
	public StringProperty entryDescriptionProperty() {
		return entryDescription;
	}
}
