package com.ourbank.app.bean;

import org.springframework.stereotype.Repository;

@Repository
public class DepositBoard_Bean {
	String dcls_month; //���������
	String fin_co_no; //����ȸ���ڵ�
	String kor_co_nm; //����ȸ���
	String fin_prdt_cd; //������ǰ �ڵ�
	String fin_prdt_nm;//���� ��ǰ��
	String join_way;//���� ��� 
	String mtrt_int; //���� �� ������
	String spcl_cnd; //�������
	String join_deny; //��������
	String join_member; //���Դ��
	String etc_note; //��Ÿ ���ǻ���
	String max_limit; //�ְ��ѵ�
	String dcls_strt_day; //���� ������
	String dcls_end_day; //���� ������
	String fin_co_subm_day;//����ȸ�� ������
	String intr_rate_type;//���� �ݸ� ����
	String intr_rate_type_nm; //���� �ݸ� ������
	String save_trm; //���� �Ⱓ [����]
	String intr_rate; //���� �ݸ�
	String intr_rate2; //�ְ� ���ݸ�
	String homp_url; //Ȩ������ �ּ�
	
	//�� ��ǰ view �� /////////////////////////////////////////////
	String id;
	String dep_or_sav;
	
	
	

	public String getDep_or_sav() {
		return dep_or_sav;
	}
	public void setDep_or_sav(String dep_or_sav) {
		this.dep_or_sav = dep_or_sav;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHomp_url() {
		return homp_url;
	}
	public void setHomp_url(String homp_url) {
		this.homp_url = homp_url;
	}
	public String getDcls_month() {
		return dcls_month;
	}
	public void setDcls_month(String dcls_month) {
		this.dcls_month = dcls_month;
	}
	public String getFin_co_no() {
		return fin_co_no;
	}
	public void setFin_co_no(String fin_co_no) {
		this.fin_co_no = fin_co_no;
	}
	public String getKor_co_nm() {
		return kor_co_nm;
	}
	public void setKor_co_nm(String kor_co_nm) {
		this.kor_co_nm = kor_co_nm;
	}
	public String getFin_prdt_cd() {
		return fin_prdt_cd;
	}
	public void setFin_prdt_cd(String fin_prdt_cd) {
		this.fin_prdt_cd = fin_prdt_cd;
	}
	public String getFin_prdt_nm() {
		return fin_prdt_nm;
	}
	public void setFin_prdt_nm(String fin_prdt_nm) {
		this.fin_prdt_nm = fin_prdt_nm;
	}
	public String getJoin_way() {
		return join_way;
	}
	public void setJoin_way(String join_way) {
		this.join_way = join_way;
	}
	public String getMtrt_int() {
		return mtrt_int;
	}
	public void setMtrt_int(String mtrt_int) {
		this.mtrt_int = mtrt_int;
	}
	public String getSpcl_cnd() {
		return spcl_cnd;
	}
	public void setSpcl_cnd(String spcl_cnd) {
		this.spcl_cnd = spcl_cnd;
	}
	public String getJoin_deny() {
		return join_deny;
	}
	public void setJoin_deny(String join_deny) {
		this.join_deny = join_deny;
	}
	public String getJoin_member() {
		return join_member;
	}
	public void setJoin_member(String join_member) {
		this.join_member = join_member;
	}
	public String getEtc_note() {
		return etc_note;
	}
	public void setEtc_note(String etc_note) {
		this.etc_note = etc_note;
	}
	public String getMax_limit() {
		return max_limit;
	}
	public void setMax_limit(String max_limit) {
		this.max_limit = max_limit;
	}
	public String getDcls_strt_day() {
		return dcls_strt_day;
	}
	public void setDcls_strt_day(String dcls_strt_day) {
		this.dcls_strt_day = dcls_strt_day;
	}
	public String getDcls_end_day() {
		return dcls_end_day;
	}
	public void setDcls_end_day(String dcls_end_day) {
		this.dcls_end_day = dcls_end_day;
	}
	public String getFin_co_subm_day() {
		return fin_co_subm_day;
	}
	public void setFin_co_subm_day(String fin_co_subm_day) {
		this.fin_co_subm_day = fin_co_subm_day;
	}
	public String getIntr_rate_type() {
		return intr_rate_type;
	}
	public void setIntr_rate_type(String intr_rate_type) {
		this.intr_rate_type = intr_rate_type;
	}
	public String getIntr_rate_type_nm() {
		return intr_rate_type_nm;
	}
	public void setIntr_rate_type_nm(String intr_rate_type_nm) {
		this.intr_rate_type_nm = intr_rate_type_nm;
	}
	public String getSave_trm() {
		return save_trm;
	}
	public void setSave_trm(String save_trm) {
		this.save_trm = save_trm;
	}
	public String getIntr_rate() {
		return intr_rate;
	}
	public void setIntr_rate(String intr_rate) {
		this.intr_rate = intr_rate;
	}
	public String getIntr_rate2() {
		return intr_rate2;
	}
	public void setIntr_rate2(String intr_rate2) {
		this.intr_rate2 = intr_rate2;
	}
	
	
	
}
