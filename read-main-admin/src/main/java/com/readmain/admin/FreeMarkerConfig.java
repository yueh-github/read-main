//package com.readmain.admin;
//
//import freemarker.template.TemplateException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
//import javax.annotation.PostConstruct;
//
///**
// * Created by yuehao on 2017/8/30.
// */
//@Configuration
//public class FreeMarkerConfig extends FreeMarkerViewResolver {
//
//    @Autowired
//    private freemarker.template.Configuration configuration;
//    @Autowired
//    private FreeMarkerViewResolver resolver;
//
//    @PostConstruct
//    public void setSharedVariable() {
//        try {
//            configuration.setDateFormat("yyyy/MM/dd");
//            configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
//
//
//            //下面三句配置的就是我自己的freemarker的自定义标签，在这里把标签注入到共享变量中去就可以在模板中直接调用了
//            //configuration.setSharedVariable("content_list", new ContentListDirective());
//            //configuration.setSharedVariable("article_list", new ArticleDirective());
//            //configuration.setSharedVariable("channel_list", new ChannelListDirective());
//
//
//            //setting配置
//            configuration.setSetting("template_update_delay", "1");
//            configuration.setSetting("default_encoding", "UTF-8");
//
//
//            //配置Freemarker视图解析器
//            resolver.setSuffix(".html"); //解析后缀为html的</span>
//            resolver.setCache(false);//是否缓存模板</span>
//            resolver.setRequestContextAttribute("request");//为模板调用时，调用request对象的变量名</span>
//            resolver.setOrder(0);
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }
//    }
//}
