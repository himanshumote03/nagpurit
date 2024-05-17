package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.driveService.UserService;
import com.appxbuild.nagpurit.entity.LoginDetails;
import com.appxbuild.nagpurit.entity.User;
import com.appxbuild.nagpurit.security.AESEncryption;
import com.appxbuild.nagpurit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginDetailsRestController {
    private LoginDetailsService loginDetailsService;

    @Autowired
    public LoginDetailsRestController(LoginDetailsService loginDetailsService) {
        this.loginDetailsService = loginDetailsService;
    }

    @GetMapping("/loginDetails")
    public List<LoginDetails> findAll(){
        return loginDetailsService.findAll();
    }


    @GetMapping("/loginDetails/{id}")
    public LoginDetails getLoginDetail(@PathVariable int id){
        LoginDetails theLoginDetails = loginDetailsService.findById(id);
        if(theLoginDetails==null){
            throw new RuntimeException("Login Detail id is not found " + id);
        }
        return theLoginDetails;
    }

    @PostMapping("/signup")
    public LoginDetails addLoginDetails(@RequestBody LoginDetails loginDetails) {

        // Encryption
        String encryptedPwd = AESEncryption.encrypt(loginDetails.getPassword());
//        System.out.println("Encrypted Password : " + encryptedPwd);
        loginDetails.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        loginDetails.setCreated(dt);
        loginDetails.setModified(null);
        loginDetails.setPassword(encryptedPwd);
        LoginDetails newLoginDetails = loginDetailsService.save(loginDetails);

        return newLoginDetails;
    }



    @PutMapping("/loginDetails")
    public LoginDetails updateLoginDetails(@RequestBody LoginDetails theLoginDetails){
        // Retrieve the existing LoginDetails object from the database
        LoginDetails existingLoginDetails = loginDetailsService.findById(theLoginDetails.getId());

        // Check if the existing LoginDetails object exists
        if (existingLoginDetails == null) {
            throw new RuntimeException("Login Detail with id " + theLoginDetails.getId() + " not found");
        }

        // Set the created field from the existing LoginDetails object
        theLoginDetails.setCreated(existingLoginDetails.getCreated());

        // Encrypt the password
        String encryptedPwd = AESEncryption.encrypt(theLoginDetails.getPassword());

        // Set the modified field and password
        LocalDateTime dt = LocalDateTime.now();
        theLoginDetails.setModified(dt);
        theLoginDetails.setPassword(encryptedPwd);

        // Save the updated LoginDetails object
        LoginDetails updatedLoginDetails = loginDetailsService.save(theLoginDetails);

        return updatedLoginDetails;
    }


    @DeleteMapping("/login/delete")
    public String delete(@RequestBody LoginDetails loginDetails) {
        LoginDetails user = loginDetailsService.findByEmail(loginDetails.getEmail());
        if (user != null) {
            String decryptedPwd = AESEncryption.decrypt(user.getPassword());
            if (decryptedPwd.equals(loginDetails.getPassword())) {
                user.setName(null);
                user.setEmail(null);
                user.setPassword(null);
                loginDetailsService.save(user);
                return "Account Deleted";
            }
        }
        return "Invalid password.";
    }
//    public String deleteLoginDetail(@PathVariable int id){
//        LoginDetails theLoginDetails = loginDetailsService.findById(id);
//        if (theLoginDetails == null) {
//            return "Login id not found";
//        }
//        loginDetailsService.save(theLoginDetails);
////        loginDetailsService.deleteById(id);
//        return "Deleted " +id;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) {
        LoginDetails user = loginDetailsService.findByEmail(loginDetails.getEmail());
//        System.out.println("\n Existing details: " + user.getEmail() + " : " + user.getPassword());
//        System.out.println("\n email: " + loginDetails.getEmail() + "\n pass:  " + loginDetails.getPassword());
        if (user != null) {
            String decryptedPwd = AESEncryption.decrypt(user.getPassword());
//            System.out.println("Decrypted Pass: " + decryptedPwd);
            if (decryptedPwd.equals(loginDetails.getPassword())) {
                System.out.println(user);
                System.out.println(user.getName());
                return ResponseEntity.ok(user);
            }
        }
        // return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        String errorMessage = "Invalid email or password.";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
}
