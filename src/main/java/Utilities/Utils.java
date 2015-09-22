package Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
	
	
	public String getDate(Integer daysFromNow) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, daysFromNow);
		String date = sdf.format(c.getTime());
		return date;
	}
	
	public List<String> getInventoryObjects() {
		List<String> objects = new ArrayList<String>();
		objects.add("xyz--xyz");
		return objects;
	}
	
	public List<String> getHotelMetadataObjects() {
		List<String> objects = new ArrayList<String>();
		objects.add("xyz--xyz");
		return objects;
	}

}
