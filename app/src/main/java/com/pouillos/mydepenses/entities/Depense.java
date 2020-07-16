package com.pouillos.mydepenses.entities;

import com.pouillos.mydepenses.dao.ContactDao;
import com.pouillos.mydepenses.dao.DaoSession;
import com.pouillos.mydepenses.dao.HistoriqueSmsDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import com.pouillos.mydepenses.dao.CategorieDepenseDao;
import com.pouillos.mydepenses.dao.DepenseDao;

@Entity
public class Depense {

    @Id
    private Long id;

    @NotNull
    private long categorieDepenseId;

    @ToOne(joinProperty = "categorieDepenseId")
    private CategorieDepense categorieDepense;

    @NotNull
    private Date date;

    @NotNull
    private String dateString;

    private boolean isRecurrent;

    private String detail;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 790387295)
    private transient DepenseDao myDao;

    @Generated(hash = 796369934)
    public Depense(Long id, long categorieDepenseId, @NotNull Date date,
            @NotNull String dateString, boolean isRecurrent, String detail) {
        this.id = id;
        this.categorieDepenseId = categorieDepenseId;
        this.date = date;
        this.dateString = dateString;
        this.isRecurrent = isRecurrent;
        this.detail = detail;
    }

    @Generated(hash = 1285756665)
    public Depense() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCategorieDepenseId() {
        return this.categorieDepenseId;
    }

    public void setCategorieDepenseId(long categorieDepenseId) {
        this.categorieDepenseId = categorieDepenseId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public boolean getIsRecurrent() {
        return this.isRecurrent;
    }

    public void setIsRecurrent(boolean isRecurrent) {
        this.isRecurrent = isRecurrent;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Generated(hash = 1212057176)
    private transient Long categorieDepense__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1582863905)
    public CategorieDepense getCategorieDepense() {
        long __key = this.categorieDepenseId;
        if (categorieDepense__resolvedKey == null
                || !categorieDepense__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategorieDepenseDao targetDao = daoSession.getCategorieDepenseDao();
            CategorieDepense categorieDepenseNew = targetDao.load(__key);
            synchronized (this) {
                categorieDepense = categorieDepenseNew;
                categorieDepense__resolvedKey = __key;
            }
        }
        return categorieDepense;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 479603015)
    public void setCategorieDepense(@NotNull CategorieDepense categorieDepense) {
        if (categorieDepense == null) {
            throw new DaoException(
                    "To-one property 'categorieDepenseId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.categorieDepense = categorieDepense;
            categorieDepenseId = categorieDepense.getId();
            categorieDepense__resolvedKey = categorieDepenseId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2041191155)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDepenseDao() : null;
    }



    

}
