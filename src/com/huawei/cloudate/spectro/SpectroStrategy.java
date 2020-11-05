package com.huawei.cloudate.spectro;

import com.huawei.cloudate.spectro.exception.SpectroExcetion;

public interface SpectroStrategy {

	boolean init(String sn) throws SpectroExcetion;
	
	double[] measure() throws SpectroExcetion;
	
	String export(String path) throws SpectroExcetion;
}
