package ntou.taoyuan.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ntou.taoyuan.domain.ParkAbsolutePositionBean;
import ntou.taoyuan.domain.ParkInfoBean;
import ntou.taoyuan.domain.ParkPriceBean;
import ntou.taoyuan.domain.ParkSpaceBean;
import ntou.taoyuan.domain.repository.ParkAbsolutePositionRepository;
import ntou.taoyuan.domain.repository.ParkPriceRepository;
import ntou.taoyuan.domain.repository.ParkSpaceRepository;
import ntou.taoyuan.server.ServerURL;

@RestController
public class WebController {

	@Autowired
	ParkAbsolutePositionRepository parkAbsolutePositionRepository;
	@Autowired
	ParkPriceRepository parkPriceRepository;
	@Autowired
	ParkSpaceRepository parkSpaceRepository;
	
	ParkInfoBean parkInfoBean;
	ParkPriceBean parkPriceBean;
	ParkSpaceBean parkSpaceBean;
	ParkAbsolutePositionBean parkAbsolutePositionBean;
	
	@RequestMapping(value = "/Load")
	   public String CreateNode(){
		RestTemplate restTemplate = new RestTemplate();
		String str=restTemplate.getForObject(ServerURL.TaoyuanOpendataAPI,String.class);
		System.out.println(str);
		
		try {
			JSONObject jsonObjectFirst = new JSONObject(str);
			if(jsonObjectFirst.getBoolean("success") == true) { // 每一種資料 各分節點 無法用Gson
				
				JSONObject result = jsonObjectFirst.getJSONObject("result");
			
				JSONArray jsonArray = result.getJSONArray("records");
				System.out.println("多長"+jsonArray.length());
				for (int i = 0; i < jsonArray.length(); i++) { 
				    JSONObject records = jsonArray.getJSONObject(i);
				    String areaId = records.getString("areaId");
				    String areaName = records.getString("areaName");
				    String parkId = records.getString("parkId");
				    String parkName = records.getString("parkName");
				    String introduction = records.getString("introduction");
				    String address = records.getString("address");
				    Double wgsX = records.getDouble("wgsX");
				    Double wgsY = records.getDouble("wgsY");
				    int totalSpace = records.getInt("totalSpace");
				    String surplusSpace = records.getString("surplusSpace");
				    String payGuide =  records.getString("payGuide");
				    
				    parkInfoBean = new ParkInfoBean(areaId, areaName, parkId, parkName, introduction, address);
				    
				    parkPriceBean = new ParkPriceBean(payGuide);
				    
				    parkSpaceBean = new ParkSpaceBean(totalSpace, surplusSpace);
				    
				    parkAbsolutePositionBean = new ParkAbsolutePositionBean(wgsX, wgsY);

					parkPriceBean.addPayForRelationship(parkPriceBean, parkInfoBean);
					parkPriceRepository.save(parkPriceBean);
					parkSpaceBean.addRemainderOfRelationship(parkSpaceBean, parkInfoBean);
					parkSpaceRepository.save(parkSpaceBean);
					parkAbsolutePositionBean.addLocatedInRelationship(parkAbsolutePositionBean, parkInfoBean);
					parkAbsolutePositionRepository.save(parkAbsolutePositionBean);
					
				}
				
			}else {
				System.out.println("初始資料取得失敗");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Done";
		
	}
}
