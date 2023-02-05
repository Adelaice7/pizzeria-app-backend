package com.rmeunier.pizzeriaapp;

import com.rmeunier.pizzeriaapp.error.ApiError;
import com.rmeunier.pizzeriaapp.model.Customer;
import com.rmeunier.pizzeriaapp.repo.CustomerRepository;
import com.rmeunier.pizzeriaapp.shared.GenericResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomerControllerTest {
    private static final String API_CUSTOMERS = "/customers";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }

    @Test
    public void postCustomer_whenCustomerIsValid_receiveOk() {
        Customer customer = createValidCustomer();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_CUSTOMERS, customer, Object.class);
    }

    @Test
    public void postCustomer_whenCustomerIsValid_CustomerSavedToDb() {
        Customer customer = createValidCustomer();
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(customerRepository.count()).isEqualTo(1);
    }

    @Test
    public void postCustomer_whenCustomerIsValid_receiveOkAndSuccessMessage() {
        Customer customer = createValidCustomer();
        ResponseEntity<GenericResponse> response = postSignup(customer, GenericResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postCustomer_whenCustomerIsValid_passweordIsHashedInDb() {
        Customer customer = createValidCustomer();
        postSignup(customer, Object.class);
        List<Customer> customers = customerRepository.findAll();
        Customer customerInDb = customers.get(0);
        assertThat(customerInDb.getPassword()).isNotEqualTo(customer.getPassword());
    }
    @Test
    public void postCustomer_whenCustomerHasNullUsername_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setUsername(null);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasNullFirstname_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setFirstName(null);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasNullPassword_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setPassword(null);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasUsernameLessThanRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setUsername("abc");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasFirstnameLessThanRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setFirstName("a");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasLastnameLessThanRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setFirstName("a");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasUsernameExceedsRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
        customer.setUsername(valueOf256Chars);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasFirstnameExceedsRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
        customer.setFirstName(valueOf256Chars);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasLastnameExceedsRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "a").collect(Collectors.joining());
        customer.setLastName(valueOf256Chars);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerHasPasswordExceedsRequired_receiveBadRequest() {
        Customer customer = createValidCustomer();
        String valueOf256Chars = IntStream.rangeClosed(1, 256).mapToObj(x -> "A1").collect(Collectors.joining());
        customer.setPassword(valueOf256Chars);
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postCustomer_whenCustomerHasPasswordWithAllLowercase_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setPassword("alllowercase");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postCustomer_whenCustomerHasPasswordWithAllUppercase_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setPassword("ALLUPPERCASE");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void postCustomer_whenCustomerHasPasswordWithAllNumbers_receiveBadRequest() {
        Customer customer = createValidCustomer();
        customer.setPassword("123456789");
        ResponseEntity<Object> response = postSignup(customer, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void postCustomer_whenCustomerIsInvalid_receiveApiError() {
        Customer customer = new Customer();
        ResponseEntity<ApiError> response = postSignup(customer, ApiError.class);
        assertThat(response.getBody().getUrl()).isEqualTo(API_CUSTOMERS);
    }

    @Test
    public void postCustomer_whenCustomerIsInvalid_receiveApiErrorWithValidationErrors() {
        Customer customer = new Customer();
        ResponseEntity<ApiError> response = postSignup(customer, ApiError.class);
        assertThat(response.getBody().getValidationErrors().size()).isEqualTo(6);
    }

    @Test
    public void postCustomer_whenCustomerHasNullUsername_receiveMessageOfNullErrorForUsername() {
        Customer customer = createValidCustomer();
        customer.setUsername(null);
        ResponseEntity<ApiError> response = postSignup(customer, ApiError.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("username")).isEqualTo("Username cannot be null");
    }
    @Test
    public void postCustomer_whenCustomerHasInvalidLengthUsername_receiveGenericMessageOfError() {
        Customer customer = createValidCustomer();
        customer.setUsername("abc");
        ResponseEntity<ApiError> response = postSignup(customer, ApiError.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("username")).isEqualTo("It must have minimum 4 and maximum 255 characters");
    }
    @Test
    public void postCustomer_whenCustomerHasInvalidPassword_receiveGenericMessageOfError() {
        Customer customer = createValidCustomer();
        customer.setPassword("abc");
        ResponseEntity<ApiError> response = postSignup(customer, ApiError.class);
        Map<String, String> validationErrors = response.getBody().getValidationErrors();
        assertThat(validationErrors.get("password")).isEqualTo("Password must have at least one uppercase, one lowercase, one number and one special character. Minimum 8 characters");
    }

    public <T> ResponseEntity<T> postSignup(Object request, Class<T> response) {
        return testRestTemplate.postForEntity(API_CUSTOMERS, request, response);
    }
    private Customer createValidCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Joe");
        customer.setLastName("Doe");
        customer.setUsername("joedoe");
        customer.setPhoneNumber("+33784514411");
        customer.setPassword("PassWord_1");

        return customer;
    }
}
