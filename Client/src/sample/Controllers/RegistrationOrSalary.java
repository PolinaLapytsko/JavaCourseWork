package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.View.*;
import sample.clientConnection;

import java.io.IOException;

public class RegistrationOrSalary {
    @FXML
    private javafx.scene.control.Button registration;

    public void RegistrationAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../Frames/registration.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private javafx.scene.control.ChoiceBox PositionChoice;

    public void SalaryAction(ActionEvent event) {
        ChoiceBox<String> cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList(
                "Простая повременная", "Повременно-премиальная", "Окладная", "Прямая сдельная", "Сдельно-премиальная",
                "Сдельно-прогрессивная")
        );

        TableView<UserAccount> table = new TableView<UserAccount>();
        AnchorPane root = new AnchorPane();

        TableColumn<UserAccount, String> userNameCol
                = new TableColumn<UserAccount, String>("Имя");
        userNameCol.setMinWidth(300);

        TableColumn<UserAccount, String> surnameCol//
                = new TableColumn<UserAccount, String>("Фамилия");
        surnameCol.setMinWidth(300);

        TableColumn<UserAccount, String> positionCol//
                = new TableColumn<UserAccount, String>("Должность");
        positionCol.setMinWidth(300);


        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("userSurname"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("userPosition"));
        ObservableList<UserAccount> list = FXCollections.observableArrayList();

        TableView<UserAccount> salaryTable = new TableView<UserAccount>();

        TableColumn<UserAccount, String> date
                = new TableColumn<UserAccount, String>("Дата");
        date.setMinWidth(130);

        TableColumn<UserAccount, String> salary//
                = new TableColumn<UserAccount, String>("Зарплата");
        salary.setMinWidth(130);

        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        ObservableList<UserAccount> salaryList = FXCollections.observableArrayList();


        Button load = new Button("Список сотрудников");
        load.setOnAction(e -> {
            clientConnection msg = new clientConnection();
            try {
                for ( int i = 0; i<table.getItems().size(); i++) {
                    table.getItems().clear();
                }
                msg.sendUserListRequest("UserList");
                while (true) {
                    String flag = msg.message();
                    if (flag.equals("Stop")) break;
                    else {
                        list.add(msg.getUserList());
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            table.setItems(list);
        });

        Button UserSalaryList = new Button("Просмотреть начисления");
        UserSalaryList.setOnAction(e->{
            for ( int i = 0; i<salaryTable.getItems().size(); i++) {
                salaryTable.getItems().clear();
            }
            clientConnection msg = new clientConnection();
            try {
                UserAccount cnt = table.getSelectionModel().getSelectedItem();
                int index = cnt.getId();
                msg.sendUserSalaryRequest("UserSalaryList", index);
                while (true) {
                    String flag = msg.message();
                    if (flag.equals("Stop")) break;
                    else {
                        salaryList.add(msg.getUserSalaryList());
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            salaryTable.setItems(salaryList);
        });


        Button count = new Button("Рассчитать");
        count.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String choice = cb.getValue();
                UserAccount cnt = table.getSelectionModel().getSelectedItem();
                int index = cnt.getId();
                clientConnection client = new clientConnection();
                try {
                    client.getUserMsgCount("Count");
                    client.sendUserIndex(index);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (choice.equals("Простая повременная")) {
                    SimpleTimeSalaryView simpleTime = new SimpleTimeSalaryView();
                    simpleTime.getView(event);
                }
                if (choice.equals("Повременно-премиальная")){
                    SimpleTimePrizeSalaryView simpleTimePrize = new  SimpleTimePrizeSalaryView();
                    simpleTimePrize.getView(event);
                }
                if(choice.equals("Окладная")){
                    SalaryView salary = new SalaryView();
                    salary.getView(event);
                }
                if(choice.equals("Прямая сдельная")){
                    DirectPiecework directPiecework = new DirectPiecework();
                    directPiecework.getView(event);
                }
                if(choice.equals("Сдельно-премиальная")){
                    PieceworkPrize pieceworkPrize = new PieceworkPrize();
                    pieceworkPrize.getView(event);
                }
                if (choice.equals("Сдельно-прогрессивная")){
                    PieceworkProg pieceworkProg = new PieceworkProg();
                    pieceworkProg.getView(event);
                }
            }
        });

        table.getColumns().addAll(userNameCol, surnameCol, positionCol);
        salaryTable.getColumns().addAll(date, salary);

        ((Node)(event.getSource())).getScene().getWindow().hide();

        AnchorPane.setTopAnchor(cb,10.0);
        AnchorPane.setLeftAnchor(cb, 10.0);

        AnchorPane.setLeftAnchor(table,500.0);
        AnchorPane.setRightAnchor(table, 10.0);
        AnchorPane.setTopAnchor(table, 10.0);
        AnchorPane.setBottomAnchor(table, 40.0);

        AnchorPane.setLeftAnchor(load,500.0);
        AnchorPane.setBottomAnchor(load, 10.0);

        AnchorPane.setLeftAnchor(count,200.0);
        AnchorPane.setTopAnchor(count, 10.0);

        AnchorPane.setTopAnchor(salaryTable, 100.0);
        AnchorPane.setLeftAnchor(salaryTable,10.0);

        AnchorPane.setTopAnchor(UserSalaryList,510.0);
        AnchorPane.setLeftAnchor(UserSalaryList,10.0);

        root.getChildren().addAll(cb, table, load, count, salaryTable, UserSalaryList);
        Stage stage = new Stage();
        stage.setTitle("TableView");

        Scene scene = new Scene(root, 1500, 700);
        stage.setScene(scene);
        stage.show();
    }

}
