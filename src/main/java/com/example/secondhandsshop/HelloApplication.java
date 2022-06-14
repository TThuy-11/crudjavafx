package com.example.secondhandsshop;

import Clothes.DBConnect;
import Clothes.clothes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketOption;
import java.util.ArrayList;
import java.util.List;



public class HelloApplication extends Application {

    TextField tfName = new TextField();
    TextField tfImg = new TextField();
    TextField tfSize = new TextField();
    TextField tfPrice = new TextField();
    VBox clothesRoot = new VBox();

    @Override
    public void start(Stage stage) throws IOException {

        DBConnect connection = new DBConnect();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        VBox hInsertClothes = new VBox();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        // scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction( e-> {
            connection.insertClothes(new clothes(tfName.getText(), tfImg.getText(),tfSize.getText(), Float.parseFloat(tfPrice.getText())));
            getThenDisplayClothes(clothesRoot, connection);
        });

        Button btnEdit = new Button("Update");
        btnEdit.setOnAction(event -> {
            connection.updateClothes(new clothes(tfName.getText(),tfImg.getText(), tfSize.getText(),Float.parseFloat(tfPrice.getText()),Integer.parseInt(tfName.getId())));
            getThenDisplayClothes(clothesRoot, connection);
        });
        Label lbName = new Label("Name:");
        Label lbImg = new Label("Img:");
        Label lbSize = new Label("Size:");
        Label lbPrice = new Label("Price:");
        hInsertClothes.getChildren().addAll(lbName,tfName,lbImg, tfImg,lbSize, tfSize,lbPrice, tfPrice, btnAdd, btnEdit);
        root.getChildren().addAll(hInsertClothes, clothesRoot);
        getThenDisplayClothes(clothesRoot, connection);
        Scene scene = new Scene(scrollPane, 900, 700);

        stage.setTitle("QUẦN ÁO");
        stage.setScene(scene);
        stage.show();
    }

    void displayClothes(DBConnect connection, VBox root, List <clothes> clothes)  {
        root.getChildren().clear();
        System.out.println(root.getChildren().getClass());
//        Label lbhId = new Label("ID");
//        Label lbhName = new Label("Name");
//        Label lbhImg = new Label("Img");
//        Label lbhSize = new Label("Size");
//        Label lbhPrice = new Label("Price");
//        root.getChildren().add(new HBox(lbhId, lbhName, lbhSize, lbhImg, lbhPrice));
        for (int i = 0; i < clothes.size(); i++) {
            final int finialI = i;
            HBox clothesBox = new HBox();
            Label lbId = new Label("" + clothes.get(i).id);
            Label lbName = new Label(String.format("%-40s", clothes.get(i).name));
            lbName.setMaxWidth(100);

            Image image = new Image(clothes.get(i).img);
            ImageView imageView = new ImageView(image);
            //Setting the position of the image
            imageView.setX(50);
            imageView.setY(25);

            //setting the fit height and width of the image view
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            //Setting the preserve ratio of the image view
            imageView.setPreserveRatio(true);
//            Label lbImg = new Label(clothes.get(i).img);
            Label lbSize = new Label(clothes.get(i).size);
            lbSize.setMaxWidth(100);

            Label lbPrice = new Label("" + clothes.get(i).price);

            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(actionEvent -> {
                System.out.println("Click delete " + clothes.get(finialI).id);
                connection.deleteClothes(clothes.get(finialI).id);
                getThenDisplayClothes(root, connection);
            });

            Button btnUpdate = new Button("Update");
            btnUpdate.setOnAction(actionEvent->{
                tfName.setText(String.valueOf((clothes.get(finialI).name)));
                tfName.setId(""+clothes.get(finialI).id);
                tfImg.setText(String.valueOf((clothes.get(finialI).img)));
                tfSize.setText(String.valueOf((clothes.get(finialI).size)));
                tfPrice.setText(String.valueOf((clothes.get(finialI).price)));
            });


            clothesBox.setSpacing(50);
            clothesBox.getChildren().addAll(lbId, lbName, lbSize, imageView, lbPrice, btnDelete, btnUpdate);
            root.getChildren().add(clothesBox);
        }
    }

    private void getThenDisplayClothes(VBox root, DBConnect connection) {
        List<clothes> clothes= connection.getClothes();
        displayClothes(connection, root, clothes);
    }

    public static void main(String[] args) {
        launch();
    }
}