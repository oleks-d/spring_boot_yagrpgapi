package ua.sansan.yagrpgapi.domain;

import jakarta.persistence.*;

@Entity
public class Role {
    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    //TODO find normal name
    public static Role getRoleByLabel(String user) {
        if(user.equals("ADMIN"))
            return new Role(1,"ADMIN");
        return new Role(0,"GAMER");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    public Role() {
    }

    @Column(name = "ROLE")
    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}