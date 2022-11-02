package com.gang.home.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gang.home.util.FileManager;
import com.gang.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String path;
	
	public List<QnaVO> getList(Pager pager) throws Exception{
		//Long totalCount = qnaMapper.getCount();
		//log.info("count : " , totalCount);
		pager.getNum(getCount(pager));
		pager.getRowNum();
		return qnaMapper.getList(pager);
	}
	
	public Long getCount(Pager pager) throws Exception{
		
		return qnaMapper.getCount(pager);
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	public int setAdd(QnaVO qnaVO) throws Exception{
		int result = qnaMapper.setAdd(qnaVO);

		File file = new File(path);
		
		if(!file.exists()) {
			boolean check = file.mkdirs();
		}	
		
		for(MultipartFile f : qnaVO.getFiles()) {
			if(!f.isEmpty()) {
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				qnaMapper.setFileAdd(qnaFileVO);
			}
		}
		return result;	
		}
}
