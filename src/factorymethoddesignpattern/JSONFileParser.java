
package factorymethoddesignpattern;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;



public class JSONFileParser implements FileParser {
    
    
    
    public String parseFile(String originPath) throws IOException{
       
        String msg = "";
       
        try {
            
            String JSONcontent = new String((Files.readAllBytes(Paths.get(originPath))));
            JSONObject jsonObject = new JSONObject(JSONcontent);

            String TXTcontent = "";
            String newestPath = originPath.substring(0,originPath.lastIndexOf(".")).concat(".txt");
            FileWriter file = new FileWriter(newestPath);
            
            Map value = toMap(jsonObject) ;

            Iterator<Map.Entry> itr = value.entrySet().iterator(); 
            while (itr.hasNext()) { 
                Map.Entry pair = itr.next();
                TXTcontent +=   (
                                    "Type: "+ applyMyTxtFormat(pair.getKey().toString())
                                   +"\n------------------\n" 
                                   + applyMyTxtFormat(pair.getValue().toString())
                        
                                ); 
                
            } 
            
            file.write(TXTcontent);
            file.close();
            
            msg = "Parsing DONE succssefully :)";
            
        }  catch(FileNotFoundException e){
            msg = "There is no file with this path";
        } catch (JSONException ex) {
            msg = "File format was not JSON";
        } catch (Exception ex) {
            msg = "Some error occurred. Please try again";
        } 

        return msg;
        
    }
    
    
    
    public Map<String, Object> toMap(JSONObject jsonobj)  throws JSONException {
        
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> keys = jsonobj.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Object value = jsonobj.get(key);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }   
            map.put(key, value);
        }  
        
        return map;
    }

    public List<Object> toList(JSONArray array) throws JSONException {
        
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }   
        
        return list;
    }
    
    public String applyMyTxtFormat(String pair) throws JSONException {
        
        pair =  pair.replaceAll("\\{" , ""  )
                    .replaceAll("\\=" , ": ")
                    .replaceAll("\\, ", "\n")
                    .replaceAll("\\}" , ""  );
        String txt = "";
        txt += pair.substring(0, 1).toUpperCase();
        for(int i=1;i<pair.length()-1;i++) {
            
            if (pair.valueOf(pair.substring(i, i+1)).matches("\n")) {
                txt += "\n";
                txt += pair.substring(i+1, i+2).toUpperCase();
                i++;
            }    
            else { 
                txt += pair.substring(i, i+1);
                
            }    
        }
        txt +=pair.substring(pair.length()-1, pair.length());
                          
        return txt;
    }
    
    
}
