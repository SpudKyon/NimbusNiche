package com.spud.nimbus.leaf.service;

import com.spud.nimbus.leaf.IDGen;
import com.spud.nimbus.leaf.common.Result;
import com.spud.nimbus.leaf.exception.InitException;
import com.spud.nimbus.leaf.segment.SegmentIDGenImpl;
import com.spud.nimbus.leaf.segment.dao.IDAllocDao;
import com.spud.nimbus.leaf.segment.dao.impl.IDAllocDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author spud
 * @date 2024/2/24
 */
@Service("SegmentService")
@Slf4j
public class SegmentService {

	private final IDGen idGen;

	public SegmentService(DataSource dataSource) throws InitException {
		// Config Dao
		IDAllocDao dao = new IDAllocDaoImpl(dataSource);

		// Config ID Gen
		idGen = new SegmentIDGenImpl();
		((SegmentIDGenImpl) idGen).setDao(dao);
		if (idGen.init()) {
			log.info("Segment Service Init Successfully");
		}
		else {
			throw new InitException("Segment Service Init Fail");
		}

	}

	public Result getId(String key) {
		return idGen.get(key);
	}

	public SegmentIDGenImpl getIdGen() {
		if (idGen instanceof SegmentIDGenImpl) {
			return (SegmentIDGenImpl) idGen;
		}
		return null;
	}

}
