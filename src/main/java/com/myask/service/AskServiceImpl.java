package com.myask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.mapper.AskMapper;

@Service
public class AskServiceImpl implements AskService {

	@Autowired
	private AskMapper askMapper;
}
