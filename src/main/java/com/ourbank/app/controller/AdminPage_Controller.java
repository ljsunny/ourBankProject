package com.ourbank.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ourbank.app.bean.DepositBoard_Bean;
import com.ourbank.app.bean.SavingBoard_Bean;
import com.ourbank.app.service.AdminPage_Service;
import com.ourbank.app.service.MyPage_Service;
import com.ourbank.app.service.User_Service;

@Controller
public class AdminPage_Controller {

	@Autowired
	private AdminPage_Service adminService;

	@RequestMapping(value = "/manageDeposit.do", method = RequestMethod.GET)
	public String manageDeposit(Model model) {
		return "/board_Adminpage/manageDeposit";
	}
	@RequestMapping(value = "/manageSaving.do", method = RequestMethod.GET)
	public String manageSaving(Model model) {
		return "/board_Adminpage/manageSaving";
	}

	private static String getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	@RequestMapping(value = "/depositProduct.do", method = RequestMethod.GET)
	public String depositProduct(Model model) {
		try {

			int total = 0;

			// parsing�� url ����(API Ű �����ؼ�)
			String url = "http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.xml?auth=b39a4b83238cf557b9afec85628cbb19&topFinGrpNo=020000&pageNo=1";

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			ArrayList<DepositBoard_Bean> all_list = new ArrayList();

			// root tag
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// �Ľ��� tag-baseinfo
			NodeList BasenList = doc.getElementsByTagName("baseinfo");
			// �Ľ��� tag-option
			NodeList OptionList = doc.getElementsByTagName("option");

			for (int temp = 0; temp < BasenList.getLength(); temp++) {// baseinfo �κ�
				Node nNode1 = BasenList.item(temp);
				Node nNode2 = OptionList.item(temp);
				DepositBoard_Bean depositBean = new DepositBoard_Bean();
				total += 1;

				if (nNode1.getNodeType() == Node.ELEMENT_NODE) {// base info
					// arraylist(bean(������))�� ���� DB�� �ֱ�
					Element eElement = (Element) nNode1;
					/////////////// ����غ�
					

					//////////// �� ����
					depositBean.setDcls_month(getTagValue("dcls_month", eElement).toString());
					depositBean.setFin_co_no(getTagValue("fin_co_no", eElement).toString());
					depositBean.setKor_co_nm(getTagValue("kor_co_nm", eElement).toString());
					depositBean.setFin_prdt_cd(getTagValue("fin_prdt_cd", eElement).toString());
					depositBean.setFin_prdt_nm(getTagValue("fin_prdt_nm", eElement).toString());
					depositBean.setJoin_way(getTagValue("join_way", eElement).toString());
					depositBean.setMtrt_int(getTagValue("mtrt_int", eElement).toString());
					depositBean.setSpcl_cnd(getTagValue("spcl_cnd", eElement).toString());
					depositBean.setJoin_deny(getTagValue("join_deny", eElement).toString());
					depositBean.setJoin_member(getTagValue("join_member", eElement).toString());
					depositBean.setEtc_note(getTagValue("etc_note", eElement).toString());
					depositBean.setMax_limit(getTagValue("max_limit", eElement));
					depositBean.setDcls_strt_day(getTagValue("dcls_strt_day", eElement).toString());
					depositBean.setDcls_end_day(getTagValue("dcls_end_day", eElement));
					depositBean.setFin_co_subm_day(getTagValue("fin_co_subm_day", eElement).toString());

				} // if end

				if (nNode2.getNodeType() == Node.ELEMENT_NODE) {// option info
					// arraylist(bean(������))�� ���� DB�� �ֱ�
					Element eElement = (Element) nNode2;
					
					//////////// �� ����
					depositBean.setIntr_rate_type(getTagValue("intr_rate_type", eElement).toString());
					depositBean.setIntr_rate_type_nm(getTagValue("intr_rate_type_nm", eElement).toString());
					depositBean.setSave_trm(getTagValue("save_trm", eElement).toString());
					depositBean.setIntr_rate(getTagValue("intr_rate", eElement).toString());
					depositBean.setIntr_rate2(getTagValue("intr_rate2", eElement).toString());

					all_list.add(depositBean);
					System.out.println("total " + total);
				} // if end

			} // for end
			//�ִ� �����ʹ� ����
			adminService.deleteDeposit();
			//������ �ֱ�
			adminService.insertDeposit(all_list);
		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end
		
		return "redirect:bankInfo.do?ds=0";
	}
	
	@RequestMapping(value = "/savingProduct.do", method = RequestMethod.GET)
	public String savingProduct(Model model) {
		//�ִ� �����ʹ� ����
		adminService.deleteSaving();
		int page=1;
		while(true) {
		try {

			int total = 0;

			// parsing�� url ����(API Ű �����ؼ�)
			String url = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.xml?auth=b39a4b83238cf557b9afec85628cbb19&topFinGrpNo=020000&pageNo="+page;

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			ArrayList<SavingBoard_Bean> all_list = new ArrayList();

			// root tag
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// �Ľ��� tag-baseinfo
			NodeList BasenList = doc.getElementsByTagName("baseinfo");
			// �Ľ��� tag-option
			NodeList OptionList = doc.getElementsByTagName("option");

			for (int temp = 0; temp < BasenList.getLength(); temp++) {// baseinfo �κ�
				Node nNode1 = BasenList.item(temp);
				Node nNode2 = OptionList.item(temp);
				SavingBoard_Bean savingBean = new SavingBoard_Bean();
				total += 1;

				if (nNode1.getNodeType() == Node.ELEMENT_NODE) {// base info
					// arraylist(bean(������))�� ���� DB�� �ֱ�
					Element eElement = (Element) nNode1;
					/////////////// ����غ�
					

					//////////// �� ����
					savingBean.setDcls_month(getTagValue("dcls_month", eElement).toString());
					savingBean.setFin_co_no(getTagValue("fin_co_no", eElement).toString());
					savingBean.setKor_co_nm(getTagValue("kor_co_nm", eElement).toString());
					savingBean.setFin_prdt_cd(getTagValue("fin_prdt_cd", eElement).toString());
					savingBean.setFin_prdt_nm(getTagValue("fin_prdt_nm", eElement).toString());
					savingBean.setJoin_way(getTagValue("join_way", eElement).toString());
					savingBean.setMtrt_int(getTagValue("mtrt_int", eElement).toString());
					savingBean.setSpcl_cnd(getTagValue("spcl_cnd", eElement).toString());
					savingBean.setJoin_deny(getTagValue("join_deny", eElement).toString());
					savingBean.setJoin_member(getTagValue("join_member", eElement).toString());
					savingBean.setEtc_note(getTagValue("etc_note", eElement).toString());
					savingBean.setMax_limit(getTagValue("max_limit", eElement));
					savingBean.setDcls_strt_day(getTagValue("dcls_strt_day", eElement).toString());
					savingBean.setDcls_end_day(getTagValue("dcls_end_day", eElement));
					savingBean.setFin_co_subm_day(getTagValue("fin_co_subm_day", eElement).toString());

				} // if end

				if (nNode2.getNodeType() == Node.ELEMENT_NODE) {// option info
					// arraylist(bean(������))�� ���� DB�� �ֱ�
					Element eElement = (Element) nNode2;
					

					//////////// �� ����
					savingBean.setIntr_rate_type(getTagValue("intr_rate_type", eElement).toString());
					savingBean.setIntr_rate_type_nm(getTagValue("intr_rate_type_nm", eElement).toString());
					savingBean.setRsrv_type(getTagValue("rsrv_type", eElement).toString());
					savingBean.setRsrv_type_nm(getTagValue("rsrv_type_nm", eElement).toString());
					savingBean.setSave_trm(getTagValue("save_trm", eElement).toString());
					savingBean.setIntr_rate(getTagValue("intr_rate", eElement).toString());
					savingBean.setIntr_rate2(getTagValue("intr_rate2", eElement).toString());
					all_list.add(savingBean);
					
					System.out.println("total " + total);
				} // if end

			} // for end
			
			//������ �ֱ�
			adminService.insertSaving(all_list);
			page+=1;
			if(page>12) break;
		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end
		}
		return "redirect:bankInfo.do?ds=1";
	}
	
	@RequestMapping(value ="/bankInfo.do", method = RequestMethod.GET)
	public String bankInfo(
			HttpServletResponse response,
			@RequestParam("ds") int ds , Model model) {
		//����ȸ�� ���� ����
		adminService.deleteBank();
		int page=1;
		while(true) {
		try {

			int total = 0;

			// parsing�� url ����(API Ű �����ؼ�)
			String url = "http://finlife.fss.or.kr/finlifeapi/companySearch.xml?auth=b39a4b83238cf557b9afec85628cbb19&topFinGrpNo=020000&pageNo="+page;

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);

			ArrayList<SavingBoard_Bean> all_list = new ArrayList();

			// root tag
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			// �Ľ��� tag-baseinfo
			NodeList BasenList = doc.getElementsByTagName("baseinfo");

			for (int temp = 0; temp < BasenList.getLength(); temp++) {// baseinfo �κ�
				Node nNode1 = BasenList.item(temp);
				
				SavingBoard_Bean savingBean = new SavingBoard_Bean();
				total += 1;

				if (nNode1.getNodeType() == Node.ELEMENT_NODE) {// base info
					// arraylist(bean(������))�� ���� DB�� �ֱ�
					Element eElement = (Element) nNode1;
					System.out.println(getTagValue("fin_co_no", eElement));
					System.out.println(getTagValue("kor_co_nm", eElement));
					System.out.println(getTagValue("homp_url", eElement));
					//////////// �� ����
					savingBean.setFin_co_no(getTagValue("fin_co_no", eElement).toString());
					savingBean.setKor_co_nm(getTagValue("kor_co_nm", eElement).toString());
					savingBean.setHomp_url(getTagValue("homp_url", eElement).toString());
					all_list.add(savingBean);
				} // if end

			} // for end
			
			//������ �ֱ�
			adminService.insertBank(all_list);;
			page+=1;
			if(page>6) break;
		} catch (Exception e) {
			e.printStackTrace();
		} // try~catch end
		}
		System.out.println(ds);
		if(ds==0) {//���� ������ ��������
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('���� ������ �Է��� �Ϸ�Ǿ����ϴ�.');</script>"); 
		        out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "board_Adminpage/manageDeposit";
		}
		//���� ������ ��������
		PrintWriter out;
		try {
			out = response.getWriter();
			out.println("<script>alert('���� ������ �Է��� �Ϸ�Ǿ����ϴ�.');</script>"); 
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "board_Adminpage/manageSaving";
	}
}
