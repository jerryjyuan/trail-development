package com.example.nationflags.config;

import java.io.BufferedReader;
import java.io.InputStream;
imoort java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.IntStream;
import javax.servlet.ServletContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.example.nationflags.repository.FlagRepositorty;
import com.example.nationflags.model.Flag;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ServletContext servletContext;
    
    @Autowired
    private FlagRepositorty flagRepo;
    
    @Override
	public void run(String... args) {
        InputStream stream = this.getClass().getResourceAsStream("/continents.json");
        Reader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line.trim());
        }
        JSONArray jsonArray = new JSONArray(buffer.toString());
        IntStream.range(0, jsonArray.length()).mapToObj(i->array.getJSONObject(i)).forEach(obj->
            String continent = obj.get("continent");
            JSONArray countriesArr = obj.get("countries");
            IntStream.range(0, countriesArr.length()).mapToObj(j->countriesArr.getJSONObject(j)).forEach(jsonObj->
                String flagLink = servletContext.getContextPath() + "/flags/" + jsonObj.get("flag");
                flagRepo.save(new Flag(continent, jsonObj.get("name"), flagLink));       
            )
        );
	}
}
