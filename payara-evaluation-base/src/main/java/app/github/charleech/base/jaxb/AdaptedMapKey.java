package app.github.charleech.base.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlVariableNode;

import app.github.charleech.base.bean.Resettable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * This is a concrete implementing class which represents the {@code Map}.
 * as a data bean.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.4
 * @since 1.0.4
 */
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
@XmlRootElement(name = "adpatedMapKey")
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings({
    "PMD.UnusedPrivateField",
    "PMD.SingularField"
})
@Dependent
public class AdaptedMapKey implements Resettable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 1.0.4
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@code Map} elements.
     *
     * @since 1.0.4
     */
    @XmlVariableNode("key")
    private List<AdaptedEntryKey> entries;

    @Override
    public void reset() {
        if (this.entries == null) {
            this.entries = new ArrayList<>();
        }
        this.entries.clear();
    }
}
