
package factorymethoddesignpattern;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
            
        Scanner scn = new Scanner(System.in);
        String originPath = "";
        String msg = "";
        FileParserFactory factory = new FileParserFactory();
        char choice;
        
        do {
            // cls 

            System.out.print(  "***************************************************************************\n"
                            +  "**********               Welcome to my File Parser               **********\n"
                            +  "**********   It helps you to parse XML, JSON files to TXT file   **********\n"
                            +  "***************************************************************************\n"
                            +  "\n\n"
                            +  "1. Parse a file \n"
                            +  "2. Exit \n"
                            +  "\n\n"
                            +  " Enter your Choice : "
                            );
            
            choice = scn.next().charAt(0);
            switch (choice) {

                case '1': {
                    
                    System.out.print(" Enter a full path for file to parse : ");
                    originPath = scn.next(); 
                    String extension = "";
                    if(originPath.lastIndexOf(".") != -1)
                        extension = originPath.substring(originPath.lastIndexOf(".")+1, originPath.length());
                    //System.out.println(extension);  
                    FileParser file = factory.getFileParser(extension);
                    
                    if (file != null) {
                        msg = file.parseFile(originPath);
                    } 
                    else {
                        msg = " Error in extension, you missed it or wrote something neither XML nor JSON ";
                    }
                    
                    System.out.println("\n" + msg + "\n");
                    break;
                    
                }
                case '2': {
                    
                    System.exit(0);
                    break;
                    
                }
                default: {
                    
                    System.out.print(" The entered value is unrecognized! \n");
                    break;
                    
                }
            }
                             
        } while (true);   
        
    }
    
}
