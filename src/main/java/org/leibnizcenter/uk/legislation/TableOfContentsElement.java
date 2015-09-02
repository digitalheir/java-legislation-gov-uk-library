package org.leibnizcenter.uk.legislation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * There are a multitude of XML elements that may appear in the table of contents, so we introduce this interface to
 * create a semantic group for them
 * <p>
 * Created by maarten on 2-9-15.
 */
public interface TableOfContentsElement {

    List<TableOfContentsElement> getToCChildren();

    class Helper {
        public static List<TableOfContentsElement> castToTableOfContentsElement(List<Object> objList) {
            List<TableOfContentsElement> l = new ArrayList<>(objList.size());
            Collections.copy(objList, l);
            return l;
        }
    }
}
