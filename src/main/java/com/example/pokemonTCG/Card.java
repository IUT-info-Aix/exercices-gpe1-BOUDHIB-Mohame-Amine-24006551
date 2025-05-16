package com.example.pokemonTCG;

// model/Card.java


import javafx.beans.property.*;

public class Card {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final IntegerProperty hp = new SimpleIntegerProperty();
    private final StringProperty imagePath = new SimpleStringProperty();

    public Card(String name, String type, int hp, String imagePath) {
        this.name.set(name);
        this.type.set(type);
        this.hp.set(hp);
        this.imagePath.set(imagePath);
    }

    // getters et propriétés pour le binding
    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public String getType() { return type.get(); }
    public IntegerProperty hpProperty() { return hp; }
    public String getImagePath() { return imagePath.get(); }
}
