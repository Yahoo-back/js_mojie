package com.hyy.ifm.util.sharekey;

import com.visionalsun.util.bean.ShareKey;

import java.util.ArrayList;
import java.util.List;

/**
 * <i>Ycfq : 超好借</i>
 * 
 * @author 饶瑞文
 *
 */
public class YcbtShareKey extends ShareKey {

	/** <i>命名空间 */
	public static final String NAMESPACE = "YCBT.NAMESPACE";

	/** <i>悦才白条月供过滤 */
	public static List<Double> ALLOW_MONTHLY = new ArrayList<Double>();
	/** <i>悦才白条申请金额过滤 */
	public static List<Double> ALLOW_APPLY_AMT = new ArrayList<Double>();

	static {
		ALLOW_MONTHLY.add(2366.67);
		ALLOW_MONTHLY.add(1183.33);
		
		ALLOW_APPLY_AMT.add(20000.00);
		ALLOW_APPLY_AMT.add(10000.00);
	}

}
