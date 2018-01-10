package ntou.taoyuan.controller;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ntou.taoyuan.domain.ParkInfoBean;
import ntou.taoyuan.domain.ParkPriceBean;
import ntou.taoyuan.domain.ParkSpaceBean;
import ntou.taoyuan.domain.repository.ParkInfoRepository;
import ntou.taoyuan.domain.repository.ParkPriceRepository;
import ntou.taoyuan.domain.repository.ParkSpaceRepository;
import ntou.taoyuan.response.AreaNameResponseBean;

/*
 * http://localhost:8787/GetAreaName
 * 
 * input:{"areaName":"桃園區"}
 * 
 * 
 * output:[{
 *       "areaName": "桃園區",
 *       "address": "桃園區縣府路一號",
 *       "parkName": "府前地下停車場",
 *       "introduction": "桃園市政府管轄之停車場",
 *       "totalSpace": 344,
 *       "surplusSpace": "84",
 *       "payGuide": "停車費率:30 元/小時。停車時數未滿一小時者，以一小時計算。逾一小時者，其超過之不滿一小時部分，如不逾三十分鐘者，以半小時計算；如逾三十分鐘者，仍以一小時計算收費。"
 *   }]
 * 
 */


@RestController
public class GetParkInfoAPI {
	
	@Autowired
	ParkInfoRepository parkInfoRepository;
	@Autowired
	ParkPriceRepository parkPriceRepository;
	@Autowired
	ParkSpaceRepository parkSpaceRepository;
	
	ParkSpaceBean parkSpaceBean;
	ParkPriceBean parkPriceBean;
	AreaNameResponseBean areaNameResponseBean;
	
	@RequestMapping(value = "/GetAreaName",method=RequestMethod.POST,produces="text/plain;charset=UTF-8")
	public String GetAreaName(@RequestBody String inputData) throws JSONException { //根據地區查詢相關停車場
		
		JSONObject jsonObject = new JSONObject(inputData);
		ArrayList<AreaNameResponseBean> arrayList = new ArrayList<AreaNameResponseBean>();
		Gson gson = new Gson();
		
		for(ParkInfoBean parkInfoBean : parkInfoRepository.findByAreaName(jsonObject.getString("areaName"))) {
			areaNameResponseBean = new AreaNameResponseBean();
			parkSpaceBean = parkSpaceRepository.findByAddress(parkInfoBean.getAddress()); //根據AreaName查詢車位
			parkPriceBean = parkPriceRepository.findByAddress(parkInfoBean.getAddress()); //根據AreaName查詢價錢
			
			areaNameResponseBean.setAreaName(parkInfoBean.getAreaName());
			areaNameResponseBean.setAddress(parkInfoBean.getAddress());
			areaNameResponseBean.setParkName(parkInfoBean.getParkName());
			areaNameResponseBean.setIntroduction(parkInfoBean.getIntroduction());
			areaNameResponseBean.setTotalSpace(parkSpaceBean.getTotalSpace());
			areaNameResponseBean.setSurplusSpace(parkSpaceBean.getSurplusSpace());
			areaNameResponseBean.setPayGuide(parkPriceBean.getPayGuide());
			arrayList.add(areaNameResponseBean);
		}
		String output = gson.toJson(arrayList);
		return output;
	}
	
	@RequestMapping(value = "/GetAllParkInfo",method=RequestMethod.GET)
	public String GetAreaName() {
		
		ArrayList<AreaNameResponseBean> arrayList = new ArrayList<AreaNameResponseBean>();
		Gson gson = new Gson();
		
		for(ParkInfoBean parkInfoBean:parkInfoRepository.findAll()){ // 載入桃園所有停車場
			areaNameResponseBean = new AreaNameResponseBean();
			parkSpaceBean = parkSpaceRepository.findByAddress(parkInfoBean.getAddress()); //根據AreaName查詢車位
			parkPriceBean = parkPriceRepository.findByAddress(parkInfoBean.getAddress()); //根據AreaName查詢價錢
			
			areaNameResponseBean.setAreaName(parkInfoBean.getAreaName());
			areaNameResponseBean.setAddress(parkInfoBean.getAddress());
			areaNameResponseBean.setParkName(parkInfoBean.getParkName());
			areaNameResponseBean.setIntroduction(parkInfoBean.getIntroduction());
			areaNameResponseBean.setTotalSpace(parkSpaceBean.getTotalSpace());
			areaNameResponseBean.setSurplusSpace(parkSpaceBean.getSurplusSpace());
			areaNameResponseBean.setPayGuide(parkPriceBean.getPayGuide());
			arrayList.add(areaNameResponseBean);
		}
		String output = gson.toJson(arrayList);
		return output;
	}
	
}
