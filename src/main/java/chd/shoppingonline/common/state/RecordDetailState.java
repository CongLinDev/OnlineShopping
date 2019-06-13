package chd.shoppingonline.common.state;

public enum RecordDetailState {


    ARREARAGE((short)1, "未付款"),//未付款
    PREPARE_SHIPMENT((short)2, "未发货"),//未发货
    SHIPMENT((short)3, "已发货"),//已发货
    DELIVERED((short)4, "已收货"),//已收货
    RETURNED((short)5, "已退货"),


    //退货中
    RETURNING1((short)7, "申请退货"),
    RETURNING2((short)8, "申请退货"),
    RETURNING3((short)9, "申请退货"),
    RETURNING4((short)10, "申请退货");

    public static final short FLAG = 5;

    private short shortValue;
    private String stringValue;
    RecordDetailState(short shortValue, String stringValue){
        this.shortValue = shortValue;
        this.stringValue = stringValue;
    }

    public short getShortValue(){
        return shortValue;
    }

    public String getStringValue(){
        return stringValue;
    }

    public static String getStringValue(Short shortValue){
        for (RecordDetailState recordDetailState : RecordDetailState.values()){
            if(recordDetailState.getShortValue() == shortValue){
                return recordDetailState.getStringValue();
            }
        }
        return null;
    }
}
