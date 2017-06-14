package todoMain.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// saves the data in entryList to XML format

@XmlRootElement(name = "entries")
public class EntryListWrapper {
	private List<Entry> entries;
	
	@XmlElement(name = "entry")
	public List<Entry> getEntries() {
		return entries;
	}
	
	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}
}
