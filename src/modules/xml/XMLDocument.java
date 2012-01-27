// XML Document
// Jacob Peck

import java.util.*;

public class XMLDocument {
  private List<XMLTag> tags = new ArrayList<XMLTag>();
  public final String header = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
  
  public void addTag(XMLTag newtag) {
    this.tags.add(newtag);
  } 
  
  public List<XMLTag> getTags() {
    return this.tags;
  }
}
