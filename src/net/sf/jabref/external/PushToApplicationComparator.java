package net.sf.jabref.external;

import java.util.Comparator;

/**
 * Comparator for sorting the selection according to name.
 */
public class PushToApplicationComparator implements Comparator<PushToApplication> {

    public int compare(PushToApplication one, PushToApplication two) {
        return one.getName().compareTo(two.getName());
    }
}