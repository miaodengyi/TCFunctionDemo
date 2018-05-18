package com.mpt.aidlservice;

/*
*	Description ： VPN安全接入客户端信息获取接口
*	@version  	： 1.0
*/

interface IApnAndSafeKey {
	/*
	* 	接口说明：判断VPN通道是否已经建立	
	* 	返回值:	true,不区分通道模式，只要通道建立即返回真；
				false,通道处于关闭状态，无法访问内网；
	*/
	boolean XKF_GetVPNTunnelState();
	
	/*
	*	接口说明: 获取接入区部署的监测服务器的IP和Port
	*	返回值:	Jason格式的返回值(详见文档)
	*/
	String XKF_GetMonitorServerAddress();
	
	/*
	*	接口说明: 获取安全卡厂商的基本信息
	*	返回值: Jason格式的返回值(详见文档)
	*/
	String XKF_GetVendorInfo();
	
	/*
	*	接口说明: 获取安全卡的硬件序列号
	*	返回值: VPN安全接入客户端当前使用的安全卡的硬件序列号
	*/
	String XKF_GetCardID();
	
	/*
	*	接口说明: 获取安全卡证书
	*	返回值: VPN安全接入客户端当前使用的安全卡证书信息(BASE64编码后的X509格式数据)
	*/
	String XKF_ReadCert();
	
	/*
	*	接口说明: SM1算法加解密测试接口
	*	参数说明：sourceData，需要加解密的数据；testKey 加解密密钥；encryptFlag 加解密标识(0x00 ECB解密,0x01 ECB加密,0x10 CBC解密, 0x11 CBC加密),iv在CBC时有效，ECB时传null
	*	返回值: 加解密处理后的数据，如果是加密模式，则是密文，如果是解密模式，则是明文。
	*/
	byte[] XKF_SM1KEY(in byte[] sourceData,in byte[] testKey,int encryptFlag,in byte[] iv);	
	
	/*
	*	接口说明: 监测卡口令是否为弱口令
	*	返回值: TRUE，是弱口令；FALSE，不是弱口令
	*/
	boolean XKF_CheckWeakPassWD();
	
	/*
	*	接口说明: 卡口令尝试接口,连续多次（根据三方的卡设定值）失败后，安全卡将被锁定。
	*	返回值: TRUE，尝试成功；FALSE，尝试失败。
	*/
	boolean XKF_PassWordEnter(String testPin);
	
}