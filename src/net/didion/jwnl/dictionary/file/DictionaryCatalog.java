package net.didion.jwnl.dictionary.file;

import net.didion.jwnl.JWNLRuntimeException;
import net.didion.jwnl.data.POS;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A container for the files associated with a catalog (the index, data, and exception
 * files associated with a POS).
 */
public class DictionaryCatalog {
    private Map<POS, DictionaryFile> _files = new HashMap<POS, DictionaryFile>();
    private DictionaryFileType _fileType;

    public DictionaryCatalog(String path, DictionaryFileType fileType, Class dictionaryFileType) {
        _fileType = fileType;
        try {
            Constructor c = dictionaryFileType.getConstructor(new Class[0]);
            DictionaryFile factory = (DictionaryFile) c.newInstance();
            for (POS pos : POS.getAllPOS()) {
                DictionaryFile file = factory.newInstance(path, pos, fileType);
                _files.put(file.getPOS(), file);
            }
        } catch (Exception ex) {
            throw new JWNLRuntimeException("DICTIONARY_EXCEPTION_0018", new Object[]{fileType, dictionaryFileType}, ex);
        }
    }

    public Object getKey() {
        return getFileType();
    }

    public void open() throws IOException {
        if (!isOpen()) {
            for (Iterator itr = getFileIterator(); itr.hasNext();) {
                ((DictionaryFile) itr.next()).open();
            }
        }
    }

    public boolean isOpen() {
        for (Iterator itr = getFileIterator(); itr.hasNext();) {
            if (!((DictionaryFile) itr.next()).isOpen()) {
                return false;
            }
        }
        return true;
    }

    public void close() {
        for (Iterator itr = getFileIterator(); itr.hasNext();) {
            ((AbstractDictionaryFile) itr.next()).close();
        }
    }

    public int size() {
        return _files.size();
    }

    public Iterator getFileIterator() {
        return _files.values().iterator();
    }

    public DictionaryFile get(POS pos) {
        return _files.get(pos);
    }

    public DictionaryFileType getFileType() {
        return _fileType;
    }
}