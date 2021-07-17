package com.demo.service;

import com.demo.model.to.InterfaceResponseTO;

public interface InterfaceCallService {
	
	public Boolean call(String requestId);
	
	public InterfaceResponseTO callInterface(String requestId); 
	
	public InterfaceResponseTO getInfoOnID(String requestId);

}
