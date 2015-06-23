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

	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;

	@Override
	public void put(final BuFile BuFile, final long seconds) {

		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keys = redisTemplate.getStringSerializer().serialize("BuFile.fileName." + BuFile.getFileName());
				connection.set(keys, BuFile.getFileData());
				if (seconds > 0) {
					connection.expire(keys, seconds);
				}
				return null;
			}
		});
	}

	@Override
	public BuFile get(final String fileName) {
		return redisTemplate.execute(new RedisCallback<BuFile>() {
			@Override
			public BuFile doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("BuFile.fileName." + fileName);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					BuFile BuFile = new BuFile();
					BuFile.setFileData(value);
					BuFile.setFileName(fileName);
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
				return connection.del(redisTemplate.getStringSerializer().serialize("BuFile.fileName." + fileName));
			}
		});
	}
}
