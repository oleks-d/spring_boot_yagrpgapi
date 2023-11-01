package ua.sansan.yagrpgapi.entities;

import ua.sansan.yagrpgapi.entities.geo.Location;
import ua.sansan.yagrpgapi.entities.nature.Creature;

import java.util.List;

public class Hero {
    Creature character;
    List<String> titles;
    Location location;
    Journal journal;
}
