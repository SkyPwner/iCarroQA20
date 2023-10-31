package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dto.UserDtoLombok;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataProviderLogin {


    @DataProvider
    public Iterator<Object[]> negativePasswordDataLogin() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("positivetest@gmail.com")
                        .password("123456A881")
                        .build()
        });
        list.add(new Object[]{
                UserDtoLombok.builder()
                        .email("negativetest@gmail.com")
                        .password("123456Aaa")
                        .build()
        });
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginYAML() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("datalogin.yaml");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            UserDtoLombok[] userData = mapper.readValue(is, UserDtoLombok[].class);

            return Arrays.stream(userData)
                    .map(user -> new Object[]{user})
                    .iterator();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
