public class city {
private String cityName;
private String cityCode;
private forcastResult result;

    public void setResult(forcastResult result) {
        this.result = result;
    }

    public forcastResult getResult() {
        return result;
    }
    public city(String cityName, String cityCode,forcastResult result)
    {
        setCityName(cityName);
        setCityCode(cityCode);
        setResult(result);
    }
    public city(String cityName, String cityCode)
{
    setCityName(cityName);
    setCityCode(cityCode);
}
public city(){
    setCityName("Holon");
    setCityCode("215838");
}
public String getCityName() {
        return cityName;
    }
public void setCityName(String cityName) {
        this.cityName = cityName;
    }
public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
public String getCityCode() {
        return cityCode;
    }
}
