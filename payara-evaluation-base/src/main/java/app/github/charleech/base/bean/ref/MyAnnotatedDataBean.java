package app.github.charleech.base.bean.ref;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import app.github.charleech.base.jaxb.MapKeyXmlAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a simple
 * data bean.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see Serializable
 */
@Schema(
    title = "This is a data bean which is annotated with schema. It is inside jar file."
)
@Data
@EqualsAndHashCode(
    callSuper       = false,
    doNotUseGetters = false
)
@ToString(
    callSuper         = false,
    includeFieldNames = true,
    doNotUseGetters   = false
)
@SuppressWarnings({
    "PMD.UnusedPrivateField",
    "PMD.SingularField"
})
public class MyAnnotatedDataBean extends MyAnnotatedSuperBean {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the simple message.
     *
     * @since 1.0.0
     */
    @Schema(
        title    = "The simple message",
        required = true
    )
    @XmlElement(name = "message")
    private String message;

    /**
     * This is a variable which represents the attributes.
     *
     * @since 1.00
     */
    @Schema(
        title    = "The simple attributes",
        required = false
    )
    @XmlElement(name = "attributes")
    @XmlJavaTypeAdapter(MapKeyXmlAdapter.class)
    private Map<String, String> attributes;
}
