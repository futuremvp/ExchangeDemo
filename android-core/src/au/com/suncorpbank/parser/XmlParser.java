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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class XmlParser {

    private HttpClient httpsClient;

    public XmlParser() {
        this.httpsClient = new DefaultHttpClient();
    }

    public XmlParser(HttpClient httpsClient) {
        this.httpsClient = httpsClient;
    }

    public ExchangeRepo getExchangeRepo(String url) {
        String data = readFromUrl(url);
        System.out.println("data = " + data);
        return parseFromData(data);
    }

    public ExchangeRepo parseFromData(String data) {
        try {
            data = data.replaceFirst("^([\\W]+)<", "<");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(data));
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

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

    public ExchangeRepo parseFromUrl(String url) {
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

        NodeList exchangeRateNodes = nList.item(0).getChildNodes();
        NodeList rateDetailNodes = exchangeRateNodes.item(0).getChildNodes();

        for (int temp = 0; temp < rateDetailNodes.getLength(); temp++) {

            Node nNode = rateDetailNodes.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String countryName = getTagValue("CountryName", eElement).trim();
                String currencyName = getTagValue("CurrencyName", eElement).trim();
                String currencyCode = getTagValue("CurrencyCode", eElement).trim();
                String buyChqAtRate = getTagValue("BuyChqAtRate", eElement).trim();
                String sellNotesAtRate = getTagValue("SellNotesAtRate", eElement).trim();

                ExchangeRate rate = new ExchangeRate(countryName, currencyName, currencyCode, Double.valueOf(buyChqAtRate).doubleValue(), Double.valueOf(sellNotesAtRate).doubleValue());

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
