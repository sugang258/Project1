package com.gang.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gang.home.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		
		Long totalCount = qnaMapper.getCount();
		pager.getNum(totalCount);
		pager.getRowNum();
		return qnaMapper.getList(pager);
	}
}
