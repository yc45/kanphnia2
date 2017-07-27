package todoMain.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import helpers.Crypt;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Entry {
	private StringProperty entryTitle;
	private StringProperty entryUsername;
	private StringProperty entryPassword;
	private StringProperty entryDate;
	
	private String hiddenPassword = "********";
	private String originalPassword;
	
	public Entry() {
		this.entryTitle = new SimpleStringProperty("");
		this.entryUsername = new SimpleStringProperty("");
		this.entryPassword = new SimpleStringProperty("");
		this.originalPassword = "";
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
		String formattedDate = date.format(formatter);
		
		this.entryDate = new SimpleStringProperty(formattedDate);
	}

	public Entry(String entry, String username, String password) throws Exception {
		this.entryTitle = new SimpleStringProperty(entry);
		this.entryUsername = new SimpleStringProperty(username);
		this.entryPassword = new SimpleStringProperty(password);
		this.originalPassword = Crypt.encrypt(password);
		
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
	
	public String getPassword(boolean b) throws Exception {
		if (b) {
			return this.hiddenPassword;
		}
		else {
			return Crypt.decrypt(this.originalPassword);
		}
	}
	
	public void setPassword(String password) {
		entryPassword.set(password);
	}
	
	public String getOriginalPassword() {
		return this.originalPassword;
	}
	
	public void setOriginalPassword(String password) {
		this.originalPassword = password;
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
}
