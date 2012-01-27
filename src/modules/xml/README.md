# XML Formatter module

Jacob Peck

## Usage

This module is comprised of three classes, `XMLFormatter`, `XMLDocument`, and `XMLTag`.
The user will only ever use `XMLFormatter`.

Create a new instance of `XMLFormatter`, and add tags to it, then call the `toString()`
method to create a nicely-indented and formatted string of the document's contents.

## Example code

    ...
    XMLFormatter xml = new XMLFormatter();
    xml.newTag("note") // create a new root tag, which will hold child tags
    xml.addNestedTag("note", "from", "Me"); // add a child tag under "note", called "from" with the value "Me"
    xml.addNestedTag("note", "to", "You"); // same deal
    xml.addNestedTag("note", "body"); // no value here... will hold more nested tags
    xml.addNestedTag("body", "subject", "hi there."); // add a child tag under "body"
    xml.addNestedTag("body", "contents", "what's up?"); // again, under "body"
    
    System.out.println(format); // toString is called implicitly
    ...
    
This will output:

    <?xml version="1.0" encoding="ISO-8859-1"?>
    <note>
      <from>Me</from>
      <to>You</to>
      <body>
        <subject>hi there</subject>
        <content>what's up?</content>
      </body>
    </note>
