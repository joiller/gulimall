# generator大致过程
用debug模式，方便跟踪运行过程

会在`com.baomidou.mybatisplus.generator.com.joiller.gulimall.common.config.builder.ConfigBuilder`
中进行配置的建立。整体可以看该方法的constructor：
将我们自己写的配置都导入进去，并且根据我们的配置，创建`tableInfoList`

然后在`com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine`相关类中，
进行模板的写入。
