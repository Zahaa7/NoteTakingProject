package ro.jademy.notetaking.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ro.jademy.notetaking.Main;
import ro.jademy.notetaking.models.Category;
import ro.jademy.notetaking.models.Note;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IOService {

    public List<Note> readJson() {
        JSONParser jsonParser = new JSONParser();
        List<Note> noteList = new ArrayList<>();
        try {
            String textReader = new String(Files.readAllBytes(Paths.get(Main.class.getClassLoader()
                    .getResource("notes.json").toURI())), StandardCharsets.UTF_8);

            JSONArray jsonArray = (JSONArray) jsonParser.parse(textReader);
            for (Object object : jsonArray) {
                JSONObject noteObject = (JSONObject) object;
                String title = (String) noteObject.get("title");
                String body = (String) noteObject.get("body");
                Long creationDate = (Long) noteObject.get("creationDate");
                Long modificationDate = (Long) noteObject.get("modificationDate");
                Boolean markedAsFinished = (Boolean) noteObject.get("markedAsFinished");
                Boolean pinnedAsFavorite = (Boolean) noteObject.get("pinnedAsFavorite");
                Category category = Category.lookup(String.valueOf(noteObject.get("category")), Category.NO_CATEGORY);
                List hashtagList = (List) noteObject.get("hashtagList");

                Note note = new Note(title, body, creationDate, modificationDate, markedAsFinished, pinnedAsFavorite,
                        category, hashtagList);
                noteList.add(note);
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        } catch (ParseException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return noteList;
    }

    public void writeJson(List<Note> noteList, File file) {
        Runnable runnable = () -> {
            JSONArray jsonArray = new JSONArray();
            try {
                FileWriter fileWriter = new FileWriter(file);
                for (Note note : noteList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("title", note.getTitle());
                    jsonObject.put("body", note.getBody());
                    jsonObject.put("creationDate", note.getCreationDate());
                    jsonObject.put("modificationDate", note.getModificationDate());
                    jsonObject.put("markedAsFinished", note.isMarkedAsFinished());
                    jsonObject.put("pinnedAsFavorite", note.isPinnedAsFavorite());
                    jsonObject.put("category", note.getCategory().getCategoryName());
                    jsonObject.put("hashtagList", note.getHashtagList());
                    jsonArray.add(jsonObject);
                }
                fileWriter.write(jsonArray.toJSONString());
                System.out.println("File written successfully!");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
