// XML Formatter module
// Jacob Peck

import java.util.*;

public class XMLFormatter {
  private XMLDocument doc = new XMLDocument();
  private Map<String,XMLTag> lookuptable = new HashMap<String,XMLTag>();

  public void newTag(String name, String value) {
    XMLTag temp = new XMLTag(name, value);
    this.lookuptable.put(name, temp);
    this.doc.addTag(temp);
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
    List<XMLTag> roottags = this.doc.getTags();
    for(XMLTag t : roottags) {
      out.append(this.processTag(depth, t));
    }
    return out.toString();
  }
  
  private String processTag(int depth, XMLTag tag) {
    StringBuilder out = new StringBuilder();
    String padding = this.spacePadding(depth);
    out.append(padding + tag.openTag());
    if(tag.numTags() != 0) {
      out.append("\n");
      for(XMLTag t : tag.nestedTags()) {
        out.append(this.processTag(depth + 1, t));
      }
      out.append(padding);
    } else {
      out.append(tag.getValue());
    }
    out.append(tag.closeTag() + "\n");
    return out.toString();
  }
  
  private String spacePadding(int depth) {
    StringBuilder out = new StringBuilder();
    for(int i = 0; i < depth; i++) {
      out.append("  ");
    }
    return out.toString();
  }
}



/*
class XMLTest {

 // this test should create a document that looks like this:
 //
 // <?xml version="1.0" encoding="ISO-8859-1"?>
 // <note>
 //   <from>Me</from>
 //   <to>You</to>
 //   <body>
 //     <subject>hello there!</subject>
 //     <content>What's up?</content>
 //   </body>
 // </note>


  public static void main(String[] args) {
    XMLFormatter format = new XMLFormatter();
    format.newTag("note");
    format.addNestedTag("note", "from", "Me");
    format.addNestedTag("note", "to", "You");
    format.addNestedTag("note", "body");
    format.addNestedTag("body", "subject", "hello there!");
    format.addNestedTag("body", "content", "What's up?");
    
    System.out.println(format);
  }
}
*/
