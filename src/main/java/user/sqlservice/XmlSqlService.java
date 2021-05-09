package user.sqlservice;

import org.springframework.stereotype.Service;
import user.dao.UserDao;
import user.sqlservice.jaxb.SqlType;
import user.sqlservice.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mileNote on 2021-05-02
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
@Service
public class XmlSqlService implements SqlService, SqlRegistry, SqlReader{
    public Map<String, String> sqlMap = new HashMap<String, String>();
    private String sqlmapFile;
    private SqlReader sqlReader;
    private SqlRegistry sqlRegistry;


    public void setSqlmapFile(String sqlmapFile) {
        this.sqlmapFile = sqlmapFile;
    }

    @PostConstruct
    public void loadSql() {
        this.sqlReader.read(this.sqlRegistry);
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        try{
            return this.sqlRegistry.findSql(key);
        }catch (SqlNotFoundException e){
            throw new SqlRetrievalFailureException(e.getMessage());
        }
    }

    private File getXmlFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    @Override
    public String findSql(String key) throws SqlNotFoundException {
        String sql = sqlMap.get(key);
        if (sql == null){
            throw new SqlNotFoundException(key +
                    "에 대한 SQL을 찾을 수 없습니다.");
        }else{
            return sql;
        }
    }

    @Override
    public void registerSql(String key, String sql) {
        sqlMap.put(key, sql);
    }

    @Override
    public void read(SqlRegistry sqlRegistry) {
        String contextPath = Sqlmap.class.getPackage().getName();
        try {
            JAXBContext context = JAXBContext.newInstance(contextPath);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Sqlmap sqlmap = (Sqlmap) unmarshaller.unmarshal(getXmlFile("sqlmap.xml"));

            for (SqlType sql : sqlmap.getSql()) {
                sqlRegistry.registerSql(sql.getKey(), sql.getValue());
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
