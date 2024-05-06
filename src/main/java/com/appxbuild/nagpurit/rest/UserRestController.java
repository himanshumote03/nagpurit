package com.appxbuild.nagpurit.rest;

import com.appxbuild.nagpurit.dao.UserDao;
import com.appxbuild.nagpurit.driveService.UserService;
import com.appxbuild.nagpurit.dto.UserDto;
import com.appxbuild.nagpurit.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {

    private UserDao userDao;
    private UserService userService;

    @Autowired
    public UserRestController(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }


    @GetMapping("/user")
    public List<User> findAll(){
        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userDao.findById(id);
        return user.map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/login/{loginId}")
    public ResponseEntity<User> getUserByLoginId(@PathVariable int loginId) {
        Optional<User> user = userDao.findAll()
                .stream()
                .filter(u -> u.getLoginDetails() != null && u.getLoginDetails().getId() == loginId)
                .findFirst();

        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/user")
    public ResponseEntity<String> addUserWithImage(
            @Valid @ModelAttribute UserDto userDto,
            @RequestParam("image") MultipartFile image,
            BindingResult result
    ) throws IOException, GeneralSecurityException {
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }

        if (image.isEmpty()) {
            return new ResponseEntity<>("Image file is required", HttpStatus.BAD_REQUEST);
        }

        try {
            // Upload image to Google Drive and get image URL
            File tempFile = File.createTempFile("user", null);
            image.transferTo(tempFile);
            String imageUrl = userService.uploadImageToDrive(tempFile);

            if (imageUrl == null || imageUrl.isEmpty()) {
                return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Create a new User entity and set user details
            User user = new User();
            user.setId(userDto.getId());
            user.setLoginDetails(userDto.getLoginDetails());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setLanguage(userDto.getLanguage());
            user.setGithubUrl(userDto.getGithubUrl());
            user.setLinkdinUrl(userDto.getLinkdinUrl());
            user.setImage(imageUrl); // Set the image URL
            user.setCreated(LocalDateTime.now());

            // Save user to database
            userDao.save(user);

            return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing image upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<String> updateUserWithImage(
            @Valid @ModelAttribute UserDto userDto,
            @RequestParam(value = "image") MultipartFile image,
            BindingResult result
    ) throws IOException, GeneralSecurityException {

        Integer userId = userDto.getId();
        if (result.hasErrors()) {
            return new ResponseEntity<>("The request body contains validation errors", HttpStatus.BAD_REQUEST);
        }
        // Check if the user ID is provided
        if (userId == null) {
            return new ResponseEntity<>("User ID is required for updating", HttpStatus.BAD_REQUEST);
        }
        // Check if the user exists
        Optional<User> optionalUser = userDao.findById(userDto.getId());
        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalUser.get();

        // Update user details
        existingUser.setLoginDetails(userDto.getLoginDetails());
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setLanguage(userDto.getLanguage());
        existingUser.setGithubUrl(userDto.getGithubUrl());
        existingUser.setLinkdinUrl(userDto.getLinkdinUrl());

        // Handle image update
        if (image != null && !image.isEmpty()) {
            try {
                File tempFile = File.createTempFile("user", null);
                image.transferTo(tempFile);
                String imageUrl = userService.uploadImageToDrive(tempFile);

                if (imageUrl == null || imageUrl.isEmpty()) {
                    return new ResponseEntity<>("Failed to upload image to Google Drive", HttpStatus.INTERNAL_SERVER_ERROR);
                }

                // Update user's image URL
                existingUser.setImage(imageUrl);
            } catch (IOException e) {
                return new ResponseEntity<>("Error processing image upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        // Update modified timestamp
        existingUser.setModified(LocalDateTime.now());

        // Save updated user
        userDao.save(existingUser);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        Optional<User> theUser = userDao.findById(id);
        if (theUser.isPresent()) {
            User user = theUser.get();
            userDao.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
