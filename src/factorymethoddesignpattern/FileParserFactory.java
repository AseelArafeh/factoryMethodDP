
package factorymethoddesignpattern;


public class FileParserFactory {
    
    public FileParser getFileParser(String fileParserType) {
        
        if(fileParserType == null) 
           return null;
           
        if(fileParserType.equalsIgnoreCase("XML"))            
           return new XMLFileParser();
           
        if(fileParserType.equalsIgnoreCase("JSON"))  
           return new JSONFileParser();

        return null;
        
   }
    
}
