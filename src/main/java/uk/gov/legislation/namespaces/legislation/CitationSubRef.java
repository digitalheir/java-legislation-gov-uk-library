//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.07 at 06:17:52 PM CEST 
//


package uk.gov.legislation.namespaces.legislation;

import uk.co.tso.assets.namespace.error.ErrorType;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;group ref="{http://www.legislation.gov.uk/namespaces/legislation}InlineLegislationCitation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attGroup ref="{http://www.legislation.gov.uk/namespaces/legislation}CitationElementIDAttributes"/&gt;
 *       &lt;attGroup ref="{http://www.legislation.gov.uk/namespaces/legislation}CommonSubAttributes"/&gt;
 *       &lt;attribute name="Type"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="standard"/&gt;
 *             &lt;enumeration value="group"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="CitationRef" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SectionRef" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="StartSectionRef" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="EndSectionRef" type="{http://www.w3.org/2001/XMLSchema}NCName" /&gt;
 *       &lt;attribute name="URI" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="UpTo" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="Operative" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "CitationSubRef")
public class CitationSubRef {

    @XmlElementRefs({
        @XmlElementRef(name = "Addition", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Proviso", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Proviso.class, required = false),
        @XmlElementRef(name = "Term", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Term.class, required = false),
        @XmlElementRef(name = "Emphasis", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Emphasis.class, required = false),
        @XmlElementRef(name = "InlineAmendment", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = InlineAmendment.class, required = false),
        @XmlElementRef(name = "CommentaryRef", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = CommentaryRef.class, required = false),
        @XmlElementRef(name = "MarginNoteRef", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = MarginNoteRef.class, required = false),
        @XmlElementRef(name = "Error", namespace = "http://www.tso.co.uk/assets/namespace/error", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Span", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Acronym", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Acronym.class, required = false),
        @XmlElementRef(name = "Character", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Character.class, required = false),
        @XmlElementRef(name = "SmallCaps", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = SmallCaps.class, required = false),
        @XmlElementRef(name = "Underline", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Superior", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "FootnoteRef", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = FootnoteRef.class, required = false),
        @XmlElementRef(name = "Abbreviation", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Abbreviation.class, required = false),
        @XmlElementRef(name = "Repeal", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Repeal.class, required = false),
        @XmlElementRef(name = "Substitution", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Strong", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Strong.class, required = false),
        @XmlElementRef(name = "Warning", namespace = "http://www.tso.co.uk/assets/namespace/error", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Definition", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = Definition.class, required = false),
        @XmlElementRef(name = "Inferior", namespace = "http://www.legislation.gov.uk/namespaces/legislation", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "Type")
    protected String type;
    @XmlAttribute(name = "CitationRef")
    protected String citationRef;
    @XmlAttribute(name = "SectionRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String sectionRef;
    @XmlAttribute(name = "StartSectionRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String startSectionRef;
    @XmlAttribute(name = "EndSectionRef")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String endSectionRef;
    @XmlAttribute(name = "URI")
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlAttribute(name = "UpTo")
    @XmlSchemaType(name = "anyURI")
    protected String upTo;
    @XmlAttribute(name = "Operative")
    protected Boolean operative;
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;
    @XmlAttribute(name = "space", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String space;
    @XmlAttribute(name = "base", namespace = "http://www.w3.org/XML/1998/namespace")
    @XmlSchemaType(name = "anyURI")
    protected String base;
    @XmlAttribute(name = "AltVersionRefs")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> altVersionRefs;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     * {@link String }
     * {@link Proviso }
     * {@link Emphasis }
     * {@link Term }
     * {@link InlineAmendment }
     * {@link CommentaryRef }
     * {@link JAXBElement }{@code <}{@link ErrorType }{@code >}
     * {@link MarginNoteRef }
     * {@link JAXBElement }{@code <}{@link InlineStructure }{@code >}
     * {@link Acronym }
     * {@link Character }
     * {@link JAXBElement }{@code <}{@link InlineStructure }{@code >}
     * {@link SmallCaps }
     * {@link JAXBElement }{@code <}{@link InlineStructure }{@code >}
     * {@link FootnoteRef }
     * {@link Abbreviation }
     * {@link Repeal }
     * {@link Strong }
     * {@link JAXBElement }{@code <}{@link ChangeType }{@code >}
     * {@link JAXBElement }{@code <}{@link ErrorType }{@code >}
     * {@link JAXBElement }{@code <}{@link InlineStructure }{@code >}
     * {@link Definition }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return this.content;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the citationRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitationRef() {
        return citationRef;
    }

    /**
     * Sets the value of the citationRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitationRef(String value) {
        this.citationRef = value;
    }

    /**
     * Gets the value of the sectionRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectionRef() {
        return sectionRef;
    }

    /**
     * Sets the value of the sectionRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectionRef(String value) {
        this.sectionRef = value;
    }

    /**
     * Gets the value of the startSectionRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartSectionRef() {
        return startSectionRef;
    }

    /**
     * Sets the value of the startSectionRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartSectionRef(String value) {
        this.startSectionRef = value;
    }

    /**
     * Gets the value of the endSectionRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndSectionRef() {
        return endSectionRef;
    }

    /**
     * Sets the value of the endSectionRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndSectionRef(String value) {
        this.endSectionRef = value;
    }

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the upTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpTo() {
        return upTo;
    }

    /**
     * Sets the value of the upTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpTo(String value) {
        this.upTo = value;
    }

    /**
     * Gets the value of the operative property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isOperative() {
        if (operative == null) {
            return false;
        } else {
            return operative;
        }
    }

    /**
     * Sets the value of the operative property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOperative(Boolean value) {
        this.operative = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the space property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpace() {
        return space;
    }

    /**
     * Sets the value of the space property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpace(String value) {
        this.space = value;
    }

    /**
     * Gets the value of the base property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the value of the base property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBase(String value) {
        this.base = value;
    }

    /**
     * Gets the value of the altVersionRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altVersionRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltVersionRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAltVersionRefs() {
        if (altVersionRefs == null) {
            altVersionRefs = new ArrayList<>();
        }
        return this.altVersionRefs;
    }

}
