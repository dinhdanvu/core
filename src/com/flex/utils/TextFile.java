package com.flex.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * <p>Title: Hospital Software</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: FSS</p>
 * @author TriTich
 * @version 1.0
 */

public class TextFile
    extends File {
  // transient private static Logger log = LoggingManager.getLoggerForClass();

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * File pEncoding. null means use the platform's default.
   */
  private String pEncoding = null;

  /**
       * Create a TextFile object to handle the named file with the given pEncoding.
   *
   * @param filename File to be read & written through this object.
   * @param pEncoding pEncoding to be used when reading & writing this file.
   */
  Writer pWriter = null;
  Reader pReader = null;
  BufferedReader pBr = null;
  String pLine = "NOTNULL";
  public TextFile(File aFileName, String aEncoding) {
    super(aFileName.toString());
    setEncoding(aEncoding);
  }

  /**
   * Create a TextFile object to handle the named file with the platform
   * default pEncoding.
   *
   * @param aFileName File to be read & written through this object.
   */
  public TextFile(File aFileName) {
    super(aFileName.toString());
  }

  /**
   * Create a TextFile object to handle the named file with the platform
   * default Encoding.
   *
   * @param aFileName Name of the file to be read & written through this object.
   */
  public TextFile(String aFileName) {
    super(aFileName);
  }

  /**
   * Create a TextFile object to handle the named file with the given
   * pEncoding.
   *
   * @param aFileName Name of the file to be read & written through this object.
   * @param aEncoding aEncoding to be used when reading & writing this file.
   */
  public TextFile(String aFileName, String aEncoding) {
    super(aFileName);
    setEncoding(aEncoding);
  }

  /**
   * Create the file with the given string as content -- or replace it's
   * content with the given string if the file already existed.
   *
   * @param aBody New content for the file.
   */
  public void setText(String aBody) {
    Writer writer = null;
    try {
      if (pEncoding == null) {
        writer = new FileWriter(this);
      }
      else {
        writer = new OutputStreamWriter(
            new FileOutputStream(this),
            pEncoding);
      }
      writer.write(aBody);
      writer.flush();
      writer.close();
    }
    catch (IOException ioe) {
      try {
        if (writer != null) {
          writer.close();
        }
      }
      catch (IOException e) {return;}
    }
  }

  /**
   * Read the whole file content and return it as a string.
   *
   * @return the content of the file
   */
  public String getText() {
    String sLineEnd = System.getProperty("line.separator");
    StringBuffer sb = new StringBuffer();
    Reader reader = null;
    try {
      if (pEncoding == null) {
        reader = new FileReader(this);
      }
      else {
        reader = new InputStreamReader(
            new FileInputStream(this),
            pEncoding);
      }
      BufferedReader br = new BufferedReader(reader);
      String line = "NOTNULL";
      while (line != null) {
        line = br.readLine();
        if (line != null) {
          sb.append(line + sLineEnd);
        }
      }
    }
    catch (IOException ioe) {
    ;
    }
    finally {
      if (reader != null)
        try {
          reader.close();
        }
        catch (IOException e) {;}
    }

    return sb.toString();
  }

  /**
   * @return pEncoding being used to read & write this file.
   */
  public String getEncoding() {
    return pEncoding;
  }

  /**
   * @param aString pEncoding to be used to read & write this file.
   */
  public void setEncoding(String aString) {
    pEncoding = aString;
  }

  /**
   * Create the file with the given string as content -- or replace it's
   * content with the given string if the file already existed.
   *
   * @param aBody New content for the file.
   */
  public void setDataText(String aBody) {

    try {
      if (pEncoding == null) {
        if (pWriter == null)
          pWriter = new FileWriter(this);
      }
      else {
        if (pWriter == null)
          pWriter = new OutputStreamWriter(
              new FileOutputStream(this),
              pEncoding);
      }
      pWriter.write(aBody);
      pWriter.flush();
    }
    catch (IOException ioe) {
      try {
        if (pWriter != null) {
          pWriter.close();
        }
      }
      catch (IOException e) {;}
    }
  }

  /**
   * Read a line of file content and return it as a string.
   *
   * @return the content of the line
   */
  public String ReadLine() {
       // String sLineEnd="";
       // sLineEnd = System.getProperty("line.separator");

        try {
      if (pEncoding == null) {
        if (pReader == null)
          pReader = new FileReader(this);
      }
      else {
        if (pReader == null)
          pReader = new InputStreamReader(
              new FileInputStream(this),
              pEncoding);
      }
      if (pBr == null)
        pBr = new BufferedReader(pReader);
      pLine = pBr.readLine();
    }
    catch (IOException ioe) {;}
    return pLine;
  }
  /**
   * close writer and reader
   */
  public void Close() {
    try {
      if (pWriter != null) {
        pWriter.close();
      }
      if (pReader != null) {
        pReader.close();
      }
      if (pBr != null) {
        pBr.close();
      }
    }
    catch (IOException e) {;}

  }
}
