package com.shar.model;

import com.shar.controller.ApiKey;
import com.shar.controller.CityCodeHushMap;
import com.shar.view.UserCustomizedScreen;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Repository {
    private static String[] parseResult(String result1) {
        String[] arrOfStr = result1.split(":", 35);
        String[] arrOfStr2 = new String[5];
        arrOfStr2[0] = arrOfStr[1];//gets the date
        arrOfStr2[1] = arrOfStr[6];//gets a general description of weather
        arrOfStr2[2] = arrOfStr[13];//minimum degrees
        arrOfStr2[3] = arrOfStr[7];//icon number
        arrOfStr2[4] = arrOfStr[5];//time
        arrOfStr2[0] = arrOfStr2[0].substring(1, 11);//trimming a perfect string for date
        arrOfStr2[1] = arrOfStr2[1].substring(1, arrOfStr2[1].indexOf(",") - 1);//trimming a perfect string for headline
        arrOfStr2[2] = arrOfStr2[2].substring(0, arrOfStr2[2].indexOf(",") - 2);//trimming a perfect string for min degrees
        arrOfStr2[3] = (arrOfStr2[3].substring(0, 1));//trimming the icon number
        arrOfStr2[4] = arrOfStr2[4].substring(0, arrOfStr2[4].indexOf(","));//trimming a perfect string for the time in millisec
        return arrOfStr2;
    }

    private static HangoutsResult[] HangoutParseResult(String result1) {
        String[] arrOfStr = result1.split("}", 0);
        String[] arrOfStr2 = new String[12];
        arrOfStr2[0] = arrOfStr[0].substring(122);
        arrOfStr2[1] = arrOfStr[18].substring(135);
        arrOfStr2[2] = arrOfStr[19].substring(132);
        arrOfStr2[3] = arrOfStr[40].substring(137);//TODO fix this
        arrOfStr2[4] = arrOfStr[2].substring(124);
        arrOfStr2[5] = arrOfStr[6].substring(129);
        arrOfStr2[6] = arrOfStr[7].substring(123);
        arrOfStr2[7] = arrOfStr[4].substring(123);
        arrOfStr2[8] = arrOfStr[5].substring(126);
        arrOfStr2[9] = arrOfStr[8].substring(130);
        arrOfStr2[10] = arrOfStr[9].substring(132);
        arrOfStr2[11] = arrOfStr[11].substring(130);


        HangoutsResult[] arrOfHangouts = new HangoutsResult[12];
        for (int i = 0; i < 12; i++) {
            HangoutsResult temp = new HangoutsResult(arrOfStr2[i].substring(23, arrOfStr2[i].indexOf(",", 24) - 1), arrOfStr2[i].substring(7, arrOfStr2[0].indexOf(",") - 1));
            arrOfHangouts[i] = temp;
        }
        return arrOfHangouts;
    }

    private boolean resultHushCheck(City ct) {
        if (CityCodeHushMap.forecasts.get(ct.getCityCode()) != null) {
            long hashTime = CityCodeHushMap.forecasts.get(ct.getCityCode()).getTime();
            //currTime=currTime*1000;
            long currTime = UserCustomizedScreen.rightNow.getTimeInMillis();
            currTime = currTime / 1000;
            if (currTime - hashTime < 60000) {
                ct.setResult(CityCodeHushMap.forecasts.get(ct.getCityCode()));
                return true;
            } else {
                CityCodeHushMap.forecasts.remove(ct.getCityCode());
            }
        }
        return false;
    }

    private boolean hangoutHushCheck(City ct) {
        if (CityCodeHushMap.hangouts.get(ct.getCityCode()) != null) {
            //long hashTime=cityCodeHushMap.forecasts.get(ct.getCityCode()).getTime();
            //currTime=currTime*1000;
            //long currTime=userCustomizedScreen.rightNow.getTimeInMillis();
            //currTime=currTime/1000;
            ct.setResult(CityCodeHushMap.forecasts.get(ct.getCityCode()));
            return true;
        } else {
            return false;
        }
    }

    private void updateCityHangoutHush(City ct, HangoutsResult[] resultsArr) {
        CityCodeHushMap.hangouts.put(ct.getCityCode(), resultsArr);
    }


    private void updateCityForecastsHush(City ct) {
        CityCodeHushMap.forecasts.put(ct.getCityCode(), ct.getResult());

    }

    public ForcastResult doHttpGet(City ct) {
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=<com.shar.controller.ApiKey>
        //http://dataservice.accuweather.com/forecasts/v1/daily/1day/<CITYID>?apikey=<com.shar.controller.ApiKey>

        String url = "http://dataservice.accuweather.com/currentconditions/v1/" + ct.getCityCode() + "?apikey=" + ApiKey.getApiKey();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        if (this.resultHushCheck(ct)) {
            return CityCodeHushMap.forecasts.get(ct.getCityCode());
        }
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            String result1 = EntityUtils.toString(entity);
            try {
                String[] finalResult = parseResult(result1);
                ForcastResult output = new ForcastResult(finalResult);
                ct.setResult(output);
                this.updateCityForecastsHush(ct);
                return output;


            } catch (Exception e1) {
                System.out.println(e1);
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong and we couldn't get the information you requested: ");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unknown Error: ");
            e.printStackTrace();
        }
        throw new ArithmeticException("cant find city results");
    }

    public ForcastResult getHangouts(City ct) {

        String url = "http://dataservice.accuweather.com/indices/v1/daily/1day/" + ct.getCityCode() + "/groups/1" + "?apikey=" + ApiKey.getApiKey();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        if (this.hangoutHushCheck(ct)) {
            return CityCodeHushMap.forecasts.get(ct.getCityCode());
        }
        try {

            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            String result1 = EntityUtils.toString(entity);
            try {
                HangoutsResult[] finalResult = HangoutParseResult(result1);
                ForcastResult output = ct.getResult();
                output.setHangoutsResultsArr(finalResult);
                ct.setResult(output);
                this.updateCityHangoutHush(ct, finalResult);
                return output;


            } catch (Exception e1) {
                System.out.println(e1);
            }
        } catch (IOException ioe) {
            System.err.println("Something went wrong and we couldn't get the information you requested: ");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unknown Error: ");
            e.printStackTrace();
        }
        throw new ArithmeticException("cant find city results");
    }


}