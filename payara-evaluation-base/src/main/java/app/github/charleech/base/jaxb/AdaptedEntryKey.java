package app.github.charleech.base.jaxb;

import javax.enterprise.context.Dependent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.eclipse.persistence.oxm.annotations.XmlValueExtension;

import app.github.charleech.base.bean.Resettable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * This is a concrete implementing class which represents the {@code Map} entry
 * as a data bean.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(
    callSuper       = false,
    doNotUseGetters = false,
    of              = { "key" }
)
@ToString(
    callSuper         = false,
    includeFieldNames = true,
    doNotUseGetters   = false
)
@XmlRootElement(name = "adaptedEntryKey")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings({
    "PMD.UnusedPrivateField",
    "PMD.SingularField"
})
@Dependent
public class AdaptedEntryKey implements Resettable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@code key}.
     *
     * @since 1.0.0
     */
    @XmlTransient
    private String key;

    /**
     * This is a variable which represents the {@code value}.
     *
     * @since 1.0.0
     */
    @XmlValue
    @XmlValueExtension
    private String value;

    @Override
    public void reset() {
        this.key   = null;
        this.value = null;
    }

}
