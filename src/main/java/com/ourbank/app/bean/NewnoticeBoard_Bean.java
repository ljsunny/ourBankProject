package com.ourbank.app.bean;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class NewnoticeBoard_Bean {
	int idx;
	String id;
	@NotBlank(message="작성자를 입력해주세요")
	String subject;
	@NotBlank(message="내용을 입력해주세요")
	String content;
	String created_date;
	int hits;
	String category;
	String filename;
	long filesize;
	
	MultipartFile file;
	private int category_num;
	private int board_idx; //게시판 전체 글 번호
	
	public int getCategory_num() {
		return category_num;
	}


	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}


	public int getBoard_idx() {
		return board_idx;
	}


	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	@Override
	public String toString() {
		return "NewnoticeBoard_Bean [idx=" + idx + ", id=" + id + ", subject=" + subject + ", content=" + content
				+ ", created_date=" + created_date + ", hits=" + hits + ", category=" + category + ", filename="
				+ filename + ", filesize=" + filesize + ", file=" + file + "]";
	}
	
}
