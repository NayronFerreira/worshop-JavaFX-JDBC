package model.services;

import java.util.List;

import model.dao.DaoFabrica;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {

	private DepartmentDao dao = DaoFabrica.createDepartmentDao();

	public List<Department> findAll() {
		return dao.findAll();
	}

}
