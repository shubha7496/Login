package com.example.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.Login.APIResponse.APIResponse;
import com.example.Login.Request.LoginRequestDTO;
import com.example.Login.Request.SignUpRequestDTO;
import com.example.Login.Service.LoginService;
import com.example.Login.Util.JwtUtils;

@Controller
public class LoginController {
	
	    @Autowired
	    private LoginService loginService;

	    @Autowired
	    private JwtUtils jwtUtils;

	    @PostMapping("/signup")
	    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO ){

	        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);

	        return ResponseEntity
	                .status(apiResponse.getStatus())
	                .body(apiResponse);
	    }

	    @PostMapping("/login")
	    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO ){

	        APIResponse apiResponse = loginService.login(loginRequestDTO);

	        return ResponseEntity
	                .status(apiResponse.getStatus())
	                .body(apiResponse);
	    }

	    @GetMapping("/privateApi")
	    public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "authorization", defaultValue = "") String auth) throws Exception {
	        APIResponse apiResponse =new APIResponse();

	        jwtUtils.verify(auth);

	        apiResponse.setData("this is private api");
	        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	    }
}
