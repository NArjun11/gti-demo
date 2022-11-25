 package resources;

import java.util.ArrayList;
import java.util.List;

import pojousable.Location;
import pojousable.Pojoserialization;

public class SpecBuilderTest {
	
	
	public Pojoserialization TestDataBuild(String name,String language,String address) {
		

		Pojoserialization ps=new Pojoserialization();
		ps.setAccuracy("50");
		ps.setAddress(address);
		ps.setLanguage(language);
		
		ps.setName(name);
		ps.setPhone_number("(+91) 983 893 3937");
		
		ps.setWebsite("http://google.com");
		
		//for the location we need to cl the Loaction class.
		
		Location l=new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		
		ps.setLocation(l);
		
		//for type we need to create an list ,because it was accepting array list.
		List<String> a=new ArrayList<String>();
		a.add("shoe park");
		a.add("shop");
		ps.setType(a);
		return ps;
	}
	public String deleteplacepayload(String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
		
	}

}
