package com.study.stuinfo.web;

import com.study.stuinfo.dao.ClassDao;
import com.study.stuinfo.dao.GradeDao;
import com.study.stuinfo.dao.StudentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class WebApplicationTests {

	@Autowired
	StudentDao foo;
	@Autowired
	ClassDao cda;
	@Autowired
	GradeDao bar;

	// 将 Redis-x64-5.0.10 文件夹下的 redis.windows.conf 的protected mod 关了 (改成off)
	@Resource
	private RedisTemplate<String, String> rTemp;

	@Test
	void contextLoads() {
		System.out.println(rTemp.opsForSet().members("key1"));
	}
}
