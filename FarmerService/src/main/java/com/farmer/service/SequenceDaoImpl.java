package com.farmer.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.farmer.exception.SequenceException;
import com.farmer.model.SequenceId;

@Repository
public class SequenceDaoImpl implements SequenceDao {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public long getNextSequenceId(String key) throws SequenceException {
		
		 SequenceId counter = mongoOperations.findAndModify(query(where("_id").is(key)),
	                new Update().inc("seq",1), options().returnNew(true).upsert(true),
	                SequenceId.class);
	        return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

}
