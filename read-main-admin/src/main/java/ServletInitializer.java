/**
 * Created by yuehao on 2017/9/12.
 */

import com.readmain.admin.ReadMainAdminApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

//tomcat启动必须配置
@Configuration
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReadMainAdminApplication.class);
    }
}
