package com.luv2code.springmvc;

import com.luv2code.springmvc.repository.HistoryGradesDao;
import com.luv2code.springmvc.repository.MathGradesDao;
import com.luv2code.springmvc.repository.ScienceGradesDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class BaseSpringBootTest {

    @Autowired
    protected  JdbcTemplate jdbc;

    @Autowired
    protected  StudentDao studentDao;

    @Autowired
    protected  MathGradesDao mathGradeDao;

    @Autowired
    protected  ScienceGradesDao scienceGradeDao;

    @Autowired
    protected  HistoryGradesDao historyGradeDao;

    @Autowired
    protected  StudentAndGradeService studentService;

    @Value("${sql.script.create.student}")
    protected String sqlAddStudent;

    @Value("${sql.script.create.math.grade}")
    protected String sqlAddMathGrade;

    @Value("${sql.script.create.science.grade}")
    protected String sqlAddScienceGrade;

    @Value("${sql.script.create.history.grade}")
    protected String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    protected String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    protected String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    protected String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    protected String sqlDeleteHistoryGrade;
}
