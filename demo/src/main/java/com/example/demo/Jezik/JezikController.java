package com.example.demo.Jezik;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping("/hello")
	public ModelAndView getSporocilo1(@RequestParam(defaultValue = "en") String q) throws IOException, InterruptedException, JSONException {
       
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

	// TODO PREVERI ALI JEZIK SPLOH OBSTAJA -DRUGAČE JAVI NAPAKO
	//endpoint za hello - vzame parameter iz urlja in vrne string "hello world" zeljenega jezika
	@GetMapping("/hello-rest")
	@ResponseBody
	public String getSporocilo(@RequestParam(defaultValue = "en") String q) throws IOException, InterruptedException, JSONException {
		//spremeni string v string z veliko zacetnico

		if (environment.acceptsProfiles(Profiles.of("api"))) {
			Jezik en = jezikService.apiPrevood(q);
		System.out.println(en.getSporocilo());
		return en.getSporocilo();
		}
		else {
		}
		Jezik en = jezikService.getJezikByIme(q);
		System.out.println(en.getSporocilo());
		return en.getSporocilo();
		
	}

	@GetMapping("/secure/hello")
	
	public String pojdiNaSecure() {
		
		return "secure";
	}

	@GetMapping("/ustvariJezik")
	public String ustvari(Model model) {
		model.addAttribute("objekt", new Jezik());
		return "ustvariJezik";
	}

	@PostMapping("/ustvariJezik")
  public void greetingSubmit(@ModelAttribute("objekt") Jezik noviJ) {
	jezikService.addNewJezik(noviJ);
  }

  @GetMapping("/API")
  @ResponseBody
  public void APIt() throws IOException, InterruptedException, JSONException {
	jezikService.apiPrevood("haw");
   
  }
	


}
