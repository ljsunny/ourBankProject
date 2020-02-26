package com.ourbank.app.bean;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class NewnoticeBoard_Bean {
	
	private int board_idx; //게시판 전체 글 번호
	private String category;
	private int category_num;
	private int idx;
	private String id;
	@NotBlank(message="작성자를 입력해주세요")
	private String subject;
	@NotBlank(message="내용을 입력해주세요")
	private String content;
	private String created_date;
	private int hits;
	private String savings;
	private String filename;
	private long filesize;
	private MultipartFile file;
	private int re_idx;
	private int ref;
	private int step;
	private int depth;
	private String newnotice_case;
	

	
	
	
	public String getNewnotice_case() {
		return newnotice_case;
	}
	public void setNewnotice_case(String newnotice_case) {
		this.newnotice_case = newnotice_case;
	}
	public int getBoard_idx() {
		return board_idx;
	}
	public void setBoard_idx(int board_idx) {
		this.board_idx = board_idx;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCategory_num() {
		return category_num;
	}
	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}
	public String getSavings() {
		return savings;
	}
	public void setSavings(String savings) {
		this.savings = savings;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	
	public int getRe_idx() {
		return re_idx;
	}
	public void setRe_idx(int re_idx) {
		this.re_idx = re_idx;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}
