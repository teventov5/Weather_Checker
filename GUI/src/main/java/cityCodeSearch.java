import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Timer;

public class cityCodeSearch extends city{
    private boolean checkCityCodeHush(city ct)
    {
        if(cityCodeHushMap.cityCodes.get(ct.getCityName())!=null) {
            ct.setCityCode(cityCodeHushMap.cityCodes.get(ct.getCityName()));
            return true;
        }
        return false;
    }
    private void updateCityCodeHush(city ct)
    {
        Timer timer=new Timer();
        long timeout=1000_000;
        cityCodeHushMap.cityCodes.put(ct.getCityName(),ct.getCityCode());

    }
    private void parseResult(String result1,city ct) {
        String[] arrOfStr = result1.split(":", 4);
        String temp=arrOfStr[2];
        temp=temp.substring(1,temp.indexOf(",")-1);
        ct.setCityCode(temp);


    }
    String url = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey.getApiKey();
    CloseableHttpClient client = HttpClients.createDefault();
    CloseableHttpResponse resp = null;
    public cityCodeSearch(city ct) throws UnsupportedEncodingException {
        if(checkCityCodeHush(ct))
        return;
        url= url + "&q=" + URLEncoder.encode(ct.getCityName(), StandardCharsets.UTF_8.name());
        try
        {
            HttpGet get = new HttpGet(url);
            resp = client.execute(get);
            HttpEntity entity=resp.getEntity();
            String result1= EntityUtils.toString(entity);
            try
            {
                this.parseResult(result1,ct);
                this.updateCityCodeHush(ct);

            }
            catch(Exception e1){
                System.out.println(e1);
            }
        }
        catch (IOException ioe)
        {
            System.err.println("Something went wrong and we couldn't get the information you requested: ");
            ioe.printStackTrace();
        }

        catch (Exception e)
        {
            System.err.println("Unknown Error: ");
            e.printStackTrace();
        }
    }
    }

