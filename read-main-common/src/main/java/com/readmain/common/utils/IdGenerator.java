package com.readmain.common.utils;


public class IdGenerator {

    private long nextId = 0L;
    private long serverId = 1L;
    private long preCalculateTime = 0L;

    public synchronized Long getNextId() {
        if (System.currentTimeMillis() - this.preCalculateTime > 60000L) {
            this.preCalculateTime = (System.currentTimeMillis() / 60000L);
            this.nextId = (this.preCalculateTime << (int) (4L + this.serverId) << 20);
            this.preCalculateTime *= 60000L;
        }
        return ++this.nextId;
    }

    public void setServerId() {
        Long rServerId = Long.valueOf(System.getProperty("serverId", "1"));
        if ((rServerId < 1L) || (rServerId > 16L)) {
            throw new IllegalArgumentException("The serverId of MemoryDbidGenerator must more 0 and less 16,  but it is " + rServerId);
        }
        this.serverId = rServerId;
//        JedisOperation jedis = ApplicationContextUtils.getBean("jedisOperation");
//        Long rServerId = jedis.incr("meId");
//        rServerId = rServerId % 4;
//        this.serverId = rServerId;
    }
}
