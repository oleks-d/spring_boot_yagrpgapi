package ua.sansan.yagrpgapi.entities;

public class GameObject {
    String id;
    public String name;
    public String description;

    public GameObject(String name, String description) {
        this.name = name;
        this.description = description;
        id = name + 1;
    }
}
