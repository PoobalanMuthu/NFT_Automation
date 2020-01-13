package com.mop.qa.testbase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APIClient {

	public ArrayList<String> getRequestByUnirest(String apiEndPoint, String extractData, String name) throws UnirestException, IOException, ParseException {

		String jsonText = "";
		String path = "";
		ArrayList<String> jsonList = new ArrayList<String>();
		PageBase pageBase = new PageBase();
		String mhMediaName = pageBase.getAppProperties("ModifyHeader_MediaName");
		String mhMediaValue = pageBase.getAppProperties("ModifyHeader_MediaValue");
		String mhPostmanName = pageBase.getAppProperties("postmanName");
		String mhPostmanValue = pageBase.getAppProperties("postmanToken");

		try {
			HttpResponse<String> response = Unirest.get(apiEndPoint)
					.header(mhMediaName, mhMediaValue)
					.header("cache-control", "no-cache")
					.header(mhPostmanName, mhPostmanValue)
					.asString();

			System.out.println("Status is : " + response.getStatus());
			System.out.println("Response Body is : " + response.getBody());
			jsonText = response.getBody();
			path = createJSONFile(jsonText, name);
			System.out.println("API JSON file created successfully, located at : " + path);
			pageBase.assertTrue("API JSON file created successfully located at : " + path);
			
			if(name.contains("Fixtures"))
				jsonList = parseJSONFileNExtractTeamName(path, extractData, name);
			else
				jsonList = parseJSONFileNExtractFieldName(path, extractData, name);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return jsonList;

	}

	@SuppressWarnings("resource")
	private String createJSONFile(String jsonText, String name) throws IOException {
		String ctPath = System.getProperty("user.dir");
		String filePath = ctPath + "\\jsonAPI\\File_" + name + "_" + getDateTime() + ".json";
		try {
			File fout = new File(filePath);
			FileOutputStream fos = new FileOutputStream(fout);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			osw.write(jsonText);
			osw.close();			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return filePath;
	}

	private String getDateTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");		
		Timestamp ts = new Timestamp(System.currentTimeMillis());		
		return sdf.format(ts);
	}

	@SuppressWarnings("resource")
	public ArrayList<String> parseJSONFileNExtractFieldName(String path, String extractData, String name) throws ParseException, IOException {	

		ArrayList<String> jsonList = new ArrayList<String>();
		String[] titleCommaSep = null;

		try {
			File f = new File(path);
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";

			while ((readLine = b.readLine()) != null) {
				if(readLine.contains("entitlement") && name.contains("MatchReplay")) {
					System.out.println(readLine);
					titleCommaSep = readLine.split("entitlement\" :");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);						
				}
				else if(readLine.contains(extractData) && name.contains("MatchReplay")) {
					System.out.println(readLine);
					titleCommaSep = readLine.split("title\" :");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);
				}
				else if(readLine.contains(extractData)) {
					System.out.println(readLine);
					titleCommaSep = readLine.split(":");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);
				}
				else if(readLine.contains("name") && name.contains("season")) {
					System.out.println(readLine);
					titleCommaSep = readLine.split(":");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);
				}
				else if(readLine.contains(extractData) && name.contains("article")) {
					System.out.println(readLine);
					titleCommaSep = readLine.split("headline\" :");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonList;

	}

	@SuppressWarnings("resource")
	public ArrayList<String> parseJSONFileNExtractTeamName(String path, String extractData, String name) throws ParseException, IOException {	

		ArrayList<String> jsonList = new ArrayList<String>();
		String[] titleCommaSep = null;

		try {
			File f = new File(path);
			BufferedReader b = new BufferedReader(new FileReader(f));
			String readLine = "";

			while ((readLine = b.readLine()) != null) {
				if(readLine.contains(extractData) && name.contains("Fixtures")) {
					System.out.println(readLine);
					titleCommaSep = readLine.split("teamName\" :");
					//titleCommaSep = titleCommaSep[1].split("\\s");
					jsonList.add(titleCommaSep[1]);
				}

			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonList;

	}	
}