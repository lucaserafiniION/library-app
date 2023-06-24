package com.example.controller;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController implements ErrorController {

	@GetMapping("/")
	public String home() {
		return "redirect:/books";
	}

	@RequestMapping("/error")
	public String error(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpStatus errorCode = getStatus(request);

		if (errorCode != null) {
			switch (errorCode.value()) {
			case 400:
				redirectAttributes.addFlashAttribute("errorMessage", "Bad Request");
				break;
			case 403:
				redirectAttributes.addFlashAttribute("errorMessage", "Access denied");
				break;
			case 404:
				redirectAttributes.addFlashAttribute("errorMessage", "Page not found");
				break;
			case 405:
				redirectAttributes.addFlashAttribute("errorMessage", "Method not allowed");
				break;
			case 415:
				redirectAttributes.addFlashAttribute("errorMessage", "Unsupported media type");
				break;
			case 500:
				redirectAttributes.addFlashAttribute("errorMessage", "Internal error");
				break;
			default:
				redirectAttributes.addFlashAttribute("errorMessage", "Unexpected error");
			}
		}

		if (request != null) {
			String referer = request.getHeader("Referer");

			if (referer != null) {
				try {
					URL url = new URL(referer);
					String path = url.getPath();
					return "redirect:" + path;
				} catch (MalformedURLException m) {
					// nothing to do
				}
			} else {
				// nothing to do
			}
		}

		return "redirect:/books";
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
