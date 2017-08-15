package com.liyuan.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BeanBase implements Serializable, Cloneable {
    protected static final long serialVersionUID = 1526427443L;
    @Column(name = "is_delete")
    protected Integer isDelate = 0;
    @Column(name = "delete_date")
    protected Date delateDate;
    @Column(name = "delete_by", length = 50)
    protected String deleteBy;
    @Column(name = "created_by", updatable = false, length = 50)
    protected String createdBy;
    @Column(name = "creation_date", updatable = false)
    protected Date creationDate;
    @Column(name = "last_update_date")
    protected Date lastUpdateDate;
    @Column(name = "last_updated_by", length = 50)
    protected String lastUpdatedBy;
    @Column(name = "last_update_ip", length = 50)
    protected String lastUpdateIp;
    @Column(name = "attribute_01", length = 50)
    protected String attribute01;
    @Column(name = "attribute_02", length = 50)
    protected String attribute02;
    @Column(name = "attribute_03", length = 50)
    protected String attribute03;
    @Column(name = "attribute_04", length = 50)
    protected String attribute04;
    @Column(name = "attribute_05", length = 50)
    protected String attribute05;
    @Column(name = "attribute_06", length = 50)
    protected String attribute06;
    @Column(name = "attribute_07", length = 50)
    protected String attribute07;
    @Column(name = "attribute_08", length = 50)
    protected String attribute08;
    @Column(name = "attribute_09", length = 50)
    protected String attribute09;
    @Column(name = "attribute_10", length = 50)
    protected String attribute10;
    @Column(name = "version")
    @Version
    protected Integer version;

    public Integer getIsDelate() {
        return isDelate;
    }

    public void setIsDelate(Integer isDelate) {
        this.isDelate = isDelate;
    }

    public Date getDelateDate() {
        return delateDate;
    }

    public void setDelateDate(Date delateDate) {
        this.delateDate = delateDate;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String delateBy) {
        this.deleteBy = delateBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public String getLastUpdateIp() {
        return this.lastUpdateIp;
    }

    public String getAttribute01() {
        return attribute01;
    }

    public String getAttribute02() {
        return attribute02;
    }

    public String getAttribute03() {
        return attribute03;
    }

    public String getAttribute04() {
        return attribute04;
    }

    public String getAttribute05() {
        return attribute05;
    }

    public String getAttribute06() {
        return attribute06;
    }

    public String getAttribute07() {
        return attribute07;
    }

    public String getAttribute08() {
        return attribute08;
    }

    public String getAttribute09() {
        return attribute09;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setAttribute01(String attribute01) {
        this.attribute01 = attribute01;
    }

    public void setAttribute02(String attribute02) {
        this.attribute02 = attribute02;
    }

    public void setAttribute03(String attribute03) {
        this.attribute03 = attribute03;
    }

    public void setAttribute04(String attribute04) {
        this.attribute04 = attribute04;
    }

    public void setAttribute05(String attribute05) {
        this.attribute05 = attribute05;
    }

    public void setAttribute06(String attribute06) {
        this.attribute06 = attribute06;
    }

    public void setAttribute07(String attribute07) {
        this.attribute07 = attribute07;
    }

    public void setAttribute08(String attribute08) {
        this.attribute08 = attribute08;
    }

    public void setAttribute09(String attribute09) {
        this.attribute09 = attribute09;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}