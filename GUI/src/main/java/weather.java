import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
///////////////////NOT IN USE ATM/////////////////////////////
import java.io.IOException;

public class weather {

    public static void doHttpGet() {
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=<ApiKey>
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/<CITYID>?apikey=<ApiKey>

        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/215838?apikey=" + apiKey.getApiKey();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;

        try
        {
            resp = client.execute(get);
            HttpEntity entity=resp.getEntity();
            System.out.println("response is:");
            String test= EntityUtils.toString(entity);
            String test2="test";
            System.out.println(test);

        }
        catch (IOException ioe)
        {
            System.err.println("Somthing went wrong and we coudlnt get the information you requested: ");
            ioe.printStackTrace();
        }
        catch (Exception e)
        {
            System.err.println("Unknown Error: ");
            e.printStackTrace();
        }
    }
}
