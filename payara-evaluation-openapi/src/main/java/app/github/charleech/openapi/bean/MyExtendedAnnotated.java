package app.github.charleech.openapi.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import app.github.charleech.base.bean.ref.MyAnnotatedDataBean;
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
    title = "This is a simple data bean which is extedned from jar file."
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
public class MyExtendedAnnotated extends MyAnnotatedDataBean {

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
        title    = "The extended message",
        required = true
    )
    @XmlElement(name = "extendedMessage")
    private String extendedMessage;
}
