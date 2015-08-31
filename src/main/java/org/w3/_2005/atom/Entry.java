package org.w3._2005.atom;


import org.leibnizcenter.uk.legislation.UkUriObject;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


/**
 * For example: <a href="http://www.legislation.gov.uk/all/data.feed?page=2">http://www.legislation.gov.uk/all/data.feed?page=2</a>
 * <p>
 * <p>Java class for anonymous complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element ref="{http://www.w3.org/2005/Atom}author"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}category"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}content"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}contributor"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}id"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}link"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}published"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}rights"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}source"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}summary"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}title"/>
 *         &lt;element ref="{http://www.w3.org/2005/Atom}updated"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.w3.org/2005/Atom}atomCommonAttributes"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * <p>
 * <p>
 * Created on 2015.08.07
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = {
//        "authorsAndCategoriesAndContents"
//})
@XmlRootElement(name = "entry")
public class Entry {
// Ignoring this generated stuff for our homegrown solution
//
//    @XmlElementRefs({
//        @XmlElementRef(name = "title", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "updated", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "source", namespace = "http://www.w3.org/2005/Atom", type = Source.class, required = false),
//        @XmlElementRef(name = "author", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "summary", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "contributor", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "link", namespace = "http://www.w3.org/2005/Atom", type = Link.class, required = false),
//        @XmlElementRef(name = "content", namespace = "http://www.w3.org/2005/Atom", type = Content.class, required = false),
//        @XmlElementRef(name = "rights", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false),
//        @XmlElementRef(name = "category", namespace = "http://www.w3.org/2005/Atom", type = Category.class, required = false),
//        @XmlElementRef(name = "id", namespace = "http://www.w3.org/2005/Atom", type = Id.class, required = false),
//        @XmlElementRef(name = "published", namespace = "http://www.w3.org/2005/Atom", type = JAXBElement.class, required = false)
//    })
//    protected List<Object> authorsAndCategoriesAndContents;

    // Start custom definition (overriding generated codes)

    /**
     * Representation URI, should be of the form <a href="http://www.legislation.gov.uk/ukpga/1998/29">http://www.legislation.gov.uk/{type}/{year}/{number}</a>
     */
    @XmlElement(required = true)
    public String id;

    /**
     * Document title
     */
    @XmlElement(required = true)
    public String title;

    /**
     * Collection of Link types
     */
    @XmlElement(required = true, name = "link")
    public final Collection<Link> links = new HashSet<>();

    /**
     * DateTime on which the document was last updated (i.e., not necessarily through a formal amendment)
     */
    @XmlElement(required = true)
    public String updated;
    /**
     * Textual summary of the document
     */
    @XmlElement(required = true)
    public String summary;
    /**
     * DateTime when this law was first published
     */
    @XmlElement(required = true)
    public String published;

    /**
     * Type of document, for example <code>UnitedKingdomPublicGeneralAct</code>
     */
    @XmlElement(required = true, namespace = "http://www.legislation.gov.uk/namespaces/metadata", name = "DocumentMainType")
    public String documentMainType;


    /**
     * Year as it appears in the representation URI
     */
    @XmlElement(required = true, namespace = "http://www.legislation.gov.uk/namespaces/metadata", name = "Year")
    public String year;
    /**
     * Number as it appears in the representation URI
     */
    @XmlElement(required = true, namespace = "http://www.legislation.gov.uk/namespaces/metadata", name = "Number")
    public String number;
    /**
     * Date on which this law was created. Maybe always the same date as <code>published</code>?
     */
    @XmlElement(required = true, namespace = "http://www.legislation.gov.uk/namespaces/metadata", name = "CreationDate")
    public String creationDate;
    /**
     * ISBN number for this law
     */
    @XmlElement(required = true, namespace = "http://www.legislation.gov.uk/namespaces/metadata", name = "isbn")
    public String isbn;

    // End custom definition (overriding generated codes)


    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    protected String base;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    // Ignore generated code:
    //
//    /**
//     * Gets the value of the authorsAndCategoriesAndContents property.
//     * <p/>
//     * <p/>
//     * This accessor method returns a reference to the live list,
//     * not a snapshot. Therefore any modification you make to the
//     * returned list will be present inside the JAXB object.
//     * This is why there is not a <CODE>set</CODE> method for the authorsAndCategoriesAndContents property.
//     * <p/>
//     * <p/>
//     * For example, to add a new item, do as follows:
//     * <pre>
//     *    getAuthorsAndCategoriesAndContents().add(newItem);
//     * </pre>
//     * <p/>
//     * <p/>
//     * <p/>
//     * Objects of the following type(s) are allowed in the list
//     * {@link JAXBElement }{@code <}{@link AtomTextConstruct }{@code >}
//     * {@link JAXBElement }{@code <}{@link AtomPersonConstruct }{@code >}
//     * {@link Source }
//     * {@link JAXBElement }{@code <}{@link AtomDateConstruct }{@code >}
//     * {@link JAXBElement }{@code <}{@link AtomTextConstruct }{@code >}
//     * {@link JAXBElement }{@code <}{@link AtomPersonConstruct }{@code >}
//     * {@link Link }
//     * {@link Content }
//     * {@link JAXBElement }{@code <}{@link AtomTextConstruct }{@code >}
//     * {@link Category }
//     * {@link Id }
//     * {@link JAXBElement }{@code <}{@link AtomDateConstruct }{@code >}
//     */
//    public List<Object> getAuthorsAndCategoriesAndContents() {
//        if (authorsAndCategoriesAndContents == null) {
//            authorsAndCategoriesAndContents = new ArrayList<Object>();
//        }
//        return this.authorsAndCategoriesAndContents;
//    }

    /**
     * Gets the value of the base property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the lang property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLang(String value) {
        this.lang = value;
    }

    public List<Link> getTableOfContentsLinks() {
        return getLinksForRel("http://purl.org/dc/terms/tableOfContents");
    }

    public List<Link> getAlternateLinks() {
        return getLinksForRel("alternate");
    }

    public List<Link> getLinksForRel(String rel) {
        List<Link> links = new ArrayList<>(getLinks().size() / 2);
        for (Link l : getLinks()) {
            if (rel.equals(l.getRel()) && l.getHreflang() != null) {
                links.add(l);
            }
        }
        return links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Link> getLinks() {
        return links;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getDocumentMainType() {
        return documentMainType;
    }

    public void setDocumentMainType(String documentMainType) {
        this.documentMainType = documentMainType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public UkUriObject getUriObject() {
        String backupLink = null;
        for (Link link : links) {
            backupLink = link.href;
            if ("self".equals(link.rel)) {
                break;
            }
        }
        return new UkUriObject(backupLink);
    }
}
