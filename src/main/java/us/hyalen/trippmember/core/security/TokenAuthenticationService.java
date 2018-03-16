package us.hyalen.trippmember.core.security;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	private static long EXPIRATION_TIME;
	private static String SECRET;
	private static String TOKEN_PREFIX;
	private static String HEADER_STRING;
	
	@Value("${security.jwt.expiration-time}")
	public void setExpirationTime(String expirationTime) {
		EXPIRATION_TIME = Long.valueOf(expirationTime);
	}
	
	@Value("${security.jwt.secret}")
	public void setSecret(String secret) {
		SECRET = secret;
	}
	
	@Value("${security.jwt.token-prefix}")
	public void setTokenPrefix(String tokenPrefix) {
		TOKEN_PREFIX = tokenPrefix;
	}
	
	@Value("${security.jwt.header-string}")
	public void setHeaderString(String headerString) {
		HEADER_STRING = headerString;
	}
	
	static void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder()
			.setSubject(username)
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SECRET)
			.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			String user = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(
					user, 
					null, 
					Arrays.asList(new SimpleGrantedAuthority(Authority.getAuthority(user))					)
				);
			}
		}
		
		return null;
	}
}