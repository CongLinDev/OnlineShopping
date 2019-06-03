package chd.shoppingonline.common.state;

public enum RecordDetailState {
    ARREARAGE((short)1, "ARREARAGE"),//未付款
    PREPARE_SHIPMENT((short)2, "PREPARE_SHIPMENT"),//未发货
    SHIPMENT((short)3, "SHIPMENT"),//已发货
    DELIVERED((short)4, "DELIVERED"),//已收货
    RETURNED((short)5, "RETURNED");//已退货

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
}
