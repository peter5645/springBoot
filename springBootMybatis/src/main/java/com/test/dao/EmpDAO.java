package com.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.Emp;

public interface EmpDAO {
    List<Emp> getAll();

    List<Emp> getEmp(@Param("empno") Integer empno, @Param("deptno") Integer deptno);

    void saveOrUpdate(Emp emp);
    
    void delete(@Param("empno") Integer empno);
    
    List<Emp> selectEmpDept();
}
