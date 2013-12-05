package plugin_package;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Stack;

import model.Barcode;
import model.Product;


public class TheRealEddysPlugin extends PluginSuper{

	public TheRealEddysPlugin(Stack<String> names) {
		super(names);
	}

	@Override
	public Product getProduct(Barcode barcode) {
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
		      while((line = rd.readLine()) != null) {
		        response.append(line);
		        response.append('\r');
		      }
		      rd.close();
		      return null;

		    } catch (Exception e) {

		      e.printStackTrace();
		      return null;

		    } finally {

		      if(connection != null) {
		        connection.disconnect(); 
		      }
		    }
	}

}
