
package factorymethoddesignpattern;

import java.io.IOException;


public interface FileParser {
    
    String parseFile(String originPath) throws IOException;
    // parseFile method: takes a file path, and returns a result message for user.
    
}
