package com.example.pokemonTCG;



import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import *;

public class MainController {
    @FXML private AnchorPane playArea;

    public void initialize() {
        // Exemple : afficher 5 cartes de la main du joueur
        for (Card c : GameController.getInstance().getPlayer().getHand()) {
            addCardToArea(c);
        }
    }

    @FXML
    private void handleNewGame() {
        GameController.getInstance().startNewGame();
        initialize();  // rafraîchir UI
    }

    private void addCardToArea(Card card) {
        // Charge CardView.fxml, lie les propriétés et ajoute dans playArea
        CardController ctl = FXMLLoaderHelper.load("/view/CardView.fxml");
        ctl.setCard(card);
        playArea.getChildren().add(ctl.getRoot());
    }
}
