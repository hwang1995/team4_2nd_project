package com.team4.webapp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/image")
	@ResponseBody
	public String getImage(String path, HttpServletResponse response) {
		String filePath = System.getProperty("user.home") + "/images" + path;
		if(path.equals("undefined")) {
			return null;
		}
		try {
			response.setHeader("Content-Disposition", "attachment;");
			response.setContentType("image/jpeg");
			InputStream is = new FileInputStream(filePath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);
			is.close();
			os.flush();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}

}
