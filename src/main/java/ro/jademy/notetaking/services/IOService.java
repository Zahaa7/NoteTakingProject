package ro.jademy.notetaking.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.jademy.notetaking.Main;
import ro.jademy.notetaking.models.Category;
import ro.jademy.notetaking.models.Note;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOService {


    public static void readJson() {
        JSONParser jsonParser = new JSONParser();
        try {
            String text = new String(Files.readAllBytes(Paths.get(Main.class.getClassLoader()
                    .getResource("notes.json").toURI())), StandardCharsets.UTF_8);
            JSONArray jsonArray = (JSONArray) jsonParser.parse(text);
            List<Note> noteList = new ArrayList<>();
            for (Object object : jsonArray) {
                JSONObject noteObject = (JSONObject) object;
                String title = (String) noteObject.get("title");
                String body = (String) noteObject.get("body");
                Long creationDate = Long.valueOf((String)noteObject.get("creationDate"));
                Long modificationDate = Long.valueOf((String)noteObject.get("modificationDate"));
                Boolean markedAsFinished = (Boolean) noteObject.get("markedAsFinished");
                Boolean pinnedAsFavorite = (Boolean) noteObject.get("pinnedAsFavorite");
                Category category = Category.valueOf((String)noteObject.get("category"));
                List hashtagList = (List) noteObject.get("hashtagList");

                Note note = new Note(title, body, creationDate, modificationDate, markedAsFinished, pinnedAsFavorite,
                        category, hashtagList);
                noteList.add(note);
            }
            noteList.forEach(System.out::println);
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (ParseException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
