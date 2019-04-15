package app.github.charleech.base.bean;

import java.io.Serializable;
import java.util.UUID;

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
    title = "This is a data bean base."
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
public abstract class MyDataBeanBase implements Resettable {

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


    @Override
    public void reset() {
        this.correlationId = UUID.randomUUID().toString();
    }
}
