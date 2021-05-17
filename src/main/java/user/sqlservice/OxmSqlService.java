package user.sqlservice;

import org.springframework.oxm.Unmarshaller;
import user.sqlservice.jaxb.SqlType;
import user.sqlservice.jaxb.Sqlmap;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mileNote on 2021-05-17
 * Blog : https://milenote.tistory.com
 * Github : https://github.com/SimKyunam
 */
public class OxmSqlService implements SqlService{
    private  final BaseSqlService baseSqlService = new BaseSqlService();
    private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
    private SqlRegistry sqlRegistry = new HashMapSqlRegistry();

    public void setSqlRegistry(SqlRegistry sqlRegistry) {
        this.sqlRegistry = sqlRegistry;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.oxmSqlReader.setUnmarshaller(unmarshaller);
    }

    public void setSqlmapFile(String sqlmapFile) {
        this.oxmSqlReader.setSqlmapFile(sqlmapFile);
    }

    @PostConstruct
    public void loadSql(){
        this.baseSqlService.setSqlReader(this.oxmSqlReader);
        this.baseSqlService.setSqlRegistry(this.sqlRegistry);

        this.baseSqlService.loadSql();
    }

    @Override
    public String getSql(String key) throws SqlRetrievalFailureException {
        return this.baseSqlService.getSql(key);
    }

    private class OxmSqlReader implements SqlReader{
        private Unmarshaller unmarshaller;
        private static final String DEFAULT_SQLMAP_FILE = "mapper/sqlmap.xml";
        private String sqlmapFile = DEFAULT_SQLMAP_FILE;

        public void setUnmarshaller(Unmarshaller unmarshaller) {
            this.unmarshaller = unmarshaller;
        }

        public void setSqlmapFile(String sqlmapFile) {
            this.sqlmapFile = sqlmapFile;
        }

        @Override
        public void read(SqlRegistry sqlRegistry) {
            try{
                Source xmlSource = new StreamSource(
                        getXmlFile("mapper/sqlmap.xml"));

                Sqlmap sqlmap = (Sqlmap) this.unmarshaller.unmarshal(xmlSource);
                for(SqlType sql : sqlmap.getSql()){
                    sqlRegistry.registerSql(sql.getKey(), sql.getValue());
                }
            } catch (IOException e){
                throw new RuntimeException(this.sqlmapFile + "을 가져올 수 없습니다.", e);
            }
        }

        private File getXmlFile(String fileName) {
            ClassLoader classLoader = getClass().getClassLoader();
            return new File(classLoader.getResource(fileName).getFile());
        }
    }
}
