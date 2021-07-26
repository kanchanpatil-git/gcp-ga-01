package googleanalyticsapi;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.EntityUserLinks;

public class ListProfile {
	 private static final String APPLICATION_NAME = "googleanalyticsapi";
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  private static final String KEY_FILE_LOCATION = "noble-cubist-320605-cf4d8eddb3f9.json";
	  public static void main(String[] args) {
		    try {
		    	Analytics analytics =initializeAnalytic();
		    	ListProfile(analytics);
		    } catch (Exception e) {
			      e.printStackTrace();
			    }
	  }
		private static void ListProfile(Analytics analytics) throws IOException {
			try {
				  EntityUserLinks profileLinks = analytics.management().
				      profileUserLinks().list("123456", "UA-123456-1", "7654321").execute();
				} catch (GoogleJsonResponseException e) {
				  System.err.println("There was a service error: "
				      + e.getDetails().getCode() + " : "
				      + e.getDetails().getMessage());
				}
		
		}
		private static Analytics initializeAnalytic() throws GeneralSecurityException, IOException {

		    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		    GoogleCredential credential = GoogleCredential
		        .fromStream(new FileInputStream(KEY_FILE_LOCATION))
		        .createScoped(AnalyticsScopes.all());

		    // Construct the Analytics service object.
		    return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
		        .setApplicationName(APPLICATION_NAME).build();
		  }

}
