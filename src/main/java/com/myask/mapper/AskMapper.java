package com.myask.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myask.domain.AskVO;

public interface AskMapper {
	public void insertAsk(@Param("askVO") AskVO requestVO) throws Exception;
	
	public List<AskVO> listNewAsk(@Param("parent_id") String parent_id) throws Exception;
	
	public List<AskVO> listCompletedAsk(@Param("parent_id") String parent_id) throws Exception;
	
	public AskVO selectAsk(@Param("parent_id") String parent_id, @Param("ask_code") long ask_code) throws Exception;
	
	public void updateAsk(@Param("askVO") AskVO requestVO) throws Exception;
	
	public void deleteAsk(@Param("ask_code") long ask_code) throws Exception;
}
