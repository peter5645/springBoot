package com.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Emp;
import com.test.service.EmpService;
import com.test.util.UtilTest;

import io.swagger.annotations.Api;


@Controller
@Api(value="apiTest")
public class EmpController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    private EmpService empService;

    @RequestMapping(value="/sample/Test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
    	System.out.println("test");
    	System.out.println("1111");
    	return new ResponseEntity<String>("Test", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/sample/EmpAll", method = RequestMethod.GET)
    public ResponseEntity<List<Emp>> getAll() {
        List<Emp> list = empService.getAll();
        return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/sample/Emp" ,method = RequestMethod.GET)
    public ResponseEntity<List<Emp>> getEmp(@RequestParam(value = "empno", required = false) Integer empno,
            @RequestParam(value = "deptno", required = false) Integer detpno) {
        List<Emp> list = empService.getEmp(empno, detpno);
        return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
    }


    @RequestMapping(value = "/sample/Emp", method = RequestMethod.POST)
    public void saveOrUpdate(@RequestParam(value = "empno", required = false) Integer empno,
            @RequestParam(value = "ename", required = false) String ename,
            @RequestParam(value = "deptno", required = false) Integer deptno) {
        Emp emp = new Emp();
        emp.setDeptno(deptno);
        emp.setEname(ename);
        emp.setEmpno(empno);

        empService.saveOrUpdate(emp);
    }

    @RequestMapping(value = "/sample/allEmpDept", method = RequestMethod.GET)
    public ResponseEntity<List<Emp>> getEmpDept() {
        List<Emp> list = empService.selectEmpDept();
        return new ResponseEntity<List<Emp>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/sample/Emp/{empno}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer empno) {
    	try{
    		empService.delete(empno);
    
    	}catch(Exception e){
    		System.out.println("Error");
    	}
    }
    
    @Autowired
    private UtilTest utilTest;

    @Value("${example.property}")
    private String hello;

    @Value("${test.test}")
    private String test;

    @RequestMapping(value = "/sample/sayHi", method = RequestMethod.GET)
    public ResponseEntity<String> sayHi() {
        String hi = utilTest.sayHi();
        System.out.println(hello);
        return new ResponseEntity<String>(hi+hello+test, HttpStatus.OK);
    }

    @RequestMapping(value = "/sample/sayHi/{name}", method = RequestMethod.GET)
    public ResponseEntity<String> sayHi(@PathVariable String name) {
        String hi = utilTest.sayHi();
        System.out.println(hello);
        return new ResponseEntity<String>(hi+hello+test+"name : "+name, HttpStatus.OK);
    }
}
