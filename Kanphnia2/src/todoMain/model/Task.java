package todoMain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
	private StringProperty taskTitle;
	private StringProperty taskDate;
	private StringProperty taskDescription;
	
	public Task() {
		this.taskTitle = new SimpleStringProperty("");
		this.taskDescription = new SimpleStringProperty("");
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
		String formattedDate = date.format(formatter);
		
		this.taskDate = new SimpleStringProperty(formattedDate);
	}

	public Task(String task, String description) {
		this.taskTitle = new SimpleStringProperty(task);
		this.taskDescription = new SimpleStringProperty(description);
		
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
		String formattedDate = date.format(formatter);
		
		this.taskDate = new SimpleStringProperty(formattedDate);
	}
	
	public String getTitle() {
		return taskTitle.get();
	}
	
	public void setTitle(String title) {
		taskTitle.set(title);
	}
	
	public StringProperty taskTitleProperty() {
		return taskTitle;
	}
	
	public String getDate() {
		return taskDate.get();
	}
	
	public void setDate(String date) {
		taskDate.set(date);
	}
	
	public StringProperty taskDateProperty() {
		return taskDate;
	}
	
	public String getDescription() {
		return taskDescription.get();
	}
	
	public void setDescription(String description) {
		taskDescription.set(description);
	}
	
	public StringProperty taskDescriptionProperty() {
		return taskDescription;
	}
}
