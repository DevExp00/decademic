package kz.djdegens.academic.controllers;

import kz.djdegens.academic.dtos.ApplicationDto;
import kz.djdegens.academic.dtos.ResultDto;
import kz.djdegens.academic.dtos.UserDto;
import kz.djdegens.academic.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/check-user")
    public ResponseEntity<ApplicationDto> checkStudent(@RequestBody UserDto userDto){
        try{
            userService.checkUser(userDto);
            return ResponseEntity.ok().body(ApplicationDto.builder()
                            .result(ResultDto.builder()
                                    .message("User checked in successfully")
                                    .status("200")
                                    .build())
                    .build());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                            .result(ResultDto.builder()
                                    .message(e.getLocalizedMessage())
                                    .status("500")
                                    .build())
                    .build());
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<ApplicationDto> getUser(@PathVariable Long userId){
        try{
            return ResponseEntity.ok().body(userService.getUser(userId));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }

    @GetMapping("/user-info")
    public ResponseEntity<ApplicationDto> getUser(@RequestParam Long telegramId,
                                                  @RequestParam Boolean isInstructor){
        try{
            return ResponseEntity.ok().body(userService.getUserByTelegramIdAndIsInstructor(telegramId,isInstructor));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ApplicationDto.builder()
                    .result(ResultDto.builder()
                            .message(e.getLocalizedMessage())
                            .status("500")
                            .build())
                    .build());
        }
    }
}
