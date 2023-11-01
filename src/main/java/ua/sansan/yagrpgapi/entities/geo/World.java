package ua.sansan.yagrpgapi.entities.geo;

import org.springframework.stereotype.Component;
import ua.sansan.yagrpgapi.entities.GameObject;

import java.util.List;
@Component
public class World {
    public GameObject info;
    List<Location> locations;

    public World(){
        info = new GameObject("Світ Пригод!", "Чудовий світ пригод");
    }
}
