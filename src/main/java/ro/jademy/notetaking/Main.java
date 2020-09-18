package ro.jademy.notetaking;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    public static void main(String[] args) {

        // Pornim cu un array - top level object este reprezentat de un array de obiecte

        JSONParser jsonParser = new JSONParser();

        try (Reader reader = new FileReader(Main.class.getClassLoader().getResource("notes.json").getFile())) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            System.out.println(jsonArray);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
}
