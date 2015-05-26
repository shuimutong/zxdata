/************************************************************************
 * @©: XiaoJian Health Science and technology Ltd.
 ***********************************************************************/
package cn.ourpass.zxdata.entity;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;


/**
 * @Author gaozx
 * @Description  微信支付返回结果
   @Date 2015年4月18日
 */
@XTable("wxpay_return")
public class WxPayReturn  implements BaseEntity {
	private static final long serialVersionUID = -3782539210187751135L;

	private Integer id; 
	
	private String appId; //公众账号ID

	private String mchId; //商户号
	
	private String deviceInfo; //设备号
	
	private String nonceStr; //随机字符串
	
	private String sign; //签名
	
	private String resultCode; //业务结果
	
	private String errCode; //错误代码
	
	private String errCodeDes; //错误代码描述
	
	private String openid; //用户标识
	
	private String isSubscribe; //是否关注公众账号
	
	private String tradeType; //交易类型
	
	private String bankType; //付款银行
	
	private String totalFee; //总金额
	
	private String feeType; //货币种类
	
	private String cashFee; //现金支付金额
	
	private String cashFeeType; //现金支付货币类型
	
	private String couponFee; //代金券或立减优惠金额
	 
	private String couponCount; //代金券或立减优惠使用数量
	
	private String couponBatchIdN; //代金券或立减优惠批次ID
	
	private String couponIdN; //代金券或立减优惠ID
	
	private String couponFeeN; //单个代金券或立减优惠支付金额
	
	private String transactionId; //微信支付订单号
	
	private String outTradeNo; //商户订单号
	
	private String attach; //商家数据包
	
	private String timeEnd; //支付完成时间
	
	@XId(isAutoIncrease=true)
	@XColumn(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XColumn(name = "appid",length=32)
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@XColumn(name = "mch_id",length=32)
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	@XColumn(name = "device_info",length=32)
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	@XColumn(name = "nonce_str",length=32)
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	@XColumn(name = "sign",length=32)
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	@XColumn(name = "result_code",length=16)
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@XColumn(name = "err_code",length=32)
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	@XColumn(name = "err_code_des",length=128)
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	@XColumn(name = "openid",length=128)
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@XColumn(name = "is_subscribe",length=1)
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	
	@XColumn(name = "trade_type",length=16)
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	@XColumn(name = "bank_type",length=16)
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
	@XColumn(name = "total_fee",length=100)
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
	@XColumn(name = "fee_type",length=8)
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	@XColumn(name = "cash_fee",length=100)
	public String getCashFee() {
		return cashFee;
	}
	public void setCashFee(String cashFee) {
		this.cashFee = cashFee;
	}
	
	@XColumn(name = "cash_fee_type",length=16)
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	
	@XColumn(name = "coupon_fee",length=10)
	public String getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(String couponFee) {
		this.couponFee = couponFee;
	}
	
	@XColumn(name = "coupon_count",length=1)
	public String getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(String couponCount) {
		this.couponCount = couponCount;
	}
	
	@XColumn(name = "coupon_batch_id_$n",length=20)
	public String getCouponBatchIdN() {
		return couponBatchIdN;
	}
	public void setCouponBatchIdN(String couponBatchIdN) {
		this.couponBatchIdN = couponBatchIdN;
	}
	
	@XColumn(name = "coupon_id_$n",length=20)
	public String getCouponIdN() {
		return couponIdN;
	}
	public void setCouponIdN(String couponIdN) {
		this.couponIdN = couponIdN;
	}
	
	@XColumn(name = "coupon_fee_$n",length=100)
	public String getCouponFeeN() {
		return couponFeeN;
	}
	public void setCouponFeeN(String couponFeeN) {
		this.couponFeeN = couponFeeN;
	}
	
	@XColumn(name = "transaction_id",length=32)
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@XColumn(name = "out_trade_no",length=32)
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	@XColumn(name = "attach",length=128)
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	@XColumn(name = "time_end",length=14)
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	@Override
	public String toString() {
		return "WxPayReturn [id=" + id + ", appId=" + appId + ", mchId="
				+ mchId + ", deviceInfo=" + deviceInfo + ", nonceStr="
				+ nonceStr + ", sign=" + sign + ", resultCode=" + resultCode
				+ ", errCode=" + errCode + ", errCodeDes=" + errCodeDes
				+ ", openid=" + openid + ", isSubscribe=" + isSubscribe
				+ ", tradeType=" + tradeType + ", bankType=" + bankType
				+ ", totalFee=" + totalFee + ", feeType=" + feeType
				+ ", cashFee=" + cashFee + ", cashFeeType=" + cashFeeType
				+ ", couponFee=" + couponFee + ", couponCount=" + couponCount
				+ ", couponBatchIdN=" + couponBatchIdN + ", couponIdN="
				+ couponIdN + ", couponFeeN=" + couponFeeN + ", transactionId="
				+ transactionId + ", outTradeNo=" + outTradeNo + ", attach="
				+ attach + ", timeEnd=" + timeEnd + "]";
	}
	
	
}
