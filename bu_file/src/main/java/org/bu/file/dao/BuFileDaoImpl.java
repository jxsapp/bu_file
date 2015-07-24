/**
 * Aug 29, 2012
 */
package org.bu.file.dao;

import java.io.Serializable;

import org.bu.file.model.BuFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("buFileDao")
public class BuFileDaoImpl implements BuFileDao {

	private static final String PREFIX_KEY = "BuFile.";

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public void put(final BuFile buFile, final long seconds) {

		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keys = redisTemplate.getStringSerializer().serialize(PREFIX_KEY + buFile.getFileKey());
				connection.set(keys, buFile.getFileData());
				if (seconds > 0) {
					connection.expire(keys, seconds);
				}
				return null;
			}
		});
	}

	@Override
	public BuFile get(final String type, final String path) {
		return redisTemplate.execute(new RedisCallback<BuFile>() {
			@Override
			public BuFile doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(PREFIX_KEY + BuFile.getKey(type, path));
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					BuFile BuFile = new BuFile(type, path);
					BuFile.setFileData(value);
					return BuFile;
				}
				return null;
			}
		});
	}

	@Override
	public long delete(final String fileName) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.del(redisTemplate.getStringSerializer().serialize(PREFIX_KEY + fileName));
			}
		});
	}
}
