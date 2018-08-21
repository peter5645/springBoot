package com.test.service;

import java.util.List;

import com.test.model.Emp;


public interface EmpService {
    
    public List<Emp> getAll();

    public List<Emp> getEmp(Integer empno, Integer deptno);

    public void saveOrUpdate(Emp emp);
    
    public void delete(Integer empno) throws Exception;
    
    public List<Emp> selectEmpDept();
    
    public String sayHallo();
}
