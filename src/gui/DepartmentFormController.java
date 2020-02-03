package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import gui.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private DepartmentService service;
	
	private Department entity;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	public void setDepartmentService(DepartmentService service) {
		this.service=service;
	}

	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label lbErrorNme;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtSaveAction() {
		entity = getFormData();
		service.saveorUpdate(entity);
	}
	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParToInt((txtId.getText())));
		obj.setName(txtName.getText());
		return obj;
	}
	@FXML
	public void onBtCancelAction() {
		System.out.println("Cancel!");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if(entity==null) {
			throw new IllegalStateException("Item não instanciado");
		}
		txtId.setText (String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
