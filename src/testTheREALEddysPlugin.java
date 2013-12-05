import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Barcode;
import model.Product;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import plugin_package.TheRealEddysPlugin;


public class testTheREALEddysPlugin {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Barcode barcode = new Barcode();
		barcode.setBarcode("5010029020519");

		
		URL url;
		String getProductURL = "http://www.searchupc.com/handlers/upcsearch.ashx?";
		HttpURLConnection connection = null;
		
		try {
			
			String urlParameters =
			        "request_type=" + 
			        URLEncoder.encode("1", "UTF-8") +
			        
			        "&access_token=" + 
			        URLEncoder.encode("C9766A5F-8C15-4B25-BF85-1A8FE7D1AA32", "UTF-8") +
			        
			        "&upc=" + 
			        barcode.getBarcode();
			
			
			
		      //Create connection
		      url = new URL(getProductURL);
		      connection = (HttpURLConnection)url.openConnection();
		      connection.setRequestMethod("GET");
		      connection.setRequestProperty("Content-Type", 
		           "application/x-www-form-urlencoded");
					
		      connection.setRequestProperty("Content-Length", "" + 
		               Integer.toString(urlParameters.getBytes().length));
		      connection.setRequestProperty("Content-Language", "en-US");  
					
		      connection.setUseCaches (false);
		      connection.setDoInput(true);
		      connection.setDoOutput(true);

		      //Send request
		      DataOutputStream wr = new DataOutputStream (
		                  connection.getOutputStream ());
		      wr.writeBytes (urlParameters);
		      wr.flush ();
		      wr.close ();

		      //Get Response	
		      InputStream is = connection.getInputStream();
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		      String line;
		      StringBuffer response = new StringBuffer();
		      
		      int i = 0;
		      while((line = rd.readLine()) != null) {
		    	if(i == 1){
		        response.append(line);
		        response.append('\r');
		    	}
		    	i++;
		      }
		      int firstOccurance = response.toString().indexOf('"');
		      int secondOccurance = response.toString().indexOf('"', response.toString().indexOf('"') + 1);
		      rd.close();
//		      System.out.println(response);
//		      System.out.println(firstOccurance);
//		      System.out.println(secondOccurance);
//		      System.out.println(response.substring(firstOccurance + 1, secondOccurance));
		      
		      String productDescription = response.substring(firstOccurance + 1, secondOccurance);
		      
		      
		      assertTrue(productDescription.equals("Alpen Original Muesli 750g"));
		      

		    } catch (Exception e) {

		      e.printStackTrace();
		      

		    } finally {

		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
	}

}
