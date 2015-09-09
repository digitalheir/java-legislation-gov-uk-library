package uk.gov.legislation.namespaces.metadata;

import javax.xml.bind.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object for CreationDate, which appears in legislation feeds. Not a part of the XML schema for some reason.
 *
 * Created by maarten on 9-9-15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "CreationDate")
public class CreationDate {

    @XmlAttribute(name = "Date", required = true)
    protected String date;

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param date allowed object is
     *             {@link String }; make sure to conform to @code{YYYY-MM-dd}
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the value of the date property.
     */
    public void setDate(Date d) {
        this.date = FORMAT.format(d);
    }

    private static final DateFormat FORMAT = new SimpleDateFormat("YYYY-MM-dd");

    public Date asDate() throws ParseException {
        return FORMAT.parse(date);
    }
}
