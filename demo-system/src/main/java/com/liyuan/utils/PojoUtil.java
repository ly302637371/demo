package com.liyuan.utils;

import com.liyuan.base.BeanBase;
import com.liyuan.base.BeanDTO;
import com.liyuan.base.PlatformConstant;
import com.liyuan.exception.DaoException;
import com.liyuan.exception.UtilsException;
import com.liyuan.session.ThreadSession;
import com.liyuan.session.UserSession;
import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class PojoUtil {
    static Logger logger = LoggerFactory.getLogger(PojoUtil.class);
    private static final long VERSION = 0L;

    public static String getJavaNameFromDBColumnName(String s) {
        String string = toUpperCamelCase(s);
        return Introspector.decapitalize(string);
    }

    public static Map<?, ?> toMap(Object bean) throws IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        Assert.notNull(bean, "参数 bean 不能为空");
        return PropertyUtils.describe(bean);
    }

    /**
     * 根据HttpServletRequest获取对象属性并转换成java对象；
     *
     * @param request
     * @param target
     * @return
     * @throws UtilsException
     */
    public static void Request2Pojo(HttpServletRequest request, Object target)
            throws UtilsException {
        try {
            Field[] fields = target.getClass().getDeclaredFields();
            if (fields.length < 1) {
                logger.error(target.getClass() + "未能找到属性");
                return;
            }
            for (int i = 0; i < fields.length; i++) {
                String properties = fields[i].getName();
                String propertiesValue = request.getParameter(properties);
                PropertyDescriptor propDes = BeanUtils.getPropertyDescriptor(
                        target.getClass(), properties);
                if (propDes == null || propertiesValue == null) {
                    continue;
                }
                if (propDes.getPropertyType() == BigDecimal.class) {
                    BigDecimal big = new BigDecimal(propertiesValue.toString());
                    PropertyUtils.setProperty(target, properties, big);
                } else {
                    PropertyUtils.setProperty(target, properties,
                            propertiesValue);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * 将map对象转换成java对象；
     *
     * @param map
     * @param target
     * @return
     * @throws UtilsException
     */
    public static Object Map2Pojo(Map<String, Object> map, Object target)
            throws UtilsException {
        try {
            for (Iterator iterator = map.entrySet().iterator(); iterator
                    .hasNext(); ) {
                Map.Entry item = (Map.Entry) iterator.next();
                String objName;
                Object objValue;
                if (item.getValue() == null) {
                    continue;
                }
                objName = getJavaNameFromDBColumnName((String) item.getKey());
                objValue = item.getValue();
                PropertyDescriptor propDes = BeanUtils.getPropertyDescriptor(
                        target.getClass(), objName);
                if (propDes == null || objValue == null) {
                    continue;
                }
                if (propDes.getPropertyType() == BigDecimal.class) {
                    BigDecimal big = new BigDecimal(objValue.toString());
                    PropertyUtils.setProperty(target, objName, big);
                }
                if (propDes.getPropertyType() == String.class) {
                    PropertyUtils.setProperty(target, objName, objValue.toString());
                } else {
                    PropertyUtils.setProperty(target, objName, objValue);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new UtilsException(e.getMessage(), e);
        }
        return target;
    }

    public static void copyProperties(Object toObj, Object fromObj)
            throws UtilsException {
        Assert.notNull(toObj, "参数 toObj 不能为空");
        Assert.notNull(fromObj, "参数 fromObj 不能为空");
        try {
            PropertyUtils.copyProperties(toObj, fromObj);
        } catch (Exception e) {
            throw new UtilsException(e.getMessage(), e);
        }
    }

    /**
     * @param toObj
     * @param fromObj
     * @param skipNull 跳过null属性
     * @throws UtilsException
     */
    public static void copyProperties(Object toObj, Object fromObj,
                                      boolean skipNull) throws UtilsException {
        int i;
        String name;
        Object value;
        Assert.notNull(toObj, "参数 toObj 不能为空");
        Assert.notNull(fromObj, "参数 fromObj 不能为空");
        Object dest = toObj;
        Object orig = fromObj;
        PropertyUtilsBean utilsBean = BeanUtilsBean.getInstance()
                .getPropertyUtils();
        if (dest == null)
            throw new IllegalArgumentException("No destination bean specified");

        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        if (orig instanceof DynaBean) {
            DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
                    .getDynaProperties();
            for (i = 0; i < origDescriptors.length; ++i) {
                name = origDescriptors[i].getName();
                if (!utilsBean.isReadable(orig, name)
                        || !utilsBean.isWriteable(dest, name)) {
                    continue;
                }
                try {
                    value = ((DynaBean) orig).get(name);
                    if ((skipNull == true) && (null == value))
                        continue;
                    if (dest instanceof DynaBean)
                        ((DynaBean) dest).set(name, value);
                    else
                        utilsBean.setSimpleProperty(dest, name, value);
                } catch (NoSuchMethodException e) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Error writing to '" + name
                                + "' on class '" + dest.getClass() + "'", e);
                        throw new UtilsException(e.getMessage(), e);
                    }
                } catch (IllegalAccessException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                }
            }
        } else if (orig instanceof Map) {
            Iterator entries = ((Map) orig).entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry entry = (Map.Entry) entries.next();
                name = (String) entry.getKey();
                if (!utilsBean.isWriteable(dest, name)) {
                    continue;
                }
                try {
                    value = entry.getValue();
                    if ((skipNull != true) || (null != value)) {
                        continue;
                    }
                    if (dest instanceof DynaBean)
                        ((DynaBean) dest).set(name, entry.getValue());
                    else
                        utilsBean.setSimpleProperty(dest, name,
                                entry.getValue());
                } catch (NoSuchMethodException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                }
            }
        } else {
            PropertyDescriptor[] origDescriptors = utilsBean
                    .getPropertyDescriptors(orig);
            for (i = 0; i < origDescriptors.length; ++i) {
                name = origDescriptors[i].getName();
                if (!utilsBean.isReadable(orig, name)
                        || !utilsBean.isWriteable(dest, name)) {
                    continue;
                }

                try {
                    value = utilsBean.getSimpleProperty(orig, name);
                    if ((skipNull == true) && (null == value))
                        continue;

                    if (dest instanceof DynaBean)
                        ((DynaBean) dest).set(name, value);
                    else
                        utilsBean.setSimpleProperty(dest, name, value);
                } catch (NoSuchMethodException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    logger.debug("Error writing to '" + name + "' on class '"
                            + dest.getClass() + "'", e);
                    throw new UtilsException(e.getMessage(), e);
                }
            }
        }
    }

    public static void setSysProperties(BeanBase beanBase,
                                        PlatformConstant.OpType type) {
        Assert.notNull(beanBase);
        HttpSession session = ThreadSession.getHttpSession();
        if(session==null){
            return;
        }
        Map<String, Object> data = ThreadSession.getHttpData();
        String userId = UserSession.getSessionUserId(session);
        String userIp = "";
        String userName = UserSession.getSessionUserName(session);
        if (userName == null || userName.equals(""))
            userName = "";
        if (data.get("remoteIp") == null)
            userIp = "127.0.0.1";
        else
            userIp = data.get("remoteIp").toString();

        switch (type.ordinal()) {
            case 1:
                beanBase.setCreatedBy(userName);
                beanBase.setCreationDate(new Date());
                beanBase.setLastUpdateDate(new Date());
                beanBase.setLastUpdatedBy(userName);
                beanBase.setLastUpdateIp(userIp);
                beanBase.setVersion(0);
                break;
            case 2:
                beanBase.setDeleteBy(userName);
                //beanBase.setDelateDate(new Date());
                break;
            case 3:
                beanBase.setLastUpdateDate(new Date());
                beanBase.setLastUpdatedBy(userName);
                beanBase.setLastUpdateIp(userIp);
        }
    }

    public static void setSysProperties(BeanDTO beanDto,
                                        PlatformConstant.OpType type) {
        Assert.notNull(beanDto);
        HttpSession session = ThreadSession.getHttpSession();
        Map<String, Object> data = ThreadSession.getHttpData();
        String userId = UserSession.getSessionUserId(session);
        String userIp = "";
        String userName = UserSession.getSessionUserName(session);

        if (userName == null || userName.equals("")) {
            userName = "";
        } else {
        }
        if (data.get("remoteIp") == null)
            userIp = "127.0.0.1";
        else
            userIp = data.get("remoteIp").toString();
        switch (type.ordinal()) {
            case 1:
                beanDto.setCreatedBy(userName);
                beanDto.setCreationDate(new Date());
                beanDto.setLastUpdateDate(new Date());
                beanDto.setLastUpdatedBy(userName);
                beanDto.setLastUpdateIp(userIp);
                beanDto.setVersion(Long.valueOf(0L));
                break;
            case 3:
                beanDto.setLastUpdateDate(new Date());
                beanDto.setLastUpdatedBy(userName);
                beanDto.setLastUpdateIp(userIp);
        }
    }

    public static <T> void setSysProperties(T t, PlatformConstant.OpType type)
            throws DaoException {
        Assert.notNull(t);
        if (t instanceof BeanBase) {
            BeanBase bean = (BeanBase) t;
            setSysProperties(bean, type);
        } else if (t instanceof BeanDTO) {
            BeanDTO bean = (BeanDTO) t;
            setSysProperties(bean, type);
        }
    }

    public static String toUpperCamelCase(String s) {
        if ("".equals(s))
            return s;

        StringBuffer result = new StringBuffer();

        boolean capitalize = true;
        boolean lastCapital = false;
        boolean lastDecapitalized = false;
        String p = null;
        for (int i = 0; i < s.length(); ++i) {
            String c = s.substring(i, i + 1);
            if (("_".equals(c)) || (" ".equals(c)) || ("-".equals(c))) {
                capitalize = true;
            } else {
                if (c.toUpperCase().equals(c)) {
                    if ((lastDecapitalized) && (!(lastCapital)))
                        capitalize = true;

                    lastCapital = true;
                } else {
                    lastCapital = false;
                }

                if (capitalize) {
                    if ((p == null) || (!(p.equals("_")))) {
                        result.append(c.toUpperCase());
                        capitalize = false;
                        p = c;
                    } else {
                        result.append(c.toLowerCase());
                        capitalize = false;
                        p = c;
                    }
                } else {
                    result.append(c.toLowerCase());
                    lastDecapitalized = true;
                    p = c;
                }
            }
        }
        String r = result.toString();
        return r;
    }

    public static Object dataFormat(String type, Object value) {
        try {
            if (type.equals("string")) {
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                return value.toString();
            }
            if (type.equals("int")) {
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                if (value.toString().indexOf(".") > -1) {
                    float tempFloat = Float.parseFloat(value.toString());
                    return (int) tempFloat;
                }
                return Integer.parseInt(value.toString());
            }
            if (type.equals("float")) {
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                return Float.parseFloat(value.toString());
            }
            if (type.equals("date")) {
                if (StringUtils.isEmpty(value)) {
                    return null;
                }
                try {
                    Date temp = DateUtil.parseDate(value.toString());
                    return temp;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            return null;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
