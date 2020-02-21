package com.ourbank.app.bean;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import javax.validation.constraints.NotBlank;
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003

@Repository
public class FreeBoard_Bean {
	
	private String category;
	private int category_num;
	private String id; 
	private int idx_num;
	private String subject;
	private String content;
	
	private String created_date;
	private int hits;
	private String filename;
	private long filesize;
	private MultipartFile file;
	private int re_idx;
	private int ref;
	private int step;
	private int depth;
	
	
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
<<<<<<< HEAD
=======
@Repository
public class FreeBoard_Bean {
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
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
	public int getIdx_num() {
		return idx_num;
	}
	public void setIdx_num(int idx_num) {
		this.idx_num = idx_num;
	}
<<<<<<< HEAD
=======
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
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
	
	
	
	
	

<<<<<<< HEAD
=======
	@Override
	public String toString() {
		return "FreeBoard_Bean [idx=" + idx + ", id=" + id + ", subject=" + subject + ", content=" + content
				+ ", created_date=" + created_date + ", hits=" + hits + ", category=" + category + ", filename="
				+ filename + ", filesize=" + filesize + ", file=" + file + "]";
	}
	
>>>>>>> f468c823fb38f81388f1f0cd521a2d8766679c8d
=======
>>>>>>> 029abcd43ddfc8ce09fdf531da8e2117ef4a1003
}
