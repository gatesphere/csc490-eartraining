// XML Tag
// Jacob Peck
// no attributes...

import java.util.*;

public class XMLTag {
  private String name;
  private String value;
  private List<XMLTag> tags = new ArrayList<XMLTag>();
  
  // constructors
  public XMLTag(String n, String v) {
    this.name = n;
    this.value = v;
  }
  public XMLTag(String n) {
    this.name = n;
  }
  
  // methods
  public void addTag(XMLTag newtag) {
    this.tags.add(newtag);
  }
  
  public List<XMLTag> nestedTags() {
    return this.tags;
  }
  
  public integer numTags() {
    return this.tags.size();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String openTag() {
    return "<" + this.name + ">";
  }
  
  public String getValue() {
    return this.value;
  }
  
  public String closeTag() {
    return "</" + this.name + ">";
  }
}