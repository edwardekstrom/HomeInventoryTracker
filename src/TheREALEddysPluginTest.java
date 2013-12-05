import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Stack;
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


public class TheREALEddysPluginTest {

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
	public void testEddysPlugin() {
		
		
		Stack<String> ss = new Stack<String>();
		ss.push("TheREALEddysPlugin");
		
		TheRealEddysPlugin trep = new TheRealEddysPlugin(ss);
		
		assertFalse(trep.getProductDescription("5010029020519").equals(""));
	}

}
