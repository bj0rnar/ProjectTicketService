package TicketService.Controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {


    private HashMap<String, Pane> screenMap = new HashMap<String, Pane>();
    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, Pane pane) {
        screenMap.put(name, pane);
    }
    public void removeScreen(String name) {
        screenMap.remove(name);
    }
    public void active(String name) {
        main.setRoot(screenMap.get(name));
    }
    public Pane getScreen(String name) {
        return screenMap.get(name);
    }
}
