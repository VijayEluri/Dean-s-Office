package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 10:55 AM
 */
public class ParentModel extends PersonModel {
    public ParentModel() {
    }

    public String getWorkInfo() {
        return get("workInfo");
    }

    public void setWorkInfo(String workInfo) {
        set("workInfo", workInfo);
    }

    public String getPhoneNumbers() {
        return get("phoneNumbers");
    }

    public void setPhoneNumbers(String phoneNumbers) {
        set("phoneNumbers", phoneNumbers);
    }
}
