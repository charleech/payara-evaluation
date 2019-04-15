package app.github.charleech.base.jaxb;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <p>
 * This is a concrete implementing class which provides the feature as a
 * {@link XmlAdapter} for {@code java.util.Map}.
 * </p>
 *
 * @author charlee.ch
 * @version 1.0.4
 * @since 1.0.4
 * @see <a href="http://bit.ly/2y0ssfv">MOXy's @XmlVariableNode - Using a Map's
 *      Key as the Node Name</a>
 * @see <a href="http://bit.ly/2sXXpex">MOXy's @XmlVariableNode - JSON Schema
 *      Example</a>
 * @see <a href="http://bit.ly/1wCti9s">JAXB and java.util.Map</a>
 */
public class MapKeyXmlAdapter
     extends XmlAdapter<AdaptedMapKey,
                        SortedMap<String, String>> {

    @Override
    public SortedMap<String, String> unmarshal(final AdaptedMapKey adaptedMap)
                                     throws Exception {
        List<AdaptedEntryKey>     adaptedEntries = null;
        SortedMap<String, String> map            = null;
        try {
            adaptedEntries = adaptedMap.getEntries();
            map            = new TreeMap<>();
            for (final AdaptedEntryKey adaptedEntry : adaptedEntries) {
                map.put(adaptedEntry.getKey(),
                        adaptedEntry.getValue());
            }
            return map;
        } finally {
            adaptedEntries = null;
            map            = null;
        }
    }

    @Override
    public AdaptedMapKey marshal(final SortedMap<String, String> map)
                         throws Exception {
        AdaptedMapKey   adaptedMap   = null;
        AdaptedEntryKey adaptedEntry = null;
        try {

            adaptedMap = new AdaptedMapKey();
            adaptedMap.reset();

            for (final Map.Entry<String, String> entry : map.entrySet()) {
                adaptedEntry = new AdaptedEntryKey();
                adaptedEntry.reset();

                adaptedEntry.setKey(entry.getKey());
                adaptedEntry.setValue(entry.getValue());
                adaptedMap.getEntries().add(adaptedEntry);
            }
            return adaptedMap;
        } finally {
            adaptedMap = null;
        }
    }

}
