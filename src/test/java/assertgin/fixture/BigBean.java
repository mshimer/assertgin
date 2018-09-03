package assertgin.fixture;

import java.math.BigDecimal;
import java.util.Date;

public class BigBean implements Comparable{

    byte	abyte;
    short	ashort;
    int	    aint;
    long	along;
    float	afloat;
    double	adouble;
    char	achar;
    boolean	aboolean;
    String astring;
    BigBean bigBean;
    SmallBean smallBean;
    Date adate;
    BigDecimal bigDecimal;


    public BigBean(byte abyte, short ashort, int aint, long along, float afloat, double adouble, char achar, boolean aboolean, String astring) {
        this.abyte = abyte;
        this.ashort = ashort;
        this.aint = aint;
        this.along = along;
        this.afloat = afloat;
        this.adouble = adouble;
        this.achar = achar;
        this.aboolean = aboolean;
        this.astring = astring;
        this.adate = new Date();
        this.bigDecimal = new BigDecimal("1.33333333333333333");
    }

    public BigBean() {
    }

    public void setBigBean(BigBean bigBean) {
        this.bigBean = bigBean;
    }

    public void setSmallBean(SmallBean smallBean) {
        this.smallBean = smallBean;
    }

    public BigBean getBigBean() {
        return bigBean;
    }

    public SmallBean getSmallBean() {
        return smallBean;
    }

    public byte getAbyte() {
        return abyte;
    }

    public short getAshort() {
        return ashort;
    }

    public int getAint() {
        return aint;
    }

    public long getAlong() {
        return along;
    }

    public float getAfloat() {
        return afloat;
    }

    public double getAdouble() {
        return adouble;
    }

    public char getAchar() {
        return achar;
    }

    public boolean isAboolean() {
        return aboolean;
    }

    public String getAstring() {
        return astring;
    }

    public Date getAdate() {
        return adate;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    @Override
    public int compareTo(Object o) {
        return this.aint = ((BigBean)o).aint;
    }
}
