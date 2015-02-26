package com.dehavenmedia.interstella;

public interface Composite {

boolean addChild(Composite c); // Returns true if operation completed successfully.
boolean removeChild(Composite c); // Returns true if operation completed successfully.

int childCount(); // Returns the composite's number of children.
Composite getParent(); // Returns parent composite of this composite.

}
