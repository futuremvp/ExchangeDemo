package au.com.suncorpbank.parser;

import au.com.suncorpbank.domain.ExchangeRepo;
import au.com.suncorpbank.util.IOUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlParserTest {

    private XmlParser parser;

    @Before
    public void setUp() {
        parser = new XmlParser(null);
    }

    @Test
    public void testGetExchangeRepo() throws Exception {
        FileInputStream fis = new FileInputStream("test/au/com/suncorpbank/xmlsource/test_data.xml");
        String response = IOUtil.getStringFromStream(fis);

        ExchangeRepo repo = parser.parseFromData(response);

        assertThat(repo.getExchange(0).getCountry(), is("Europe"));
        assertThat(repo.getExchange(1).getCountry(), is("U.S.A"));
        assertThat(repo.getExchange(0).getSellNotesAtRate(), is(0.7491));
        assertThat(repo.getExchange(1).getSellNotesAtRate(), is(0.959));
    }
}
