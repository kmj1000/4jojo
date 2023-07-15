package com.spring.controller;

import java.util.ArrayList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.spring.domain.P_DTO;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/*")
public class ApiPetDataController {
	// http://localhost:8080/4jojo/api/petdata
    private final ApiService service;
    private static final int max = 20;
    private static String serviceKey = "8mI5YJHYDClBCO0nVGTefXN%2FNRNDL4R68OP9EmufvlXPqdTKQSDm%2BsFUOYWKMuHHs%2Bi%2B1wxPQXr5HDnyjtr%2B8A%3D%3D";
    @RequestMapping("/petdata")
    public String fetchPetData() {
        // original main() 메소드에서 하는 작업들을 여기로 옮깁니다.
    	
        // heritage 객체들을 저장할 list
        ArrayList<P_DTO> list = new ArrayList<>();
      
        try {
            // parsing할 url 지정(API 키 포함해서)
            for (int i = 1; i < max; i++) {
            	String url = "https://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?"
                        + "pageNo="+i+"&"
                           + "numOfRows=1000&"
                           + "serviceKey="+serviceKey;
      
                  DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
                  DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
                  Document doc = dBuilder.parse(url);
                  
                  // root tag
                  doc.getDocumentElement().normalize();
                  System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
      
                  // 파싱할 tag
                  NodeList nList = doc.getElementsByTagName("item");
                  System.out.println("파싱할 리스트 수 : " + nList.getLength());
                  System.out.println("여기1");
                  
                  for (int temp = 0; temp < nList.getLength(); temp++) {
                     Node nNode = nList.item(temp);
                     if (nNode.getNodeType() == Node.ELEMENT_NODE) {
      
                        Element eElement = (Element) nNode;
      
                        // heritage vo를 저장할 객체
                        P_DTO heri = new P_DTO();
      
                        heri.setHappenDt(getTagValue("happenDt", eElement)); // 종목코드
                        heri.setHappenPlace(getTagValue("happenPlace", eElement)); // 지정번호
                        heri.setKindCd(getTagValue("kindCd", eElement));
                        heri.setColorCd(getTagValue("colorCd", eElement)); // 시도코드
                        heri.setAge(getTagValue("age", eElement)); // 위도
                        heri.setWeight(getTagValue("weight", eElement)); // 경도
                        heri.setNoticeNo(getTagValue("noticeNo", eElement));
                        heri.setNoticeSdt(getTagValue("noticeSdt", eElement));
                        heri.setNoticeEdt(getTagValue("noticeEdt", eElement));
                        heri.setPopfile(getTagValue("popfile", eElement));
                        heri.setProcessState(getTagValue("processState", eElement));
                        heri.setSexCd(getTagValue("sexCd", eElement));
                        heri.setNeuterYn(getTagValue("neuterYn", eElement));
                        heri.setSpecialMark(getTagValue("specialMark", eElement));
                        heri.setCareNm(getTagValue("careNm", eElement));
                        heri.setCareAddr(getTagValue("careAddr", eElement));
                        heri.setCareTel(getTagValue("careTel", eElement));
                        list.add(heri);
      
                        // 서비스 시작!
                        
      
                       service.regitsterPetData(heri);
                     }
                     System.out.println("들어가는중");
                  }
               } // if end

            // for end
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/api/api"; // 실행 후 결과를 표시할 view를 반환합니다.
    }

    // 기존의 main() 메소드를 삭제하거나 주석합니다.

    // tag값의 정보를 가져오는 메소드
    public static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
           return null;
        return nValue.getNodeValue();
     }
}