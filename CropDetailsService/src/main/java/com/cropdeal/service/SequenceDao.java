package com.cropdeal.service;

import com.cropdeal.exception.SequenceException;

public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;

}