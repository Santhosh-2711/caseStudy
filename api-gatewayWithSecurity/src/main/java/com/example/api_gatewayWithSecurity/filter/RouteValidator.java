package com.example.api_gatewayWithSecurity.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import com.example.api_gatewayWithSecurity.service.JwtService;
 
@Component
public class RouteValidator {
	@Autowired
	JwtService jwtservice;

	public static final List<String> openApiEndpoints=
			List.of(
					"/auth/signup",
					"/auth/authenticate",
					
					"/eureka"
					);
	public static final List<String> AdminApiEndpoints=
			List.of(
//					"auth/admin/**",
//					"/auth/admin/washers",
//					"auth/admin/customers",
//					"auth/washers/**",
//					"auth/admin/customers/**",
//					"/order/customer/**",
//					"/order/washer/**",
//					"/order/admin/**",
//					"/feedback/admin/**",
//					"/feedback/customer/**",
//					"/auth/washers",
//					"/auth/customers"
//					"/auth/admin/**",
//					"/order/**",
//					"/feedback/**"
					"/auth/.*",
					"/order/.*",
					"/feedaback/.*"
					);
	public static final List<String> WasherApiEndpoints=
			List.of(
					
					"/order/washer/.*",
					"/auth/washers/.*"
//					"/auth/washers/**"
					);
	public static final List<String> UserApiEndpoints=
			List.of(
					
					"/order/customer/.*",
					"/feedback/customer/.*",
					"/auth/customers/.*"
//					"/auth/customers"
					);
	
	
	public Predicate<ServerHttpRequest> isSecured=
			request -> openApiEndpoints.stream()
			.noneMatch(uri->request.getURI().getPath().contains(uri));
			
			

	public Predicate<ServerHttpRequest> isAdminUri=
					(request) -> AdminApiEndpoints.stream()
					.anyMatch(uri->request.getURI().getPath().matches(uri));
	public Predicate<ServerHttpRequest> isUserUri=
							request -> UserApiEndpoints.stream()
							.anyMatch(uri->request.getURI().getPath().matches(uri));
	
//    public Predicate<ServerHttpRequest> isWahserUri=
//									request -> WasherApiEndpoints.stream()
//									.anyMatch(uri->request.getURI().getPath().contains(uri));
							
	public Predicate<ServerHttpRequest> isWasherUri =
							 request -> WasherApiEndpoints.stream()
							.anyMatch(uri -> request.getURI().getPath().matches(uri));
	public boolean ValidateUsers(String token, ServerHttpRequest request) {

String role = jwtservice.extractRole(token);
//    if (role.startsWith("ROLE_")) {
//        role = role.substring(5); // Remove "ROLE_" prefix if present
//    }
//System.out.println(role);

System.out.println("Request Path: " + request.getURI().getPath());
System.out.println("Extracted Role: " + role);
System.out.println("Is Customer URI: " + isUserUri.test(request));

		if(isAdminUri.test(request) && role.equals("ADMIN")) {
			return true;
		}
		if(isUserUri.test(request) && role.equals("CUSTOMER")) {
			return true;
		}
		if(isWasherUri.test(request) && role.equals("WASHER")) {
			return true;
		}
		return false;
	}
							
	
			
}
