package com.example.carwash.controller;

import com.example.carwash.entity.Customer;
import com.example.carwash.entity.User;
import com.example.carwash.entity.Washer;
import com.example.carwash.service.JwtService;
import com.example.carwash.service.impl.UserServiceImpl;
import com.example.carwash.entity.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserServiceImpl serviceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;


  
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    //  Get All Users
    @GetMapping("admin/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //  Get All Customers
    @GetMapping("admin/customers")
    public List<Customer> getAllCustomers() {
        return userService.getAllCustomers();
    }

    //  Get All Washers
    @GetMapping("admin/washers")
    public List<Washer> getAllWashers() {
        return userService.getAllWashers();
    }

    //  Update Customer Details
    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        return userService.updateCustomer(id, customerDetails);
    }
//    public Customer updateC(@PathVariable Long id,@RequestBody Customer customer) {
//		Customer change =  userService.getCOne(id);
//		
//		change.setUsername(customer.getUsername());
//		change.setPhoneNumber(customer.getPhoneNumber());
//		change.setEmail(customer.getEmail());
//		change.setAddress(customer.getAddress());
//		change.setAge(customer.getAge());
//		return userService.registerUser(change);

    // ✅ Update Washer Details
    @PutMapping("/washers/{id}")
    public Washer updateWasher(@PathVariable Long id, @RequestBody Washer washerDetails) {
    	
        return userService.updateWasher(id, washerDetails);
    }

    @GetMapping("admin/washers/{id}")
	public Washer getW(@PathVariable Long id){
		return userService.getWOne(id);
	}
    
    @GetMapping("admin/customers/{id}")
   	public Customer getC(@PathVariable Long id){
   		return userService.getCOne(id);
   	}
    
    
    
    // Delete Customer
    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return "Customer deleted successfully!";
    }

    //  Delete Washer
    @DeleteMapping("/washers/{id}")
    public String deleteWasher(@PathVariable Long id) {
        userService.deleteWasher(id);
        return "Washer deleted successfully!";
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // Fetch user details from database
        	User user = serviceImpl.getUserByUsername(authRequest.getUsername());
        	System.out.println("USERNAME :"+user.getUsername());
        	
            // Generate JWT with role
            return jwtService.generateToken(authRequest.getUsername(), user.getRole().toString());
        } else {
            throw new UsernameNotFoundException("Invalid user request! Authentication failed.");
        }
    }
}
