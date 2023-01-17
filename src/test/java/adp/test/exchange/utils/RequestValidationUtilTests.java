package adp.test.exchange.utils;

import adp.test.exchange.exception.RequestValidationException;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class RequestValidationUtilTests {

    @InjectMocks
    RequestValidationUtil validationUtil;


    @Before
    public void setupTests() {
        //we are using Reflection Utils to set value which are configured from properties file
        Set<Integer> bills = new HashSet<>();
        bills.addAll(Lists.newArrayList(1, 2, 5, 10, 20));
        ReflectionTestUtils.setField(validationUtil, "allowedBills", bills);
    }

    @Test(expected = RequestValidationException.class)
    public void test_validateInput_exception() {
        validationUtil.validateInput(101);
    }

    @Test
    public void test_validateInput_success() {
        Boolean result = validationUtil.validateInput(1);
        Assert.assertEquals(true, result);
    }

}
