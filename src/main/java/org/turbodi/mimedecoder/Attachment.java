/**
 * $Id: $
 */
package org.turbodi.mimedecoder;

import javax.activation.DataSource;

/**
 * @author borisov
 * @since 19.12.2012
 */
public class Attachment
{
    private String _cid;
    private String _filename;
    private DataSource _dataSource;
    
    public Attachment(String filename, DataSource dataSource)
    {
        _filename = filename;
        _dataSource = dataSource;
    }
    
    public Attachment(String id, String filename, DataSource dataSource)
    {
        _cid = id;
        _filename = filename;
        _dataSource = dataSource;
    }

    public String getContentId()
    {
        return _cid;
    }
    
    public void setContentId(String id)
    {
        _cid = id;
    }

    public String getFilename()
    {
        return _filename;
    }
    
    public void setFilename(String filename)
    {
        _filename = filename;
    }

    public DataSource getDataSource()
    {
        return _dataSource;
    }
    
    public void setDataSource(DataSource dataSource)
    {
        _dataSource = dataSource;
    }
}
