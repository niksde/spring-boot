package com.luv2code.springmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.springmvc.models.CollegeStudent;
import com.luv2code.springmvc.models.MathGrade;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional // for jpa entity manager
public class GradebookControllerTest extends BaseSpringBootTest {

    private static MockHttpServletRequest request;

    @PersistenceContext
    private EntityManager entityManager;

    @Mock
    StudentAndGradeService studentAndGradeServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private CollegeStudent student;

    public static final MediaType APPLICATION_JSON_UTF8 = MediaType.APPLICATION_JSON;

    @BeforeAll
    public static void setup() {
        request = new MockHttpServletRequest();
        request.setParameter("firstname","Eric");
        request.setParameter("lastname","Public");
        request.setParameter("emailAdress","eric@school.com");
    }

    @BeforeEach
    public void setupDatabase() {
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade);
    }

    @AfterEach
    public void setupAfterTransaction() {
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }

    @Test
    public void getStudentsHttpRequest() throws Exception {
        student.setFirstname("Mary");
        student.setLastname("Test");
        student.setEmailAddress("mary@school.com");
        entityManager.persist(student);
        entityManager.flush();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void createStudentHttpRequest() throws Exception {
        student.setFirstname("Mary");
        student.setLastname("Test");
        student.setEmailAddress("mary@school.com");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));

        CollegeStudent verifyStudent = studentDao.findByEmailAddress("mary@school.com");
        assertNotNull(verifyStudent, "Student should be valid.");
    }

    @Test
    public void deleteStudentHttpRequest() throws Exception {
        Optional<CollegeStudent> verifyStudent = studentDao.findById(1);
        assertTrue(verifyStudent.isPresent(), "Student should be present.");

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/student/{id}", 1)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(0)));

        verifyStudent = studentDao.findById(1);
        assertFalse(verifyStudent.isPresent(), "Student should be not present.");

    }

    @Test
    public void deleteStudentHttpRequestErrorResponse() throws Exception {
        assertFalse(studentDao.findById(0).isPresent(), "Student should not be present.");

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/student/{id}", 0)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));

    }

    @Test
    public void studentInformationHttpRequest() throws Exception {
        Optional<CollegeStudent> verifyStudent = studentDao.findById(1);
        assertTrue(verifyStudent.isPresent(), "Student should be present.");

    mockMvc.perform(
            MockMvcRequestBuilders.get("/studentInformation/{id}", 1)
    )
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(verifyStudent.get().getId())))
            .andExpect(jsonPath("$.firstname", is(verifyStudent.get().getFirstname())))
            .andExpect(jsonPath("$.lastname", is(verifyStudent.get().getLastname())))
            .andExpect(jsonPath("$.emailAddress", is(verifyStudent.get().getEmailAddress())));
    }

    @Test
    public void studentInformationHttpRequestErrorResponse() throws Exception {
        assertFalse(studentDao.findById(0).isPresent(), "Student should not be present.");

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/studentInformation/{id}", 0)
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void createGradesHttpRequest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/grades")
                        .param("grade", "80.50")
                        .param("gradeType", "math")
                        .param("studentId", "1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.studentGrades.mathGradeResults", hasSize(2)));
    }

    @Test
    public void createGradesHttpRequestInValidStudentErrorResponse() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/grades")
                                .param("grade", "80.50")
                                .param("gradeType", "math")
                                .param("studentId", "0")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void createGradesHttpRequestInValidGradeTypeErrorResponse() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/grades")
                                .param("grade", "80.50")
                                .param("gradeType", "literature")
                                .param("studentId", "1")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void createGradesHttpRequestInValidGradeErrorResponse() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/grades")
                                .param("grade", "180.50")
                                .param("gradeType", "math")
                                .param("studentId", "1")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/grades")
                                .param("grade", "-80.50")
                                .param("gradeType", "math")
                                .param("studentId", "1")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void deleteAValidGradeHttpRequest() throws Exception {
        Optional<MathGrade> mathGrade = mathGradeDao.findById(1);
        assertTrue(mathGrade.isPresent());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/grades/{id}/{gradeType}", 1,"math")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(mathGrade.get().getStudentId())))
                .andExpect(jsonPath("$.studentGrades.mathGradeResults", hasSize(0)));
    }

    @Test
    public void deleteInvalidGradeHttpRequestGradeIdNotExistsErrorResponse() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/grades/{id}/{gradeType}", 0, "math")
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }

    @Test
    public void deleteInvalidGradeHttpRequestGradeTypeNotExistsErrorResponse() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/grades/{id}/{gradeType}", 1, "literature")
                )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("Student or Grade was not found")));
    }





}
