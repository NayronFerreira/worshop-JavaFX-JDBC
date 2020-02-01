package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	public DepartmentService dpService;

	@FXML
	private Button btNew;
	@FXML
	private TableView<Department> tableViewDepartment;
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	@FXML
	private TableColumn<Department, Integer> tableColumnName;
	
	private ObservableList<Department> obsList;

	@FXML
	public void onBtnAction() {
		System.out.println("onBtnAction");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void setDepartmentService(DepartmentService dpService) {
		this.dpService = dpService;
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());

	}
	
	public void updateTableView() {
		if (dpService == null) {
			throw new IllegalStateException("Serviço não instaciado");
		}
		List<Department> lista = dpService.findAll();
		obsList = FXCollections.observableArrayList(lista);
		tableViewDepartment.setItems(obsList);
	}

}
