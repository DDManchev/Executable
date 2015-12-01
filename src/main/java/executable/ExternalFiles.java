package executable;
import java.io.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class ExternalFiles {
	
	
	public void RunTheApp(String[] TheArgs) throws IOException{
		ExternalFiles exFiles = new ExternalFiles();
	 	if(new File(TheArgs[0]).isFile() == false){
	 	exFiles.createandWritetoJSON("MySSID", -10, TheArgs[0]);
	 	exFiles.createandWritetoJSON("Appolica", -15, TheArgs[0]);
	 	exFiles.createandWritetoJSON("MySSID", -1, TheArgs[0]);
	 	exFiles.createandWritetoJSON("Appolica", -5, TheArgs[0]);
	 	exFiles.createandWritetoJSON("Appolica", -50, TheArgs[0]);
	 	exFiles.readJSON(TheArgs[0],TheArgs[1]);
	 	}
	 	else {
		exFiles.readJSON(TheArgs[0],TheArgs[1]);
		}
	 	}
	
	
	public void createandWritetoJSON(String name, Integer value, String pathName){
		try{
	if(new File(pathName).length() == 0){
		JSONArray theArr = new JSONArray();
		JSONObject jobj = new JSONObject();
		jobj.put("stationName", name);
		jobj.put("power", value);
		File thefile = new File(pathName);
		FileWriter fr = new FileWriter(thefile);
		theArr.add(jobj);
		theArr.writeJSONString(fr);
		System.out.println(jobj);
		fr.close();
	}
	else{
	JSONParser parser = new JSONParser();
	FileReader fileReader = new FileReader(pathName);
	Object obj = parser.parse(fileReader);
	JSONArray jArray = (JSONArray) obj;
	JSONObject jobj = new JSONObject();
	jobj.put("stationName", name);
	jobj.put("power", value);
	File thefile = new File(pathName);
	FileWriter fr = new FileWriter(thefile);
	jArray.add(jobj);
	jArray.writeJSONString(fr);
	System.out.println(jobj);
	fr.close();
	}
 }
		catch(Exception exception){
		exception.printStackTrace();
		}
	
	}
	
	
	 public void readJSON(String pathName, String outPutFileName) {
		int Appolicasum=0,MySSIDSum=0,counterAppolica = 0,counterMySSID = 0;
		Long AppoValPow = 0L ,MySSIDValPow = 0L;
		
		try{
		JSONParser parser = new JSONParser();
		FileReader fReader = new FileReader(pathName);
		Object obj = parser.parse(fReader);
		JSONObject test = new JSONObject();
		JSONArray jArry = (JSONArray) obj;
		
		for(int i=0;i<jArry.size();i++){
		test =(JSONObject) jArry.get(i);
		if(test.containsValue("Appolica"))
		 {
		AppoValPow = (Long) test.get("power");
		Appolicasum += AppoValPow.intValue();
		counterAppolica++;
		}
		 else if(test.containsValue("MySSID")){
		MySSIDValPow = (Long) test.get("power");
		MySSIDSum += MySSIDValPow.intValue();
		counterMySSID++;
		}
		}
		System.out.println(Appolicasum);
		System.out.println(MySSIDSum);
		double avgAppolica = (double)Appolicasum / (double)counterAppolica;
    	double avgMySSID = (double)MySSIDSum / (double)counterMySSID;
    	JSONObject theOutputApp = new JSONObject();
    	JSONObject theOutputMySSID = new JSONObject();
    	theOutputApp.put("stationName", "MySSID");
    	theOutputApp.put("power", avgMySSID);
    	theOutputMySSID.put("stationName", "Appolica");
    	theOutputMySSID.put("power", avgAppolica);
    	JSONArray outputarr = new JSONArray();
    	outputarr.add(theOutputMySSID);
    	outputarr.add(theOutputApp);
    	File outputsave = new File(outPutFileName);
    	FileWriter fr = new FileWriter(outputsave);
    	outputarr.writeJSONString(fr);
    	fr.close();
		}
		catch(Exception ex){
		System.out.println("The file was not found!");	
		ex.printStackTrace();
		}
	} 
	
}
