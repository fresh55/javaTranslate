package com.example.demo.Jezik;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

@Controller
public class JezikController {

	private final JezikService jezikService;

	@Autowired
	public JezikController(JezikService jezikService) {
		this.jezikService = jezikService;
	}

	@Autowired
	private Environment environment;

	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public ModelAndView getSporocilo1(@RequestParam(defaultValue = "en") String q)
			throws IOException, InterruptedException, JSONException {

		if (environment.acceptsProfiles(Profiles.of("api"))) {
			ModelAndView mav = new ModelAndView("greeting");
			mav.addObject("message", jezikService.apiPrevood(q));
			return mav;

		} else {
			ModelAndView mav = new ModelAndView("greeting");
			mav.addObject("message", jezikService.getJezikByIme(q));
			return mav;
		}

	}

	

	@RequestMapping(path = "/hello-rest", method = RequestMethod.GET)
	@ResponseBody
	public String getSporocilo(@RequestParam(defaultValue = "en") String q)
			throws IOException, InterruptedException, JSONException {
		// spremeni string v string z veliko zacetnico

		if (environment.acceptsProfiles(Profiles.of("api"))) {
			Jezik en = jezikService.apiPrevood(q);
			System.out.println(en.getSporocilo());
			return en.getSporocilo();
		} else {
		}
		Jezik en = jezikService.getJezikByIme(q);
		System.out.println(en.getSporocilo());
		return en.getSporocilo();

	}

	@RequestMapping(path = "/secure/hello", method = RequestMethod.GET)
	public String pojdiNaSecure() {

		return "secure";
	}

	@RequestMapping(path = "/ustvariJezik", method = RequestMethod.GET)
	public String ustvari(Model model) {
		model.addAttribute("objekt", new Jezik());
		return "ustvariJezik";
	}

	@RequestMapping(path = "/ustvariJezik", method = RequestMethod.POST)
	public void greetingSubmit(@ModelAttribute("objekt") Jezik noviJ) {
		jezikService.addNewJezik(noviJ);
	}

	@RequestMapping(path = "/API", method = RequestMethod.GET)
	@ResponseBody
	public void APIt() throws IOException, InterruptedException, JSONException {
		jezikService.apiPrevood("haw");

	}

}
