package user.sqlservice.sqlmap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import user.dao.UserDao;

public class UserSqlMapConfig implements SqlMapConfig{
    @Override
    public Resource getSqlMapResource() {
        return new ClassPathResource("/mapper/sqlmap.xml", UserDao.class);
    }
}
