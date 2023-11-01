package ua.sansan.yagrpgapi.entities.geo;

import ua.sansan.yagrpgapi.entities.GameObject;
import ua.sansan.yagrpgapi.entities.Item;
import ua.sansan.yagrpgapi.entities.nature.Creature;
import ua.sansan.yagrpgapi.entities.nature.Person;

import java.util.List;

public class Location {
    GameObject info;
    List<Item> items;
    List<Person> persons;
    List<Creature> creatures;
    List<Location> exits;

}
