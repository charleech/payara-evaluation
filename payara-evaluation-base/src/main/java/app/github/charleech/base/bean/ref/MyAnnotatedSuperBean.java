package app.github.charleech.base.bean.ref;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * This is a base class which provides the feature as a based data bean.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 * @see Serializable
 */
@Schema(
    title = "This is a base data bean which is annotated with schema. It is inside jar file."
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
@XmlTransient
public class MyAnnotatedSuperBean implements Serializable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the correlation id.
     *
     * @since 1.0.0
     */
    @Schema(
        title    = "The correlation identifier",
        required = true,
        format   = "uuid"
    )
    @XmlElement(name = "correlationId")
    private String correlationId;

}
