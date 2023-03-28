package pl.ds360.lab4zad2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "GeoIP")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoIP implements Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement(name = "Country")
    String country;
    @XmlElement(name = "State")
    String state;

    public GeoIP() {
        super();
    }

    public GeoIP(String country, String state) {
        super();
        this.country = country;
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "GeoIP{" +
                "Country='" + country + '\'' +
                ", State='" + state + '\'' +
                '}';
    }
}
