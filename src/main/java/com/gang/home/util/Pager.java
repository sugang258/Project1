package com.gang.home.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pager {
	
	private Long page;
	private Long startNum;
	private Long lastNum;
	private Long startRow;
	private Long lastRow;
	private Long perPage;
	private Long perBlock;
	
	private boolean pre;
	private boolean next;
	
	private String search;
	
	public Pager() {
		this.perPage=10L;
		this.perBlock=5L;
	}
	
	public void getRowNum() throws Exception{
		this.startRow = (this.getPage()-1)*perPage;
		this.lastRow = 10L;
	}
	
	public void getNum(Long totalCount) throws Exception {
		//2.totalCount로 totalPage구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount % this.getPerPage() != 0) {
			totalPage++;
		}

		if(this.getPage()>totalPage) {
			this.setPage(totalPage);
		}
		
		//3.totalPage로 totalBlock 구하기
		Long totalBlock = totalPage/this.getPerBlock();
		if(totalPage % this.getPerBlock() != 0) {
			totalBlock++;
		}
		
		//4. page로 curBlock 찾기
		Long curBlock = this.getPage()/this.getPerBlock();
		if(this.getPage()%this.getPerBlock() != 0) {
			curBlock++;
		}
		
		//5. curBlock으로 startNum, lastNum 구하기
		this.startNum = (curBlock - 1)*this.getPerBlock() + 1;
		this.lastNum = curBlock * this.getPerBlock();
		
		//6. curBlock이 마지막 block(totalBlock과 같을 때)
		if(curBlock == totalBlock) {
			this.lastNum = totalPage;
		}

		//7. 이전, 다음 블럭의 유무
		if(curBlock>1) {
			pre = true;
		}
		
		if(curBlock <= totalBlock) {
			next = true;
		}
		
		}
	
	public Long getPage() {
		
		if(this.page == null || this.page < 1) {
			this.page = 1L;
		}
		
		return page;
	}
	public Long getPerPage() {
		
		if(this.perPage == null) {
			this.perPage=10L;
		}
		
		return perPage;
	}
	
	public String getSearch() {
		if(this.search == null) {
			this.search="";
		}
		return search;
	}


	public void setSearch(String search) {
		this.search = search;
	}
	
	
	

}
