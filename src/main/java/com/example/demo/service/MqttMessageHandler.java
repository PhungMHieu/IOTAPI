package com.example.demo.service;


import com.example.demo.Controller.DataSensorRepository;
import com.example.demo.model.DataSensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageHandler {

    @Autowired
    private DataSensorRepository dataSensorRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void processMessage(String jsonMessage) {
        try {
            // Chuyển đổi JSON thành đối tượng DataSensor
            DataSensor data = objectMapper.readValue(jsonMessage, DataSensor.class);
            // Lưu dữ liệu vào database
            dataSensorRepository.save(data);
            System.out.println("Saved Data: " + data);
        } catch (Exception e) {
//            e.printStackTrace();
        	
        }
    }
    
}
