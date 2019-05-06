/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author rimi
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileWrap extends AbstractPersistable<Long> {
    public long contentLength;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    public byte[] content;
    public String contentType;
    public String name;
}
