package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dto.AddCarDto;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class DataProviderCar {
    @DataProvider
    public Iterator<Object[]> carAddYaml() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("datacar.yaml");
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            AddCarDto[] userData = mapper.readValue(is, AddCarDto[].class);

            return Arrays.stream(userData)
                    .map(user -> new Object[]{user})
                    .iterator();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
