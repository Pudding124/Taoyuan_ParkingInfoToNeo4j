package ntou.taoyuan.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
public class DataUpdateController {
	
	@Autowired
	ParkSpaceRepository parkSpaceRepository;
	
	ParkSpaceBean parkSpaceBean;
	
	@Scheduled(cron = "0 0/1 * * * ?") //每過一分鐘 自動更新每個停車場的車位資料
	public void DataUpdate() {
		
		ExecutorService cachedThreadPool = Executors.newScheduledThreadPool(10); //分配執行續
		
	    RestTemplate restTemplate = new RestTemplate();
	    String str=restTemplate.getForObject(ServerURL.TaoyuanOpendataAPI,String.class);
	    System.out.println(str);
	  
			try {
				JSONObject jsonObjectFirst = new JSONObject(str);
				if(jsonObjectFirst.getBoolean("success") == true) {
				
					JSONObject result = jsonObjectFirst.getJSONObject("result");
			
					JSONArray jsonArray = result.getJSONArray("records");
					
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject records = jsonArray.getJSONObject(i);
				  
						String address = records.getString("address");
						int totalSpace = records.getInt("totalSpace");
						String surplusSpace = records.getString("surplusSpace");
				    	
						cachedThreadPool.execute(new Runnable(){ // 分配任務
							
					        @Override
					        public void run(){
					        	parkSpaceBean = parkSpaceRepository.findByAddress(address);
								parkSpaceBean.setSurplusSpace(surplusSpace);
								parkSpaceBean.setTotalSpace(totalSpace);
								parkSpaceRepository.save(parkSpaceBean);	
					        }
					    });	
					
					}
				
				}else {
					System.out.println("資料更新失敗");
				}
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("已更新");
		}

}
