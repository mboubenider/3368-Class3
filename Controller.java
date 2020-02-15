package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private ListView<Employee> employeeListView;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private CheckBox isActiveCheckBox;
    @FXML
    private Button AddNewName;
    @FXML
    private Button ClearName;
    @FXML
    private Button DeleteSelected;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        employeeListView.getSelectionModel().selectedItemProperty().addListener((
                        ObservableValue < ? extends Worker> ov, Worker old_val, Worker new_val)->
                {
                    Worker selectedItem = employeeListView.getSelectionModel().getSelectedItem();

                    firstNameTextField.setText(((Employee)selectedItem).firstName);
                    lastNameTextField.setText(((Employee)selectedItem).lastName);
                    isActiveCheckBox.setSelected(((Employee)selectedItem).isActive);
                }
        );

        ObservableList<Employee> items = employeeListView.getItems();

        EventHandler<ActionEvent> add = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.add(new Employee(firstNameTextField.getText(),
                        (lastNameTextField.getText()),
                        Boolean.parseBoolean(String.valueOf(isActiveCheckBox.isSelected()))));
            }
        };
        AddNewName.setOnAction(add);

        EventHandler<ActionEvent> clear = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                firstNameTextField.clear();
                lastNameTextField.clear();
                isActiveCheckBox.setSelected(false);//clicking new button makes left side visible
            }
        };
        ClearName.setOnAction(clear);

        EventHandler<ActionEvent> delete = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Employee> selectedRows, allPeople;
                allPeople = employeeListView.getItems();

                //rows that were selected
                selectedRows = employeeListView.getSelectionModel().getSelectedItems();

                //loop over the selected rows and remove the Employee objects from the table
                for (Employee employee: selectedRows)
                {
                    allPeople.remove(employee);
                }

            }
        };
        DeleteSelected.setOnAction(delete);


        Employee employee1 = new Employee("Robert", "Smith", true);
        Employee employee2 = new Employee("Lisa", "Smith", false);
        items.add(employee1);
        items.add(employee2);

        for(int i = 0; i < 10; i++)
        {
            Employee employee = new Employee("Generic", "Employee", true);
            employee.hire();
            items.add(employee);
        }

        Staff staff1 = new Staff("StaffPerson", "GoodWorker", true);
        Faculty faculty1 = new Faculty("FacultyPerson", "TerribleWorker", false);
        items.add(staff1);
        items.add(faculty1);

    }
}
