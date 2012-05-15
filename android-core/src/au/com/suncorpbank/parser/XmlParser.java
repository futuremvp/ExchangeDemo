package au.com.suncorpbank.parser;

import au.com.suncorpbank.domain.ExchangeRate;
import au.com.suncorpbank.domain.ExchangeRepo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser {

    private HttpClient httpsClient = new DefaultHttpClient();

    public ExchangeRepo getExchangeRepo(String url) {
        String data = readFromUrl(url);
        return parse(data, url);
    }

    private ExchangeRepo parse(String data, String url) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);
            doc.getDocumentElement().normalize();

            System.out.println("position = " + doc.getFirstChild().getNodeValue());

            NodeList nList = doc.getElementsByTagName("ExchangeRates");

            return parseNodes(nList);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ExchangeRepo parseNodes(NodeList nList) {
        ExchangeRepo repo = new ExchangeRepo();

        NodeList rateNodes = nList.item(0).getChildNodes();
        NodeList rateNodes2 = rateNodes.item(0).getChildNodes();

        System.out.println("position = " + nList.getLength() + " || " + rateNodes.getLength() + " || " + rateNodes2.getLength());

        for (int temp = 0; temp < rateNodes2.getLength(); temp++) {

            Node nNode = rateNodes2.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String countryName = getTagValue("CountryName", eElement).trim();
                String currencyName = getTagValue("CurrencyName", eElement).trim();
                String currencyCode = getTagValue("CurrencyCode", eElement).trim();
                String buyChqAtRate = getTagValue("BuyChqAtRate", eElement).trim();
                String sellAtRate = getTagValue("SellAtRate", eElement).trim();

                ExchangeRate rate = new ExchangeRate(countryName, currencyName, currencyCode, Double.valueOf(buyChqAtRate).doubleValue(), Double.valueOf(sellAtRate).doubleValue());

                repo.addNewExchange(rate);
            }
        }
        return repo;
    }

    private static String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        return nValue.getNodeValue();
    }


    ////////////////Data Server Code////////////////////////////////

    private String readFromUrl(String my_url) {
        HttpGet request = new HttpGet(my_url);
        return doExecute(request);
    }

    private String doExecute(HttpRequestBase request) {
        try {
            HttpResponse response = httpsClient.execute(request);
            if (isSuccess(response)) {
                String responseContent;

                responseContent = EntityUtils.toString(response.getEntity());
                return responseContent;
            }
            throw new Exception("error status code: " + response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isSuccess(HttpResponse response) {
        if (response == null) return false;
        int statusCode = parseStatusCode(response);
        return statusCode == 200 || statusCode == 201;
    }

    private int parseStatusCode(HttpResponse response) {
        int statusCode = response.getStatusLine().getStatusCode();
        return statusCode;
    }
}
