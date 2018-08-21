package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.EmpDAO;
import com.test.model.Emp;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDAO empDAO;

    @Override
    public List<Emp> getAll() {

        return empDAO.getAll();
    }

    @Override
    public List<Emp> getEmp(Integer empno, Integer deptno) {
        return empDAO.getEmp(empno, deptno);
    }

    @Override
    public void saveOrUpdate(Emp emp) {
        empDAO.saveOrUpdate(emp);
    }
    
    @Override
    @Transactional
    public void delete(Integer empno) throws Exception {
       empDAO.delete(empno);
    }
    
    @Override
    public List<Emp> selectEmpDept() {
        
        return empDAO.selectEmpDept();
    }
    
    @Override
    public String sayHallo() {
    	System.out.println("hihihihihihi");
    	return "hihihihihihi";
    }

}
