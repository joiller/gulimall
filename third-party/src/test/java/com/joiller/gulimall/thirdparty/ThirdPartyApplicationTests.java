package com.joiller.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class ThirdPartyApplicationTests {

    @Autowired
    OSSClient ossClient;

    @Test
    void contextLoads() {
    }

    @Test
    void ossTest() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/jianghuilai/Desktop/Screen Shot 2020-02-29 at 3.49.09 PM.png");
        PutObjectResult putObjectResult = ossClient.putObject("mine-gulimall", "ohe.jpeg", fileInputStream);
        ossClient.shutdown();
        System.out.println("okok");
    }
}
