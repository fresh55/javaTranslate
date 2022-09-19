package com.example.demo.Jezik;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mashape.unirest.http.Unirest;

import net.bytebuddy.implementation.bytecode.Throw;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class JezikService {

	private final JezikRepository jezikRepository;

	@Autowired
	public JezikService(JezikRepository jezikRepository) {
		this.jezikRepository = jezikRepository;
	}

	public List<Jezik> getJeziki() {
		return jezikRepository.findAll();
	}
    
	public void addNewJezik(Jezik jezik) {
		String q1 = jezik.getIme().toLowerCase();
		Optional<Jezik> jezikOptional = jezikRepository.findJezikByIme(q1);
		if (jezikOptional.isPresent()) {
			System.out.println("Jezik je ze v bazi");

		} else {
			jezikRepository.save(jezik);
		}

	}

	public Jezik getJezikByIme(String ime) {
		String q1 = ime.toLowerCase();
		System.out.println(q1);
		if (jezikRepository.findJezikByIme(q1).isPresent()) {
			return jezikRepository.findJezikByIme(q1).get();
		} else {
			System.out.println("Jezika ni v bazi");
			throw new IllegalStateException("jezika ni v bazi");
		}
	}

	public Jezik apiPrevood(String target) throws IOException, InterruptedException, JSONException {
		String api = "text=Hello%world&tl=" + target + "&sl=en";
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://google-translate20.p.rapidapi.com/translate"))
				.header("content-type", "application/x-www-form-urlencoded")
				.header("X-RapidAPI-Key", "044359bf0bmshe6d7793e01a090fp125da6jsna14ba4ff76c0")
				.header("X-RapidAPI-Host", "google-translate20.p.rapidapi.com")
				.method("POST", HttpRequest.BodyPublishers.ofString(api))
				.build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject obj = new JSONObject(response.body());
		String sporocilo = obj.getJSONObject("data").getString("translation");

		return new Jezik(target, sporocilo);

	}
}