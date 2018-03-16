package us.hyalen.trippmember.core.security;

import java.util.HashMap;
import java.util.Map;

public class Authority {
	static Map<String, String> map;
	
	static {
		map = new HashMap<>();
		
		map.put("default", "DEFAULT_USER");
		map.put("super", "SUPER_USER");
		map.put("admin", "ADMIN_USER");
	}
	
	public static String getAuthority(String userName) {
		return map.get(userName);
	}
}
