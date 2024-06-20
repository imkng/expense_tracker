package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.AuthModel;
import in.krishna.expensetrackerapi.entity.JwtResponse;
import in.krishna.expensetrackerapi.entity.User;
import in.krishna.expensetrackerapi.entity.UserModel;
import in.krishna.expensetrackerapi.security.CustomUserDetailsService;
import in.krishna.expensetrackerapi.service.UserService;
import in.krishna.expensetrackerapi.utils.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @Operation(summary = "You will get JWT token as a Response for Auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Bearer Token will be added in Authorize. Format: Bearer {Your_Token}",
                    content = {@Content(mediaType = "application/json")}),

            @ApiResponse(responseCode = "404",
                    description = "Not Available",
                    content = @Content)
    })
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {
        // Authenticate the user
        authenticate(authModel.getEmail(),authModel.getPassword());
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());
        User user = userService.findByEmail(authModel.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        //we need to generate the Jwt token
        return new ResponseEntity<JwtResponse>(new JwtResponse(token, user.getId()),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,
                    password));
        }catch (DisabledException e){
            throw new Exception("User Disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credential");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }
}
