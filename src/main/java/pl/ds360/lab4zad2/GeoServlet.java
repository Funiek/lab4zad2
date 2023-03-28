package pl.ds360.lab4zad2;

import com.lavasoft.GeoIPServiceSoap;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;

@WebServlet(name = "GeoServlet", value = "/GeoServlet")
public class GeoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String areaContext = request.getParameter("TextArea1");

        URL url = new URL("http://wsgeoip.lavasoft.com/ipservice.asmx?WSDL");
        QName qname = new QName("http://lavasoft.com/","GeoIPService");

        Service service = Service.create(url, qname);
        GeoIPServiceSoap soap = service.getPort(GeoIPServiceSoap.class);

        String res = soap.getIpLocation(areaContext);
        try {
            JAXBContext jaxb = JAXBContext.newInstance(GeoIP.class);
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();
            GeoIP geoIP = (GeoIP) unmarshaller.unmarshal(new StringReader(res));

            PrintWriter out = response.getWriter();
            out.println("<html><head><style> h1 {\n" +
                    "   text-transform: uppercase;\n" +
                    "  background-image: linear-gradient(\n" +
                    "    -225deg,\n" +
                    "    #231557 0%,\n" +
                    "    #44107a 29%,\n" +
                    "    #ff1361 67%,\n" +
                    "    #fff800 100%\n" +
                    "  );\n" +
                    "  background-size: auto auto;\n" +
                    "  background-clip: border-box;\n" +
                    "  background-size: 200% auto;\n" +
                    "  color: #fff;\n" +
                    "  background-clip: text;\n" +
                    "  text-fill-color: transparent;\n" +
                    "  -webkit-background-clip: text;\n" +
                    "  -webkit-text-fill-color: transparent;\n" +
                    "  animation: textclip 2s linear infinite;\n" +
                    "  display: inline-block;\n" +
                    "      font-size: 190px;\n" +
                    "}\n" +
                    "\n" +
                    "@keyframes textclip {\n" +
                    "  to {\n" +
                    "    background-position: 200% center;\n" +
                    "  }\n" +
                    "}</style></head><body>");
            out.println("<h1>" + geoIP.getCountry() + "</h1>");
            out.println("</body></html>");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        // Hello

    }
}
