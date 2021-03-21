package com.spring.urlshorten.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.urlshorten.model.ShortenUrl;

@Controller
public class UrlShortenController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String urlShorten() {
		return "index";
	}
	
	private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();
	
	@RequestMapping(value="/shortenurl", method=RequestMethod.POST)
	public String getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
		String randomChar = getRandomChars();
		setShortUrl(randomChar, shortenUrl);
		
		session.setAttribute("shortUrl",shortenUrl.getShort_url());
		
		return "index";
	}
	
	@RequestMapping(value="/spring/{randomstring}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
		response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
	}

	private void setShortUrl(String randomChar, ShortenUrl shortenUrl) throws MalformedURLException {
		 shortenUrl.setShort_url("https://url.shrtn/spring/"+randomChar);
		 shortenUrlList.put(randomChar, shortenUrl);
	}

	private String getRandomChars() {
		String randomStr = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 7; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomStr;
	}
	
}
