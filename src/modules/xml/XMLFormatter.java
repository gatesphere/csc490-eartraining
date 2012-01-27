// XML Formatter module
// Jacob Peck

import java.util.*;

public class XMLFormatter {
  private XMLDocument doc = new XMLDocument();
  private Map<String,XMLTag> lookuptable = new HashMap<String,XMLTag>();

  public void newTag(String name, String value) {
    XMLTag temp = new XMLTag(name, value);
    this.lookuptable.put(name, temp);
    this.doc.addTag(newtag);
  }
  
  public void newTag(String name) {
    this.newTag(name, null);
  }
  
  public void addTag(XMLTag newtag) {
    this.lookuptable.put(newtag.getName(), newtag);
    this.doc.addTag(newtag);
  }
  
  public void addNestedTag(String parentname, XMLTag nestedtag) {
    this.lookuptable.get(parentname).addTag(nestedtag);
    this.lookuptable.put(nestedtag.getName(), nestedtag);
  }
  
  public void addNestedTag(String parentname, String nestedname, String nestedvalue) {
    XMLTag temp = new XMLTag(nestedname, nestedvalue);
    this.addNestedTag(parentname, temp);
  }
  
  public void addNestedTag(String parentname, String nestedname) {
    this.addNestedTag(parentname, nestedname, null);
  }

  public String toString() {
    int depth = 0;
    StringBuilder out = new StringBuilder();
    out.append(this.doc.header + "\n");
    // stub
  }
}