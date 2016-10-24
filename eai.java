import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

//Isaac Tessler 
//inputs: integer N
//outputs: The top N sites by traffic and unique users, separated by newlines, as reported by Alexa.com


public class eai {

	
	
	
	public static void main(String[] args) throws IOException {
		
		//get target number of domain names from user
		Scanner s = new Scanner(System.in);
		int N = Integer.parseInt(s.nextLine());
		
		//build URL starting at page 0
		String urlBase = "http://www.alexa.com/topsites/global;";
		int pageNumber = 0;
		
		
		//loop until counter is target count
		int counter = 0;
		while(true){
			
			//make url based on current page
			URL url = new URL(urlBase+pageNumber);
			//read it as a stream of HTML
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String inputLine;
	        while ((inputLine = in.readLine()) != null){
	        	if (counter>=N){
	        		break;
	        	}
	        	//search for domain names by locating "siteinfo" string and cutting around it
	        	if(inputLine.contains("/siteinfo/")){
	        	
	        		int start = inputLine.indexOf("/siteinfo/");
	        		int end = inputLine.substring(start).indexOf("\"");
	        		
	        		//print the domain and increment count
	        		if(end!=-1){
	        			System.out.println(inputLine.substring(start+10,start+end));
	        			counter++;
	        		}
	        	}
	        }
	        in.close();
	        
	        //if count is reached, done
			if(counter>=N){
				break;
			}
			
			//if over 500, stop at 500 (there are no more in the given dataset)
			if(counter>=500){
				break;
			}
	        
			//if you need to collect more domains, increment the page number and repeat.
			pageNumber++;
			
		}
		
		
		

	}

	
	
	
}
