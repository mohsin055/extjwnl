package net.didion.jwnl.dictionary;

import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.util.factory.Createable;

import java.util.List;

/**
 * A <code>MorphologicalProcessor</code> tries to turn the inflected form of a word or phrase into
 * the form that can be found in WordNet. For example, if one calls
 * lookupBaseForm(POS.VERB, "running"), the index word for "run" should be returned.
 */
public interface MorphologicalProcessor extends Createable {
    /**
     * Try to turn <var>derivation</var> into a word that is found in the index file for <var>pos</var>.
     * If there is more than one possible base form, then the first call to this method should
     * return the first base form found. The return value for subsequent calls is undefined (it could
     * be the same base form, or the next base form - it is up to the implementer to decide, but the
     * decision should be noted.
     */
    public IndexWord lookupBaseForm(POS pos, String derivation) throws JWNLException;

    /**
     * Return all the base forms of <var>derivation</var>
     */
    public List lookupAllBaseForms(POS pos, String derivation) throws JWNLException;
}

