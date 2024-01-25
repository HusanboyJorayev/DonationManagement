package com.example.donationmanagement.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<UserDto> get(@RequestParam Integer id) {
        return this.userService.get(id);
    }


    @GetMapping("/getWithCarts")
    public ResponseEntity<UserDto> getWithCarts(@RequestParam Integer id) {
        return this.userService.getWithCarts(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestParam Integer id, @RequestBody UserDto dto) {
        return this.userService.update(id, dto);
    }

    @GetMapping("/getAllUsers")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
