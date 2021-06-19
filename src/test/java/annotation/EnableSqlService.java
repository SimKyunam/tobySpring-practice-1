package annotation;

import org.springframework.context.annotation.Import;
import user.dao.SqlServiceContext;

@Import(value = SqlServiceContext.class)
public @interface EnableSqlService {
}
