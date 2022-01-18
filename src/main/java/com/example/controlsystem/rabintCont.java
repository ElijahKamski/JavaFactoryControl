package com.example.controlsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

import static com.example.controlsystem.utils.ReadFrom;

public class rabintCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField field_sub;


    @FXML
    private Label l1;

    @FXML
    private Label label_task;

    @FXML
    private Label label_TTM;

    @FXML
    private Label label_art;

    @FXML
    private Label label_desc;

    @FXML
    private Label label_greet;

    @FXML
    private Label label_plan;

    @FXML
    private Button submit_btn;

    @FXML
    private TableView<WorkerRecord> table = new TableView<>();

    private String permission;

    private ObservableList<WorkerRecord> list;

    public void setPerm(String perm, String login){
        permission=perm;
        String name = ReadFrom("select name from workers where login = '" +
                login +
                "'");
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time_format = new SimpleDateFormat("HH:mm:ss");
        String today = formater.format(date);
        if (permission.equals("sklad")){
            label_art.setVisible(false);
            label_desc.setVisible(false);
            l1.setVisible(false);
            label_task.setVisible(false);
            field_sub.setVisible(false);
            submit_btn.setVisible(false);
            label_plan.setVisible(false);
            label_TTM.setVisible(false);
            list = FXCollections.observableArrayList(utils.getTableSklad());
        }
        else if (permission.equals("rabochiy")){
            label_art.setVisible(true);
            label_desc.setVisible(true);
            l1.setVisible(true);
            label_task.setVisible(true);
            field_sub.setVisible(true);
            submit_btn.setVisible(true);
            label_plan.setVisible(true);
            label_TTM.setVisible(true);

            label_art.setText(ReadFrom("select articul from task where login='login' and date=''"));
            String art = ReadFrom("select coalesce((select" +
                    "(select articul as res from task where login='" +
                    login +
                    "' and date='" +
                    today +
                    "')" +
                    "as res),0) as res");
            label_art.setText("Артикул: "+art);
            String desc = ReadFrom("select description from details where articul ='" +
                    art +
                    "'");
            label_desc.setText("Описание: "+ desc);
            String plan = ReadFrom("select plan from details where articul='" +
                    art +
                    "'");
            label_plan.setText("План: "+plan);
            String TTM = ReadFrom("select time_to_make from details where articul='" +
                    art +
                    "'");
            label_TTM.setText("Время на изготовление: "+TTM);
            list=FXCollections.observableArrayList(utils.getTableRab());
        }
        label_greet.setText("Здравствуйте, "+name+"! \n Удачной работы!");
        table.setItems(list);
    }


    private final Vector<String> dates = utils.getDatesWindow(7);
    private TableColumn<WorkerRecord, String> nameCol = new TableColumn<>("Имя");
    private TableColumn<WorkerRecord, String> day1Col = new TableColumn<>(dates.get(0));
    private TableColumn<WorkerRecord, String> day2Col = new TableColumn<>(dates.get(1));
    private TableColumn<WorkerRecord, String> day3Col = new TableColumn<>(dates.get(2));
    private TableColumn<WorkerRecord, String> day4Col = new TableColumn<>(dates.get(3));
    private TableColumn<WorkerRecord, String> day5Col = new TableColumn<>(dates.get(4));
    private TableColumn<WorkerRecord, String> day6Col = new TableColumn<>(dates.get(5));
    private TableColumn<WorkerRecord, String> day7Col = new TableColumn<>(dates.get(6));

    private void addFact4Color(TableColumn<WorkerRecord,String> clm, String name) {
        clm.setCellFactory(new Callback<TableColumn<WorkerRecord, String>,
                TableCell<WorkerRecord, String>>()
        {
            @Override
            public TableCell<WorkerRecord, String> call(
                    TableColumn<WorkerRecord, String> param) {
                return new TableCell<WorkerRecord, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (!empty) {
                            int currentIndex = indexProperty()
                                    .getValue() < 0 ? 0
                                    : indexProperty().getValue();
                            String clmItem = param
                                    .getTableView().getItems()
                                    .get(currentIndex).fields.get(name);
                            char clmStatus = clmItem.charAt(0);
                            String res=clmItem.substring(1);

                            switch (clmStatus) {
                                case 'r' -> {
                                    setTextFill(Color.WHITE);
                                    setStyle("-fx-background-color: red");
                                    setText(res);
                                }
                                case 'g' -> {
                                    setTextFill(Color.WHITE);
                                    setStyle("-fx-background-color: green");
                                    setText(res);
                                }
                                case 'o' -> {
                                    setTextFill(Color.WHITE);
                                    setStyle("-fx-background-color: orange");
                                    setText(res);
                                }
                                case '_' ->{
                                    setTextFill(Color.WHITE);
                                    setStyle("-fx-background-color: gray");
                                }
                                default -> {
                                    setStyle("-fx-font-weight: bold");
                                    setText(clmItem);
                                }
                            }
                        }
                    }
                };
            }
        });
        clm.setMinWidth(100);
    }


    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'scene.fxml'.";
        addFact4Color(nameCol, "name");
        addFact4Color(day1Col, "day1");
        addFact4Color(day2Col, "day2");
        addFact4Color(day3Col, "day3");
        addFact4Color(day4Col, "day4");
        addFact4Color(day5Col, "day5");
        addFact4Color(day6Col, "day6");
        addFact4Color(day7Col, "day7");
        table.setEditable(true);
        table.getColumns().addAll(nameCol, day1Col, day2Col, day3Col, day4Col, day5Col, day6Col, day7Col);
    }

}
