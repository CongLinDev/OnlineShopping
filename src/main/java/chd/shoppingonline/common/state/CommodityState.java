package chd.shoppingonline.common.state;

public enum CommodityState {
    ON_SELL((short)0, "ON_SELL"),//上架
    OFF_SELL((short)1, "OFF_SELL");//下架

    private short shortValue;
    private String stringValue;
    CommodityState(short shortValue, String stringValue){
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
