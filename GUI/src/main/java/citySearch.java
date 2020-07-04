public class citySearch extends city{

    public citySearch() { }

public forcastResult searchForCityResult(String cityUserInput)
{
    try {
        try {
            city ct=new city(cityUserInput,"0000");
            new cityCodeSearch(ct);
            new repository().doHttpGet(ct);
            new repository().getHangouts(ct);
            this.setCityName(ct.getCityName());
            this.setCityCode(ct.getCityCode());
            return ct.getResult();
        }
        catch(Exception e2){
            System.out.println("Api reached max get requests");
        }

    }
    catch(Exception e1)
    {
        System.out.println("Unknown Error");
    }
    return null;
}


}