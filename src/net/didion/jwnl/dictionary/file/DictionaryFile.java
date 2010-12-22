package net.didion.jwnl.dictionary.file;

import net.didion.jwnl.data.POS;

import java.io.File;
import java.io.IOException;

/**
 * Represents a single dictionary file. Extensions or implementations of this interface should provide
 * the appropriate methods to read from the file.
 */
public interface DictionaryFile {

    /**
     * Create a new instance of the dictionary file
     */
    public DictionaryFile newInstance(String path, POS pos, DictionaryFileType fileType);

    /**
     * Close the file
     */
    public void close();

    /**
     * Return true if the file is open
     */
    public boolean isOpen();

    /**
     * The POS associated with this file.
     */
    public POS getPOS();

    public File getFile();

    /**
     * The file type associated with this file.
     */
    public DictionaryFileType getFileType();

    /**
     * Open the file.
     */
    public void open() throws IOException;
}
