package com.gang.home.board.qna;
 
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.gang.home.util.Pager;

@Repository
@Mapper
public interface QnaMapper {

	public List<QnaVO> getList(Pager pager) throws Exception;
	public long getCount(Pager pager) throws Exception;
	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
	public int setAdd(QnaVO qnaVO) throws Exception;
	public int setFileAdd(QnaFileVO qnaFileVO) throws Exception;
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception;
}
