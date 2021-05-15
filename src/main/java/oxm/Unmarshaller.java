package oxm;

import org.springframework.oxm.XmlMappingException;

import javax.xml.transform.Source;
import java.io.IOException;

/**
 * Created by mileNote on 2021-05-15
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public interface Unmarshaller {
    boolean supports(Class<?> clazz);

    Object unmarshal(Source source) throws IOException, XmlMappingException;
}
