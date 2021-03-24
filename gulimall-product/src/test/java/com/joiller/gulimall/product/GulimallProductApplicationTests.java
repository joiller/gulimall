package com.joiller.gulimall.product;

import com.aliyun.oss.OSSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Test
    void contextLoads() {
    }

    @Test
    void upload() throws FileNotFoundException {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//        String endpoint = "oss-cn-shanghai.aliyuncs.com";
//// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "LTAI5tLmJv9oWR8DcR5cUNsS";
//        String accessKeySecret = "JPpyD3KgMk6BSWyixGzitA3mw0Vfzn";
//
//// 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("/Users/jianghuilai/Desktop/Screen Shot 2020-06-14 at 9.01.39 PM.png");
// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("mine-gulimall", "scene.jpeg", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
