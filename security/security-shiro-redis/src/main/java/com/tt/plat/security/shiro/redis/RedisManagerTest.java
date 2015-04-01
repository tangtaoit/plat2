package com.tt.plat.security.shiro.redis;


import com.tt.plat.core.utils.SerializeUtils;

public class RedisManagerTest {
	
	
	public void testSet(){
		RedisManager redisManager  = new RedisManager();
		redisManager.setHost("10.165.122.204");
		redisManager.setPort(6379);
		redisManager.setExpire(2);
		redisManager.setTimeout(0);
		redisManager.setPassword("test");
		redisManager.init();
		

		String key = "abc";

		
		redisManager.set(key.getBytes(), SerializeUtils.serialize(""));
	}

	public static void main(String[] args){
		RedisManagerTest redisManagerTest = new RedisManagerTest();
		redisManagerTest.testSet();
	}


}
